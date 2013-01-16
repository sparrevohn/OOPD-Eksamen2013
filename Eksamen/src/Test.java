import spreadsheet.Application;
import spreadsheet.Expression;
import spreadsheet.History;
import spreadsheet.Position;
import spreadsheet.arithmetic.Int;
import spreadsheet.arithmetic.Sum;
import spreadsheet.command.NewSpreadsheet;
import spreadsheet.command.Set;

class Test {

	
  public static void main(String[] _) {
	 Expression nullTest = new Sum(null);
	 System.out.println(nullTest.getDescription());
  }
}
