import spreadsheet.Application;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.Spreadsheet;
import spreadsheet.arithmetic.Sum;
import spreadsheet.textual.Text;

class Test {

	
  public static void main(String[] _) {
	  Position pos = new Position(0,0);
	  Reference ref = new Reference(new Spreadsheet(), new Range(pos, new Position(5,5)));
	  System.out.println(ref.getDescription());
	  System.out.println(new Sum(ref).getDescription());
	  Application.instance.set(pos, new Text("Hej"));
	  System.out.println(Application.instance.get(pos).toInt());
	  System.out.println(new Sum(ref));
  }
}
