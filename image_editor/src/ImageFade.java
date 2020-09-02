import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class ImageFade extends ImageProcessor {
	private float fadeAmount = 0.0f; // fade amount

	// constructor
	public ImageFade(String inputFile) {
		this(inputFile, 0.0f);
	}
	
	// constructor
	public ImageFade(Photo photo) {
		this(photo, 0.0f);
	}

	// constructor
	public ImageFade(BufferedImage image) {
		this(image, 0.0f);
	}

	// constructor
    public ImageFade(String inputFile, float fadeAmount) {
    	super(inputFile);
        this.fadeAmount = fadeAmount;
		System.out.println("FadePhoto");
    }

	// constructor
    public ImageFade(Photo photo, float fadeAmount) {
    	super(photo);
        this.fadeAmount = fadeAmount;
		System.out.println("FadePhoto");
    }
    
	// constructor
    public ImageFade(BufferedImage image, float fadeAmount) {
    	super(image);
        this.fadeAmount = fadeAmount;
		System.out.println("FadePhoto");
    }
    
    // get fade
    public float getFadeAmount() {
    	return fadeAmount;
    }

    // set fade
    public void setFadeAmount(float fadeAmount) {
    	this.fadeAmount = fadeAmount;
    }
    
	// brighten image (brighten the image by 30% => scaleFactor = 1.3f)
	// darken image (darken the image by 90% => scaleFactor = 0.9f) 90% darken
	public static BufferedImage fade(BufferedImage image, float fadeAmount) {
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		float offset = 1.0f - fadeAmount;
	    float[] scales = { fadeAmount, fadeAmount, fadeAmount, 1.0f };
	    float[] offsets = { offset, offset, offset, 0.0f };
		RescaleOp op = new RescaleOp(scales, offsets, null);
		newImage = op.filter(image, null);
		return newImage;
	}

	@Override
	public BufferedImage getProcessedImg() {
		// TODO Auto-generated method stub
		return fade(getImg(), this.fadeAmount);
	}

	// ImageProcessor.getAdditionalUserInput() method override
	/*@Override
	public void getAdditionalUserInput() {
		this.fade = UserInput.getFloat("Please enter fade amount :");
	}*/
}
