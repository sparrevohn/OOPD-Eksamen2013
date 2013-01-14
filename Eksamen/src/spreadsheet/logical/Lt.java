package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.expression.BinaryExpression;

public final class Lt extends BinaryExpression {

	public Lt(final Expression alpha, final Expression beta) {
	    super(LogicalType.instance, alpha, beta);		
	}
	
	@Override
	public boolean toBoolean() {
		return this.alpha.toInt() < this.beta.toInt();
		
	}

}
