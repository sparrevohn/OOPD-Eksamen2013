package spreadsheet;

import java.util.ArrayList;
import java.util.Iterator;

import spreadsheet.exception.CycleException;
import spreadsheet.textual.Text;

public final class Reference
    extends Expression implements Iterable<Expression> {

  private final Spreadsheet spreadsheet;
  public final Range range;
  private ArrayList<Expression> expressions;

  public Reference(final Spreadsheet spreadsheet, final Range range) {
    super(GenericType.instance);
    this.spreadsheet = spreadsheet;
    this.range = range;
    range.makeArray();
  }

  private Expression getExpression() {
	  int i = 0;
	  expressions = new ArrayList<Expression>();
	  while (i <= range.posArray.size()-1) {
	    Expression expression = this.spreadsheet.get(this.range.posArray.get(i));
	    if (expression == null) {
	      expression = new Text("");
	    }
	    expressions.add(expression);
	    i++;
	  }
	return null;
  }

  public boolean toBoolean() {
    return this.getExpression().toBoolean();
  }

  public int toInt() {
    return this.getExpression().toInt();
  }

  public String toString() {
	  if (this.getExpression() != null)
    return this.getExpression().toString();
	  else return "";
  }

  public void checkAcyclic(final Path path)
      throws CycleException {
    if (path.contains(this)) {
      throw new CycleException(new Path(this, path));
    }
    getExpression().checkAcyclic(new Path(this, path));
  }

  public String getDescription() {
	int lastIndex = range.posArray.size()-1;
    final String positionDescription = range.posArray.get(0).getDescription() + "-"
    								  + range.posArray.get(lastIndex).getDescription();
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
        otherReference.range.posArray.equals(this.range.posArray);
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
