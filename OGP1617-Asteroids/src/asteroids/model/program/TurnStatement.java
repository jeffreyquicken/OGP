package asteroids.model.program;
import asteroids.model.*;

public class TurnStatement extends Statement {
	public TurnStatement(Expression<?> turnExpression) {
		this.turnExpression = turnExpression;
	}

	private Expression<?> turnExpression;
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		this.turnExpression.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		turnExpression.setProgram(newProgram);
	}
	
	public void evaluate(double time)throws NotEnoughTimeException,UnsupportedOperationException{
		if(turnExpression.getValue() instanceof Double){
			if(this.getFunction() != null)
				throw new UnsupportedOperationException();
			if(time<0.2)
				throw new NotEnoughTimeException(time);
			this.getProgram().getUser().turn(((Expression<Double>)this.turnExpression).getValue());
			}
		else
			throw new IllegalArgumentException();
		}
}
