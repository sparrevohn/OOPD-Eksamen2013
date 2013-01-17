package spreadsheet;

public final class Paste {
	
	public void execute() {
		Application.instance.set(Copy.getClipboard());
	}

}
