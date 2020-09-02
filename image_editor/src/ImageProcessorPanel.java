import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageProcessorPanel extends JPanel {
	Photo[] photoArray = null; // the photos
	int totalNumberOfPhotos = 1; // total # of photos
	int currentIndex = 0; // current index #
	BufferedImage image = null; // image to display in the panel
	
	float factor = 1.0f; // brightness factor +/-0.3 (0.1 ~ 4.0)
	float angle = 0.0f; // rotation angle +/-20
	int radius = 1; // blur radius +/-3 (1 ~ 30)
	ImageProcessor processor = null; // default processor

	public ImageProcessorPanel(Photo[] photoArray) {
		loadPhotos(photoArray);
	}
	public void loadPhotos(Photo[] photoArray) {
		if (photoArray == null) return;
		this.photoArray = photoArray;
		this.totalNumberOfPhotos = this.photoArray.length;
		this.currentIndex = 0;
		if (photoArray[currentIndex].getImg() != null)	this.image = photoArray[currentIndex].getImg();
	}
	public void savePhoto(String outputFile) {
		if (photoArray != null && photoArray[currentIndex] != null) {
			savePhoto(outputFile, photoArray[currentIndex].getFormat());			
		}
		else {
			System.out.println("Error! cannot save " + outputFile);
		}
	}
	public void savePhoto(String outputFile, String format) {
		try {
			System.out.println(format);
			boolean result = ImageIO.write(image, format, new File(outputFile));
			if (result) System.out.println("saved to " + outputFile + " successfully.");						
		} catch (IOException e) {
			System.out.println("Error! cannot save.");						
			e.printStackTrace();
		}
	}
	@Override
	public void paintComponent(Graphics g) { // paint
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (image != null) {
			g2.drawImage(image, 0, 0, this);
		}
	}
	// ImageConvert
	public void convert(ConvertMode mode) {
		if (mode == null || image == null) return;
		System.out.println("convert image to : " + mode);
		if (!(processor instanceof ImageConvert)) {
			processor = new ImageConvert(image);			
		}
		((ImageConvert)processor).setConvertMode(mode);
		image = processor.getProcessedImg();
		String filename = photoArray[currentIndex].getFullpathWithoutExt() + "." + ConvertMode.name(mode);
		savePhoto(filename, ConvertMode.name(mode));
	}
	// ImageResize
	public void rescale(int scaledWidth, int scaledHeight) {
		if (image == null) return;
		if(!(processor instanceof ImageResize)) {
			processor = new ImageResize(image);
		}
		((ImageResize)processor).setHeight(20);
		((ImageResize)processor).setWidth(20);
		image = processor.getProcessedImg();
		repaint();
	}
	// ImageBrightnessAdjust (factor -0.3)
	public void darken() {
		System.out.println("DARKEN");
		if (image == null) return;
		factor -= 0.3;
		if(factor <=0.1) factor = 0.1f;
		if(!(processor instanceof ImageBrightnessAdjust)) {
			processor = new ImageBrightnessAdjust(image);
		}
		((ImageBrightnessAdjust)processor).setFactor(factor);
		image = processor.getProcessedImg();
		repaint();
	}
	// ImageBrightnessAdjust (factor +0.3)
	public void brighten() {
		System.out.println("BRIGHTEN");
		if (image == null) return;
		factor += 0.3;
		if(factor >=4.0) factor =4.0f;
		if(!(processor instanceof ImageBrightnessAdjust)) {
			processor = new ImageBrightnessAdjust(image);
		}
		((ImageBrightnessAdjust)processor).setFactor(factor);
		image = processor.getProcessedImg();
		repaint();
		}
	// ImageBrightnessAdjust (radius -3)
	public void blurDec() {
		System.out.println("BLUR DEC");
		if (image == null) return;
		radius -=3;
		if(radius <=1) radius =1;
		if (!(processor instanceof ImageBlur)) {
			processor = new ImageBlur(image);			
		}
		((ImageBlur)processor).setRadius(radius);
		image = processor.getProcessedImg();
		repaint(); // paint component 호출 
	}
	// ImageBrightnessAdjust (radius +3)
	public void blurInc() {
			System.out.println("BLUR INC");
			if (image == null) return;
			radius +=3;
			if(radius >=30) radius =30;
			if (!(processor instanceof ImageBlur)) {
				processor = new ImageBlur(image);			
			}
			((ImageBlur)processor).setRadius(radius);
			image = processor.getProcessedImg();
			repaint(); // paint component 호출 
	}
	// ImageRotate (angle -20.0)
	public void rotateDec() {
		System.out.println("ROTATE DEC");
		if (image == null) return;
		angle -=20.0;
		if(!(processor instanceof ImageRotate)) {
			processor = new ImageRotate(image);
		}
		((ImageRotate)processor).setAngle(angle);
		image = processor.getProcessedImg();
		repaint();
	}
	// ImageRotate (angle +20.0)
	public void rotateInc() {
		System.out.println("ROTATE INC");
		if (image == null) return;
		angle +=20.0;
		if(!(processor instanceof ImageRotate)) {
			processor = new ImageRotate(image);
		}
		((ImageRotate)processor).setAngle(angle);
		image = processor.getProcessedImg();
		repaint();
		}
	// ImageEdgeDetect
	public void edgeDetect() {
		System.out.println("EDGEDETECT");
		if (image == null) return;
		if (!(processor instanceof ImageEdgeDetect)) {
			processor = new ImageEdgeDetect(image);
		}
		image = processor.getProcessedImg();
		repaint();
	}
	// ImageGrayscale
	public void grayscale() {
		System.out.println("GRAYSCALE");
		if (image == null) return;
		if (!(processor instanceof ImageGrayscale)) {
			processor = new ImageGrayscale(image);
		}
		image = processor.getProcessedImg();
		repaint();
	}
	// ImageNegative
	public void negative() {
		System.out.println("NEGATIVE");
		if (image == null) return;
		if (!(processor instanceof ImageNegative)) {
			processor = new ImageNegative(image);			
		}
		image = processor.getProcessedImg();
		repaint(); // paint component 호출 
	}
	// reset	
	public void reset() {
		if (image == null) return;
		image = photoArray[currentIndex].getImg();
		factor = 1.0f;
		angle = 0.0f;
		radius = 1;
		processor = null; // reset processor
		repaint();	
	}
	// decrease currentIndex (move to previous)
	public void previous() {
		if (image == null) return;
		if (currentIndex == 0) currentIndex = totalNumberOfPhotos;
		currentIndex = (currentIndex - 1) % totalNumberOfPhotos; // decrease index
		reset();	
	}	
	// increase currentIndex (move to next)
	public void next() {
		if (image == null) return;
		currentIndex = (currentIndex + 1) % totalNumberOfPhotos; // increase index
		reset();
	}
	
	
	
	//UserCode (Sharpen)
	public void sharpen() {
	System.out.println("SHARPEN");
	if (image == null) return;
	if (!(processor instanceof ImageSharpen)) {
		processor = new ImageSharpen(image);			
	}
	image = processor.getProcessedImg();
	repaint(); // paint component 호출 
	}
}
