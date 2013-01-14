package spreadsheet;

import java.util.ArrayList;

public final class Range {

		private int minColumn;
		private int maxColumn;
		private int minRow;
		private int maxRow;
		public ArrayList<Position> posArray; 
	
	public Range(final Position a, final Position b) {
		minColumn = Math.min(a.getColumn(),b.getColumn());
		maxColumn = Math.max(a.getColumn(), b.getColumn());
		minRow = Math.min(a.getRow(), b.getRow());
		maxRow = Math.max(a.getRow(), b.getRow());
	}
	
	public void makeArray() {
		posArray = new ArrayList<Position>();
		int startRow = minRow;
		while (minColumn <= maxColumn) {
			while (minRow <= maxRow) {
				posArray.add(new Position(minColumn, minRow));
				minRow++;
			}
			minRow = startRow;
			minColumn++;
		}
	  }	
}

