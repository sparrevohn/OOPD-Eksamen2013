package spreadsheet;

import java.util.ArrayList;

public final class Range {

		private int minColumn;
		private int maxColumn;
		private int minRow;
		private int maxRow;
		public static ArrayList<Position> posArray; 
	
	public Range(final Position a, final Position b) {
		minColumn = Math.min(a.getColumn(),b.getColumn());
		maxColumn = Math.max(a.getColumn(), b.getColumn());
		minRow = Math.min(a.getRow(), b.getRow());
		maxRow = Math.max(a.getRow(), b.getRow());
		posArray = new ArrayList<Position>();
		makeArray();
	}
	
	private void makeArray() {
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
	
	public ArrayList<Position> getArray() {
		return posArray;
	}
	
	public void setArray(Position pos) {
		int index = containedIndex(pos); 
		if (index == -1) {
			new Range(pos, posArray.get(posArray.size()-1));
		}
		else posArray.set(index, pos);
	}
	
	public int containedIndex(Position pos) {
		int i = 0;
		while (i < Range.posArray.size()-1) {
			if (pos.getColumn() == posArray.get(i).getColumn()
				&& pos.getRow() == posArray.get(i).getRow()) {
				return i;
			}
			else i++;
		}
		return -1;
	}
}

