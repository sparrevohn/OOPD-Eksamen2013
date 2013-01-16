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
	 new NewSpreadsheet();
	 new NewSpreadsheet();
	 new NewSpreadsheet();
	 new NewSpreadsheet();
	 new NewSpreadsheet();
	 History.instance.pop().undo();
  }
}
