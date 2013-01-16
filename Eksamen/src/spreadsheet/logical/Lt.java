package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.expression.BinaryExpression;

/**
 * @author Kenneth S. Mørck
 * Lt stands for "Less than" and is a class for boolean expressions 
 */
public final class Lt extends BinaryExpression {

	/**
	 * Defines the type as logical and uses the parameters in the superclass
	 * @param alpha Assumed not null
	 * @param beta Assumed not null
	 */
	public Lt(final Expression alpha, final Expression beta) {
	    super(LogicalType.instance, alpha, beta);		
	}
	
	/**
	 * Overriding toBoolean() to be able to evaluate
	 * @return Whether the numerical value of alpha
	 * 		   is less than the numerical value of beta
	 */
	@Override
	public boolean toBoolean() {
		return this.alpha.toInt() < this.beta.toInt();
		
	}

}
