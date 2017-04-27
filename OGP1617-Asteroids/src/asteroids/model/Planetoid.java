package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public class Planetoid extends MinorPlanet {
	public Planetoid(double x, double y, double xVel,double yVel,double radius)throws IllegalArgumentException{
		super(x,y,xVel,yVel,radius);
		this.initialRadius = this.getRadius();
	}
	
	private final double initialRadius;
	
	private double distanceTravelled = 0;
	
	public void collision(Ship ship){
		if(ship == null){
			throw new NullPointerException();
		}
		else if (ship.getWorld() != this.getWorld())
			throw new IllegalArgumentException();
		else{
			ship.teleportToRandomLocation();
			for(Object object:this.getWorld().getWorldEntities()){
				if(object instanceof Circle){
					if(ship.collides((Circle)object)){
						if(object instanceof Bullet)
							ship.collision((Bullet)object);
						else if(object instanceof Ship)
							ship.collision((Ship)object);
						else
							ship.collides((MinorPlanet)object);
						break;
					}	
				}
			}
		}
	}

	
}
