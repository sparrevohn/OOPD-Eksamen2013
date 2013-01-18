package spreadsheet;

import java.util.ArrayList;
import java.util.Iterator;

import spreadsheet.exception.CycleException;
import spreadsheet.textual.Text;

/**
 * @author Kenneth S. Mørck
 * Makes a reference to one or more cells in the spreadsheet
 */
public final class Reference
    extends Expression implements Iterable<Expression> {

  private final Spreadsheet spreadsheet;
  private final Position position;
  private final Range range;
  private ArrayList<Expression> expressions;

  /**
   * Defines Reference as a GenericType and initializes the variables
   * if given a position
   * @param spreadsheet Assumed not null
   * @param position Assumed not null
   */
  public Reference(final Spreadsheet spreadsheet, final Position position) {
	  super(GenericType.instance);
	  this.spreadsheet = spreadsheet;
	  this.position = position;
	  this.range = new Range(position, position);
	  expressions = new ArrayList<Expression>();
	  makeArray();
  }
  
  /**
   * Defines Reference as a GenericType and initializes the variables
   * if given a range
   * @param spreadsheet Assumed not null
   * @param position Assumed not null
   */
  public Reference(final Spreadsheet spreadsheet, final Range range) {
    super(GenericType.instance);
    this.spreadsheet = spreadsheet;
    this.position = range.getArray().get(0);
    this.range = range;
	expressions = new ArrayList<Expression>();
	makeArray();
  }

  /**
   * Returns the expression on the given position unless no expression
   * is associated with the position the returns an empty Text 
   * @return Guaranteed not null
   */
  private Expression getExpression() {
	    Expression expression = this.spreadsheet.get(this.position);
	    if (expression == null) {
	      expression = new Text("");
	    }
	    return expression;
  }

  /**
   * Fills the expressions ArrayList with all expression in the range
   */
  private void makeArray() {
	  ArrayList<Position> array = range.getArray();
	  int i = 0;
	  while (i < array.size()) {
		  expressions.add(Application.instance.get(array.get(i)));
		  i++;
	  }  
  }
  
  /**
   * Evaluates the expression to a boolean value
   * @throws UnsupportedOperationException If the expression holds 
   * 									   a reference to multiple cells
   */
  public boolean toBoolean() throws UnsupportedOperationException {
	  if (range.getUpperLeft().isEqualTo(range.getLowerRight()))
		  return this.getExpression().toBoolean();
	  else throw new UnsupportedOperationException();
  }

  /**
   * Evaluates the expression to a integer value
   * @throws UnsupportedOperationException If the expression holds 
   * 									   a reference to multiple cells
   */
  public int toInt() throws UnsupportedOperationException {
	  if (range.getUpperLeft().isEqualTo(range.getLowerRight()))
		  return this.getExpression().toInt();
	  else throw new UnsupportedOperationException();
  }

  /**
   * Evaluates the expression to a string value
   * @throws UnsupportedOperationException If the expression holds 
   * 									   a reference to multiple cells
   */
  public String toString() throws UnsupportedOperationException {
	  if (range.getUpperLeft().isEqualTo(range.getLowerRight()))
		  return this.getExpression().toString();
	  else throw new UnsupportedOperationException();
  }

  public void checkAcyclic(final Path path)
      throws CycleException {
    if (path.contains(this)) {
      throw new CycleException(new Path(this, path));
    }
    getExpression().checkAcyclic(new Path(this, path));
  }
  
  /**
   * Method for getting an easily understandable string of the reference
   * @return String
   */
  public String getDescription() {
	  int size = range.getArray().size();
	  final String positionDescription;
	  if (size > 1) {
		  positionDescription = this.position.getDescription() + ":"
				  				+ range.getLowerRight().getDescription();
	  }
	  else positionDescription = this.position.getDescription();
    if (Application.instance.getWorksheet().equals(this.spreadsheet)) {
      return positionDescription;
    } else {
      return String.format("%s!%s",
        this.spreadsheet.getName(),
        positionDescription);
    }
  }
  
  /**
   * Checks to see if two references are the same
   * @return boolean representation of two references being equal
   */
  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Reference)) {
      return false;
    }
    final Reference otherReference = (Reference)other;
    return
        otherReference.spreadsheet.equals(this.spreadsheet) &&
        otherReference.position.equals(this.position);
  }
  
  @Override
  public boolean refersTo(final Spreadsheet spreadsheet) {
    return this.spreadsheet.equals(spreadsheet);
  }

  /**
   * Iterator used by Sum to go through the expressions with at 'for-each' loop
   */
  @Override
  public Iterator<Expression> iterator() {
	return expressions.iterator();
  }

  /**
   * Used by sum to get the right description
   * @return 
   */
  public String getRangeDescription() {
	  return range.getUpperLeft().getDescription() + ":" 
		   + range.getLowerRight().getDescription(); 
  }

}
