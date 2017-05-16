package asteroids.model.program;

public class MathematicalExpression extends Expression<Double> {
	
	public MathematicalExpression(Mathematical type, Expression<?> firstArgument, Expression<?>secondArgument){
		this.type = type;
		this.firstArgument = firstArgument;
		this.secondArgument = secondArgument;
	}
	
	
	private Mathematical type;
	private Expression<?> firstArgument;
	private Expression<?> secondArgument;
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		firstArgument.setFunction(newFunction);
		secondArgument.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		firstArgument.setProgram(newProgram);
		secondArgument.setProgram(newProgram);
	}
	
	public Double getValue(){
		if(firstArgument.getValue() instanceof Double && secondArgument.getValue() instanceof Double){
		switch (type){
			case MULTIPLICATION:
				return ((Expression<Double>)firstArgument).getValue()*((Expression<Double>)secondArgument).getValue();
			case SUSTRACTION:
				return ((Expression<Double>)firstArgument).getValue()-((Expression<Double>)secondArgument).getValue();
			case ADDITION:
				return ((Expression<Double>)firstArgument).getValue()+((Expression<Double>)secondArgument).getValue();
		}
		throw new AssertionError();
		}
		else
			throw new IllegalArgumentException();
	}
}
