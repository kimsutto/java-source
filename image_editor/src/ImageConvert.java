import java.awt.image.BufferedImage;

// ImageConvert Class (process image format conversion)
public class ImageConvert extends ImageProcessor {
	private ConvertMode convertMode = ConvertMode.TO_PNG; // convert mode

	// constructor
	public ImageConvert(String inputFile) {
		this(inputFile, ConvertMode.TO_PNG);
	}

	// constructor
	public ImageConvert(Photo photo) {
		this(photo, ConvertMode.TO_PNG);
	}

	// constructor
	public ImageConvert(BufferedImage image) {
		this(image, ConvertMode.TO_PNG);
	}

	// constructor
    public ImageConvert(String inputFile, ConvertMode convertMode) {
    	super(inputFile);
    	this.mode = ImageMode.CONVERT;
        this.convertMode = convertMode;
		System.out.println("ConvertPhoto");
    }

	// constructor
    public ImageConvert(Photo photo, ConvertMode convertMode) {
    	super(photo);
    	this.mode = ImageMode.CONVERT;
        this.convertMode = convertMode;
		System.out.println("ConvertPhoto");
    }

	// constructor
    public ImageConvert(BufferedImage image, ConvertMode convertMode) {
    	super(image);
    	this.mode = ImageMode.CONVERT;
        this.convertMode = convertMode;
		System.out.println("ConvertPhoto");
    }

    // get convertMode
    public ConvertMode getConvertMode() {
    	return convertMode;
    }

    // set convertMode
    public void setConvertMode(ConvertMode convertMode) {
    	this.convertMode = convertMode;
    }

    // ImageProcessor.getFormat() method override
    @Override
    public String getFormat() {
    	return convertMode.getName(); // use the new format
    }

    // ImageProcessor.getProcessedImg() method override
    @Override
	public BufferedImage getProcessedImg() {
		return getImg(); // use the original image
	}

    // ImageProcessor.getAdditionalUserInput() method override
	/*@Override
	public void getAdditionalUserInput() {
		this.convertMode = UserInput.getConvertMode("Please enter ConvertMode [1.TO_JPG 2.TO_PNG 3.TO_GIF]: ");		
	}*/
}
