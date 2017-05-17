package asteroids.model.program;

public class ReturnStatement extends Statement {
	public ReturnStatement(Expression<?> returnExpression){
		this.returnExpression = returnExpression;
	}
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		returnExpression.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		returnExpression.setProgram(newProgram);
	}
	
	private Expression<?> returnExpression;
	
	public void evaluate(double time) throws ReturnedException, NotEnoughTimeException{
		if(this.getFunction() == null)
			throw new AssertionError();
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		throw new ReturnedException(returnExpression.getValue());
	}
}