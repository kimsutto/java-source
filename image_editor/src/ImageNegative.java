import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

// ImageNegative Class (process negative image)
public class ImageNegative extends ImageProcessor {

	public ImageNegative(String inputFile) {
	   	super(inputFile);
    	this.mode =  ImageMode.NEGATIVE;
		System.out.println("NegativePhoto");
	}

	public ImageNegative(Photo photo) {
    	super(photo);
    	this.mode =  ImageMode.NEGATIVE;
		System.out.println("NegativePhoto");
	}

	public ImageNegative(BufferedImage image) {
    	super(image);
    	this.mode =  ImageMode.NEGATIVE;
		System.out.println("NegativePhoto");
	}

    public BufferedImage getNegativeImg() {
    	return negative(getImg());
    }
    
    // Image negative. Multiply each color value by -1.0 and add 255
	public static BufferedImage negative(BufferedImage image) {
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		RescaleOp op = new RescaleOp(-1.0f, 255f, null);
		newImage = op.filter(image, null);
		System.out.println("negative");
		return newImage;
	}

    @Override
	public BufferedImage getProcessedImg() {
		return getNegativeImg();
	}	

	/*@Override
	public void getAdditionalUserInput() {
	}*/
}
