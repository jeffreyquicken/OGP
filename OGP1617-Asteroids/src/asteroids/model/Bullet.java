package asteroids.model;

public class Bullet extends Circle {
	
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius){
		super(x,y,xVelocity,yVelocity,radius,minRadius);
	}
	
	private static double minRadius =1;
	private Ship owner;
	private Object holder;
	
	public Ship getOwner(){
		return this.owner;
	}
	
	public void setOwner(Ship newOwner){
		this.owner = newOwner;
	}
	
	public Object getHolder(){
		return this.holder;
	}
	
	public void setHolder(Object newHolder){
		this.holder = newHolder;
	}
	
	private static double density = 7.8E12;
	private final double mass = density*(4.0/3.0)*Math.pow(this.getRadius(), 3)*Math.PI;
	
	public static double getDensity(){
		return density;
	}
	
	public double getMass(){
		return this.mass;
	}
	
	private double amountOfBounces = 0;
	private final double maxBounces = 3;
	
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
