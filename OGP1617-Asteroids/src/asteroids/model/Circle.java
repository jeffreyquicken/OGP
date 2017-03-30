package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

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
	protected Circle(double x, double y, double xVelocity, double yVelocity, double radius,double minRadius) throws IllegalArgumentException{
		this.minRadius = minRadius;
		this.setPosX(x);
		this.setPosY(y);
		this.setVel(xVelocity, yVelocity);
		this.setRadius(radius);
	}
	
	private boolean terminated = false;
	
	public void terminate(){
		this.terminated = true;
	}
	
	public boolean isTerminated(){
		return this.terminated;
	}
	
	private Vector2D position;
	
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
			position.setX(newPos);
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
		return position.getX();
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
			position.setY(newPos);
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
		return position.getY();
	}
	
	/**
	 * Checks if a given position is a valid position.
	 * 
	 * @param pos 
	 * 		  The position to be validated
	 * @return 
	 * 		   Returns true if the position is a number.
	 * 		   | result == ((pos<=0) || (pos>=0))
	 */
	private static boolean isValidPos(double pos){
		return (pos>=0) ;
		}
	
	private Vector2D velocity;
	private double speedLimit = 300000;
	
	/**
	 * Returns the velocity in the X direction of this circle.
	 * 
	 * @return
	 * 		  Returns the velocity in the X direction of this circle.
	 * 		  |result == this.velX
	 */
	@Basic
	public double getVelX(){
		return velocity.getX();
	}
	
	/**
	 * Sets the velocity in the X direction of this circle to a given velocity.
	 * 
	 * @param newVel
	 * 		  The new velocity of this circle.
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
	 * 		  |result == this.velY
	 */
	@Basic
	public double getVelY(){
		return velocity.getY();
	}
	
	private double radius;
	private double minRadius;
	
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
	private boolean isValidRadius(double rad){
		return (rad>=minRadius);
	}
	
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
	
	private World world;
	
	public World getWorld(){
		return this.world;
	}
	
	public void setWorld(World newWorld){
		if(this.canHaveAsWorld(newWorld))
			this.world = newWorld;
	}
	
	private boolean canHaveAsWorld(World newWorld){
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
			return (Math.sqrt(Math.pow(this.getPosX()-circle.getPosX(),2)+Math.pow(this.getPosY()-circle.getPosY(), 2))-this.getRadius()-circle.getRadius());
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
	 * 		   The circle doesn't exist.
	 * 		   |(circle == null)
	 */
	public boolean overlaps (Circle circle) throws NullPointerException{
		if(circle == null){
			throw new NullPointerException();
		}
		else if(circle == this)
			return true;
		else{
			return (this.getDistanceBetween(circle)+this.getRadius()+circle.getRadius()<=0.99*(this.getRadius()+circle.getRadius()));
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
	 */
	public double getTimeToCollision(Circle circle) throws NullPointerException, IllegalArgumentException{
		if(circle == null)
			throw new NullPointerException();
		else if(this.overlaps(circle))
			throw new IllegalArgumentException();
		Vector2D deltaV = new Vector2D (this.getVelX()-circle.getVelX(),this.getVelY()-circle.getVelY());
		Vector2D deltaR = new Vector2D (this.getPosX()-circle.getPosX(),this.getPosY()-circle.getPosY());
		double totalSigma = Math.pow(this.getRadius()+circle.getRadius(),2);
		double d = Math.pow(deltaV.scalarProduct(deltaR),2)-deltaV.scalarProduct(deltaV)*
				(deltaR.scalarProduct(deltaR)-totalSigma);
		if(deltaV.scalarProduct(deltaR)>=0)
			return Double.POSITIVE_INFINITY;
		else if(d<=0)
			return Double.POSITIVE_INFINITY;
		else
			return -(deltaV.scalarProduct(deltaR)+Math.sqrt(d))/deltaV.scalarProduct(deltaV);
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
	 * @throws IllegalArgumentException
	 * 		   The given circle overlaps with this circle.
	 * 		   |this.overlaps(circle)
	 */
	public double[] getCollisionPosition(Circle circle) throws NullPointerException, IllegalArgumentException{
		if(circle == null)
			throw new NullPointerException();
		else if(this.overlaps(circle))
			throw new IllegalArgumentException();
		double deltaT = this.getTimeToCollision(circle);
		if(deltaT == Double.POSITIVE_INFINITY)
			return null;
		else{
			double[] collisionPosition = {(((this.getPosX()+deltaT*this.getVelX())*circle.getRadius())+(circle.getPosX()+deltaT*circle.getVelX())*this.getRadius())/(this.getRadius()+circle.getRadius()),
										  (((this.getPosY()+deltaT*this.getVelY())*circle.getRadius())+(circle.getPosY()+deltaT*circle.getVelY())*this.getRadius())/(this.getRadius()+circle.getRadius())};
			return collisionPosition;
		}
	}

	
	public double getDistanceBetween(World world){
		double distance = 0;
		double angle = Math.atan2(this.getVelY(), this.getVelX());
		if((angle>=-Math.PI/4) && (angle<=Math.PI/4))
			distance = ((world.getWidth()-this.getPosX())/Math.cos(angle))-this.getRadius();
		if((angle>=Math.PI/4) && (angle<=3*Math.PI/4))
			distance = ((world.getHeight()-this.getPosY())/Math.sin(angle))-this.getRadius();
		if((angle>=3*Math.PI/4)&&(angle<=-3*Math.PI/4))
			distance = ((this.getPosX())/Math.cos(angle))-this.getRadius();
		if((angle>=-3*Math.PI/4)&&(angle<=-Math.PI/4))
			distance = ((this.getPosY())/Math.sin(angle))-this.getRadius();
		return distance;
	}
	
	public double getTimeToCollision(World world){
		if((this.getVelX() == 0) && (this.getVelY() ==0 ))
				return Double.POSITIVE_INFINITY;
		if (world == null)
			return Double.POSITIVE_INFINITY;
		
		return this.getDistanceBetween(world)/this.velocity.length();
	}
	
	public double[]  getCollisionPosition(World world){
		double time = this.getTimeToCollision(world);
		if(time == Double.POSITIVE_INFINITY)
			return null;
		double[] posArray = {this.getPosX()+this.getVelX()*time,this.getPosY()+this.getVelY()*time};
		return posArray;
	}
	
	public void bounce(World world){
		double angle = this.velocity.angle();
		if(((angle>=-Math.PI/4) && (angle<=Math.PI/4)) || ((angle>=3*Math.PI/4)&&(angle<=-3*Math.PI/4)))
			this.setVel(-this.getVelX(), this.getVelY());
		if(((angle>=Math.PI/4) && (angle<=3*Math.PI/4)) || ((angle>=-3*Math.PI/4)&&(angle<=-Math.PI/4)))
			this.setVel(this.getVelX(), -this.getVelY());
	}
	
	public abstract void collision(Bullet bullet);
	public abstract void collision(Ship ship);
	

}
