package spreadsheet;

import spreadsheet.expression.NullaryExpression;

/**
 * @author Kenneth S. Mørck
 * A ternary expression type that, 
 * depending on the condition, 
 * returns one of two Expressions
 */
public final class IfThenElse extends NullaryExpression {

	//Variables saved to be evaluated
	private Expression condition;
	private Expression ifTrue;
	private Expression ifFalse;
	
	/**
	 * Defines IfThenElse as a GenericType and initiates the variables above  
	 * @param condition Assumed not null also determines whether
	 * 					ifTrue or ifFalse will be returned when evaluated
	 * @param ifTrue Assumed not null
	 * @param ifFalse Assumed not null
	 */
	public IfThenElse(final Expression condition, final Expression ifTrue,
			final Expression ifFalse) {
		super(GenericType.instance);
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	/**
	 * Evaluation method for Arithmetic Expressions
	 * @return If condition is true returns ifTrue else ifFalse
	 */
	@Override
	public int toInt() {
		if (condition.toBoolean())
			return ifTrue.toInt();
		else 
			return ifFalse.toInt();
	}
	
	/**
	 * Evaluation method for Logical Expressions
	 * @return If condition is true returns ifTrue else ifFalse
	 */
	@Override
	public boolean toBoolean() {
		if (condition.toBoolean())
			return ifTrue.toBoolean();
		else 
			return ifFalse.toBoolean();
	}
	
	/**
	 * Evaluation method for Textual Expressions
	 * @return If condition is true returns ifTrue else ifFalse
	 */
	@Override
	public String toString() {
		if (condition.toBoolean())
			return ifTrue.toString();
		else 
			return ifFalse.toString();
	}
	
	/**
	 * A method for getting an easily understandable string 
	 * of this expression type
	 * @return Description of this expression type
	 */
	@Override
	public String getDescription() {
		return "If " + condition.getDescription()
				+ " Then " + ifTrue.getDescription()
				+ " Else " + ifFalse.getDescription();
	}

	
}