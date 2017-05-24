package asteroids.model;

public class SingleMathematicalExpression extends Expression<Double> {
	public SingleMathematicalExpression(SingleMathematical type, Expression<?> expression){
		this.type = type;
		this.expression = expression;
	}
	
	private Expression<?> expression;
	private SingleMathematical type;
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		expression.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		expression.setProgram(newProgram);
	}
	
	@Override
	public void setIndex(int i){
		super.setIndex(i);
		expression.setIndex(i);
	}
	
	@Override
	public Double getValue(){
		Object value = expression.getValue();
		if(value instanceof Double){
			switch(this.type){
			case SQRT: return Math.sqrt((Double)value);
			case NEGATE: return -(Double)value;
				}
			throw new AssertionError();
		}
		else
			throw new IllegalArgumentException();
	}
}
