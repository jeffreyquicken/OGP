package asteroids.model;
import be.kuleuven.cs.som.taglet.*;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 * 
 * @invar The X-coordinate of the ship is a valid coordinate.
 * 		  |isValidPos(posX)
 * @invar The Y-coordinate of the ship is a valid coordinate.
 * 		  |isValidPos(posY)
 * @invar The total velocity of the ship does not exceed the speedLimit.
 * 		  |speedLimit>=(Math.sqrt(Math.pow(velX,2)+Math.pow(velY,2)))
 * @invar The radius of the ship is a valid radius.
 * 		  |isValidRadius(radius)
 * @invar The orientation of the ship is a valid orientation.
 * 		  |isValidOrientation(orientation)
 *
 */
public class Ship {
	
	/**
	 * Initializes a Ship with a given x-coordinate,y-coordinate,x-velocity,y-velocity,radius and orientation.
	 * 
	 * @param x
	 * 		  The initial X-coordinate of this ship.
	 * @param y
	 * 		  The initial Y-coordinate of this ship.
	 * @param xVelocity
	 * 		  The initial velocity in the X direction of this ship.
	 * @param yVelocity
	 * 		  The initial velocity in the Y direction of this ship.
	 * @param radius
	 * 		  The initial radius of this ship.
	 * @param orientation
	 * 		  The initial orientation of this ship.
	 * 
	 * @effect setPosX(x)
	 * @effect setPosY(y)
	 * @effect setVelX(xVelocity)
	 * @effect setVelY(yVelocity)
	 * @effect setRadius(radius)
	 * @effect setOrientation(orientation)
	 * 
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation) throws IllegalArgumentException{
		try{this.setPosX(x);}
		catch(IllegalArgumentException e){
			throw e;
		}
		try{this.setPosY(y);}
		catch(IllegalArgumentException d){
			throw d;
		}
		this.setVelX(xVelocity);
		this.setVelY(yVelocity);
		try{this.setRadius(radius);}
		catch(IllegalArgumentException f){
			throw f;
		}
		this.setOrientation(orientation);
	}
	
	private double posX;
	private double posY;
	
	/**
	 * Sets the X-coordinate of this ship to a given coordinate.
	 * 
	 * @param newPos
	 * 		   The new X position for the ship.
	 * @throws IllegalArgumentException
	 * 		   The given newPos is not a valid position.
	 * 		   |!isValidPos(newPos)
	 * @post   If the newPos is a valid position , posX will be set to newPos.
	 * 		   |new.getPosX() == newPos
	 */
	public void setPosX(double newPos) throws IllegalArgumentException{
		if(!isValidPos(newPos))
			throw new IllegalArgumentException();
		else
			posX = newPos;
	}
	
	/**
	 * Returns the X-coordinate of this ship.
	 * 
	 * @return 
	 * 		  The X position of this ship.
	 * 		  |result == this.posX
	 * 		
	 */
	public double getPosX(){
		return this.posX;
	}
	
	/**
	 * Sets the Y-coordinate of this ship to a given coordinate.
	 * 
	 * @param newPos
	 * 		   The new Y position for this ship.
	 * @throws IllegalArgumentException
	 * 		   The given newPos is not a valid position.
	 * 		   |!isValidPos(newPos)
	 * @post   If the newPos is a valid position , posY will be set to newPos.
	 * 		   |new.getPosY() == newPos
	 */
	public void setPosY(double newPos) throws IllegalArgumentException{
		if(!isValidPos(newPos))
			throw new IllegalArgumentException();
		else
			posY = newPos;
	}
	
	/**
	 * Returns the Y-coordinate of this ship.
	 * 
	 * @return 
	 * 		  The Y position of this ship.
	 * 		  |result == this.posY
	 * 	
	 */
	public double getPosY(){
		return this.posY;
	}
	
	/**
	 * Checks if a given position is a valid position.
	 * 
	 * @param pos 
	 * 		  The position to be validated
	 * @return 
	 * 		   Returns true if the position is greater then or equal to zero.
	 * 		   | result == (pos>=0)
	 */
	private static boolean isValidPos(double pos){
		return (pos>=0);
		}
	
	private double velX;
	private double velY;
	private double speedLimit = 300000;
	
	public double getVelX(){
		return this.velX;
	}
	
