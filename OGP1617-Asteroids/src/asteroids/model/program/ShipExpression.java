package asteroids.model.program;
import asteroids.model.*;

public class ShipExpression extends Expression<Ship> {
	@Override
	public Ship getValue(){
		Ship user = this.getProgram().getUser();
		return (Ship)user.getNearestArray()[0][0];
	}
}
