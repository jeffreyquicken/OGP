package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public class Bullet extends Circle {
	
	@Raw
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius){
		super(x,y,xVelocity,yVelocity,radius);
	}
	
	private static double minRadius =1;
	
	protected boolean isValidRadius(double newRadius){
		return newRadius>=minRadius;
	}
	private Ship owner;
	private Ship ship;
	private World world;
	
	@Basic
	public Ship getOwner(){
		return this.owner;
	}
	
	private boolean canHaveAsOwner(Ship newOwner){
		return (!newOwner.isTerminated() && this.getDistanceBetween(newOwner)+this.getRadius()<=0);
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
	public World getWorld(){
		return this.world;
	}
	
	@Basic
	public Ship getShip(){
		return this.ship;
	}
	
	private boolean canHaveAsShip(Ship newShip){
		return this.getWorld() == null;
	}
	
	private boolean canHaveAsWorld(World newWorld){
		return this.getShip() == null;
	}
	
	@Basic
	public void setShip(Ship newShip) throws IllegalArgumentException{
		if(!canHaveAsShip(newShip))
			throw new IllegalArgumentException();
		else
			this.ship = newShip;
	}
	
	@Basic
	public void setWorld(World newWorld){
		if(!canHaveAsWorld(newWorld))
			throw new IllegalArgumentException();
		else
			this.world = newWorld;
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
	
	private void setAmountOfBounces(double newAmount){
		this.amountOfBounces = newAmount;
	}
	
	public void increaseAmountOfBounces(){
		this.setAmountOfBounces(this.getAmountOfBounces()+1);
		if(this.getAmountOfBounces()>=this.maxBounces)
			this.terminate();
	}
	
	public void collision(Bullet bullet){
		if(bullet == null){
			throw new NullPointerException();
		}
		else if (bullet == this)
			throw new IllegalArgumentException();
		else{
			if(this.overlaps(bullet)){
				this.terminate();
				bullet.terminate();
			}
		}
	}
	
	public void collision(Ship ship){
		ship.collision(this);
	}
	
}
