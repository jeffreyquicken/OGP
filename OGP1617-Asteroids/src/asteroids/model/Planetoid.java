package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public class Planetoid extends MinorPlanet {
	public Planetoid(double x, double y, double xVel,double yVel,double radius)throws IllegalArgumentException{
		super(x,y,xVel,yVel,radius);
		this.initialRadius = this.getRadius();
	}
	
	@Override
	public void terminate(){
		this.setTerminated(true);
		if(this.getWorld() != null && this.getRadius()>=30){
			double direction = Math.random();
			double otherDirection = Math.sqrt(1-Math.pow(direction,2));
			this.getWorld().add(
					new Asteroid(this.getPosX()+direction*this.getRadius()/2,this.getPosY()+otherDirection*this.getRadius()/2,
							1.5*this.getVelVector().length()*direction,1.5*this.getVelVector().length()*otherDirection,this.getRadius()/2));
			this.getWorld().add(
					new Asteroid(this.getPosX()-direction*this.getRadius()/2,this.getPosY()-otherDirection*this.getRadius()/2,
							-1.5*this.getVelVector().length()*direction,-1.5*this.getVelVector().length()*otherDirection,this.getRadius()/2));
		}
	}
	
	private final double initialRadius;
	
	private double distanceTravelled = 0;
	
	public double getDistanceTraveled(){
		return this.distanceTravelled;
	}
	
	public void updateDistanceTraveled(double dt) {
		if(dt<0)
			dt=0;
		else
			this.distanceTravelled+=this.getVelVector().length()*dt;
	}
	
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
	
	@Override
	public void move(double time)throws IllegalArgumentException {
		if(time<0)
			throw new IllegalArgumentException();
		else
			this.setPosX(this.getPosX() + this.getVelX()*time);
			this.setPosY(this.getPosY() + this.getVelY()*time);
			this.distanceTravelled+=this.getVelVector().length()*time;
			try{this.setRadius(0.999999*this.getRadius());}
			catch(IllegalArgumentException e){
				this.terminate();
				this.setWorld(null);
			}
	}

	
}
