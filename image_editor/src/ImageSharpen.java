import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class ImageSharpen extends ImageProcessor {
	// constructor
	public ImageSharpen(String inputFile) {
		super(inputFile);
		this.mode = ImageMode.SHARPEN;	
		System.out.println("SharpenPhoto");
	}
	
	// constructor
	public ImageSharpen(Photo photo) {
		super(photo);
		this.mode = ImageMode.SHARPEN;	
		System.out.println("SharpenPhoto");
	}

	// constructor
	public ImageSharpen(BufferedImage image) {
		super(image);
		this.mode = ImageMode.SHARPEN;	
		System.out.println("SharpenPhoto");
	}

	// sharpen
	public static final float[] sharpen3x3Kernel = { -1.0f, -1.0f, -1.0f, -1.0f, 9.0f, -1.0f, -1.0f, -1.0f, -1.0f };
	public static BufferedImage sharpen(BufferedImage image)	{
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		ConvolveOp op = new ConvolveOp(new Kernel(3, 3, sharpen3x3Kernel));
		newImage = op.filter(image, null);
		return newImage;
	}

	// ImageProcessor.getProcessedImage() method override
	@Override
	public BufferedImage getProcessedImg() {
		return sharpen(getImg());
	}

	// ImageProcessor.getAdditionalUserInput() method override
	/*@Override
	public void getAdditionalUserInput() {
	}*/
}
