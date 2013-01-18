package spreadsheet.arithmetic;

import spreadsheet.Expression;
import spreadsheet.Reference;
import spreadsheet.expression.NullaryExpression;

/**
 * @author Kenneth S. Mørck
 * Class able to get the numeric sum of a reference
 */
public final class Sum extends NullaryExpression {
	
	private final Reference reference; 
	private String description;
	
	/**
	 * Defines Sum as an arithmetic type and initializes the variables
	 * @param reference Assumed not null
	 */
	public Sum(final Reference reference) {
		super(ArithmeticType.instance);
		this.reference = reference;
		description = reference.getRangeDescription();
	}

	/**
	 * Overidden to make evaluation possible
	 * @return A numeric sum value for all expressions referenced
	 */
	@Override
	public int toInt() {
		int sum = 0;
		for (Expression exp : reference) {
				sum = sum + exp.toInt();
		}
		return sum;
	}
	
	/**
	 * Method for getting an easily understandable string 
	 * of this expression type
	 * @return Description of this expression type
	 */
	@Override
	public String getDescription() {
		return this.getClass().getSimpleName() + " " 
			   + description;
	}
}
