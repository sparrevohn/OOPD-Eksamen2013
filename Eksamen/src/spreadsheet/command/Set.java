package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.Expression;
import spreadsheet.History;
import spreadsheet.Position;
import spreadsheet.exception.NoSuchSpreadsheet;

/**
 * @author Kenneth S. Mørck
 * Set makes it possible to associate an expression with a position
 */
public final class Set
    extends Command 
    implements Change{

  //All these variables are necessary for the undo method
  private final Position position;
  private final Expression newExpression;
  private final Expression oldExpression;
  private final String sheet;

  /**
   * Initializes the variables so the can be executed
   * @param position Assumed not null
   * @param expression Assumed not null
   */
  public Set(final Position position, final Expression expression) {
    this.position = position;
    this.newExpression = expression;
    this.oldExpression = Application.instance.get(position);
    this.sheet = Application.instance.getWorksheet().getName();
  }

  /**
   * Associates the above expression with the above position
   * also adds a change to the top of the history stack
   */
  public void execute() {
    Application.instance.set(position, newExpression);
    History.instance.push(this);
  }

  /**
   * Undoes the last change made in any spreadsheet
   */
  @Override
  public void undo() {
	  String current = Application.instance.getWorksheet().getName(); 
	  if (Application.instance.getSpreadsheet(sheet) != null && 
			  (position != null && oldExpression != null)) {
			try {
			  Application.instance.changeWorksheet(sheet);
			  Application.instance.set(position, oldExpression);
			  Application.instance.changeWorksheet(current);
			} catch (NoSuchSpreadsheet e) {
			}
	  }
  }  
}
