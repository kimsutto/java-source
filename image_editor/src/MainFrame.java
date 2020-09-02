import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.io.*;

//Kim sujin 32170712 
// 자바프로그래밍1_1 // ProcessorPanel Gui
public class MainFrame extends JFrame implements ActionListener {

	Photo[] photoArray = null; // photoArray

	JPanel panel = new JPanel(new BorderLayout());
	JPanel buttonPanel = new JPanel(new GridLayout(6, 2));
    ImageProcessorPanel displayPanel = new ImageProcessorPanel(photoArray); // image process panel
    JButton[] buttons = new JButton[12]; // buttons for image process
    
	public MainFrame() {
		super("PhotoPanelMainFrame");

		setMenu(); // load menu
		this.add(panel);
		displayPanel.loadPhotos(load()); // load photos in displayPanel
		panel.add(displayPanel, BorderLayout.CENTER);
	    buttons[0] = new JButton("<< Darkness"); // darken button
	    buttons[1] = new JButton("Brightness >>"); // brighten
	    buttons[2] = new JButton("<< Blur Dec"); // blur decrease
	    buttons[3] = new JButton("Blur Inc >>"); // blur increase
	    buttons[4] = new JButton("<< Rotate CW"); // rotate CW
	    buttons[5] = new JButton("Rotate CCW >>"); // rotate CCW
	    buttons[6] = new JButton("Edge Detect"); // edge detect
	    buttons[7] = new JButton("Grayscale"); // grayscale
	    buttons[8] = new JButton("Negative"); // negative
	    buttons[9] = new JButton("Reset"); // reset
	    buttons[10] = new JButton("<< Previous"); // previous photo in array
	    buttons[11] = new JButton("Next >>"); // next photo in array		
	    for (int i=0; i<12; i++) {
			buttonPanel.add(buttons[i]);	    	
			buttons[i].addActionListener(this); // event
	    }
		panel.add(buttonPanel, BorderLayout.SOUTH);

		this.setSize(512, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// load photos
	private Photo[] load() {
		Photo[] photos = new Photo[7]; // the photo
		for (int i = 0; i < 7; i++) {
			String fullPath = "C:/JAVA/tacgun" + (i+1) + ".jpg";
			Photo photo = new Photo(fullPath);
			if (photo.getImg() != null) {
				photos[i] = photo;
				System.out.println(fullPath + " loading");
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return photos;
	}

	
	// button event listener
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(buttons[0])) {
			displayPanel.darken();
		} else if (button.equals(buttons[1])) {
			displayPanel.brighten();
		} else if (button.equals(buttons[2])) {
			displayPanel.blurDec();// 1보다 아래로 못내려감
		} else if (button.equals(buttons[3])) {
			displayPanel.blurInc();
		} else if (button.equals(buttons[4])) {
			displayPanel.rotateInc();
		} else if (button.equals(buttons[5])) {
			displayPanel.rotateDec();
		} else if (button.equals(buttons[6])) {
			displayPanel.edgeDetect();
		} else if (button.equals(buttons[7])) {
			displayPanel.grayscale();
		} else if (button.equals(buttons[8])) {
			displayPanel.negative();
		} else if (button.equals(buttons[9])) {
			displayPanel.reset();
		} else if (button.equals(buttons[10])) {
			displayPanel.previous();
		} else if (button.equals(buttons[11])) {
			displayPanel.next();
		}
	}

	// menu
	public void setMenu() {
		// menu
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		// File Menu, F - Mnemonic
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);
		// File->Open MenuItem, O - Mnemonic
		JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
		fileMenu.add(openMenuItem);
		openMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
		        System.out.println("OPEN");
		        File[] selectedFile = openFileDialog();
		       List photoList = new ArrayList(); // photoList
		        for (int i = 0; i < selectedFile.length; i++) {
		            try {
		                String fullPath = selectedFile[i].getCanonicalPath(); //String fullPath = file.getCanonicalPath().replace('\\', '/');
		                System.out.println("OPEN fullPath=" + fullPath);
		                Photo photo = new Photo(fullPath);
		                if (photo.getImg() != null) { // if image is loaded
		                    photoList.add(photo);
		                }
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }	
			photoArray = new Photo[photoList.size()]; // create photoArray with photoList.size()
			photoArray = (Photo[]) photoList.toArray(photoArray); // photoList => photoArray
			for (Photo p : photoArray) p.print(); // debug to print photoArray
			
			displayPanel.loadPhotos(photoArray); // reload photoArray in displayPanel
			displayPanel.repaint(); // repaint
			}
		});

		// File->Save MenuItem, S - Mnemonic
		JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
		fileMenu.add(saveMenuItem);
		saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
		        System.out.println("SAVE");
				String selectedFilename = saveFileDialog();
				if (selectedFilename != null) {
					displayPanel.savePhoto(selectedFilename);
				}
			}
		});
		
		// File->Exit MenuItem, E - Mnemonic
		JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		fileMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
		        System.out.println("EXIT");
		        System.exit(0);
			}
		});

		// Photo Menu, P - Mnemonic
		JMenu photoMenu = new JMenu("Photo");
		photoMenu.setMnemonic(KeyEvent.VK_P);
		menuBar.add(photoMenu);

		// Photo->Convert MenuItem, C - Mnemonic
		JMenuItem convertMenuItem = new JMenuItem("Convert Format", KeyEvent.VK_C);
		photoMenu.add(convertMenuItem);
		this.setJMenuBar(menuBar);
		convertMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
				String[] mode = ConvertMode.names();
				String m = (String)JOptionPane.showInputDialog(null, "변경할 모드를 고르세요" , "ConvertMode", JOptionPane.QUESTION_MESSAGE, null, mode, mode[1]);
				ConvertMode C = ConvertMode.modeOf(m);
				displayPanel.convert(C);
			}
		});
		// Photo->Resize MenuItem, R - Mnemonic
		JMenuItem resizeMenuItem = new JMenuItem("Resize", KeyEvent.VK_R);
		photoMenu.add(resizeMenuItem);
		this.setJMenuBar(menuBar);
		resizeMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
			String R = JOptionPane.showInputDialog("변경할 너비를 쓰시오");
				String H = JOptionPane.showInputDialog("변경 높이를 쓰시오");
		        System.out.println("RESIZE");
		        int r = Integer.parseInt(R);
		        int h = Integer.parseInt(H);
		        displayPanel.rescale(r,h);
			}	
		});
		//UserCode : Photo->Sharpen MenuItem, H - Memonic
		JMenuItem sharpenMenuItem = new JMenuItem("Sharpen", KeyEvent.VK_H);
		photoMenu.add(sharpenMenuItem);
		this.setJMenuBar(menuBar);
		sharpenMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent event) {
			displayPanel.sharpen();
			}
		});
	}
	public File[] openFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
        int returnValue = fileChooser.showOpenDialog(null); // OpenDialog
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	File[] SelectedFiles = fileChooser.getSelectedFiles();
        	return SelectedFiles;
        }
        else {
            System.out.println("file not opened");
        	return null;
        }
	}
	public String saveFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(null); // SaveDialog
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          String selectedFilename = fileChooser.getSelectedFile().getAbsolutePath().toString();
          return selectedFilename;
        }
        else {
            System.out.println("file not selected");
        	return null;
        }
	}
	public static void main(String args[]) {
		new MainFrame(); // frame
	}
}