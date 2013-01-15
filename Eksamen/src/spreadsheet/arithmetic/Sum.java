package spreadsheet.arithmetic;

import spreadsheet.Expression;
import spreadsheet.Reference;
import spreadsheet.expression.NullaryExpression;

public final class Sum extends NullaryExpression {
	
	private final Reference reference; 
	
	public Sum(final Reference reference) {
		super(ArithmeticType.instance);
		this.reference = reference;
	}

	@Override
	public int toInt() {
		int sum = 0;
		for (Expression exp : reference) {
				sum = sum + exp.toInt();
				reference.iterator().next();
		}
		return sum;
	}
	
	@Override
	public String getDescription() {
		String ref = reference.getDescription();
		return this.getClass().getSimpleName() + " " 
			   + ref.substring(ref.indexOf('!')+1);
	}
}
