package spreadsheet;

import java.util.ArrayList;

/**
 * @author Kenneth S. Mørck
 * Range makes referring to multiple positions possible
 */
public final class Range {

		//Variables used by later method to 
		//create an ArrayList of Positions
		private int minColumn;
		private int maxColumn;
		private int minRow;
		private int maxRow;
		public static ArrayList<Position> posArray; 
		
		/**
		 * Gets the minimum and maximum column and row values of two position
		 * and initializes the variables 
		 * @param a Assumed not null
		 * @param b Assumed not null
		 */
	public Range(final Position a, final Position b) {
		minColumn = Math.min(a.getColumn(),b.getColumn());
		maxColumn = Math.max(a.getColumn(), b.getColumn());
		minRow = Math.min(a.getRow(), b.getRow());
		maxRow = Math.max(a.getRow(), b.getRow());
		posArray = new ArrayList<Position>();
		makeArray();
	}
	
	/**
	 * Uses the column and row variables to create an ArrayList of
	 * all positions in the given area
	 */
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
	
	/**
	 * Used to get posArray the right way
	 * @return posArray
	 */
	public ArrayList<Position> getArray() {
		return posArray;
	}
	
	public Position getUpperLeft() {
		return posArray.get(0);
	}
	
	
	public Position getLowerRight() {
		return posArray.get(posArray.size()-1);
	}
}

