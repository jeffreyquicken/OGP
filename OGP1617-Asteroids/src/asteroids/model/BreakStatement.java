package asteroids.model;

public class BreakStatement extends Statement {
	
	@Override
	public void evaluate(double time) throws BreakException,NotEnoughTimeException{
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		throw new BreakException();
	}
}