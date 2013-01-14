package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.expression.BinaryExpression;

public final class Eq extends BinaryExpression {

	public Eq(final Expression alpha, final Expression beta) {
	    super(LogicalType.instance, alpha, beta);
	}
	
	@Override
	public boolean toBoolean() {
		return this.alpha.toString().equals(this.beta.toString());
	}
	
}
