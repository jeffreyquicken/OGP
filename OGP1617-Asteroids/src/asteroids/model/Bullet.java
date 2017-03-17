package asteroids.model;

public class Bullet extends Circle {
	
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius){
		super(x,y,xVelocity,yVelocity,radius,minRadius);
	}
	
	private static double minRadius =1;
	private Ship owner;
	
	private static double density = 7.8E12;
	private double mass = density*(4.0/3.0)*Math.pow(this.getRadius(), 3)*Math.PI;
	
	public double getMass(){
		return this.mass;
	}
	
}
