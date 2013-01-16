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
    this.position = Range.posArray.get(0);
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
	  int i = 0;
	  while (i < range.getArray().size()) {
		  expressions.add(Application.instance.get(range.getArray().get(i)));
		  i++;
	  }  
  }
  
  public boolean toBoolean() {
    return this.getExpression().toBoolean();
  }

  public int toInt() {
    return this.getExpression().toInt();
  }

  public String toString() {
    return this.getExpression().toString();
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
   * @return
   */
  public String getDescription() {
	  int size = range.getArray().size();
	  final String positionDescription;
		  positionDescription = this.position.getDescription() + ":"
				  				+ range.getArray().get(size-1).getDescription();
    if (Application.instance.getWorksheet().equals(this.spreadsheet)) {
      return positionDescription;
    } else {
      return String.format("%s!%s",
        this.spreadsheet.getName(),
        positionDescription);
    }
  }
  
  @Override
  public boolean equals(Object other) {
    if (other == null || !(other instanceof Reference)) {
      return false;
    }
    final Reference otherReference = (Reference)other;
    return
        otherReference.spreadsheet.equals(this.spreadsheet) &&
        otherReference.range.getArray().equals(this.range.getArray());
  }
  
  @Override
  public boolean refersTo(final Spreadsheet spreadsheet) {
    return this.spreadsheet.equals(spreadsheet);
  }

@Override
public Iterator<Expression> iterator() {
	return expressions.iterator();
}



}
