package asteroids.model.program;

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
	public Boolean getValue(){
		if(this.expression.getValue() instanceof Boolean)
			return !(Boolean)this.expression.getValue();
		else
			throw new IllegalArgumentException();
	}
}
