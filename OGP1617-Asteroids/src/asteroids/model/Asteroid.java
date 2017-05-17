package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public class Asteroid extends MinorPlanet{
	public Asteroid(double x, double y, double xVel,double yVel,double radius)throws IllegalArgumentException{
		super(x,y,xVel,yVel,radius,(4.0/3.0)*Math.PI*Math.pow(radius, 3)*getDensity());
	}
	
	private static double density = 2.65E12;
	
	public static double getDensity(){
		return density;
	}
	
	public void shipCollision(Ship ship){
		if(ship == null){
			throw new NullPointerException();
		}
		else if (this.getWorld() != ship.getWorld())
			throw new IllegalArgumentException();
		else{
			if(this.getWorld().getWorldEntities().contains(ship))
				this.getWorld().remove(ship);
				ship.terminate();
		}
	}
}
