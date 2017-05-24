package asteroids.model;

public class NotExpression extends Expression<Boolean>{
	public NotExpression(Expression<?> expression){
		this.expression = expression;
	}
	
	private Expression<?> expression;
	
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
	public Boolean getValue(){
		Object value = this.expression.getValue();
		if(value instanceof Boolean)
			return !(Boolean)value;
		else
			throw new IllegalArgumentException();
	}
}
