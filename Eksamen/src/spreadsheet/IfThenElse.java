package spreadsheet;

import spreadsheet.expression.NullaryExpression;

public final class IfThenElse extends NullaryExpression {

	private static final Type type = GenericType.instance;
	private Expression condition;
	private Expression ifTrue;
	private Expression ifFalse;
	
	public IfThenElse(final Expression condition, final Expression ifTrue,
			final Expression ifFalse) {
		super(type);
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}

	@Override
	public int toInt() {
		if (condition.toBoolean())
			return ifTrue.toInt();
		else 
			return ifFalse.toInt();
	}
	
	@Override
	public boolean toBoolean() {
		if (condition.toBoolean())
			return ifTrue.toBoolean();
		else 
			return ifFalse.toBoolean();
	}
	
	@Override
	public String toString() {
		if (condition.toBoolean())
			return ifTrue.toString();
		else 
			return ifFalse.toString();
	}
	
	@Override
	public String getDescription() {
		return "If " + condition.getDescription()
				+ " Then " + ifTrue.getDescription()
				+ " Else " + ifFalse.getDescription();
	}

	
}