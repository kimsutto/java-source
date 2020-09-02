
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

// ImageEdgeDetect Class (process image edge detection)
public class ImageEdgeDetect extends ImageProcessor {
	
	// constructor
	public ImageEdgeDetect(String inputFile) {
		super(inputFile);
		this.mode = ImageMode.EDGE_DETECT;	
		System.out.println("EdgeDetectPhoto");
	}
	
	// constructor
	public ImageEdgeDetect(Photo photo) {
		super(photo);
		this.mode = ImageMode.EDGE_DETECT;		
		System.out.println("EdgeDetectPhoto");
	}

	// constructor
	public ImageEdgeDetect(BufferedImage image) {
		super(image);
		this.mode = ImageMode.EDGE_DETECT;		
		System.out.println("EdgeDetectPhoto");
	}

	// edge detection kernel
	public static final float[] edge3x3Kernel = { 0.0f, -0.75f, 0.0f, -0.75f, 3.0f, -0.75f, 0.0f, -0.75f, 0.0f };

	// edge detect
	public static BufferedImage edgeDetect(BufferedImage image)	{
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		ConvolveOp op = new ConvolveOp(new Kernel(3, 3, edge3x3Kernel));
		newImage = op.filter(image, null);
		return newImage;
	}

	// ImageProcessor.getProcessedImage() method override
	@Override
	public BufferedImage getProcessedImg() {
		return edgeDetect(getImg());
	}
    
	/*// ImageProcessor.getAdditionalUserInput() method override
	@Override
	public void getAdditionalUserInput() {	
	}*/
}
