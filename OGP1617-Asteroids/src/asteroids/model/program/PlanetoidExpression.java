package asteroids.model.program;

import asteroids.model.*;
public class PlanetoidExpression extends Expression<Planetoid> {
	public Planetoid getValue(){
		Ship user = this.getProgram().getUser();
		return (Planetoid)user.getNearestArray()[2][0];
	}
}
