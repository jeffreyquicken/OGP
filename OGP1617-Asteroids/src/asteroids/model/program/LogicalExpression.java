package asteroids.model.program;

public class LogicalExpression extends Expression<Boolean> {

	public LogicalExpression (Logical type,Expression<Double> firstExpression, Expression<Double> secondExpression) {
		this.type = type;
		this.firstExpression = firstExpression;
		this.secondExpression = secondExpression;
		this.setValue(this.determineValue());
	}
	
	private Logical type;
	private Expression<Double> firstExpression;
	private Expression<Double> secondExpression;
	
	public boolean determineValue(){
		switch(type){
		case EQUAL:
			return firstExpression.getValue()==secondExpression.getValue();
		case GREATER:
			return firstExpression.getValue()>secondExpression.getValue();
		case SMALLER:
			return firstExpression.getValue()<secondExpression.getValue();
		case EQGREATER:
			return firstExpression.getValue()>=secondExpression.getValue();
		case EQSMALLER:
			return firstExpression.getValue()<=secondExpression.getValue();
		}
		throw new AssertionError();
	}

}
