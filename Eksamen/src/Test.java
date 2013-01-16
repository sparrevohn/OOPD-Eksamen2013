import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.History;
import spreadsheet.Position;
import spreadsheet.arithmetic.Int;
import spreadsheet.command.NewSpreadsheet;
import spreadsheet.command.Set;

class Test {

	
  public static void main(String[] _) {
	 int i = 0;
	 Position pos = new Position(0,0);
	 Expression exp = new Int(i);
	 new Set(pos, exp).execute();
	 i++;
	 System.out.println(Application.instance.get(pos));
	 while (i <= 20) {	
	 	exp = new Int(i);
	 	new Set(pos, exp).execute();
	 	i++;
	 	System.out.println(Application.instance.get(pos));
	 }
	 i = 0;
	 while (i <= 20) {
		 History.instance.pop().undo();
		 i++;
	 }
//	 System.out.println(Application.instance.get(pos));
//	 new NewSpreadsheet().execute();
//	 new NewSpreadsheet().execute();
//	 new NewSpreadsheet().execute();
//	 System.out.println(Application.instance.getSpreadsheets());
//	 History.instance.pop().undo();
//	 System.out.println(Application.instance.getSpreadsheets());
  }
}
