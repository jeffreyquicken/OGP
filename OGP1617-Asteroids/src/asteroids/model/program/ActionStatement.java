package asteroids.model.program;
import asteroids.model.*;

public class ActionStatement extends Statement {
	public ActionStatement(Action action) {
		this.action = action;
		this.user=this.getProgram().getUser();
	}
	
	private Ship user;
	private Action action;
	
	public boolean evaluate(){
		switch(action){
		case FIRE:
			user.fireBullet();
			return true;
		case THRUST_ON:
			user.setThruster(true);
			return true;
		case THRUST_OFF:
			user.setThruster(false);
			return true;
		case SKIP:
			return true;
		}
		return true;
	}

}
