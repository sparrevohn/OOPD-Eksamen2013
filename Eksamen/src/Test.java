import spreadsheet.Application;
import spreadsheet.Position;
import spreadsheet.Range;
import spreadsheet.Reference;
import spreadsheet.Spreadsheet;
import spreadsheet.arithmetic.Sum;
import spreadsheet.textual.Text;
import ui.PositionInterpreter;
import ui.exception.InvalidPosition;

class Test {

	
  public static void main(String[] _) {
	  System.out.println("Hej");
	  Position pos = new Position(0,0);
	  Application.instance.set(pos, new Text("Hej"));
	  Reference ref = new Reference(new Spreadsheet(), new Range(pos, new Position(5,5)));
	  System.out.println(ref.getDescription());
	  System.out.println(new Sum(ref).getDescription());
	  System.out.println(Application.instance.get(pos).toInt());
	  System.out.println(ref == null);
	  System.out.println(new Sum(ref));
	  String text = " A0:A5";
	  int indexOfColon = text.indexOf(':');
	  Position pos1 = null;
	try {
		pos1 = PositionInterpreter.interpret(
				  					text.substring(1, indexOfColon));
	} catch (InvalidPosition e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  Position pos2 = null;
	try {
		pos2 = PositionInterpreter.interpret(
				  					text.substring(indexOfColon+1));
	} catch (InvalidPosition e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(pos1.getDescription());
	System.out.println(pos2.getDescription());
	  System.out.println((new Reference(Application.instance.getWorksheet(),
			  		 new Range(pos1, pos2))).getDescription());
	  System.out.println(new Sum((new Reference(Application.instance.getWorksheet(),
		  		 new Range(pos1, pos2)))).getDescription());
  }
}
