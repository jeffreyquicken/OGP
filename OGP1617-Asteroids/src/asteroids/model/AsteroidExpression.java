package asteroids.model;
import asteroids.model.*;
public class AsteroidExpression extends Expression<Asteroid> {
	@Override
	public Asteroid getValue(){
		Ship user = this.getProgram().getUser();
		return user.getNearestAsteroid();
	}
}
