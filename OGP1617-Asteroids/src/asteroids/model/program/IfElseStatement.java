package asteroids.model.program;

public class IfElseStatement extends Statement {
	public IfElseStatement(Expression<Boolean> condition, Statement ifStatement, Statement elseStatement){
		super();
		this.condition = condition;
		this.ifStatement = ifStatement;
		this.elseStatement =elseStatement;
	}
	
	private Expression<Boolean> condition;
	private Statement ifStatement;
	private Statement elseStatement;
	
	public boolean evaluate(){
		if(condition.getValue() == true){
			ifStatement.evaluate();
			return true;
		}
		else{
			elseStatement.evaluate();
			return true;
		}
	}
}
