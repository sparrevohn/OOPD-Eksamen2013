package spreadsheet;

import ui.PositionInterpreter;
import ui.exception.InvalidPosition;

public class Copy {
	
	private static Expression tempCurrentExp;
	private static Expression startExp;
	private static Position tempCurrentPos;
	private static Position startPos;
	private static int minColOffset;
	private static int minRowOffset;
	private static String sheet;
	
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

	public static Expression getTempCurrentExp() {
		return tempCurrentExp;
	}
	
	public static void setTempCurrentExp(Expression exp) {
		tempCurrentExp = exp;
	}

	public static Position getTempCurrentPos() {
		return tempCurrentPos;
	}
	
	public static void setTempCurrentPos(Position pos) {
		tempCurrentPos = pos;
	}

	public static Expression getStartExp() {
		return startExp;
	}

	public static void setStartExp(Expression exp) {
		startExp = exp;
	}

	public static Position getStartPos() {
		return startPos;
	}

	public static void setStartPos(Position pos) {
		startPos = pos;
	}

	public static int getMinColOffset() {
		return minColOffset;
	}
	
	public static void setMinColOffset(int i) {
		minColOffset = i;
	}
	
	public static int getMinRowOffset() {
		return minRowOffset;
	}
	
	public static void setMinRowOffset(int i) {
		minRowOffset = i;
	}
	
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

	public static String getSheet() {
		return sheet;
	}

	public static void setSheet(String sheet) {
		Copy.sheet = sheet;
	}
}
