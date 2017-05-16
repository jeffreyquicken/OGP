package asteroids.model.program;

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
	public Double getValue(){
		if(expression.getValue() instanceof Double){
			switch(this.type){
			case SQRT: return Math.sqrt(((Expression<Double>)this.expression).getValue());
			case NEGATE: return -((Expression<Double>)this.expression).getValue();
				}
			throw new AssertionError();
		}
		else
			throw new IllegalArgumentException();
	}
}
