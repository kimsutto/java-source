
public enum ConvertMode {
	TO_JPG(1), // convert it to jpg
	TO_PNG(2), // convert it to png
	TO_GIF(3); // convert it to gif
	
	private final int mode;
	ConvertMode(int mode) {
		this.mode = mode;
	}
	
	public int getMode() {
		return mode;
	}

	public String getName() {
		switch (mode) { // switch
		case 1:
			return "jpg";
		case 2:
			return "png";
		case 3:
			return "gif";
		}
		return null;		
	}
	
	public static ConvertMode modeOf(int mode) {
		switch (mode) { // switch
		case 1:
			return TO_JPG;
		case 2:
			return TO_PNG;
		case 3:
			return TO_GIF;
		default:
			return null;
		}
	}
	
	public static ConvertMode modeOf(String name) {
		switch (name) { // switch
		case "1":
		case "TO_JPG":
			return TO_JPG;
		case "2":
		case "TO_PNG":
			return TO_PNG;
		case "3":
		case "TO_GIF":
			return TO_GIF;
		default:
			return null;
		}
	}
	
	public static String name(ConvertMode mode) {
		switch (mode) { // switch
		case TO_JPG:
			return "jpg";
		case TO_PNG:
			return "png";
		case TO_GIF:
			return "gif";
		default:
			return null;
		}
	}
	
	public static String[] names() {
		String[] names = new String[ConvertMode.values().length];
		for (ConvertMode m : ConvertMode.values()) 
			names[m.ordinal()] = m.toString();
		return names;
	}
}
