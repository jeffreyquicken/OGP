package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
/**
 * A class of bullets.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 * 
 * @invar If a bullet has a ship, the ships bullets collection contains the bullet.
 * 		  |if(this.getShip()) != null then this.getShip().getBullets().contains(this)
 * @invar A bullet can have its owner as an owner.
 * 		  |canHaveAsOwner(this.getOwner())
 * @invar A bullet can have its ship as a ship
 * 		  |canHaveAsShip(this.getShip())
 * @invar If a bullet is in a world, the collection of the worlds bullets contains the bullet.
 * 		  |if(this.getWorld()) != null then this.getWorld().getWorldBullets().contains(bullet)
 * @invar The amount of bounces of a bullet is smaller then or equal to the maximum amount of bounces.
 * 		  |this.getAmountOfBounces() < maxBounces
 *
 */
public class Bullet extends Circle {
	
	/**
	 * Creates a new bullet with a given x position,y position, x velocity, y velocity and radius.
	 * 
	 * @param x
	 * 		  The initial x position of the bullet.
	 * @param y
	 * 		  The initial y position of the bullet.
	 * @param xVelocity
	 * 		  The initial x velocity of the bullet.
	 * @param yVelocity
	 * 		  The initial y velocity of the bullet.
	 * @param radius
	 * 		  The radius of the bullet.
	 * 
	 * @effect setPosX(x)
	 * 		  Sets the x position of the bullet to x.
	 * @effect setPosY(y)
	 * 		  Sets the y position of the bullet to y.
	 * @effect setVel(xVelocity,yVelocity)
	 * 		  Sets the x and y velocity to xVelocity and yVelocity.
	 * @effect setRadius(radius)
	 * 		  Sets the radius of the bullet to radius.
	 * @throws IllegalArgumentException
	 * 		  The given x or y position are not valid positions.
	 * 		  |!isValidPos(x) || !isValidPos(y)
	 */		 
	@Raw
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius) throws IllegalArgumentException{
		super(x,y,xVelocity,yVelocity,radius,getDensity()*(4.0/3.0)*Math.pow(radius, 3)*Math.PI);
	}
	
	private static double minRadius = 1;
	
	/**
	 * Returns the minimum radius of the bullets.
	 * 
	 * @return
	 * 		 The minimum radius of the bullets.
	 */
	@Immutable
	public static double getMinRadius(){
		return minRadius;
	}
	
	/**
	 * Sets the minimum radius of the bullets to newMinRadius.
	 * 
	 * @param newMinRadius
	 * 		  The new minimum radius of the bullets.
	 */
	@Basic
	public static void setMinRadius(double newMinRadius){
		minRadius = newMinRadius;
	}
	
	/**
	 * Checks if the radius is a valid radius.
	 * 
	 * @param newRadius
	 * 		  The radius of which to check if it is a valid radius.
	 * @return 
	 * 		  Returns true if newRadius is greater then or equal to the minRadius.
	 * 		  |newRadius>=getMinRadius()
	 */
	protected boolean isValidRadius(double newRadius){
		return newRadius>=getMinRadius();
	}
	private Ship owner = null;
	private Ship ship = null;
	
	/**
	 * Returns the owner ship of the bullet.
	 * 
	 * @return
	 * 		  Returns the owner ship of the bullet. 
	 * 		  Returns null if the bullet has no owner.
	 * 		  |result == this.owner
	 */
	@Basic
	public Ship getOwner(){
		return this.owner;
	}
	
	/**
	 * Checks if a owner for this bullet is a valid owner.
	 * 
	 * @param newOwner
	 * 		  The new owner ship of the bullet
	 * @return
	 * 		  Returns true if newOwner is not terminated and the bullet lies within the bounds of the ship.
	 * 		  Returns false otherwise.	
	 * 		  |result == (!newOwner.isTerminated() && this.getDistanceBetween(newOwner)+this.getRadius()<=0)
	 */
	private boolean canHaveAsOwner(Ship newOwner){
		return (!newOwner.isTerminated() && this.getDistanceBetween(newOwner)+this.getRadius()<=0);
	}
	
	/**
	 * Sets the owner of the ship to newOwner.
	 * 
	 * @param newOwner
	 * 		  The new owner of the ship.
	 * 
	 * @throws IllegalArgumentException
	 * 		   The bullet can not have newOwner as owner.
	 * 		   |!canHaveAsOwner(newOwner)
	 * 
	 * @throws NullPointerException
	 * 		   The new owner of the ship is null.
	 * 		   |newOwner == null
	 * 
	 * @post   The new owner of the ship is newOwner.
	 * 		   |new.getOwner() == newOwner
	 */
	@Basic
	public void setOwner(Ship newOwner) throws IllegalArgumentException,NullPointerException{
		if(newOwner == null)
			throw new NullPointerException();
		else if(!canHaveAsOwner(newOwner))
			throw new IllegalArgumentException();
		else
			this.owner = newOwner;
	}
	
	/**
	 * Returns the ship of the bullet.
	 * 
	 * @return
	 * 		 Returns the ship of the bullet.
	 * 		 Returns null if the bullet is terminated or the bullet is in the world.
	 * 		 |result == this.ship
	 */
	@Basic
	public Ship getShip(){
		return this.ship;
	}
	
	/**
	 * Checks if the newShip is a valid ship.
	 * 
	 * @param newShip
	 * 		  The new ship of the bullet.
	 * @return
	 * 		  True if the ship is the owner of the bullet and if this ship is not terminated 
	 * 		  or if newShip is null.
	 * 		  |result == (newShip == null  || (this.getOwner() == newShip && !newShip.isTerminated()))
	 */
	private boolean canHaveAsShip(Ship newShip){
		if(newShip != null)
			return this.getOwner() == newShip && !newShip.isTerminated();
		else
			return true;
	}
	
	/**
	 * Sets the ship to newShip.
	 * 
	 * @param newShip
	 * 		  The new ship of the bullet.
	 * 
	 * @throws IllegalArgumentException
	 * 		  The bullet can not have newShip as ship.
	 * 		  |!canHaveAsShip(newShip)
	 * @post  The ship of the bullet is set to newShip.
	 * 		  |new.getShip() == newShip
	 * 
	 */
	@Basic
	public void setShip(Ship newShip) throws IllegalArgumentException{
		if(!canHaveAsShip(newShip))
			throw new IllegalArgumentException();
		else
			this.ship = newShip;
	}
	
	
	private static double density = 7.8E12;
		
	/**
	 * Returns the density of bullets.
	 * @return
	 * 		  The density of the bullets
	 * 		  |result == density
	 */
	@Basic
	public static double getDensity(){
		return density;
	}
	
