package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade {
	
	/**
	 * @return new Ship()
	 * 		  Returns a new default ship.
	 * 		  |result == new Ship()
	 */
	public Ship createShip()
			throws ModelException{
		try{return new Ship();}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @return new Ship(x,y,xVelocity,yVelocity,radius,orientation)
	 * 		  Returns a ship with a specified position,velocity,radius and orientation.
	 * 		  |result == new Ship(x,y,xVelocity,yVelocity,radius,orientation)
	 */
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException{
		try{return new Ship(x,y,xVelocity,yVelocity,radius,orientation);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @param ship
	 * 		  The ship of which the coordinates are returned.
	 * @return positionArray
	 * 		  Returns a position array of the x and y coordinates of the ship
	 * 		  |result == {ship.getPosX(),ship.getPosY()}
	 */
	public double[] getShipPosition(Ship ship){
		double [] positionArray = {ship.getPosX(),ship.getPosY()};
		return positionArray;
	}
	
	/**
	 * @param ship
	 * 		  The ship of which the velocities are returned.
	 * @return velocityArray
	 * 		  Returns a velocity array of the x and y velocities of the ship
	 * 		  |result == {ship.getVelX(),ship.getVelY()}
	 * 			
	 */
	public double[] getShipVelocity(Ship ship){
		double[] velocityArray = {ship.getVelX(),ship.getVelY()};
		return velocityArray;
	}
	
	/**
	 * @param ship
	 * 		  The ship of which the radius is returned.
	 * @return ship.getRadius()
	 * 		  Returns the radius of the ship in kilometers.
	 * 		  |result == ship.getRadius()
	 */
	public double getShipRadius(Ship ship){
		return ship.getRadius();
	}
	
	/**
	 * @param ship
	 * 		  The ship of which the orientation is returned.
	 * @return ship.getOrientation()
	 * 		  Returns the orientation of the ship in radians.
	 * 		  |result == ship.getOrientation()
	 */
	public double getShipOrientation(Ship ship){
		return ship.getOrientation();
	}
	
	/**
	 * @param ship
	 * 		  The ship that has to move.
	 * @param dt
	 * 		  The amount of time the ship is moving.
	 * @effect ship.move(dt)
	 * 		   Moves the ship around during a specified amount of time.
	 */
	public void move(Ship ship, double dt) throws ModelException{
		try{ship.move(dt);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @param ship
	 * 		  The ship that has to be propelled forward.
	 * @param amount
	 * 		  The amount of acceleration of the ship.
	 * @effect ship.thrust(amount)
	 * 		  Thrusts the ship forward with a specified amount.
	 * 		 
	 */
	public void thrust(Ship ship,double amount){
		ship.thrust(amount);
	}
	
	/**
	 * @param ship
	 * 		  The ship that has to turn.
	 * @param angle
	 * 		  The amount of radians to be added to the current orientation of the ship.
	 * @effect ship.turn(angle)
	 * 		  Turns the ship with a specified angle.
	 */
	public void turn(Ship ship, double angle){
		ship.turn(angle);
	}
	
	/**
	 * @param ship1
	 * 		  The first ship of which the distance between the second ship is returned
	 * @param ship2
	 * 		  The second ship of which the distance between the first ship is returned
	 * @return ship1.getDistanceBetween(ship2)
	 * 		  Returns the distance in kilometers between ship1 and ship2.
	 * 		  |result == ship1.getDistanceBetween(ship2)
	 */
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException{
		try{return ship1.getDistanceBetween(ship2);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @param ship1
	 * 		  The first ship of which is checked if it overlaps with the second ship.
	 * @param ship2
	 * 		  The second ship of which is checked if it overlaps with the first ship.
	 * @return ship1.overlaps(ship2)
	 * 		  Returns true if the two ships overlap, returns false otherwise.
	 * 		  |result == ship1.overlaps(ship2)
	 */
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException{
		try{return ship1.overlaps(ship2);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @param ship1
	 * 		  The first ship of which the collision time with the second ship is returned.
	 * @param ship2
	 * 		  The second ship of which the collision time with the first ship is returned.
	 * @return ship1.getTimeToCollision(ship2)
	 * 		   Returns the time in seconds when the two ships will collide.
	 * 		   Returns Double.POSITIVE_INFINITY if they will never collide.
	 * 		   |result == ship1.getTimeToCollision(ship2)
	 */
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException{
		try{return ship1.getTimeToCollision(ship2);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	/**
	 * @param ship1
	 * 		  The first ship of which the collision coordinates with the second ship are returned.
	 * @param ship2
	 * 		  The second ship of which the collision coordinates with the first ship are returned.
	 * @return ship1.getCollisionPosition(ship2)
	 * 		   Returns the position array where the ships will collide.
	 * 		   Returns null if they will never collide.
	 * 		   |result == ship1.getCollisionPosition(ship2)
	 */
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException{
		try{return ship1.getCollisionPosition(ship2);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
}
