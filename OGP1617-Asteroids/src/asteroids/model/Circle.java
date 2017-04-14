package asteroids.model;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of circular objects.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 *
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
	protected Circle(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException{
		this.setPosX(x);
		this.setPosY(y);
		this.setVel(xVelocity,yVelocity);
		this.setRadius(radius);
	}
	
	private boolean terminated = false;
	
	@Basic
	public void terminate() throws IllegalArgumentException{
		if(this.isTerminated())
			throw new IllegalArgumentException();
		else
			this.terminated = true;	
	}
	
	@Basic
	public boolean isTerminated(){
		return this.terminated;
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
	 * 		   Returns true if the position is a number.
	 * 		   | result == ((pos<=0) || (pos>=0))
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
	 * 		  |result == this.velX
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
	 * 		  |result == this.velY
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
	
	@Basic
	public World getWorld(){
		return this.world;
	}
	
	@Basic
	public void setWorld(World newWorld) throws IllegalArgumentException{
		if(!canHaveAsWorld(newWorld))
			throw new IllegalArgumentException();
		this.world = newWorld;
	}
	
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
			return (this.getPosVector().substract(circle.getPosVector())).length()-this.getRadius()-circle.getRadius();
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
			return this.getDistanceBetween(circle)+0.01*(this.getRadius()+circle.getRadius())<=0;
		}
	}
	
	public boolean actualOverlaps (Circle circle) throws NullPointerException{
		if(circle == null){
			throw new NullPointerException();
		}
		else if(circle == this)
			return true;
		else{
			return this.getDistanceBetween(circle)<=0;
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
	public double getTimeToCollision(Circle circle) throws NullPointerException{
		if(circle == null)
			throw new NullPointerException();
		//else if(this.overlaps(circle))
		//	return 0;
		Vector2D deltaV = this.getVelVector().substract(circle.getVelVector());
		Vector2D deltaR = this.getPosVector().substract(circle.getPosVector());
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
		//else if(this.actualOverlaps(circle))
		//	throw new IllegalArgumentException();
		double deltaT = this.getTimeToCollision(circle);
		if(deltaT == Double.POSITIVE_INFINITY)
			return null;
		else{
			double[] collisionPosition = {(((this.getPosX()+deltaT*this.getVelX())*circle.getRadius())+(circle.getPosX()+deltaT*circle.getVelX())*this.getRadius())/(this.getRadius()+circle.getRadius()),
										  (((this.getPosY()+deltaT*this.getVelY())*circle.getRadius())+(circle.getPosY()+deltaT*circle.getVelY())*this.getRadius())/(this.getRadius()+circle.getRadius())};
			return collisionPosition;
		}
	}

	
	public double getDistanceBetween(World world)throws NullPointerException{
		if(world == null)
			throw new NullPointerException();
		
		/*double distance = Double.POSITIVE_INFINITY;
		
		if(distance>this.getPosX()-this.getRadius())
			distance = this.getPosX()-this.getRadius();
		
		if(distance>world.getWidth()-this.getPosX()-this.getRadius())
			distance = world.getWidth()-this.getPosX()-this.getRadius();
		
		if(distance>this.getPosY()-this.getRadius())
			distance = this.getPosY()-this.getRadius();
		
		if(distance >world.getHeight()-this.getPosY()-this.getRadius())
			distance = world.getHeight()-this.getPosY()-this.getRadius();
		
		return distance;*/
		Direction direction = this.getNearestWorldBoundary(world);
		switch(direction){
		case UP:
			return world.getHeight()-this.getPosY()-this.getRadius();
		case DOWN:
			return this.getPosY()-this.getRadius();
		case LEFT:
			return this.getPosX()-this.getRadius();
		case RIGHT:
			return world.getWidth()-this.getPosX()-this.getRadius();
		}
		throw new AssertionError();
	}
	
	public double getTimeToCollision(World world) throws NullPointerException{
		if (world == null)
			throw new NullPointerException();
		if(this.getVelX() == 0 && this.getVelY() == 0)
			return Double.POSITIVE_INFINITY;
		/*double time = Double.POSITIVE_INFINITY;
		
		if(this.getVelX()<0 && time > -(this.getPosX()-this.getRadius())/this.getVelX())
			time = -(this.getPosX()-this.getRadius())/this.getVelX();
		
		if(this.getVelX()>0 && time > (world.getWidth()-this.getPosX()-this.getRadius())/this.getVelX())
			time = (world.getWidth()-this.getPosX()-this.getRadius())/this.getVelX();
		
		if(this.getVelY()<0 && time> -(this.getPosY()-this.getRadius())/this.getVelY())
			time = -(this.getPosY()-this.getRadius())/this.getVelY();
		
		if(this.getVelY()>0 && time> (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY())
			time = (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY();*/
		Direction collisionDirection = this.getWorldCollisionDirection(world);
		switch(collisionDirection){
		case UP:
			return (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY();
		case DOWN:
			return -(this.getPosY()-this.getRadius())/this.getVelY();
		case LEFT:
			return -(this.getPosX()-this.getRadius())/this.getVelX();
		case RIGHT:
			return (world.getWidth()-this.getPosX()-this.getRadius())/this.getVelX();
		}
		throw new AssertionError();
	}
	
	public double[]  getCollisionPosition(World world) throws NullPointerException{
		
		double collisionTime = this.getTimeToCollision(world);
		
		if(collisionTime == Double.POSITIVE_INFINITY)
			return null;
		else{
			Direction direction = this.getWorldCollisionDirection(world);
			switch(direction){
			case UP:
				Vector2D upVector = new Vector2D(0,this.getRadius());
				return (this.getPosVector().add(this.getVelVector().multiply(collisionTime))).add(upVector).array();
			case DOWN:
				Vector2D downVector = new Vector2D(0,-this.getRadius());
				return (this.getPosVector().add(this.getVelVector().multiply(collisionTime))).add(downVector).array();
			case RIGHT:
				Vector2D rightVector = new Vector2D(this.getRadius(),0);
				return (this.getPosVector().add(this.getVelVector().multiply(collisionTime))).add(rightVector).array();
			case LEFT:
				Vector2D leftVector = new Vector2D(-this.getRadius(),0);
				return (this.getPosVector().add(this.getVelVector().multiply(collisionTime))).add(leftVector).array();
			}
			return null;
		}
	}
	
	private Direction getWorldCollisionDirection(World world){
		
		if(this.getVelX()<0){
			double time = -(this.getPosX()-this.getRadius())/this.getVelX();
			if(this.getVelY()<0 && time> -(this.getPosY()-this.getRadius())/this.getVelY())
				return Direction.DOWN;
			else if(this.getVelY()>0 && time> (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY())
				return Direction.UP;
			else
				return Direction.LEFT;
		}
		else{
			double time = (world.getWidth()-this.getPosX()-this.getRadius())/this.getVelX();
			if(this.getVelY()<0 && time> -(this.getPosY()-this.getRadius())/this.getVelY())
				return Direction.DOWN;
			else if(this.getVelY()>0 && time> (world.getHeight()-this.getPosY()-this.getRadius())/this.getVelY())
				return Direction.UP;
			else
				return Direction.RIGHT;
		}
	}
	
	private Direction getNearestWorldBoundary(World world){
		if(this.getPosX()-this.getRadius()<world.getWidth()-this.getPosX()-this.getRadius() &&
				this.getPosX()-this.getRadius()<this.getPosY()-this.getRadius() &&
				this.getPosX()-this.getRadius()<world.getHeight()-this.getPosY()-this.getRadius())
			return Direction.LEFT;
		
		else if(world.getWidth()-this.getPosX()-this.getRadius()<this.getPosX()-this.getRadius() &&
				world.getWidth()-this.getPosX()-this.getRadius()<this.getPosY()-this.getRadius() && 
						world.getWidth()-this.getPosX()-this.getRadius()<world.getHeight()-this.getPosY()-this.getRadius())
			return Direction.RIGHT;
		
		else if(this.getPosY()-this.getRadius()<this.getPosX()-this.getRadius() &&
				this.getPosY()-this.getRadius()<world.getWidth()-this.getPosX()-this.getRadius() &&
				this.getPosY()-this.getRadius()<world.getHeight()-this.getPosY()-this.getRadius())
			return Direction.DOWN;
		
		else
			return Direction.UP;
	}
	
	public void bounce(World world){
		/*double angle = this.velocity.angle();
		if(((angle>=-Math.PI/4) && (angle<=Math.PI/4)) || ((angle>=3*Math.PI/4)&&(angle<=-3*Math.PI/4)))
			this.setVel(-this.getVelX(), this.getVelY());
		if(((angle>=Math.PI/4) && (angle<=3*Math.PI/4)) || ((angle>=-3*Math.PI/4)&&(angle<=-Math.PI/4)))
			this.setVel(this.getVelX(), -this.getVelY());*/
		Direction direction = this.getNearestWorldBoundary(world);
		switch(direction){
		case UP:
			this.setVel(this.getVelX(), -this.getVelY());
			return;
		case DOWN:
			this.setVel(this.getVelX(), -this.getVelY());
			return;
		case LEFT:
			this.setVel(-this.getVelX(), this.getVelY());
			return;
		case RIGHT:
			this.setVel(-this.getVelX(), this.getVelY());
			return;
		}
	}
	
	public abstract void collision(Bullet bullet);
	public abstract void collision(Ship ship);
	
	public Vector2D getPosVector(){
		return this.position;
	}
	
	public Vector2D getVelVector(){
		return this.velocity;
	}
	

}