//	/**
//	 * Returns the mass of the bullet
//	 * @return
//	 * 		 The mass of the bullet.
//	 * 		 |result == this.mass
//	 */
//	@Basic
//	public double getMass(){
//		return this.mass;
//	}
	
	private double amountOfBounces = 0;
	private final double maxBounces = 3;
	
	/**
	 * Returns the amount of times the bullet has bounced against the world.
	 * @return
	 * 	     The amount of times the bullet has bounced against the world.
	 * 		 |result == this.amountOfBounces
	 */
	@Basic
	public double getAmountOfBounces(){
		return this.amountOfBounces;
	}
	
	/**
	 * Sets the amount of bounces to newAmount.
	 * 
	 * @param newAmount
	 * 		  The new amount of bounces of the bullet.
	 * @post
	 * 		The amount of bounces of the bullet is newAmount.
	 * 	    |new.getAmountOfBounces() == newAmount
	 */
	@Raw
	@Basic
	private void setAmountOfBounces(double newAmount){
		this.amountOfBounces = newAmount;
	}
	
	/**
	 * Increases the amount of bounces of this bullet with 1.
	 * 
	 * @effect this.setAmountOfBounces(this.getAmountOfBounces()+1)
	 * 		   Increases the amount of bounces with 1.
	 * 
	 * @post The bullet is terminated and removed from the world if 
	 * 		 the new amount of bounces exceeds the maximum amount of bounces.
	 * 		 |if(new.getAmountOfBounces()+1>=maxBounces) then
	 * 		 |(new.isTerminated() && new.getWorld == null && !this.getWorld().getWorldBullets().contains(this)
	 */
	@Raw
	public void increaseAmountOfBounces(){
		this.setAmountOfBounces(this.getAmountOfBounces()+1);
		if(this.getAmountOfBounces()>=maxBounces){
			this.terminate();
			this.getWorld().remove(this);
		}
	}
	
	/**
	 * Resolves a collision of a bullet and a circle that is not a ship.
	 * 
	 * @param other
	 * 		  The other circle to collide with this bullet.
	 * 
	 * @throws NullPointerException
	 * 		  The other circle is null.
	 * 		  |other == null
	 * @throws IllegalArgumentException
	 * 		  The other circle is this bullet.
	 * 		  |other == this
	 * 
	 * @post The world of this bullet and the other circle is set to null.
	 * 		  |new.getWorld() == null && newOther.getWorld() == null
	 * @post The world doesn't contain this and the other circle
	 * 		  |!this.getWorld().getWorldBullets().contains(this) && other.getWorld().getWorldBullets().contains(other)
	 * @post Both the circle and the bullet are terminated.
	 * 		  |this.isTerminated() && other.isTerminated()
	 * 
	 */
	@Raw
	public void CircleCollision(Circle other) throws NullPointerException,IllegalArgumentException{
		if(other == null){
			throw new NullPointerException();
		}
		else if (other == this)
			throw new IllegalArgumentException();
		else{
			if(this.getWorld().getWorldEntities().contains(this))
				this.getWorld().remove(this);
			if(this.getWorld().getWorldEntities().contains(other))
				this.getWorld().remove(other);
				this.terminate();
				other.terminate();
		}
	}
	
	
	
	/**
	 * Resolves the collision of a bullet with a ship.
	 * 
	 * @param ship
	 * 		  The ship to collide with.
	 * @effect ship.collision(this)
	 * 		  The ship collides with this bullet.
	 */
	@Raw
	public void ShipCollision(Ship ship) throws NullPointerException{
		if(ship == null)
			throw new NullPointerException();
		else
			ship.collision(this);
	}
	
	public void collision(Object other) throws NullPointerException, IllegalArgumentException{
		if(other == null)
			throw new NullPointerException();
		else if(other == this)
			throw new IllegalArgumentException();
		if(other instanceof Ship)
			this.ShipCollision((Ship)other);
		else if(other instanceof Circle)
			this.CircleCollision((Circle)other);
		//else if(other instanceof World)
		//	this.collision((World)other);
	}
	
}
