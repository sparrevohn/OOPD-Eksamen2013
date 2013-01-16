package spreadsheet;

import java.util.Stack;

public class History extends Stack<History> {

	public static final History instance = new History();
	
	public History() {
		
	}
}
