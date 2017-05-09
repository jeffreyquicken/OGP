package asteroids.model.program;

public class WhileStatement extends Statement {
	
	public WhileStatement(Expression<Boolean> checkExpression,Statement body){
		this.checkExpression = checkExpression;
		this.body = body;
	}
	
	private Expression<Boolean> checkExpression;
	private Statement body;
	
	public boolean evaluate(){
		while(checkExpression.getValue() == true){
			if(!body.evaluate())
				break;
		}
		return true;
	}

}
