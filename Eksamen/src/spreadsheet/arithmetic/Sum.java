package spreadsheet.arithmetic;

import spreadsheet.Expression;
import spreadsheet.Reference;
import spreadsheet.expression.NullaryExpression;

/**
 * @author Kenneth S. Mørck
 *  
 */
public final class Sum extends NullaryExpression {
	
	private final Reference reference; 
	private String description;
	
	public Sum(final Reference reference) {
		super(ArithmeticType.instance);
		this.reference = reference;
		description = reference.getDescription();
	}

	@Override
	public int toInt() {
		int sum = 0;
		for (Expression exp : reference) {
				sum = sum + exp.toInt();
		}
		return sum;
	}
	
	@Override
	public String getDescription() {
		return this.getClass().getSimpleName() + " " 
			   + description.substring(description.indexOf('!')+1);
	}
}
