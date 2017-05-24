package asteroids.model;

import asteroids.model.*;

public class SelfExpression extends Expression<Ship> {
	@Override
	public Ship getValue(){
		return this.getProgram().getUser();
	}
}
