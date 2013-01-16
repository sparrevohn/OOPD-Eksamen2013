package spreadsheet.command;

import spreadsheet.Application;
import spreadsheet.Change;
import spreadsheet.Expression;
import spreadsheet.History;
import spreadsheet.Position;

public final class Set
    extends Command 
    implements Change{

  private final Position position;
  private final Expression newExpression;
  private final Expression oldExpression;

  public Set(final Position position, final Expression expression) {
    this.position = position;
    this.newExpression = expression;
    this.oldExpression = Application.instance.get(position);
  }

  public void execute() {
    Application.instance.set(position, newExpression);
    History.instance.push(this);
  }

  @Override
  public void undo() {
	  if (position != null && oldExpression != null)
		  Application.instance.set(position, oldExpression);
  }
  
}
