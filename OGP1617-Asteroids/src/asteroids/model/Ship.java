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
	 * @effect setOrientation(orientation)
	 * 		   Sets the orientation of this ship to orientation.
	 * @throws IllegalArgumentException
	 * 		   Throws IllegalArgumentException if the x coordinate,y coordinate or the radius are not valid.
	 * 		   |((!isValidPos(x)) || (!isValidPos(y)) || (!isValidRadius(radius)))
	 * 
	 */
	@Raw
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
	
	/**
	 * Initializes a default Ship with a default position, velocity, radius and orientation.
	 * 
	 * @effect Ship(0,0,0,0,minRadius,0)
	 * 		   Creates a new ship with x-coordinate 0 , y-coordinate 0, x-velocity 0 , y-velocity 0, radius minRadius, orientation 0.
	 * 
	 */
	@Raw
	public Ship() throws IllegalArgumentException{
		try{new Ship(0,0,0,0,minRadius,0);}
		catch(IllegalArgumentException e){
			throw e;
		}
		
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
	@Basic
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
	@Basic
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
	@Basic
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
	 */
	@Basic
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
	 * 		   | result == (pos!= Double.NaN)
	 */
	private static boolean isValidPos(double pos){
		return ((pos<=0) || (pos>=0)) ;
		}
	
	private double velX;
	private double velY;
	private double speedLimit = 300000;
	
	/**
	 * Returns the velocity in the X direction of this ship.
	 * 
	 * @return
	 * 		  Returns the velocity in the X direction of this ship.
	 * 		  |result == this.velX
	 */
	@Basic
	public double getVelX(){
		return this.velX;
	}
	
	/**
	 * Sets the velocity in the X direction of this ship to a given velocity.
	 * 
	 * @param newVel
	 * 		  The new velocity of this ship.
	 * @post
	 * 		If the total new speed does not exceed the speed limit, 
	 * 		the speed in the velocity in the X direction is set to newVel.
	 * 		Otherwise, the velocity in the X direction is set in such a way that the
	 * 		total new speed is equal to the speed limit.
	 * 		|if((Math.sqrt(Math.pow(newVel,2) + Math.pow(this.getVelY(),2)))>speedLimit) then
	 * 		| new.getVelX() == Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelY(), 2))
	 * 		|else then new.getVelX() == newVel 
	 */
	@Basic
	public void setVelX(double newVel){
		double totalSpeed = Math.sqrt(Math.pow(newVel, 2) + Math.pow(this.getVelY(), 2));
		if(totalSpeed>speedLimit)
			this.velX = Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelY(), 2));
		else 
			this.velX = newVel;
	}
	
	/**
	 * Returns the velocity in the Y direction of this ship.
	 * 
	 * @return
	 * 		  Returns the velocity in the Y direction of this ship.
	 * 		  |result == this.velY
	 */
	@Basic
	public double getVelY(){
		return this.velY;
	}
	
	/**
	 * Sets the velocity in the Y direction of this ship to a given velocity.
	 * 
	 * @param newVel
	 * 		  The new velocity of this ship.
	 * @post
	 * 		If the total new speed does not exceed the speed limit, 
	 * 		the speed in the velocity in the Y direction is set to newVel.
	 * 		Otherwise, the velocity in the Y direction is set in such a way that the
	 * 		total new speed is equal to the speed limit.
	 * 		|if((Math.sqrt(Math.pow(newVel,2) + Math.pow(this.getVelX(),2)))>speedLimit) then
	 * 		| new.getVelY() == Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelX(), 2))
	 * 		|else then new.getVelY() == newVel 
	 */
	@Basic
	public void setVelY(double newVel){
		double totalSpeed = Math.sqrt(Math.pow(this.getVelX(), 2) + Math.pow(newVel, 2));
		if(totalSpeed>speedLimit)
			this.velY = Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelX(), 2));
		else 
			this.velY = newVel;
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
	
	private double radius;
	private static double minRadius = 10;
	
	/**
	 * Returns the radius of this ship.
	 * 
	 * @return
	 * 		  Returns the radius of this ship.
	 * 		  |result == this.radius
	 */
	@Basic
	public double getRadius(){
		return this.radius;
	}
	
	/**
	 * Checks if a given radius is a valid radius.
	 * 
	 * @param rad
	 * 		The radius of which is checked if it is a valid radius.
	 * @return
	 * 		Returns true if the radius is greater or equal then the minimal radius.
	 * 		|result == (rad>=minRadius)
	 */
	private static boolean isValidRadius(double rad){
		return (rad>=minRadius);
	}
	
	/**
	 * Sets the radius of this ship to a given radius.
	 * 
	 * @param newRadius
	 * 		   The new radius for the ship.
	 * @throws IllegalArgumentException
	 * 		   The given newRadius is not a valid radius.
	 * 		   |!isValidRadius(newRadius)
	 * @post   If the newRadius is a valid radius , radius will be set to newRadius.
	 * 		   |new.getRadius() == newRadius
	 */
	@Basic
	public void setRadius(double newRadius) throws IllegalArgumentException {
		if(!isValidRadius(newRadius))
			throw new IllegalArgumentException();
		else
			this.radius = newRadius;
	}
	
	/**
	 * Moves this ship around for a given amount of time.
	 * 
	 * @param time
	 * 		  The amount of time this ship is moving.
	 * @throws IllegalArgumentException
	 * 		   The time is a negative number.
	 * 		   |(time<0)
	 * @effect this.setPosX(this.getPosX() + this.getVelX()*time)
	 * 		   Sets the X position to sum of the current x position and the added distance in the x direction. 
	 * @effect this.setPosY(this.getPosY() + this.getVelY()*time)
	 * 		   Sets the Y position to sum of the current y position and the added distance in the y direction.
	 */
	public void move(double time)throws IllegalArgumentException {
		if(time<0)
			throw new IllegalArgumentException();
		else
			try{this.setPosX(this.getPosX() + this.getVelX()*time);}
			catch(IllegalArgumentException e){
				throw e;
			}
			try{this.setPosY(this.getPosY() + this.getVelY()*time);}
			catch(IllegalArgumentException d){
				throw d;
			}
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
	 * 		  |result == Math.sqrt(Math.pow(this.getPosX()-ship.getPosX(),2)+
	 * 		  |			 Math.pow(this.getPosY()-ship.getPosY(), 2))-this.getRadius()-ship.getRadius()
	 * @throws NullPointerException
	 * 		   The ship doesn't exist.
	 * 		   |(ship == null)
	 */
	public double getDistanceBetween(Ship ship) throws NullPointerException{
		if(ship == null){
			throw new NullPointerException();
		}
		else 
			return (Math.sqrt(Math.pow(this.getPosX()-ship.getPosX(),2)+Math.pow(this.getPosY()-ship.getPosY(), 2))-this.getRadius()-ship.getRadius());
	}
	
	/**
	 * Checks if this ship overlaps with a given ship.
	 * 
	 * @param ship 
	 * 		  The ship of which must be checked if it overlaps with this ship.
	 * @return 
	 * 		  Returns true if the ship overlaps with the given ship or the given ship is equal to the given ship.
	 * 		  |result == this.getDistanceBetween(ship)<=0
	 * @throws NullPointerException
	 * 		   The ship doesn't exist.
	 * 		   |(ship == null)
	 */
	public boolean overlaps (Ship ship) throws NullPointerException{
		if(ship == null){
			throw new NullPointerException();
		}
		else{
			try{return (this.getDistanceBetween(ship)<=0);}
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
	 * 		  result == vect1[0]*vect2[0] + vect1[1]*vect2[1]
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
	 * 		   |if(this.getTimeToCollision(ship) == Double.POSITIVE_INFINITY) then (result == null)
	 * 		   |else result ==  {(((this.getPosX()+deltaT*this.getVelX())*ship.getRadius())+(ship.getPosX()+deltaT*ship.getVelX())*this.getRadius())/(this.getRadius()+ship.getRadius()),
	 *						     (((this.getPosY()+deltaT*this.getVelY())*ship.getRadius())+(ship.getPosY()+deltaT*ship.getVelY())*this.getRadius())/(this.getRadius()+ship.getRadius())}
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
			double[] collisionPosition = {(((this.getPosX()+deltaT*this.getVelX())*ship.getRadius())+(ship.getPosX()+deltaT*ship.getVelX())*this.getRadius())/(this.getRadius()+ship.getRadius()),
										  (((this.getPosY()+deltaT*this.getVelY())*ship.getRadius())+(ship.getPosY()+deltaT*ship.getVelY())*this.getRadius())/(this.getRadius()+ship.getRadius())};
			return collisionPosition;
		}
	}
	


}

