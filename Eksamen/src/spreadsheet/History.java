package spreadsheet;

import java.util.Stack;
import spreadsheet.Change;

/**
 * @author Kenneth S. Mørck
 * History keeps track of the last 20 commands
 */
public class History {

	public static final History instance = new History();
	private Stack<Change> changeStack;
	
	/** History is a singleton */
	private History() {	
	changeStack = new Stack<Change>();
	}
	
	/**
	 * Adds the change to a stack
	 * @param change Holds information about the change made
	 */
	public void push(Change change) {
		if (changeStack.size() <= 20)
			changeStack.push(change);
		else {
			changeStack.push(change);
			changeStack.setSize(20);
		}
	}
	
	/**
	 * Gets last change made
	 * @return Last change if any else null
	 */
	public Change pop() {
		if (!changeStack.isEmpty())
			return changeStack.pop();
		else {
			return null;
		}
	}
}
	