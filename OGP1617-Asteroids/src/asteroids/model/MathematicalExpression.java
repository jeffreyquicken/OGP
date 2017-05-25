package asteroids.model;

public class MathematicalExpression extends Expression<Double> {
	
	public MathematicalExpression(Mathematical type, Expression<?> firstArgument, Expression<?>secondArgument){
		this.type = type;
		this.firstArgument = firstArgument;
		this.secondArgument = secondArgument;
	}
	
	
	private final Mathematical type;
	private final Expression<?> firstArgument;
	private final Expression<?> secondArgument;
	
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
		Object value1 = firstArgument.getValue();
		Object value2 = secondArgument.getValue();
		if(value1 instanceof Double && value2 instanceof Double){
		switch (type){
			case MULTIPLICATION:
				return (Double)value1*(Double)value2;
			case SUSTRACTION:
				return (Double)value1-(Double)value2;
			case ADDITION:
				return (Double)value1+(Double)value2;
		}
		throw new AssertionError();
		}
		else
			throw new IllegalArgumentException();
	}
}
