import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

import javax.imageio.ImageIO;

// Photo Class (Comparable vs Comparator interface) (equals & hashCode override)
public class Photo implements Comparable<Photo> {
    private String fullPath = null;
    private BufferedImage img = null;

    // constructor
    public Photo(String fullPath) {
		load(fullPath);
		//print();
    }
    
    // copy constructor
    public Photo(Photo other) {
        this.fullPath = other.fullPath;
        this.img = other.img;
		//print();
    }
    
    // load an image    
    public void load(String fullPath) {
		this.fullPath = fullPath;
    	try {
    		this.img = ImageIO.read(new File(fullPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	if (this.img == null) {
			System.out.println(fullPath  + " cannot be loaded. It's not an image!");
    	}
    	else {
    		this.img = toCompatibleImage(this.img);
    		System.out.println(fullPath  + " is loaded.");
    	}
    }

    // convert an image read in with the image io api to a BufferedImage that has a Image Data Model compatible with the default screen device 
    public static BufferedImage toCompatibleImage(BufferedImage image) {
        BufferedImage compatibleImage = null;
    	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    	GraphicsDevice gd = ge.getDefaultScreenDevice();
    	GraphicsConfiguration gc = gd.getDefaultConfiguration();
    	compatibleImage = gc.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
    	Graphics2D g2d = compatibleImage.createGraphics();
    	g2d.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    	g2d.dispose();
        return compatibleImage;
    }

    // get fullPath
    public String getFullPath() {
    	return this.fullPath;
    }
    
    // get BufferedImage
    public BufferedImage getImg() {
    	return this.img;
    }

    // get image width
    public int getWidth() {
    	if (this.img != null)
    		return this.img.getWidth();
    	return 0;
    }
    
    // get image height
    public int getHeight() {
    	if (this.img != null)
    		return this.img.getHeight();
    	return 0;
    }
     
    // get image format(extension)
    public String getFormat() {
    	return getExtension(fullPath);
    }

    // get fullPathWithoutExt
    public String getFullpathWithoutExt() {
    	return getFullpathWithoutExt(fullPath);
    }

    // get path from fullPath (e.g. C:/JAVA/IMG.jpg => C:/JAVA/) 
    public static String getPath(String fullPath) {
    	return fullPath.lastIndexOf('/') == -1 ? null : fullPath.substring(0, fullPath.lastIndexOf('/'));
    }
    
    // get filenameWithoutExt from fullPath (e.g. C:/JAVA/IMG.jpg => IMG) 
    public static String getFilenameWithoutExt(String fullPath) {
    	return fullPath.substring(fullPath.lastIndexOf('/') + 1, fullPath.lastIndexOf('.'));
    }    
    
    // get fullPathWithoutExt from fullPath (e.g. C:/JAVA/IMG.jpg => C:/JAVA/IMG) 
    public static String getFullpathWithoutExt(String fullPath) {
    	return fullPath.substring(0, fullPath.lastIndexOf('.'));
    }
    
    // get extension from fullPath (e.g. C:/JAVA/IMG.jpg => jpg) 
    public static String getExtension(String fullPath) {
    	return fullPath.substring(fullPath.lastIndexOf('.') + 1);
    }

    // print
    public void print() {
        System.out.println(this);
    }
    
    // Object.toString method override
    @Override
    public String toString() {
        return String.format("%1$s,%2$s,%3$d,%4$d", fullPath, getFormat(), getWidth(), getHeight()); // CSV
    }  
	
    // equals (equality) JDK7 or higher
    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other instanceof Photo) {
        	Photo that = (Photo) other;
        	return Objects.equals(this.fullPath, that.fullPath) && Objects.equals(this.img, that.img);
        }
        return false;
    }
    
    // hashCode (identity) for HashMap, HashSet, HashTable JDK7 or higher
    @Override
    public int hashCode() {
        return Objects.hash(this.fullPath, this.img);
    }
    
	@Override
	public int compareTo(Photo other) {
		return this.fullPath.compareTo(other.fullPath);
	}
	
	// Comparator<Photo> implementation 익명구현객체
	public static Comparator<Photo> FormatComparator = new Comparator<Photo>() {
		public int compare(Photo p1, Photo p2) {
			return p1.getFormat().compareTo(p2.getFormat()); //ascending order
		}
	};

	public static Comparator<Photo> WidthComparator = new Comparator<Photo>() {
		public int compare(Photo p1, Photo p2) {     
			return p1.getWidth() - p2.getWidth(); //ascending order
		}
	};

	public static Comparator<Photo> HeightComparator = new Comparator<Photo>() {
		public int compare(Photo p1, Photo p2) {     
			return p1.getHeight() - p2.getHeight(); //ascending order
		}
	};
	   
	public static String[] columnNames() {
		return new String[] {"FullPath", "Format", "Width", "Height"};
	}

}
