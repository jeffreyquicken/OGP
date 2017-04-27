package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public class Asteroid extends MinorPlanet{
	public Asteroid(double x, double y, double xVel,double yVel,double radius)throws IllegalArgumentException{
		super(x,y,xVel,yVel,radius);
	}
	
	public void collision(Ship ship){
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
