package spreadsheet;

import gui.StatusView;
import spreadsheet.command.Set;
import spreadsheet.exception.InvalidReference;
import ui.PositionInterpreter;
import ui.exception.InvalidPosition;

public class PasteRef {
	
	private Position pos, pos2;

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
			Copy.setTempCurrentExp(Copy.getStartExp());
			Copy.setTempCurrentPos(Copy.getStartPos());
			StatusView.instance.errorView.setText("Cannot Paste here");
		}
		
	}
	
	public int columnOffset() {
		int col1 = pos2.getColumn();
		int col2 = pos.getColumn();
		return col1 - col2;
	}
	
	public int rowOffset() {
		int row1 = pos2.getRow();
		int row2 = pos.getRow();
		return row1 - row2;
	}
	
	
}
