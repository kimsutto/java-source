import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// ImageResize Class (process image resizing)
public class ImageResize extends ImageProcessor {	
	private int scaledWidth = 300;
	private int scaledHeight = 300;

	// constructor
	public ImageResize(String inputFile) {
		this(inputFile, 300, 300);
	}

	// constructor
	public ImageResize(Photo photo) {
		this(photo, 300, 300);
	}

	// constructor
	public ImageResize(BufferedImage image) {
		this(image, 300, 300);
	}

	// constructor
    public ImageResize(String inputFile, int scaledWidth, int scaledHeight) {
    	super(inputFile);
    	this.mode = ImageMode.RESIZE;
        this.scaledWidth = scaledWidth;
        this.scaledHeight = scaledHeight;
		System.out.println("ResizePhoto");
    }

	// constructor
    public ImageResize(Photo photo, int scaledWidth, int scaledHeight) {
    	super(photo);
    	this.mode = ImageMode.RESIZE;
        this.scaledWidth = scaledWidth;
        this.scaledHeight = scaledHeight;
		System.out.println("ResizePhoto");
    }
    
	// constructor
    public ImageResize(BufferedImage image, int scaledWidth, int scaledHeight) {
    	super(image);
    	this.mode = ImageMode.RESIZE;
        this.scaledWidth = scaledWidth;
        this.scaledHeight = scaledHeight;
		System.out.println("ResizePhoto");
    }
    
    // get width
    public int getWidth() {
    	return scaledWidth;
    }

    // get height
    public int getHeight() {
    	return scaledHeight;
    }

    // set width
    public void setWidth(int scaledWidth) {
    	this.scaledWidth = scaledWidth;
    }

    // set height
    public void setHeight(int scaledHeight) {
    	this.scaledHeight = scaledHeight;
    }

	// resize an image to scaledWidth x scaledHeight
	public static BufferedImage resize(BufferedImage image, int scaledWidth, int scaledHeight) {
		if (image == null) return null;
        // creates output image
        BufferedImage newImage = new BufferedImage(scaledWidth, scaledHeight, image.getType());
        // scales the input image to the output image
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(image, 0, 0, scaledWidth, scaledHeight, null);
        return newImage;
	}

	// ImageProcessor.getProcessedImage() method override
	@Override
	public BufferedImage getProcessedImg() {
		return resize(getImg(), this.scaledWidth, this.scaledHeight);
	}

	// ImageProcessor.getAdditionalUserInput() method override
	/*@Override
	public void getAdditionalUserInput() {
		this.scaledWidth = UserInput.getInteger("Please enter scaledWidth: ");		
		this.scaledHeight = UserInput.getInteger("Please enter scaledHeight: ");	
	}*/
}
