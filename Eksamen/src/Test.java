import java.util.Scanner;

class Test {

  public static void main(String[] _) {
	  String local = new String("If Int 1 Then Int 2 Else Int 3");
	  Scanner scanner = new Scanner(local);
	  String scanner2 = scanner.next();
	  String ifTE = scanner.nextLine();
	  int indexThen = ifTE.indexOf("Then");
	  int indexElse = ifTE.indexOf("Else");
	  String thenExp = ifTE.substring(indexThen+5, indexElse);
	  String elseExp = ifTE.substring(indexElse+5);
	  System.out.println(ifTE);
	  System.out.println(indexThen);
	  System.out.println(indexElse);
	  System.out.println(thenExp);
	  System.out.println(elseExp);
	
  }

}
