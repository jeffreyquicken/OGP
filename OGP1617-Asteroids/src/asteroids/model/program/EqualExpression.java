package asteroids.model.program;

public class EqualExpression extends Expression<Boolean> {
	public EqualExpression(Expression<?> e1, Expression<?> e2){
		this.firstExpression=e1;
		this.secondExpression = e2;
	}
	
	private Expression<?> firstExpression;
	private Expression<?> secondExpression;
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		firstExpression.setFunction(newFunction);
		secondExpression.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		firstExpression.setProgram(newProgram);
		secondExpression.setProgram(newProgram);
	}
	
	@Override
	public Boolean getValue(){
		firstExpression.setFunction(this.getFunction());
		secondExpression.setFunction(this.getFunction());
		firstExpression.setProgram(this.getProgram());
		secondExpression.setProgram(this.getProgram());
		if(firstExpression.getClass().equals(secondExpression.getClass()) 
				|| (firstExpression.getValue() == null && secondExpression.getValue() == null))
			return firstExpression.getValue() == (secondExpression.getValue());
		else
			return false;
	}
}
