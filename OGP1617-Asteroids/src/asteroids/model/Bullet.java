package asteroids.model;

public class Bullet extends Circle {
	
	public Bullet(double x, double y, double xVelocity, double yVelocity, double radius){
		super(x,y,xVelocity,yVelocity,radius,minRadius);
	}
	
	private static double minRadius =1;
	

}
