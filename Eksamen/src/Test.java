import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.History;
import spreadsheet.Position;
import spreadsheet.arithmetic.Int;
import spreadsheet.command.NewSpreadsheet;
import spreadsheet.command.Set;

class Test {

	
  public static void main(String[] _) {
	 Position pos = new Position(0,0);
	 Expression exp = new Int(1);
	 Expression exp2 = new Int(2);
//	 new Set(pos, exp).execute();
//	 new Set(pos, exp2).execute();
	 new NewSpreadsheet().execute();
	 System.out.println(Application.instance.getSpreadsheets());
	 History.instance.pop().undo();
	 System.out.println(Application.instance.getSpreadsheets());
  }
}
