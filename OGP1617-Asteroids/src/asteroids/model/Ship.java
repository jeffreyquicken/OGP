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
public class Ship extends Circle {
	
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
	 * 
	 * @effect setPosX(x)
	 * 		   Sets the X-coordinates of this ship to x. It may also throw an IllegalExceptionError if the position is not a
	 * 		   valid position.
	 * @effect setPosY(y)
	 * 		   Sets the Y-coordinates of this ship to y. It may also throw an IllegalExceptionError if the position is not a
	 * 		   valid position.
	 * @effect setVelX(xVelocity)
	 * 		   Sets the velocity in the X direction of this ship to xVelocity.
	 * @effect setVelY(yVelocity)
	 * 		   Sets the velocity in the Y direction of this ship to yVelocity.
	 * @effect setRadius(radius)
	 * 		   Sets the radius of this ship to radius. It may also throw an IllegalExceptionError if the radius is not a
	 * 		   valid radius.
	 * @throws IllegalArgumentException
	 * 		   Throws IllegalArgumentException if the x coordinate,y coordinate or the radius are not valid.
	 * 		   |((!isValidPos(x)) || (!isValidPos(y)) || (!isValidRadius(radius)))
	 * 
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius,double orientation){
		super(x,y,xVelocity,yVelocity,radius,minRadius);
		this.setOrientation(orientation);
	}
	
	public double orientation;
	
	/**
	 * Checks if a given orientation is a valid orientation.
	 * 
	 * @param orient
	 * 		The orientation of which is checked if it is a valid orientation.
	 * @return
	 * 		Returns true if the orientation is greater or equal then zero and
	 * 		smaller or equal then 2*PI.
	 * 		|result == ((orient>=0) && (orient<=2*Math.PI))
	 */
	private static boolean isValidOrientation(double orient){
		return ((orient>=0) && (orient<=2*Math.PI));
	}
	
	/**
	 * Returns the orientation of this ship.
	 * 
	 * @return
	 * 		  Returns the orientation of this ship.
	 * 		  |result == this.orientation
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;
	}
	
	/**
	 * Sets the orientation of this ship to a new given orientation.
	 * 
	 * @param newOrientation
	 * 		The new orientation of this ship.
	 * @pre 
	 * 		The orientation is a valid orientation.
	 * 		|isValidOrientation(newOrientation)
	 * @post
	 * 		The new orientation of this ship is equal to the given orientation.
	 * 		|new.getOrientation() == newOrientation
	 */
	@Basic
	public void setOrientation(double newOrientation){
		assert isValidOrientation(newOrientation);
		this.orientation = newOrientation;
	}
	
	private static double minRadius = 10;
	
	private double mass;
	private static double minDensity = 1.42E12;
	
	public void setMass(double newMass){
		if(newMass<(4/3)*Math.PI*Math.pow(this.getRadius(),3)*minDensity)
			this.mass = (4/3)*Math.PI*Math.pow(this.getRadius(),3)*minDensity;
		else
			this.mass = newMass;
	}
	
	public double getMass(){
		return this.mass;
	}
	
	private World world;
	
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Increases the orientation of this ship with a given angle.
	 * 
	 * @param angle
	 * 		  	   The angle to be added to the current angle of this ship.
	 * @pre 
	 * 		The sum of the current orientation and the added angle is a valid orientation.
	 * 		|isValidOrientation(this.getOrientation()+angle)
	 *@post 
	 *		The orientation of this ship is set to the sum of the current orientation and the added angle.
	 *		|new.getOrientation() == (this.getOrientation() + angle)
	 */
	public void turn(double angle){
		assert isValidOrientation(this.getOrientation()+angle);
		this.setOrientation(this.getOrientation()+angle);
	}
	
	/**
	 * Thrusts this ship forward with a given acceleration.
	 * @param acceleration
	 * 		  The amount of acceleration of this ship.
	 * @post If the acceleration is a negative number, the acceleration is set to zero.
	 * 		 Then the X-velocity and Y-velocity of this ship is increased with a given amount of acceleration.
	 * 		 |if(acceleration<0) then acceleration = 0
	 * @effect this.setVelX(this.getVelX()+acceleration*Math.cos(this.getOrientation()))
	 * @effect this.setVelY(this.getVelY()+acceleration*Math.sin(this.getOrientation()))
	 */
	public void thrust(double amount){
		if(amount < 0)
			amount = 0;
		this.setVel(this.getVelX()+amount*Math.cos(this.getOrientation()), this.getVelY()+amount*Math.sin(this.getOrientation()));
	}
	


}

