import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

// ImageBrightnessAdjust Class (process image brightness adjustment)
public class ImageBrightnessAdjust extends ImageProcessor {
	private float factor = 1.3f; // brightness factor

	// constructor
	public ImageBrightnessAdjust(String inputFile) {
		this(inputFile, 1.3f);
	}

	// constructor
	public ImageBrightnessAdjust(Photo photo) {
		this(photo, 1.3f);
	}

	// constructor
	public ImageBrightnessAdjust(BufferedImage image) {
		this(image, 1.3f);
	}

	// constructor
    public ImageBrightnessAdjust(String inputFile, float factor) {
    	super(inputFile);
    	this.mode =  ImageMode.BRIGHTNESS_ADJUST;
        this.factor = factor;
		System.out.println("BrightnessAdjustPhoto");
    }

	// constructor
    public ImageBrightnessAdjust(Photo photo, float factor) {
    	super(photo);
    	this.mode =  ImageMode.BRIGHTNESS_ADJUST;
        this.factor = factor;
		System.out.println("BrightnessAdjustPhoto");
    }
    
	// constructor
    public ImageBrightnessAdjust(BufferedImage image, float factor) {
    	super(image);
    	this.mode =  ImageMode.BRIGHTNESS_ADJUST;
        this.factor = factor;
		System.out.println("BrightnessAdjustPhoto");
    }
    
    // get factor
    public float getFactor() {
    	return factor;
    }

    // set factor
    public void setFactor(float factor) {
    	this.factor = factor;
    }

	// brighten image (brighten the image by 30% => scaleFactor = 1.3f)
	// darken image (darken the image by 90% => scaleFactor = 0.9f) 90% darken
	public static BufferedImage brightnessAdjust(BufferedImage image, float factor) {
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		RescaleOp op = new RescaleOp(factor, 0, null);
		newImage = op.filter(image, null);
		return newImage;
	}

	// ImageProcessor.getProcessedImg() method override
	@Override
	public BufferedImage getProcessedImg() {
		return brightnessAdjust(getImg(), this.factor);
	}	

	// ImageProcessor.getAdditionalUserInput() method override
	/*@Override
	public void getAdditionalUserInput() {
		this.factor = UserInput.getFloat("Please enter BrightnessAdjust factor :");
	}*/
}