	public void setVelX(double newVel){
		double totalSpeed = Math.sqrt(Math.pow(this.getVelX(), 2) + Math.pow(this.getVelY(), 2));
		if(totalSpeed>speedLimit)
			this.velX = Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelY(), 2));
		else 
			this.velX = newVel;
	}
	
	public double getVelY(){
		return this.velY;
	}
	
	public void setVelY(double newVel){
		double totalSpeed = Math.sqrt(Math.pow(this.getVelX(), 2) + Math.pow(this.getVelY(), 2));
		if(totalSpeed>speedLimit)
			this.velY = Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelX(), 2));
		else 
			this.velY = newVel;
	}
	
	public double orientation;
	
	private static boolean isValidOrientation(double orient){
		return ((orient>=0) && (orient<=2*Math.PI));
	}
	
	public double getOrientation(){
		return this.orientation;
	}
	
	public void setOrientation(double newOrientation){
		assert isValidOrientation(newOrientation);
		this.orientation = newOrientation;
	}
	
	private double radius;
	private static double minRadius = 10;
	
	public double getRadius(){
		return this.radius;
	}
	
	private static boolean isValidRadius(double rad){
		return (rad>=minRadius);
	}
	
	public void setRadius(double newRadius) throws IllegalArgumentException {
		if(!isValidRadius(newRadius))
			throw new IllegalArgumentException();
		else
			this.radius = newRadius;
	}
	
	public void move(double time)throws IllegalArgumentException {
		if(time<0)
			throw new IllegalArgumentException();
		else
			this.setPosX(this.getPosX() + this.getVelX()*time);
			this.setPosY(this.getPosY() + this.getVelY()*time);
	}
	
	public void turn(double angle){
		assert isValidOrientation(this.getOrientation()+angle);
		this.setOrientation(this.getOrientation()+angle);
	}
	
	public void thrust(double acceleration){
		if(acceleration < 0)
			acceleration = 0;
		this.setVelX(this.getVelX()+acceleration*Math.cos(this.getOrientation()));
		this.setVelY(this.getVelY()+acceleration*Math.sin(this.getOrientation()));
	}
	
	/**
	 * Calculates the distance between this ship and the given ship.
	 * 
	 * @param ship 
	 * 		  The ship between which the distance must be calculated.
	 * @return 
	 * 		  Returns the distance in kilometers between the center of this ship and the center of the given ship.
	 * @throws NullPointerException
	 * 		   The ship doesn't exist.
	 * 		   |(ship == null)
	 */
	public double getDistanceBetween(Ship ship) throws NullPointerException{
		if(ship == null){
			throw new NullPointerException();
		}
		else 
			return Math.sqrt(Math.pow(this.getPosX()-ship.getPosX(),2)+Math.pow(this.getPosY()-ship.getPosY(), 2));
	}
	
	/**
	 * Checks if this ship overlaps with a given ship.
	 * 
	 * @param ship 
	 * 		  The ship of which must be checked if it overlaps with this ship.
	 * @return 
	 * 		  Returns true if the ship overlaps with the given ship or the given ship is equal to the given ship.
	 * @throws NullPointerException
	 * 		   The ship doesn't exist.
	 * 		   |(ship == null)
	 */
	public boolean overlaps (Ship ship) throws NullPointerException{
		if(ship == null){
			throw new NullPointerException();
		}
		else{
			try{return ((this.getDistanceBetween(ship)<=this.getRadius())||(this.getDistanceBetween(ship)<=ship.getRadius()));}
			catch (NullPointerException e){
				throw e;
			}
		}
	}
	
	/**
	 * Calculates the time when this ship and a given ship will collide.
	 * 
	 * @param ship
	 * 		  The ship of which the collision time with this ship is calculated.
	 * @return 
	 * 		   Returns the time in seconds when this ship and the given ship will collide.
	 * 		   Returns Double.POSITIVE_INFINITY if the ships will never collide.
	 * @throws NullPointerException
	 * 		   The ship doesn't exist.
	 * 		   |(ship == null)
	 * @throws IllegalArgumentException
	 * 		   The given ship overlaps with this ship.
	 * 		   |this.overlaps(ship)
	 */
	public double getTimeToCollision(Ship ship) throws NullPointerException, IllegalArgumentException{
		if(ship == null)
			throw new NullPointerException();
		else if(this.overlaps(ship))
			throw new IllegalArgumentException();
		double[] deltaV = {this.getVelX()-ship.getVelX(),this.getVelY()-ship.getVelY()};
		double[] deltaR = {this.getPosX()-ship.getPosX(),this.getPosY()-ship.getPosY()};
		double totalSigma = Math.pow(this.getRadius()+ship.getRadius(),2);
		double d = Math.pow(scalarProduct(deltaV,deltaR),2)-scalarProduct(deltaV,deltaV)*
				(scalarProduct(deltaR,deltaR)-totalSigma);
		if(scalarProduct(deltaV,deltaR)>=0)
			return Double.POSITIVE_INFINITY;
		else if(d<=0)
			return Double.POSITIVE_INFINITY;
		else
			return -(scalarProduct(deltaV,deltaR)+Math.sqrt(d))/scalarProduct(deltaV,deltaV);
	}
	
	/**
	 * Calculates the scalar product of two 2-dimensional vectors.
	 * 
	 * @param vect1 
	 * 		  The first 2-dimensional vector
	 * @param vect2 
	 * 		  The second 2-dimensional vector
	 * @return 
	 * 		  Returns the scalar product of the 2-dimensional vectors.
	 */
	private double scalarProduct(double[] vect1, double[] vect2){
		return vect1[0]*vect2[0] + vect1[1]*vect2[1];
	}
	
	/**
	 * Calculates the coordinates (an array) where this ship and the given ship will collide.
	 * 
	 * @param ship
	 * 		   The ship of which the collision coordinates with this ship are calculated.
	 * @return 
	 * 		   Returns an array of the X and Y coordinate where this ship and the given ship will collide.
	 * 		   Returns null if the ships will never collide.
	 * @throws NullPointerException
	 *  	   The ship doesn't exist.
	 * 		   |(ship == null)
	 * @throws IllegalArgumentException
	 * 		   The given ship overlaps with this ship.
	 * 		   |this.overlaps(ship)
	 */
	public double[] getCollisionPosition(Ship ship) throws NullPointerException, IllegalArgumentException{
		if(ship == null)
			throw new NullPointerException();
		else if(this.overlaps(ship))
			throw new IllegalArgumentException();
		double deltaT = this.getTimeToCollision(ship);
		if(deltaT == Double.POSITIVE_INFINITY)
			return null;
		else{
			double[] collisionPosition = {this.getPosX()+deltaT*this.getVelX(),this.getPosY()+deltaT*this.getVelY()};
			return collisionPosition;
		}
	}
	


}

