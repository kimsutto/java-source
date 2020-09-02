import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel {
	private String fullPath;
	
	public ImageLabel(Photo photo) {
		super();
		fullPath = photo.getFullPath();
		setIcon(loadImageIcon(photo.getImg(), 200, 200));
	}

	public String getFullPath() {
		return fullPath;
	}
	
	public ImageIcon loadImageIcon(Image img, int width, int height) {
		Image scaled = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(scaled);
	}
}
