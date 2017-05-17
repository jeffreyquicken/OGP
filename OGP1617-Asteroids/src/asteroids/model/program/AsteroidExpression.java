package asteroids.model.program;
import asteroids.model.*;
public class AsteroidExpression extends Expression<Asteroid> {
	@Override
	public Asteroid getValue(){
		Ship user = this.getProgram().getUser();
		//return (Asteroid)user.getNearestArray()[3][0];
		return user.getNearestAsteroid();
	}
}
