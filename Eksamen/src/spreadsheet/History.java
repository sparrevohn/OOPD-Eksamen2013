package spreadsheet;

import java.util.Stack;
import spreadsheet.Change;

public class History {

	public static final History instance = new History();
	private Stack<Change> changeStack;
	
	private History() {	
	changeStack = new Stack<Change>();
	}
	
	public void push(Change change) {
		if (changeStack.size() <= 20)
			changeStack.push(change);
		else {
			changeStack.push(change);
			changeStack.setSize(20);
		}
	}
	
	public Change pop() {
		if (!changeStack.isEmpty())
			return changeStack.pop();
		else {
			return null;
		}
	}
}
	