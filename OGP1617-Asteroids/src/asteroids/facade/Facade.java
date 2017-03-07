package asteroids.facade;

import asteroids.model.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade {
	
	public Ship createShip()
			throws ModelException{
		try{return new Ship(0,0,0,0,0,0);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException{
		try{return new Ship(x,y,xVelocity,yVelocity,radius,orientation);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	public double[] getShipPosition(Ship ship){
		double [] positionArray = {ship.getPosX(),ship.getPosY()};
		return positionArray;
	}
	
	public double[] getShipVelocity(Ship ship){
		double[] velocityArray = {ship.getVelX(),ship.getVelY()};
		return velocityArray;
	}
	
	public double getShipRadius(Ship ship){
		return ship.getRadius();
	}
	
	public double getShipOrientation(Ship ship){
		return ship.getOrientation();
	}
	
	public void move(Ship ship, double dt){
		ship.move(dt);
	}
	
	public void thrust(Ship ship,double amount){
		ship.thrust(amount);
	}
	public void turn(Ship ship, double angle){
		ship.turn(angle);
	}
	public double getDistanceBetween(Ship ship1, Ship ship2){
		return ship1.getDistanceBetween(ship2);
	}
	public boolean overlap(Ship ship1, Ship ship2){
		return ship1.overlaps(ship2);
	}
	public double getTimeToCollision(Ship ship1, Ship ship2){
		return ship1.getTimeToCollision(ship2);
	}
	public double[] getCollisionPosition(Ship ship1, Ship ship2){
		return ship1.getCollisionPosition(ship2);
	}
}
