import spreadsheet.Position;
import spreadsheet.Range;

class Test {

	private static Range test;
	
  public static void main(String[] _) {
	  test = new Range(new Position(0,0), new Position(5,5));
	  test.makeArray();
	  int i = 0;
	  System.out.println(test.posArray.size());
	  while (i < test.posArray.size()) {
		  System.out.println(test.posArray.get(i).getDescription());
		  i++;
	  }
  }
}
