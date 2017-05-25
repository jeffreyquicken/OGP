package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

/**
 * A class of minor planets.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 *
 */
public abstract class MinorPlanet extends Circle {
	
	/**
	 * Creates a new minor planet with a given position,velocity,radius and mass.
	 * 
	 * @param x
	 * 		  The x coordinate of the minor planet.
	 * @param y
	 * 		  The y coordinate of the minor planet.
	 * @param xVel
	 * 		  The x velocity of the minor planet.
	 * @param yVel
	 * 		  The y velocity of the minor planet.
	 * @param radius
	 * 		  The radius of the minor planet.
	 * @param mass
	 * 		  The mass of the minor planet.
	 * @effect Creates a new Circle with a given position,velocity,radius and mass.
	 * 		  |Circle(x,y,xVel,yVel,radius,mass)
	 */
	public MinorPlanet(double x, double y, double xVel, double yVel, double radius,double mass){
		super(x,y,xVel,yVel,radius,mass);
	}
	
	private static double minRadius = 5;
	
	/**
	 * Returns the minimum radius of the minor planets.
	 * 
	 * @return The minimum radius of the minor planets.
	 * 		   |result == minRadius
	 */
	public static double getMinRadius(){
		return minRadius;
	}
	
	/**
	 * Sets the minimum radius of the minor planets to a new minimum radius.
	 * 
	 * @param newRadius 
	 *		  The new minimum radius of the minor planets.
	 * @post If the new minimum radius is greater then zero and not NaN, 
	 * 		 the minimum density of the asteroids is set to new minimum radius.
	 * 		 |getMinRadius() == newRadius
	 * 	
	 */
	public static void setMinRadius(double newRadius){
		if(newRadius>0 && !Double.isNaN(newRadius))
			minRadius = newRadius;
	}
	
	/**
	 * Checks if the radius is a valid radius.
	 * 
	 * @param newRadius
	 * 		  The radius to be checked.
	 * @return True if the radius is greater then or equal to the minimum radius.
	 * 		   |result == newRadius>=getMinRadius()
	 */
	protected boolean isValidRadius(double newRadius){
		return newRadius>=getMinRadius();
	}
	/**
	 * Do a collision between two MinorPlanets
	 * 
	 * @param otherMinor
	 * 		  The minor planet that collides with this minor planet.
	 * 
	 * @post The velocities of both the minor planets are adjusted.
	 * 		 |@see implementation
	 * 
	 * @throws NullPointerException
	 * 		   The other minor planet is null
	 * 		   |otherMinor == null
	 * @throws IllegalArgumentException
	 * 		   This minor planet is not in the same world as this minor planet 
	 * 		   or the other minor planet is this minor planet.
	 * 		   |this.getWorld() != otherMinor.getWorld() || this == otherMinor
	 * 		
	 * 
	 */
	public void collision(MinorPlanet otherMinor) throws NullPointerException,IllegalArgumentException{
		if(otherMinor == null)
			throw new NullPointerException();
		else if(this.getWorld() != otherMinor.getWorld() || this == otherMinor)
			throw new IllegalArgumentException();
		else{
			Vector2D deltaV = this.getVelVector().substract(otherMinor.getVelVector());
			Vector2D deltaR = this.getPosVector().substract(otherMinor.getPosVector());
			double sigma = this.getRadius() + otherMinor.getRadius();
			double J = 2*this.getMass()*otherMinor.getMass()*deltaV.scalarProduct(deltaR)/((this.getMass()+otherMinor.getMass())*sigma);
			double Jx = J*(deltaR.getX())/sigma;
			double Jy = J*(deltaR.getY())/sigma;
			this.setVel(this.getVelX()-(Jx/this.getMass()), this.getVelY()-(Jy/this.getMass()));
			otherMinor.setVel(otherMinor.getVelX()+(Jx/otherMinor.getMass()), otherMinor.getVelY()+(Jy/otherMinor.getMass()));
		}
	}
	
	/**
	 * Do a collision between an minorplanet and an object
	 * @ see implementation
	 */
	public void collision(Object object){
		if(object == null)
			throw new NullPointerException();
		if(this == object)
			throw new IllegalArgumentException();
		if(object instanceof Circle){
			Circle objectCast = (Circle)object;
			if(this.getWorld() != objectCast.getWorld())
				throw new IllegalArgumentException();
			if(objectCast instanceof Ship)
				this.collision((Ship)objectCast);
			else if(objectCast instanceof Bullet)
				((Bullet)objectCast).collision(this);
			else if(objectCast instanceof MinorPlanet)
				this.collision((MinorPlanet)objectCast);
		}
	}
	
	/**
	 * Do the collision between a minor planet and a ship.
	 * @param ship
	 * 		  The ship that collides with this minor planet.
	 */
	public abstract void collision(Ship ship);
}
