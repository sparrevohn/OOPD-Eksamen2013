package spreadsheet.logical;

import spreadsheet.Expression;
import spreadsheet.expression.NullaryExpression;

public final class IfThenElse extends NullaryExpression {

	private Expression condition;
	private Expression ifTrue;
	private Expression ifFalse;

	public IfThenElse(final Expression condition, 
						 final Expression ifTrue,
						 final Expression ifFalse) {
		super(LogicalType.instance);
		this.condition = condition;
		this.ifTrue = ifTrue;
		this.ifFalse = ifFalse;
	}
	
	@Override
	public boolean toBoolean() {
		return condition.toBoolean();
	}

	@Override
	public String getDescription() {
		return "If " + condition.getDescription()
				+ " Then " + ifTrue.getDescription()
				+ " Else " + ifFalse.getDescription();
	}
}