import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// ImageRotate Class (process image rotation)
public class ImageRotate extends ImageProcessor {
	private float angle = 45.0f;
	
	// constructor
	public ImageRotate(String inputFile) {
		this(inputFile, 45.0f);
	}

	// constructor
	public ImageRotate(Photo photo) {
		this(photo, 45.0f);
	}

	// constructor
    public ImageRotate(BufferedImage image) {
    	this(image, 45.0f);
    }
    
	// constructor
    public ImageRotate(String inputFile, float angle) {
    	super(inputFile);
    	this.mode = ImageMode.ROTATE;
        this.angle = angle;
		System.out.println("RotatePhoto");
    }

	// constructor
    public ImageRotate(Photo photo, float angle) {
    	super(photo);
    	this.mode = ImageMode.ROTATE;
        this.angle = angle;
		System.out.println("RotatePhoto");
    }
    
	// constructor
    public ImageRotate(BufferedImage image, float angle) {
    	super(image);
    	this.mode = ImageMode.ROTATE;
        this.angle = angle;
		System.out.println("RotatePhoto");
    }
    
    // get angle
    public float getAngle() {
    	return angle;
    }
    
    // set angle
    public void setAngle(float angle) {
    	this.angle = angle;
    }

    // rotate an image by angle
	public static BufferedImage rotate(BufferedImage image, float rotateAngle) {
		if (image == null) return null;
		// creates output image
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		// rotate the input image by angle to the output image
		Graphics2D g2d = newImage.createGraphics();
		g2d.rotate(Math.toRadians(rotateAngle), image.getWidth()/2, image.getHeight()/2);  
		g2d.drawImage(image, null, 0, 0);
		return newImage;  
	}

	// ImageProcessor.getProcessedImage() method override
	@Override
	public BufferedImage getProcessedImg() {
		return rotate(getImg(), this.angle);
	}

	// ImageProcessor.getAdditionalUserInput() method override
	/*@Override
	public void getAdditionalUserInput() {
		this.angle = UserInput.getFloat("Please enter Rotate angle :");
	}*/
}
