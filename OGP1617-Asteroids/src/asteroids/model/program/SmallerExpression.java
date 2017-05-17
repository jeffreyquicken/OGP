package asteroids.model.program;

public class SmallerExpression extends Expression<Boolean> {
	public SmallerExpression(Expression<?> e1, Expression<?> e2){
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
		if(firstExpression.getValue() instanceof Double && secondExpression.getValue() instanceof Double)
			return (Double)firstExpression.getValue()<(Double)secondExpression.getValue();
		else
			throw new IllegalArgumentException();
	}
}
