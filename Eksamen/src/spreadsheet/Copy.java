package spreadsheet;

import spreadsheet.textual.Text;
import ui.PositionInterpreter;
import ui.exception.InvalidPosition;

/**
 * @author Kenneth S. Mørck
 * Copy uses multiple statics to copy an expression 
 * and make it possible to paste in a different position
 */
public class Copy {
	
	//Current expression in the paste loop that allows recursive pasting
	private static Expression tempCurrentExp;
	//Start expression used to change the tempCurrentExp back to its
	//starting expression, so another paste loop can happen
	private static Expression startExp;
	//Current position in the paste loop that allows recursive pasting
	private static Position tempCurrentPos;
	//Start position used to change the tempCurrentPos back to its
	//starting position, so another paste loop can happen
	private static Position startPos;
	//Minimum column offset making sure the paste loop won't start in a 
	//column index that is to small for the loop to finish 
	private static int minColOffset;
	//Minimum row offset making sure the paste loop won't start in a 
	//row index that is to small for the loop to finish
	private static int minRowOffset;
	//Keeps track of the initial worksheet of the copy, so references won't
	//be pasted in a different spreadsheet
	private static String sheet;
	
	/**
	 * Initializes the variables and runs an offset check
	 */
	public Copy() {
		Application app = Application.instance;
		setTempCurrentExp(app.get());
		setTempCurrentPos(app.getCurrentPosition());
		setStartExp(app.get());
		setStartPos(app.getCurrentPosition());
		setMinColOffset(0);
		setMinRowOffset(0);
		setSheet(app.getWorksheet().getName());
		offsetCheck();
		
	}

	/**
	 * Getter for tempCurrentExp
	 * @return Guaranteed not null
	 */
	public static Expression getTempCurrentExp() {
		return tempCurrentExp;
	}
	
	/**
	 * Setter for tempCurrentExp
	 * @param exp Checked for null
	 */
	public static void setTempCurrentExp(Expression exp) {
		if (exp != null)
			tempCurrentExp = exp;
		else
			tempCurrentExp = new Text("");
	}

	/**
	 * Getter for tempCurrentPos
	 * @return Assumed not null
	 */
	public static Position getTempCurrentPos() {
		return tempCurrentPos;
	}
	
	/**
	 * Setter for tempCurrentPos
	 * @param pos Assumed not null, user cannot make position null
	 */
	public static void setTempCurrentPos(Position pos) {
			tempCurrentPos = pos;
	}

	/**
	 * Getter for startExp
	 * @return Guaranteed not null
	 */
	public static Expression getStartExp() {
		return startExp;
	}

	/**
	 * Setter for startExp
	 * @param exp Checked for null
	 */
	public static void setStartExp(Expression exp) {
		if (exp != null)
			startExp = exp;
		else
			startExp = new Text("");
	}

	/**
	 * Getter for startPos
	 * @return Assumed not null
	 */
	public static Position getStartPos() {
		return startPos;
	}

	/**
	 * Setter for startPos
	 * @param pos Assumed not null, user cannot make position null 
	 */
	public static void setStartPos(Position pos) {
			startPos = pos;
	}
	
	/**
	 * Getter for minColOffset
	 * @return 0 only for non-reference expressions
	 */
	public static int getMinColOffset() {
		return minColOffset;
	}
	
	/**
	 * Setter for minColOffset
	 * @param i Integer to be set
	 */
	public static void setMinColOffset(int i) {
		minColOffset = i;
	}
	
	/**
	 * Getter for minRowOffset
	 * @return 0 only for non-reference expressions
	 */
	public static int getMinRowOffset() {
		return minRowOffset;
	}
	
	/**
	 * Setter for minRowOffset
	 * @param i Integer to be set
	 */
	public static void setMinRowOffset(int i) {
		minRowOffset = i;
	}
	
	/**
	 * Getter for sheet
	 * @return Guaranteed not null
	 */
	public static String getSheet() {
		return sheet;
	}

	/**
	 * Setter for sheet
	 * @param sheet Assumed not null, user cannot make sheet null
	 */
	public static void setSheet(String sheet) {
		Copy.sheet = sheet;
	}
	
	/**
	 * Checks the offset necessary to paste a reference
	 * If copied expression is not a reference offsets will be 0
	 */
	private void offsetCheck() {
		if (Copy.getTempCurrentExp().getDescription().matches(
														  "[a-zA-Z]+[0-9]+")) {
			try {
				Position pos = PositionInterpreter.interpret(
								Copy.getTempCurrentExp().getDescription());
				Copy.setTempCurrentExp(Application.instance.get(
														getTempCurrentPos()));
				setMinColOffset(Math.min(getMinColOffset(), 
								Copy.getTempCurrentPos().getColumn() 
							  - Copy.getStartPos().getColumn()));
				setMinRowOffset(Math.min(getMinRowOffset(), 
								Copy.getTempCurrentPos().getRow() 
							  - Copy.getStartPos().getRow()));
				Copy.setTempCurrentPos(pos);
				offsetCheck();
			} catch (InvalidPosition e) {
				//This should never happen
			}
		}
		else
			Copy.setTempCurrentPos(getStartPos());
			Copy.setTempCurrentExp(getStartExp());
	}

}
