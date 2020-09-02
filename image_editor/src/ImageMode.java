
public enum ImageMode {
	CONVERT(1), // convert format
	RESIZE(2), // resize
	ROTATE(3), // rotate
	GRAYSCALE(4), // grayscale
	BLUR(5), // blur
	BRIGHTNESS_ADJUST(6), // brightness adjust
	NEGATIVE(7), // negative
	EDGE_DETECT(8), // edge detect
	SHARPEN(9);
	
	private final int mode;
	ImageMode(int mode) {
		this.mode = mode;
	}
	
	public int getMode() {
		return mode;
	}

	public static ImageMode modeOf(int value) {
		switch (value) { // switch
		case 1:
			return CONVERT;
		case 2:
			return RESIZE;
		case 3:
			return ROTATE;
		case 4:
			return GRAYSCALE;
		case 5:
			return BLUR;
		case 6:
			return BRIGHTNESS_ADJUST;
		case 7:
			return NEGATIVE;
		case 8:
			return EDGE_DETECT;
		case 9:
			return SHARPEN;
		default:
			return null;
		}
	}

	public static ImageMode modeOf(String name) {
		switch (name) { // switch
		case "1":
		case "CONVERT":
			return CONVERT;
		case "2":
		case "RESIZE":
			return RESIZE;
		case "3":
		case "ROTATE":
			return ROTATE;
		case "4":
		case "GRAYSCALE":
			return GRAYSCALE;
		case "5":
		case "BLUR":
			return BLUR;
		case "6":
		case "BRIGHTNESS_ADJUST":
			return BRIGHTNESS_ADJUST;
		case "7":
		case "NEGATIVE":
			return NEGATIVE;
		case "8":
		case "EDGE_DETECT":
			return EDGE_DETECT;
		case "9":
		case "SHARPEN":
			return SHARPEN;
		default:
			return null;
		}
	}

	public static String name(ImageMode mode) {
		switch (mode) { // switch
		case CONVERT:
			return "CONVERT";
		case RESIZE:
			return "RESIZE";
		case ROTATE:
			return "ROTATE";
		case GRAYSCALE:
			return "GRAYSCALE";
		case BLUR:
			return "BLUR";
		case BRIGHTNESS_ADJUST:
			return "BRIGHTNESS_ADJUST";
		case NEGATIVE:
			return "NEGATIVE";
		case EDGE_DETECT:
			return "EDGE_DETECT";
		case SHARPEN:
			return "SHARPEN";
		default:
			return null;
		}
	}

	public static String[] names() {
		String[] names = new String[ImageMode.values().length];
		for (ImageMode m : ImageMode.values()) 
			names[m.ordinal()] = m.toString();
		return names;
	}
}
