package asteroids.model;
import be.kuleuven.cs.som.taglet.*;
import be.kuleuven.cs.som.annotate.*;

public class Ship {
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param xVelocity
	 * @param yVelocity
	 * @param radius
	 * @param orientation
	 * 
	 * @effect setPosX(x)
	 * @effect setPosY(y)
	 */
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius, double orientation){
		this.setPosX(x);
		this.setPosY(y);
	}
	
	private double posX;
	private double posY;
	
	/**
	 * 
	 * @param newPos
	 * 		  The new X position for the ship.
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
	 * 
	 * @return The X position of the ship.
	 * 	
	 */
	public double getPosX(){
		return this.posX;
	}
	
	/**
	 * 
	 * @param newPos
	 * 		  The new Y position for the ship.
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
	 * 
	 * @return The Y position of the ship.
	 * 	
	 */
	public double getPosY(){
		return this.posY;
	}
	
	/**
	 * 
	 * @param pos The position to be validated
	 * @return True if the position is greater then or equal to zero.
	 * 		   | result == (pos>=0)
	 */
	public static boolean isValidPos(double pos){
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
		if(newVel <0)
			this.velX = 0;
		else if(totalSpeed>speedLimit)
			this.velX = Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelY(), 2));
		else 
			this.velX = newVel;
	}
	
	public double getVelY(){
		return this.velY;
	}
	
	public void setVelY(double newVel){
		double totalSpeed = Math.sqrt(Math.pow(this.getVelX(), 2) + Math.pow(this.getVelY(), 2));
		if(newVel <0)
			this.velY = 0;
		else if(totalSpeed>speedLimit)
			this.velY = Math.sqrt(Math.pow(speedLimit, 2)-Math.pow(this.getVelX(), 2));
		else 
			this.velY = newVel;
	}
	
	public double orientation;
	
	public static boolean isValidOrientation(double orient){
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
	
	public static boolean isValidRadius(double rad){
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
	
	public double getDistanceBetween(Ship ship) throws NullPointerException{
		if(ship == null){
			throw new NullPointerException();
		}
		else 
			return Math.sqrt(Math.pow(this.getPosX()-ship.getPosX(),2)+Math.pow(this.getPosY()-ship.getPosY(), 2));
	}
	
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
	
	public double getTimeToCollision(Ship ship) throws NullPointerException, IllegalArgumentException{
		if(ship == null)
			throw new NullPointerException();
		else if(this.overlaps(ship))
			throw new IllegalArgumentException();
		double[] deltaV = {this.getVelX()-ship.getVelX(),this.getVelY()-ship.getVelY()};
		double[] deltaR = {this.getPosX()-ship.getPosX(),this.getPosY()-ship.getPosY()};
		double totalSigma = Math.pow(this.getRadius(), 2) + Math.pow(ship.getRadius(), 2);
		double d = Math.pow(scalarProduct(deltaV,deltaR),2)-scalarProduct(deltaV,deltaV)*
				(scalarProduct(deltaR,deltaR)-totalSigma);
		if(scalarProduct(deltaV,deltaR)>=0)
			return Double.POSITIVE_INFINITY;
		else if(d<=0)
			return Double.POSITIVE_INFINITY;
		else
			return -(scalarProduct(deltaV,deltaR)+Math.sqrt(d))/scalarProduct(deltaV,deltaV);
	}
	
	private double scalarProduct(double[] vect1, double[] vect2){
		return vect1[0]*vect2[0] + vect1[1]*vect2[1];
	}
	
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

