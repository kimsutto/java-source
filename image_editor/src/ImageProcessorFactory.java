// ImageProcessorFactory (return an instance of ImageProcessor)
public class ImageProcessorFactory {
	// get an instance of ImageProcessor
	public static ImageProcessor getInstance(ImageMode mode, Photo photo) {
		switch (mode) {
		case CONVERT:
			return new ImageConvert(photo);
		case RESIZE:
			return new ImageResize(photo);
		case ROTATE: 
			return new ImageRotate(photo);
		case GRAYSCALE: 
			return new ImageGrayscale(photo);
		case NEGATIVE: 
			return new ImageNegative(photo);
		case BLUR:
			return new ImageBlur(photo);
		case BRIGHTNESS_ADJUST:
			return new ImageBrightnessAdjust(photo);
		case EDGE_DETECT:
			return new ImageEdgeDetect(photo);
		default:
			return null;
		}
	}
}
