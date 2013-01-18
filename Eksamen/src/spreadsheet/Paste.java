package spreadsheet;

import spreadsheet.command.Set;
import spreadsheet.exception.InvalidReference;

public final class Paste {
	
	public Paste() throws InvalidReference {
		if (Application.instance.getCurrentPosition().getColumn() 
				+ Copy.getMinColOffset() >= 0 &&
			Application.instance.getCurrentPosition().getRow() 
				+ Copy.getMinRowOffset() >= 0) {
			pasteLoop();
		}
		else
			throw new InvalidReference();
	}
	
	public static void pasteLoop() {
		if (Copy.getTempCurrentExp().getDescription().matches(
				  "[a-zA-Z]+[0-9]+")) {
			new PasteRef();
		} 
		else 
			execute();
	}
	
	public static void execute() {
		new Set(Application.instance.getCurrentPosition(), 
				Copy.getTempCurrentExp()).execute();
		Copy.setTempCurrentExp(Copy.getStartExp());
		Copy.setTempCurrentPos(Copy.getStartPos());
	}

}
