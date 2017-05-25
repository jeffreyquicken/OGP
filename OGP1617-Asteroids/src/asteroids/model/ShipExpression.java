package asteroids.model;
import asteroids.model.*;

public class ShipExpression extends Expression<Ship> {
	@Override
	public Ship getValue(){
		Ship user = this.getProgram().getUser();
		return user.getNearestShip();
	}
}
