package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public abstract class MinorPlanet extends Circle {
	
	public MinorPlanet(double x, double y, double xVel, double yVel, double radius,double mass) throws IllegalArgumentException{
		super(x,y,xVel,yVel,radius,mass);
	}
	
	private static double minRadius = 5;
	
	public static double getMinRadius(){
		return minRadius;
	}
	
	protected boolean isValidRadius(double newRadius){
		return newRadius>=getMinRadius();
	}
		/**
	 * Resolves the collision between two MinorPlanets
	 */
	public void planetCollision(MinorPlanet otherMinor){
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
				this.shipCollision((Ship)objectCast);
			else if(objectCast instanceof Bullet)
				((Bullet)objectCast).collision(this);
			else if(objectCast instanceof MinorPlanet)
				this.planetCollision((MinorPlanet)objectCast);
		}
	}
	
	public abstract void shipCollision(Ship ship);
}
