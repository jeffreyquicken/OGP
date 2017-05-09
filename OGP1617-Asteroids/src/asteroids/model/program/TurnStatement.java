package asteroids.model.program;
import asteroids.model.*;

public class TurnStatement extends Statement {
	public TurnStatement(Expression<Double> turnExpression) {
		super();
		this.user=this.getProgram().getUser();
		this.turnExpression = turnExpression;
	}
	
	private Expression<Double> turnExpression;
	private Ship user;
	
	public boolean evaluate(){
		this.user.turn(this.turnExpression.getValue());
		return true;
	}

}
