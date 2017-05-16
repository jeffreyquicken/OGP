package asteroids.model.program;
import asteroids.model.*;

public class ActionStatement extends Statement {
	public ActionStatement(Action action) {
		this.action = action;
	}
	
	private Action action;
	
	public void evaluate(double time) throws NotEnoughTimeException{
		if(this.getFunction() != null)
			throw new AssertionError();
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		switch(action){
		case FIRE:
			this.getProgram().getUser().fireBullet();
			break;
		case THRUST_ON:
			this.getProgram().getUser().setThruster(true);
			break;
		case THRUST_OFF:
			this.getProgram().getUser().setThruster(false);
			break;
		case SKIP:
			break;
		}
	}

}
