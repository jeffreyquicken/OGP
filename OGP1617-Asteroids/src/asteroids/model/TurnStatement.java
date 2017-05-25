package asteroids.model;
import asteroids.model.*;

public class TurnStatement extends Statement {
	public TurnStatement(Expression<?> turnExpression) {
		this.turnExpression = turnExpression;
	}

	private final Expression<?> turnExpression;
	
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
		Object value = turnExpression.getValue();
		if(value instanceof Double){
			if(this.getFunction() != null)
				throw new AssertionError();
			if(time<0.2)
				throw new NotEnoughTimeException(time);
			this.getProgram().getUser().turn((Double)value);
			}
		else
			throw new IllegalArgumentException();
		}
}
