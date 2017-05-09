package asteroids.model.program;

public class MathematicalExpression extends Expression<Double> {
	
	public MathematicalExpression(Mathematical type, Expression<Double> firstArgument, Expression<Double>secondArgument){
		this.type = type;
		this.firstArgument = firstArgument;
		this.secondArgument = secondArgument;
		this.setValue(this.determineValue());
	}
	
	
	private double value;
	private Mathematical type;
	private Expression<Double> firstArgument;
	private Expression<Double> secondArgument;
	
	public double determineValue(){
		switch (type){
			case MULTIPLICATION:
				return firstArgument.getValue()*secondArgument.getValue();
			case SUSTRACTION:
				return firstArgument.getValue()-secondArgument.getValue();
			case ADDITION:
				return firstArgument.getValue()+secondArgument.getValue();
			case SQUAREROOT:
				return Math.sqrt(firstArgument.getValue());
		}
		throw new AssertionError();
	}
}
