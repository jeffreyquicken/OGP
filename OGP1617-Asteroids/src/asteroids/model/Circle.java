package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of circular objects.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 *
 * @invar The X-coordinate of the circle is a valid coordinate.
 * 		  |isValidPos(posX)
 * @invar The Y-coordinate of the circle is a valid coordinate.
 * 		  |isValidPos(posY)
 * @invar The total velocity of the circle does not exceed the speedLimit.
 * 		  |speedLimit>=(Math.sqrt(Math.pow(velX,2)+Math.pow(velY,2)))
 * @invar The radius of the circle is a valid radius.
 * 		  |isValidRadius(radius)
 * @invar If a circle is in a world, the worlds circle collection contains the circle.
 * 		  |if(this.getWorld() != null) then this.getWorld().getWorldCircles().contains(this)
 * @invar The circle can have its world as a world.
 * 		  |(canHaveAsWorld(this.getWorld())
 */
public abstract class Circle {
	
	/**
	 * Initializes a Circle with a given x-coordinate,y-coordinate,x-velocity,y-velocity and radius.
	 * 
	 * @param x
	 * 		  The initial X-coordinate of this circle.
	 * @param y
	 * 		  The initial Y-coordinate of this circle.
	 * @param xVelocity
	 * 		  The initial velocity in the X direction of this circle.
	 * @param yVelocity
	 * 		  The initial velocity in the Y direction of this circle.
	 * @param radius
	 * 		  The initial radius of this circle.
	 * 
	 * @effect setPosX(x)
	 * 		   Sets the X-coordinates of this circle to x. It may also throw an IllegalExceptionError if the position is not a
	 * 		   valid position.
	 * @effect setPosY(y)
	 * 		   Sets the Y-coordinates of this circle to y. It may also throw an IllegalExceptionError if the position is not a
	 * 		   valid position.
	 * @effect setVelX(xVelocity)
	 * 		   Sets the velocity in the X direction of this circle to xVelocity.
	 * @effect setVelY(yVelocity)
	 * 		   Sets the velocity in the Y direction of this circle to yVelocity.
	 * @effect setRadius(radius)
	 * 		   Sets the radius of this circle to radius. It may also throw an IllegalExceptionError if the radius is not a
	 * 		   valid radius.
	 * @throws IllegalArgumentException
	 * 		   Throws IllegalArgumentException if the x coordinate,y coordinate or the radius are not valid.
	 * 		   |((!isValidPos(x)) || (!isValidPos(y)) || (!isValidRadius(radius)))
	 * 
	 */
	@Raw
	protected Circle(double x, double y, double xVelocity, double yVelocity, double radius, double mass) throws IllegalArgumentException{
		this.setPosX(x);
		this.setPosY(y);
		this.setVel(xVelocity,yVelocity);
		this.setRadius(radius);
		this.setMass(mass);
	}
	
	protected boolean terminated = false;
	
	/**
	 * Terminates the circle.
	 * @post
	 * 		The circle is terminated.
	 * 		|new.isTerminated()
	 */
	@Basic
	public void terminate() {
		this.terminated = true;	
	}
	
	/**
	 * Returns if the circle is terminated
	 * @return
	 * 		  Returns true if the circle is terminated.
	 * 		  |result == this.terminated
	 */
	@Basic
	public boolean isTerminated(){
		return this.terminated;
	}
	
	private double mass;
	
	public double getMass(){
		return this.mass;
	}
	
	protected double massCorrection(double newMass){
		if(newMass<0)
			return 0;
		else 
			return newMass;
	}
	
	public void setMass(double newMass){
		double correctedMass = this.massCorrection(newMass);
		this.mass = correctedMass;
	}
	
	private Vector2D position = new Vector2D(0,0);
	
	/**
	 * Sets the X-coordinate of this circle to a given coordinate.
	 * 
	 * @param newPos
	 * 		   The new X position for the circle.
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
			this.position.setX(newPos);
	}
	
	/**
	 * Returns the X-coordinate of this circle.
	 * 
	 * @return 
	 * 		  The X position of this circle.
	 * 		  |result == this.posX
	 * 		
	 */
	@Basic
	public double getPosX(){
		return this.getPosVector().getX();
	}
	
	/**
	 * Sets the Y-coordinate of this circle to a given coordinate.
	 * 
	 * @param newPos
	 * 		   The new Y position for this circle.
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
			this.position.setY(newPos);
	}
	
	/**
	 * Returns the Y-coordinate of this circle.
	 * 
	 * @return 
	 * 		  The Y position of this circle.
	 * 		  |result == this.posY
	 */
	@Basic
	public double getPosY(){
		return this.getPosVector().getY();
	}
	
	/**
	 * Checks if a given position is a valid position.
	 * 
	 * @param pos 
	 * 		  The position to be validated
	 * @return 
	 * 		   Returns true if the position is a positive number.
	 * 		   | result == pos>=0
	 */
	private static boolean isValidPos(double pos){
		return (pos>=0);
	}
	
	private Vector2D velocity = new Vector2D(0,0);
	private double speedLimit = 300000;
	
	/**
	 * Returns the velocity in the X direction of this circle.
	 * 
	 * @return
	 * 		  Returns the velocity in the X direction of this circle.
	 * 		  |result == this.getVelVector().getX()
	 */
	@Basic
	public double getVelX(){
		return this.getVelVector().getX();
	}
	
	/**
	 * Sets the velocity this circle to a given velocity.
	 * 
	 * @param newVelX
	 * 		  The new x velocity of this circle.
	 * @param newVelY
	 * 		  The new y velocity of this circle.
	 * 
	 * @post
	 * 		If the total new speed does not exceed the speed limit, 
	 * 		the speed in the x and y direction is set to newVelX and newVelY.
	 * 		Otherwise, the velocity in the X and Y direction is set in such a way that the
	 * 		total new speed is equal to the speed limit, but the direction of the new velocity remains the same.
	 * 		|if(Math.sqrt(Math.pow(newVelX, 2) + Math.pow(newVelY, 2))>speedLimit) then
	 * 		|new.getVelX() == speedLimit*newVelX/totalSpeed && new.getVelY() == speedLimit*newVelY/totalSpeed)
	 * 		|else
	 * 		|new.getVelX() == newVelX && new.getVelY() == newVelY
	 */
	@Basic
	public void setVel(double newVelX, double newVelY){
		double totalSpeed = Math.sqrt(Math.pow(newVelX, 2) + Math.pow(newVelY, 2));
		if(totalSpeed>speedLimit){
			this.velocity.setX(speedLimit*newVelX/totalSpeed);
			this.velocity.setY(speedLimit*newVelY/totalSpeed);
		}
		else 
			this.velocity.setX(newVelX);
			this.velocity.setY(newVelY);
	}
	
	/**
	 * Returns the velocity in the Y direction of this circle.
	 * 
	 * @return
	 * 		  Returns the velocity in the Y direction of this circle.
	 * 		  |result == this.getVelVector().getY()
	 */
	@Basic
	public double getVelY(){
		return this.getVelVector().getY();
	}
	
	private double radius;
	
	/**
	 * Returns the radius of this circle.
	 * 
	 * @return
	 * 		  Returns the radius of this circle.
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
	protected abstract boolean isValidRadius(double rad);
	
	/**
	 * Sets the radius of this circle to a given radius.
	 * 
	 * @param newRadius
	 * 		   The new radius for the circle.
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
	
	private World world = null;
	
	/**
	 * Returns the world of the circle.
	 * @return
	 * 		 The world of the circle.
	 * 	 	 |result == this.world
	 */
	@Basic
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Sets the world of the circle to newWorld
	 * @param newWorld
	 * 		  The new world of the circle.
	 * @throws IllegalArgumentException
	 * 		  The circle can't have this world as a world.
	 * 		  |!canHaveAsWorld(newWorld)
	 * @post  The world of the circle is newWorld
	 * 		  |new.getWorld() == newWorld
	 */
	@Basic
	public void setWorld(World newWorld) throws IllegalArgumentException{
		if(!canHaveAsWorld(newWorld))
			throw new IllegalArgumentException();
		this.world = newWorld;
	}
	
	/**
	 * Checks if the circle can have this world as a world.
	 * @param newWorld
	 * 		  The world that has to be validated.
	 * @return
	 * 		  True if the world is null or if the world is not terminated.
	 * 		  |result == (newWorld == null || !newWorld.isTerminated())
	 */
	private boolean canHaveAsWorld(World newWorld){
		if(newWorld != null)
			return !newWorld.isTerminated();
		else 
			return true;
	}
	
	/**
	 * Moves this circle around for a given amount of time.
	 * 
	 * @param time
	 * 		  The amount of time this circle is moving.
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
			this.setPosX(this.getPosX() + this.getVelX()*time);
			this.setPosY(this.getPosY() + this.getVelY()*time);
	}
	
	/**
	 * Calculates the distance between this circle and the given circle.
	 * 
	 * @param circle 
	 * 		  The circle between which the distance must be calculated.
	 * @return 
	 * 		  Returns the distance in kilometers between the center of this circle and the center of the given circle.
	 * 		  |result == Math.sqrt(Math.pow(this.getPosX()-circle.getPosX(),2)+
	 * 		  |			 Math.pow(this.getPosY()-circle.getPosY(), 2))-this.getRadius()-circle.getRadius()
	 * @throws NullPointerException
	 * 		   The circle doesn't exist.
	 * 		   |(circle == null)
	 */
	public double getDistanceBetween(Circle circle) throws NullPointerException{
		if(circle == null){
			throw new NullPointerException();
		}
		else if(circle == this)
			return 0;
		else 
			return this.getDistanceBetweenCenter(circle)-this.getRadius()-circle.getRadius();
	}
	
	/**
	 * Returns the distance between the centers of two circles.
	 * @param circle
	 * 		  The other circle.
	 * @return
	 * 		 The distance between the other two circles.
	 * 		 |result == this.getPosVector().substract(circle.getPosVector())).length()
	 * @throws NullPointerException
	 * 		  The other circle is null
	 * 		  |circle == null
	 */
	public double getDistanceBetweenCenter(Circle circle) throws NullPointerException{
		if(circle == null){
			throw new NullPointerException();
		}
		else if(circle == this)
			return 0;
		else 
			return (this.getPosVector().substract(circle.getPosVector())).length();
	}
	
	/**
	 * Checks if this circle overlaps with a given circle.
	 * 
	 * @param circle 
	 * 		  The circle of which must be checked if it overlaps with this circle.
	 * @return 
	 * 		  Returns true if the circle overlaps with the given circle or the given circle is equal to the given circle.
	 * 		  |result == this.getDistanceBetween(circle)<=0
	 * @throws NullPointerException
	 * 		  The circle doesn't exist.
	 * 		  |(circle == null)
	 */
	public boolean overlaps (Circle circle) throws NullPointerException{
		if(circle == null){
			throw new NullPointerException();
		}
		else if(circle == this)
			return true;
		else{
			return this.getDistanceBetweenCenter(circle)<=0.99*(this.getRadius()+circle.getRadius());
		}
	}
	
	/**
	 * Calculates the time when this circle and a given circle will collide.
	 * 
	 * @param circle
	 * 		  The circle of which the collision time with this circle is calculated.
	 * @return 
	 * 		   Returns the time in seconds when this circle and the given circle will collide.
	 * 		   Returns Double.POSITIVE_INFINITY if the circles will never collide.
	 * @throws NullPointerException
	 * 		   The circle doesn't exist.
	 * 		   |(circle == null)
	 * @throws IllegalArgumentException
	 * 		   The given circle overlaps with this circle.
	 * 		   |this.overlaps(circle)
	 * @return 
	 * 		   The collapsed time when the two circles collide.
	 * 		   |if this.move(result) then new.collides(circle)
	 */
	public double getTimeToCollision(Circle circle) throws NullPointerException{
		if(circle == null)
			throw new NullPointerException();
		Vector2D deltaV = this.getVelVector().substract(circle.getVelVector());
		Vector2D deltaR = this.getPosVector().substract(circle.getPosVector());
		double totalSigma = Math.pow(this.getRadius()+circle.getRadius(),2);
		double d = Math.pow(deltaV.scalarProduct(deltaR),2)-deltaV.scalarProduct(deltaV)*
				(deltaR.scalarProduct(deltaR)-totalSigma);
		if(deltaV.scalarProduct(deltaR)>=0)
			return Double.POSITIVE_INFINITY;
		else if(d<=0)
			return Double.POSITIVE_INFINITY;
		else{
			return -((deltaV.scalarProduct(deltaR)+Math.sqrt(d))/deltaV.scalarProduct(deltaV));
		}
	}
	
	/**
	 * Calculates the coordinates (an array) where this circle and the given circle will collide.
	 * 
	 * @param circle
	 * 		   The circle of which the collision coordinates with this circle are calculated.
	 * @return 
	 * 		   Returns an array of the X and Y coordinate where this circle and the given circle will collide.
	 * 		   Returns null if the circles will never collide.
	 * 		   |if(this.getTimeToCollision(circle) == Double.POSITIVE_INFINITY) then (result == null)
	 * 		   |else result ==  {(((this.getPosX()+deltaT*this.getVelX())*circle.getRadius())+(circle.getPosX()+deltaT*circle.getVelX())*this.getRadius())/(this.getRadius()+circle.getRadius()),
	 *						     (((this.getPosY()+deltaT*this.getVelY())*circle.getRadius())+(circle.getPosY()+deltaT*circle.getVelY())*this.getRadius())/(this.getRadius()+circle.getRadius())}
	 * @throws NullPointerException
	 *  	   The circle doesn't exist.
	 * 		   |(circle == null)
	 */
	public double[] getCollisionPosition(Circle circle) throws NullPointerException, IllegalArgumentException{
		if(circle == null)
			throw new NullPointerException();
		double deltaT = this.getTimeToCollision(circle);
		if(deltaT == Double.POSITIVE_INFINITY)
			return null;
		if(deltaT <=0)
			deltaT = 0;
		double[] collisionPosition = {(((this.getPosX()+deltaT*this.getVelX())*circle.getRadius())+(circle.getPosX()+deltaT*circle.getVelX())*this.getRadius())/(this.getRadius()+circle.getRadius()),
										 (((this.getPosY()+deltaT*this.getVelY())*circle.getRadius())+(circle.getPosY()+deltaT*circle.getVelY())*this.getRadius())/(this.getRadius()+circle.getRadius())};
		return collisionPosition;
	}

	/**
	 * Calculates the distance between the circle and the world.
	 * @param world
	 * 		  The world of which the distance between this circle is calculated.
	 * @return
	 * 		  The distance between the world and the circle.
	 * 		  |result == Math.min(Math.min(world.getHeight()-this.getPosY()-this.getRadius(), this.getPosY()-this.getRadius()), 
			  | 	Math.min(this.getPosX()-this.getRadius(), world.getWidth()-this.getPosX()-this.getRadius()))
	 * @throws NullPointerException
	 * 		  The world is null.
	 * 		  |world == null
	 */
	public double getDistanceBetween(World world)throws NullPointerException{
		if(world == null)
			throw new NullPointerException();
		return Math.min(Math.min(world.getHeight()-this.getPosY()-this.getRadius(), this.getPosY()-this.getRadius()), 
				Math.min(this.getPosX()-this.getRadius(), world.getWidth()-this.getPosX()-this.getRadius()));
	}
	
	/**
	 * Calculates in how much time the circle collides with a world.
	 * 
	 * @param world
	 * 		  The world with which the circle collides.
	 * @return
	 * 		  The expired time if the circle collides with the world.
	 * 		  @see implementation
	 * @throws NullPointerException
	 * 		   The world is null.
	 * 		   |world == null
	 */
	public double getTimeToCollision(World world) throws NullPointerException{
		if (world == null)
			throw new NullPointerException();
		
		if(this.getVelX()<0){
			double time = -(this.getPosX()-this.getRadius())/this.getVelX();
			if(this.getVelY()<0 && time> -(this.getPosY()-this.getRadius())/this.getVelY())
				return -(this.getPosY()-this.getRadius())/this.getVelY();
			else if(this.getVelY()>0 && time> (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY())
				return (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY();
			else
				return time;
		}
		else{
			double time = (world.getWidth()-this.getPosX()-this.getRadius())/this.getVelX();
			if(this.getVelY()<0 && time> -(this.getPosY()-this.getRadius())/this.getVelY())
				return -(this.getPosY()-this.getRadius())/this.getVelY();
			else if(this.getVelY()>0 && time> (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY())
				return (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY();
				
			else
				return time;
		}
	}
	
	
	/**
	 * Returns the collision position of a circle with the world
	 * @param world
	 * 		  The world with which the circle collides.
	 * @return
	 * 		  An array of the coordinates where the circle collides with the boundary of the world.
	 * 		  Returns null if the circle never collides with the world.
	 * 		  |if(this.getTimeToCollision(world) == Double.POSITIVE_INFINITY) then result == null
	 * 		  |else see implementation
	 * @throws NullPointerException
	 * 		   The world is null;
	 * 		   |world == null;
	 */
	public double[]  getCollisionPosition(World world) throws NullPointerException{
		if(world == null)
			throw new NullPointerException();
		
		double collisionTime = this.getTimeToCollision(world);
		
		if(collisionTime == Double.POSITIVE_INFINITY)
			return null;
		else{
			double distance = this.getDistanceBetween(world);
			Vector2D centerPosition = this.getPosVector().add(this.getVelVector().multiply(collisionTime));
			Vector2D radiusVector = new Vector2D(0,0);
			if(distance == world.getHeight()-this.getPosY()-this.getRadius())
				radiusVector.setY(this.getRadius());
			else if(distance == this.getPosY()-this.getRadius())
				radiusVector.setY(-this.getRadius());
			else if(distance == this.getPosX()-this.getRadius())
				radiusVector.setX(-this.getRadius());
			else
				radiusVector.setX(this.getRadius());
			return centerPosition.add(radiusVector).array();
			}
	}
	
	/**
	 * Bounces the circle off the edge of the world.
	 * 
	 * @param world
	 * 		  The world against which the circle bounces.
	 * @post If the closest world boundary of the circle is a vertical boundary, the x velocity is reversed.
	 * 		 |if(this.getDistanceBetween(world) == Math.min(this.getPosX()-this.getRadius(), world.getWidth()-this.getPosX()-this.getRadius()))
	 * 	     |then -this.getVelX() == new.getVelX()
	 * @post If the closest world boundary of the circle is a horizontal boundary, the y velocity is reversed.
	 * 		 |if(this.getDistanceBetween(world) == Math.min(world.getHeight()-this.getPosY()-this.getRadius(), this.getPosY()-this.getRadius()))
	 * 		 |then -this.getVelY() == new.getVelY()
	 * @throws NullPointerException
	 * 		 The world is null.
	 * 		 |world == null
	 * @throws IllegalArgumentException
	 * 		 The ship is not in this world.
	 * 		 |this.getWorld() != world
	 */
	@Raw
	public void bounce(World world) throws NullPointerException, IllegalArgumentException{
		if(world == null)
			throw new NullPointerException();
		if(this.getWorld() != world)
			throw new IllegalArgumentException();
		if(this.getDistanceBetween(world) == Math.min(this.getPosX()-this.getRadius(), world.getWidth()-this.getPosX()-this.getRadius()))
			this.setVel(-this.getVelX(), this.getVelY());
		if(this.getDistanceBetween(world) == Math.min(world.getHeight()-this.getPosY()-this.getRadius(), this.getPosY()-this.getRadius()))
			this.setVel(this.getVelX(), -this.getVelY());
	}
	
	public abstract void collision(Object other);
	
	/**
	 * Returns the 2 dimensional position vector
	 * @return
	 * 		  The 2 dimensional position vector.
	 * 		  |result == this.position
	 */
	@Basic
	public Vector2D getPosVector(){
		return this.position;
	}
	
	/**
	 * Returns the 2 dimensional velocity vector
	 * @return
	 * 		  The 2 dimensional velocity vector.
	 * 		  |result == this.velcity
	 */
	@Basic
	public Vector2D getVelVector(){
		return this.velocity;
	}
	
	/**
	 * Checks if 2 circles are colliding.
	 * @param circle
	 * 		  The other circle to be checked.
	 * @return
	 * 		  True if the distance between the centers of the circles is between 99% and 101% of the sum of their radii.
	 * 		  |result == (this.getDistanceBetweenCenter(circle)<=1.01*(this.getRadius()+circle.getRadius())
			  | 		  && this.getDistanceBetweenCenter(circle)>=0.99*(this.getRadius()+circle.getRadius()))
	 */
	public boolean collides(Circle circle){
		return (this.getDistanceBetweenCenter(circle)<=1.01*(this.getRadius()+circle.getRadius())
				 && this.getDistanceBetweenCenter(circle)>=0.99*(this.getRadius()+circle.getRadius()));
	}
	

}
