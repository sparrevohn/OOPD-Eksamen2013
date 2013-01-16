package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.expression.BinaryExpression;

/**
 * @author Kenneth S. Mørck
 * Eq stands for equals and is a class for boolean expressions
 */
public final class Eq extends BinaryExpression {

	/**
	 * Defines the type as logical and sends the parameters to the superclass
	 * @param alpha Assumed not null
	 * @param beta Assumed not null
	 */
	public Eq(final Expression alpha, final Expression beta) {
	    super(LogicalType.instance, alpha, beta);
	}
	
	/**
	 * Overriding toBoolean() to be able to evaluate
	 * @return Whether alpha is the same as beta
	 */
	@Override
	public boolean toBoolean() {
		return this.alpha.toString().equals(this.beta.toString());
	}
	
}
