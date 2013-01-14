import spreadsheet.Application;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.Spreadsheet;
import spreadsheet.textual.Text;

class Test {

	
  public static void main(String[] _) {
	  Reference local = new Reference(new Spreadsheet(), new Range(new Position(0,0), new Position(2,2)));
	  Position pos = new Position(0,0);
	  System.out.println(Range.posArray);
  }
}
