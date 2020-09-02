import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

// ImageBlur Class (process gaussian blur)
public class ImageBlur extends ImageProcessor {
	private int radius = 1;
	
	// constructor
	public ImageBlur(String inputFile) {
		this(inputFile, 1);
	}
	
	// constructor
	public ImageBlur(Photo photo) {
		this(photo, 1);
	}

	// constructor
	public ImageBlur(BufferedImage image) {
		this(image, 1);
	}

	// constructor
	public ImageBlur(String inputFile, int radius) {
		super(inputFile);
		this.mode = ImageMode.BLUR;	
		this.radius = radius;
		System.out.println("BlurPhoto");
	}
	
	// constructor
	public ImageBlur(Photo photo, int radius) {
		super(photo);
		this.mode = ImageMode.BLUR;		
		this.radius = radius;
		System.out.println("BlurPhoto");
	}

	// constructor
	public ImageBlur(BufferedImage image, int radius) {
		super(image);
		this.mode = ImageMode.BLUR;		
		this.radius = radius;
		System.out.println("BlurPhoto");
	}

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    public int getRadius() {
    	return this.radius;
    }

	// blur kernel
	public static final float[] blur3x3Kernel = { 1/16f, 1/8f, 1/16f, 1/8f, 1/4f, 1/8f, 1/16f, 1/8f, 1/16f };
	public static final float[] blur5x5Kernel = { 1/273f, 4/273f, 7/273f, 4/273f, 1/273f, 
												  4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
												  7/273f, 26/273f, 41/273f, 26/273f, 7/273f,
												  4/273f, 16/273f, 26/273f, 16/273f, 4/273f,
												  1/273f, 4/273f, 7/273f, 4/273f, 1/273f };

	// gaussian blur
	public static BufferedImage blur(BufferedImage image, int radius)	{
		if (image == null) return null;
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		//ConvolveOp op = new ConvolveOp(new Kernel(3, 3, blur3x3Kernel));
		//ConvolveOp op = new ConvolveOp(new Kernel(5, 5, blur5x5Kernel));
		ConvolveOp op = getGaussianBlurFilter(radius, true);
		newImage = op.filter(image, null);
		return newImage;
	}
	
    public static ConvolveOp getGaussianBlurFilter(int radius, boolean horizontal) {
        if (radius < 1) {
            throw new IllegalArgumentException("Radius must be >= 1");
        }
        
        int size = radius * 2 + 1;
        float[] data = new float[size];
        
        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;
        float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
        float total = 0.0f;
        
        for (int i = -radius; i <= radius; i++) {
            float distance = i * i;
            int index = i + radius;
            data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
            total += data[index];
        }
        
        for (int i = 0; i < data.length; i++) {
            data[i] /= total;
        }        
        
        Kernel kernel = null;
        if (horizontal) {
            kernel = new Kernel(size, 1, data);
        } else {
            kernel = new Kernel(1, size, data);
        }
        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }
    
	// ImageProcessor.getProcessedImage() method override
	@Override
	public BufferedImage getProcessedImg() {
		return blur(getImg(), this.radius);
	}

	// ImageProcessor.getAdditionalUserInput() method override
	/*@Override
	public void getAdditionalUserInput() {
		this.radius = UserInput.getInteger("Please enter Blurring radius :");
	} */
}
