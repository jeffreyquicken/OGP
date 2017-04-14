package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
/**
 * A class of bullets.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 *
 */
public class Bullet extends Circle {
	
	@Raw
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius){
		super(x,y,xVelocity,yVelocity,radius);
	}
	
	private static double minRadius = 1;
	
	@Immutable
	public static double getMinRadius(){
		return minRadius;
	}
	
	@Basic
	public static void setMinRadius(double newMinRadius){
		minRadius = newMinRadius;
	}
	
	protected boolean isValidRadius(double newRadius){
		return newRadius>=minRadius;
	}
	private Ship owner = null;
	private Ship ship = null;
	
	@Basic
	public Ship getOwner(){
		return this.owner;
	}
	
	private boolean canHaveAsOwner(Ship newOwner){
		//return (!newOwner.isTerminated() && this.getDistanceBetween(newOwner)+this.getRadius()<=0);
		return true;
	}
	
	@Basic
	public void setOwner(Ship newOwner) throws IllegalArgumentException,NullPointerException{
		if(newOwner == null)
			throw new NullPointerException();
		else if(!canHaveAsOwner(newOwner))
			throw new IllegalArgumentException();
		else
			this.owner = newOwner;
	}
	
	@Basic
	public Ship getShip(){
		return this.ship;
	}
	
	private boolean canHaveAsShip(Ship newShip){
		if(newShip != null)
			return this.getOwner() == newShip && !newShip.isTerminated();
		else
			return true;
	}
	
	@Basic
	public void setShip(Ship newShip) throws IllegalArgumentException{
		if(!canHaveAsShip(newShip))
			throw new IllegalArgumentException();
		else
			this.ship = newShip;
	}
	
	
	private static double density = 7.8E12;
	
	private final double mass = density*(4.0/3.0)*Math.pow(this.getRadius(), 3)*Math.PI;
	
	@Basic
	public static double getDensity(){
		return density;
	}
	
	@Basic
	public double getMass(){
		return this.mass;
	}
	
	private double amountOfBounces = 0;
	private final double maxBounces = 3;
	
	@Basic
	public double getAmountOfBounces(){
		return this.amountOfBounces;
	}
	
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
	 * @post The bullet is terminated if the new amount of bounces exceeds the maximum amount of bounces.
	 * 		 |if(this.getAmountOfBounces()+1>=maxBounces) then
	 * 		 |this.isTerminated()	 
	 */
	public void increaseAmountOfBounces(){
		this.setAmountOfBounces(this.getAmountOfBounces()+1);
		if(this.getAmountOfBounces()>=maxBounces)
			this.terminate();
			this.getWorld().remove(this);
	}
	
	/**
	 * Resolves a collision of a bullet and a bullet.
	 * 
	 * @param other
	 * 		  The other bullet to collide with this bullet.
	 * 
	 * @throws NullPointerException
	 * 		  The other bullet is null.
	 * 		  |other == null
	 * @throws IllegalArgumentException
	 * 		  The other bullet is this bullet.
	 * 		  |other == this
	 * 
	 * @post The world of this bullet and the other bullet is set to null.
	 * 		  |this.getWorld() == null && other.getWorld() == null
	 * @post Both bullets are terminated.
	 * 		  |this.isTerminated() && other.isTerminated()
	 * 
	 */
	public void collision(Bullet other) throws NullPointerException,IllegalArgumentException{
		if(other == null){
			throw new NullPointerException();
		}
		else if (other == this)
			throw new IllegalArgumentException();
		else{
				this.getWorld().remove(this);
				other.getWorld().remove(other);
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
	public void collision(Ship ship) throws NullPointerException{
		if(ship == null)
			throw new NullPointerException();
		else
			ship.collision(this);
	}
	
}
