package asteroids.model;

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
	
	@Override
	public void setIndex(int i){
		super.setIndex(i);
		firstArgument.setIndex(i);
		secondArgument.setIndex(i);
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
