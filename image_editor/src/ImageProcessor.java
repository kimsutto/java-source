import java.awt.Image;
import java.awt.image.BufferedImage;

//ImageProcessor Abstract Class (process Photo image by ImageMode)
public abstract class ImageProcessor {
	protected String format = null;
	protected BufferedImage image = null;
	protected ImageMode mode = null;

	// constructor
	protected ImageProcessor(String inputFile) {
		Photo photo = new Photo(inputFile);
		this.format = photo.getFormat();
		this.image = photo.getImg();
		this.mode = null;
	}

	// constructor
	protected ImageProcessor(Photo photo) {
		this.format = photo.getFormat();
	    this.image = photo.getImg();
		this.mode = null;
	}
	
	// constructor
	protected ImageProcessor(BufferedImage image) {
	    this.image = image;
		this.mode = null;
	}
	
	// get format
	public String getFormat() {
    	return format;
	}

	// set image
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	// set image mode
	public void setImageMode(ImageMode mode) {
		this.mode = mode;
	}

    // get image
	public BufferedImage getImg() {
		return this.image;
	}

    // get image mode
	public ImageMode getImageMode() {
		return this.mode;
	}

    // get processedImage abstract method (convert, resize, rotate, grayscale, blur, adjust, etc)
    public abstract BufferedImage getProcessedImg();

    // get additional user input abstract method
    //public abstract void getAdditionalUserInput();
}
