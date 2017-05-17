package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public class Planetoid extends MinorPlanet {
	public Planetoid(double x, double y, double xVel,double yVel,double radius, double distanceTravelled)throws IllegalArgumentException{
		super(x,y,xVel,yVel,radius,(4.0/3.0)*Math.PI*Math.pow(radius, 3)*getDensity());
		this.initialRadius = radius;
		this.setDistanceTraveled(distanceTravelled);
	}
	
	@Override
	public void terminate(){
		if(this.getWorld() != null){
			World world = this.getWorld();
			if(this.getRadius()>=30){
				double direction = Math.random();
				double otherDirection = Math.sqrt(1-Math.pow(direction,2));
				world.remove(this);
				this.setTerminated(true);
				world.add(
						new Asteroid(this.getPosX()+direction*this.getRadius()/2,this.getPosY()+otherDirection*this.getRadius()/2,
								1.5*this.getVelVector().length()*direction,1.5*this.getVelVector().length()*otherDirection,this.getRadius()/2));
				world.add(
						new Asteroid(this.getPosX()-direction*this.getRadius()/2,this.getPosY()-otherDirection*this.getRadius()/2,
								-1.5*this.getVelVector().length()*direction,-1.5*this.getVelVector().length()*otherDirection,this.getRadius()/2));
			}
			else{
				world.remove(this);
				this.setTerminated(true);
			}
		}
		else
			this.setTerminated(true);
	}
	
	private static double density = 0.917E12;
	
	public static double getDensity(){
		return density;
	}
	
	private final double initialRadius;
	
	public final double getInitialRadius(){
		return this.initialRadius;
	}
	
	private double distanceTravelled;
	
	private boolean canHaveAsDistance(double newDistance){
		return getMinRadius()>=(this.getInitialRadius()-0.000001*newDistance);
	}
	
	public void setDistanceTraveled(double newDistance){
		if(!this.canHaveAsDistance(newDistance))
			this.terminate();
		else{
			this.distanceTravelled = newDistance;
			this.setRadius(this.getInitialRadius()-0.000001*this.getDistanceTraveled());
		}
	}
	
	public double getDistanceTraveled(){
		return this.distanceTravelled;
	}
	
	public void updateDistanceTraveled(double dt) {
		if(dt<0)
			dt=0;
		double newDistance = this.getDistanceTraveled()+this.getVelVector().multiply(dt).length();
		this.setDistanceTraveled(newDistance);
	}
	
	public void shipCollision(Ship ship){
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
