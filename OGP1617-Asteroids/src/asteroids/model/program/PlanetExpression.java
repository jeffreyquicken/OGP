package asteroids.model.program;

import asteroids.model.*;
public class PlanetExpression extends Expression<MinorPlanet> {
	public MinorPlanet getValue(){
		Ship user = this.getProgram().getUser();
		if((double)user.getNearestArray()[2][1]<(double)user.getNearestArray()[3][1])
			return (MinorPlanet)user.getNearestArray()[2][0];
		else
			return (MinorPlanet)user.getNearestArray()[3][0];
	}
}
