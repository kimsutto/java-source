// 32170712 김수진
// 2018.06.07 
// 자바프로그래밍1_1
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;

public class MainFrame extends JFrame implements ActionListener {

	JPanel panel = new JPanel(new BorderLayout());

	JPanel photoPanel = new JPanel();
	JScrollPane scrollPane = new JScrollPane(photoPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
    JButton[] buttons = new JButton[6]; // buttons for photoManager

    PhotoManager photoManager = new PhotoManager(); // photoManager can add/find/remove/sort/etc
    
	public MainFrame() {
		super("PhotoPanelMainFrame");
		this.add(panel);

		photoPanel.setPreferredSize(new Dimension(620, 2000));
//
		
		panel.add(scrollPane);
		panel.add(photoPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
	    buttons[0] = new JButton("Add image"); // add
	    buttons[1] = new JButton("Find image"); // find
	    buttons[2] = new JButton("Remove image"); // remove
	    buttons[3] = new JButton("Remove all images"); // remove all
	    buttons[4] = new JButton("Sort image"); // sort
	    buttons[5] = new JButton("Close"); // close
	    for (int i=0; i<6; i++) {
			buttonPanel.add(buttons[i]);	    	
			buttons[i].addActionListener(this); // event
	    }
		this.setSize(660, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	// button event listener
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		if (button.equals(buttons[0])) {
			load();
		} else if (button.equals(buttons[1])) {
			find();
		} else if (button.equals(buttons[2])) {
			remove();
		} else if (button.equals(buttons[3])) {
			removeAll();
		} else if (button.equals(buttons[4])) {
			sort();
		} else if (button.equals(buttons[5])) {
			close();
		} 
	}
	
	// open file dialog to return selectedFiles
	private File[] openFileDialog() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
        	System.out.println("openFile");
        	File[] SelectedFiles = fileChooser.getSelectedFiles();
        	return SelectedFiles;
        }
        else {
            System.out.println("file not opened");
        	return null;
        }
	}
	
	// load imagefile and add it to photoManager & photoPanel
	public void load(String fullPath) {
		Photo photo = new Photo(fullPath);
		if (photo.getImg() != null) { 
			photoManager.add(photo); // add photo into the photoList
			photoPanel.add(new ImageLabel(photo)); // add imagelabel in photoPanel
			photoPanel.revalidate(); // revalidate
		}
	}

	// load selected imagefile and add it to photoManager & photoPanel
	public void load() {
		System.out.println("Add image");
		File[] selectedFiles = openFileDialog(); // get selectedFile
		for (int i = 0; i < selectedFiles.length; i++) {
            try {
                String fullPath = selectedFiles[i].getCanonicalPath(); 
                load(fullPath);	
            }
            catch (IOException e) {
                e.printStackTrace();
            }
		}

	}
	

	// find photo by fullPath and highlight it
		public void find() {
			System.out.println("Find image");
			String[] options = photoManager.getPhotoFullPaths(); // get photoList's fullPaths (as String[])
			if (options == null) return; // if photoManager has no photo, then return here
			String fullPath = (String)JOptionPane.showInputDialog(null, "Find the image:", "Find", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (fullPath != null) {
				Photo found = photoManager.find(fullPath); // find photo by fullPath 
				if (found == null) return; // if photo is not found, then return here
				found.print();
				// find ImageLabel component that has the same fullPath. If found, then highlight its border to red
				Component[] componentArray = photoPanel.getComponents(); 
				for (Component c: componentArray) {
					if (c instanceof ImageLabel) 
						((JLabel)c).setBorder(javax.swing.BorderFactory.createEmptyBorder());
				} // find 선택할때마다 이전에 선택한것을 깨끗하게 지워줌
				for(Component c : componentArray) {
					if (c instanceof ImageLabel) {
						if (((ImageLabel)c).getFullPath() == fullPath) { // find ImageLabel component that has the same fullPath
							Border border = BorderFactory.createLineBorder(Color.RED, 3); // highlight its border to red,  빨간색 두께3
							((JLabel)c).setBorder(border);
						}
					}
				}
			}
		}



	// remove photo by fullPath and remove it from photoManager & photoPanel
	public void remove() {	
		System.out.println("Remove image");
		String[] options = photoManager.getPhotoFullPaths(); // get photoList's fullPaths (as String[])
		if (options == null) return; // if photoManager has no photo, then return here
		String a = (String)JOptionPane.showInputDialog(null, "Remove the image:", "Remove", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		photoManager.remove(a);
		}	
	// remove all photos from photoManager & photoPanel
	public void removeAll() {
		System.out.println("Remove all");
		int input = JOptionPane.showConfirmDialog(null, "Do you like to remove all images in the list?"); // 0:yes 1:no 2:cancel
		if(input == 0) {
		photoManager.removeAll();
		
		}
	}
	// sort photos of photoManager & photoPanel
	public void sort() {
		System.out.println("Sort image");
		String[] options = Photo.columnNames();	// Photo's columnNames "FullPath", "Format", "Width", "Height"
		String columnName = (String)JOptionPane.showInputDialog(null, "Sort the Image:", "Sort", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		photoManager.sort(columnName);
	}

	// close program
	public void close() {
		System.out.println("Close");
		this.setVisible(false);
		this.dispose();
	}

	public static void main(String args[]) {
		new MainFrame(); // frame
	}

}