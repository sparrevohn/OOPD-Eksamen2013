package spreadsheet;

public class Copy {
	
	private static Expression clipboard;
	private static String sheet;
	
	public Copy() {
		clipboard = Application.instance.get();
		sheet = Application.instance.getWorksheet().getName();
	}

	public static Expression getClipboard() {
		return clipboard;
	}

	public static String getSheet() {
		return sheet;
	}

}
