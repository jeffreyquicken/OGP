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
	}
	
	private boolean terminated = false;
	
	public void terminate(){
		this.terminated = true;
	}
	
	public boolean isTerminated(){
		return this.terminated;
	}
	
	private double posX;
	private double posY;
	
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
			posX = newPos;
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
		return this.posX;
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
			posY = newPos;
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
		return this.posY;
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
		return ((pos<=0) || (pos>=0)) ;
		}
	
	private double velX;
	private double velY;
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
		return this.velX;
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
	public void setVelX(double newVel){
		double totalSpeed = Math.sqrt(Math.pow(newVel, 2) + Math.pow(this.getVelY(), 2));
		if(totalSpeed>speedLimit)
			this.velX = Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelY(), 2));
		else 
			this.velX = newVel;
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
		return this.velY;
	}
	
	/**
	 * Sets the velocity in the Y direction of this circle to a given velocity.
	 * 
	 * @param newVel
	 * 		  The new velocity of this circle.
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
		else 
			return (Math.sqrt(Math.pow(this.getPosX()-circle.getPosX(),2)+Math.pow(this.getPosY()-circle.getPosY(), 2))-this.getRadius()-circle.getRadius());
	}
	
	public double getDistanceBetween(Bullet bullet) throws NullPointerException{
		if(bullet == null){
			throw new NullPointerException();
		}
		else 
			return (Math.sqrt(Math.pow(this.getPosX()-bullet.getPosX(),2)+Math.pow(this.getPosY()-bullet.getPosY(), 2))-this.getRadius()-bullet.getRadius());
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
		else{
			try{return (this.getDistanceBetween(circle)<=0);}
			catch (NullPointerException e){
				throw e;
			}
		}
	}
	
	public boolean overlaps (Bullet bullet) throws NullPointerException{
		if(bullet == null){
			throw new NullPointerException();
		}
		else{
			try{return (this.getDistanceBetween(bullet)<=0);}
			catch (NullPointerException e){
				throw e;
			}
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
		double[] deltaV = {this.getVelX()-circle.getVelX(),this.getVelY()-circle.getVelY()};
		double[] deltaR = {this.getPosX()-circle.getPosX(),this.getPosY()-circle.getPosY()};
		double totalSigma = Math.pow(this.getRadius()+circle.getRadius(),2);
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
	

}
