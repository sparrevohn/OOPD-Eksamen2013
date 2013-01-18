import spreadsheet.Copy;

class Test {

	
  public static void main(String[] _) {
	  Copy.setTempCurrentPos(null);
	  System.out.println(Copy.getTempCurrentPos().getDescription());
  }
}
