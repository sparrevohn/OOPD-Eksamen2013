package spreadsheet;

import spreadsheet.command.Set;
import spreadsheet.exception.InvalidReference;

/**
 * @author Kenneth S. Mørck
 * Pastes the copied expression(expressions if references are involved)
 * if it lives up to the requirements 
 */
public final class Paste {
	
	/**
	 * Starts a paste loop if no expressions will go out of bounds
	 * and a reference is not pasted in a different spreadsheet 
	 * @throws InvalidReference If some expressions go out of bounds
	 * 							or a reference is pasted in a different
	 * 							spreadsheet;
	 */
	public Paste() throws InvalidReference {
		Application app = Application.instance; 
		if (app.getCurrentPosition().getColumn() 
				+ Copy.getMinColOffset() >= 0 
		 && app.getCurrentPosition().getRow() 
				+ Copy.getMinRowOffset() >= 0) {
			if (Copy.getSheet().equals(app.getWorksheet().getName()) 
				 || (Copy.getMinColOffset() == 0 
				  && Copy.getMinRowOffset() == 0))
			pasteLoop();
			else
				throw new InvalidReference();
		}
		else
			throw new InvalidReference();
	}
	
	/**
	 * If the temporarily current expression is a reference it initiates
	 * a loop to find the new position and new reference to be set, if not
	 * it pastes the expression (and ends the loop)
	 */
	public static void pasteLoop() {
		if (Copy.getTempCurrentExp().getDescription().matches(
				  "[a-zA-Z]+[0-9]+")) {
			new PasteRef();
		} 
		else 
			execute();
	}
	
	/**
	 * Pastes the temporarily current expression on the current position
	 * and sets the temporary position and expression to their starting values
	 * in order to be able to perform more than one paste loop
	 */
	public static void execute() {
		new Set(Application.instance.getCurrentPosition(), 
				Copy.getTempCurrentExp()).execute();
		Copy.setTempCurrentExp(Copy.getStartExp());
		Copy.setTempCurrentPos(Copy.getStartPos());
	}

}
