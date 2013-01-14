import spreadsheet.Application;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.Spreadsheet;
import spreadsheet.arithmetic.Int;
import spreadsheet.textual.Text;

class Test {

	
  public static void main(String[] _) {
	  Reference local = new Reference(new Spreadsheet(), new Range(new Position(0,0), new Position(2,2)));
	  Application.instance.set(new Position(0,0), new Text("Hej"));
	  System.out.println(local.getDescription());
	  System.out.println(local.toString());
	  System.out.println(local.range.posArray);
  }
}
