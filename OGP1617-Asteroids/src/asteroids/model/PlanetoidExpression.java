package asteroids.model;

import asteroids.model.*;
public class PlanetoidExpression extends Expression<Planetoid> {
	public Planetoid getValue(){
		Ship user = this.getProgram().getUser();
		return user.getNearestPlanetoid();
	}
}
