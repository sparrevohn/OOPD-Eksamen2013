package spreadsheet;

import spreadsheet.command.Set;
import spreadsheet.exception.InvalidReference;
import ui.PositionInterpreter;
import ui.exception.InvalidPosition;

/**
 * @author Kenneth S. Mørck
 * Class for changing expression to the right reference and inserting it 
 * only if the copied expression is a reference
 */
public class PasteRef {
	
	//Position of the reference and the referenced
	private Position pos, pos2;

	/**
	 * Finds the position referenced, then changes the tempCurrents in copy,
	 * Sets the new reference gotten from Expression.copy(), changes current
	 * position and runs a new paste loop.
	 * 
	 * Do to the Set command being used, undoing is possible but each part
	 * of the paste loop must be individually undone
	 */
	public PasteRef() {
		try {
		Expression ref = Copy.getTempCurrentExp();
		pos = Copy.getTempCurrentPos();
			pos2 = PositionInterpreter.interpret(ref.getDescription());
			Copy.setTempCurrentExp(Application.instance.get(pos2));
			Copy.setTempCurrentPos(pos2);
			new Set(Application.instance.getCurrentPosition(), 
					ref.copy(columnOffset(), rowOffset())).execute();
			Application.instance.setCurrentPosition(
					PositionInterpreter.interpret(
							ref.copy(columnOffset(), 
										rowOffset()).getDescription()));
			Paste.pasteLoop();
		} catch (InvalidPosition | InvalidReference e) {
			//This cannot ever happen in GUI since every exception is already
			//ensured not to happen in the other classes calling this
			System.err.println("This error should only happen in testing");
		}
		
	}
	
	/**
	 * Returns the column offset of the references position 
	 * and the referenced's position
	 * @return Integer representing column index difference
	 */
	public int columnOffset() {
		int col1 = pos2.getColumn();
		int col2 = pos.getColumn();
		return col1 - col2;
	}
	
	/**
	 * Returns the row offset of the references position 
	 * and the referenced's position
	 * @return Integer representing row index difference
	 */
	public int rowOffset() {
		int row1 = pos2.getRow();
		int row2 = pos.getRow();
		return row1 - row2;
	}
	
	
}
