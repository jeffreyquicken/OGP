package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public abstract class MinorPlanet extends Circle {
	
	public MinorPlanet(double x, double y, double xVel, double yVel, double radius) throws IllegalArgumentException{
		super(x,y,xVel,yVel,radius,(4/3)*Math.PI*Math.pow(radius, 3)*getDensity());
	}
	
	private static double minRadius = 5;
	
	public static double getMinRadius(){
		return minRadius;
	}
	
	protected boolean isValidRadius(double newRadius){
		return newRadius>=getMinRadius();
	}
	
	private static double density = 0.917E12;
	
	public static double getDensity(){
		return density;
	}
	
	public static void setDensity(double newDensity){
		density = newDensity;
	}
	
	/**
	 * Resolves the collision between two MinorPlanets
	 */
	public void collision(MinorPlanet otherMinor){
		if(otherMinor == null)
			throw new NullPointerException();
		else if(this.getWorld() != otherMinor.getWorld() || this == otherMinor)
			throw new IllegalArgumentException();
		else{
			Vector2D deltaV = this.getPosVector().substract(otherMinor.getPosVector());
			Vector2D deltaR = this.getVelVector().substract(otherMinor.getVelVector());
			double sigma = deltaR.length();
			double J = 2*this.getMass()*otherMinor.getMass()*deltaV.scalarProduct(deltaR)/((this.getMass()+otherMinor.getMass())*sigma);
			double Jx = J*(deltaR.getX())/sigma;
			double Jy = J*(deltaR.getY())/sigma;
			this.setVel(this.getVelX()+(Jx/this.getMass()), this.getVelY()+(Jy/this.getMass()));
			otherMinor.setVel(otherMinor.getVelX()-(Jx/otherMinor.getMass()), otherMinor.getVelY()-(Jy/otherMinor.getMass()));
		}
	}
	
	public void collision(Bullet bullet){
		if(bullet == null){
			throw new NullPointerException();
		}
		else if (bullet.getWorld() != this.getWorld())
			throw new IllegalArgumentException();
		else{
			if(this.getWorld().getWorldEntities().contains(this))
				this.getWorld().remove(this);
			if(this.getWorld().getWorldEntities().contains(bullet))
				this.getWorld().remove(bullet);
				this.terminate();
				bullet.terminate();
		}
	}
	
	public abstract void collision(Ship ship);
}
