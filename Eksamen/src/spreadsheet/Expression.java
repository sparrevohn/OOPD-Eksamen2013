package spreadsheet;

import spreadsheet.exception.CycleException;
import spreadsheet.exception.InvalidReference;

public abstract class Expression {

  private final Type type;

  protected Expression(final Type type) {
    this.type = type;
  }

  /** Evaluates the expression. */
  public boolean toBoolean() {
    return this.type.toBoolean(this);
  }

  /** Evaluates the expression. */
  public int toInt() {
    return this.type.toInt(this);
  }

  /** Evaluates the expression.
   *
   * Guaranteed to be not null. */
  public String toString() {
    return this.type.toString(this);
  }

  public abstract void checkAcyclic(final Path path)
    throws CycleException;

  public abstract String getDescription();
  
  public boolean refersTo(final Spreadsheet spreadsheet) {
    return false;
  }
  
  public Expression copy(final int columnOffset, final int rowOffset)
  						throws InvalidReference {
	  Position current = Application.instance.getCurrentPosition();
	  int column = current.getColumn();
	  int row = current.getRow();
	  Position returnedRef = new Position(column + columnOffset, row + rowOffset);
	  if (returnedRef.getColumn() >= 0 && returnedRef.getRow() >= 0)
		  return new Reference(Application.instance.getWorksheet(), returnedRef);
	  else
		  throw new InvalidReference();
  }
 
}
