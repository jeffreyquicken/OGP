package asteroids.model;

import asteroids.model.*;
public class PlanetExpression extends Expression<MinorPlanet> {
	public MinorPlanet getValue(){
		Ship user = this.getProgram().getUser();
		return user.getNearestPlanet();
	}
}
