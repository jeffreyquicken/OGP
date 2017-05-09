package asteroids.model.program;
import asteroids.model.*;

public class GetExpression extends Expression<Double> {

	public GetExpression(Getter getter) {
		this.getter = getter;
		this.user = this.getProgram().getUser();
		this.setValue(this.determineValue());
	}
	
	private Getter getter;
	private Ship user;
	
	public double determineValue(){
		switch(this.getter){
		case GETX:
			return this.user.getPosX();
		case GETY:
			return this.user.getPosY();
		case GETVX:
			return this.user.getVelX();
		case GETVY:
			return this.user.getVelY();
		case GETDIRECTION:
			return this.user.getOrientation();
		case GETRADIUS:
			return this.user.getRadius();
		}
		throw new AssertionError();
	}
	
	
}
