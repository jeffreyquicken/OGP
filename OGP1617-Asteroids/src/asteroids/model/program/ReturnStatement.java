package asteroids.model.program;

public class ReturnStatement extends Statement {
	public ReturnStatement(Expression<?> returnExpression){
		super();
		this.returnExpression = returnExpression;
	}
	
	private Expression<?> returnExpression;
	public Expression<?> execute(){
		return returnExpression;
	}
}
