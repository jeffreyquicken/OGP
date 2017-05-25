package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

/**
 * A class of planetoids.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 *
 *@invar The planetoid can have the distance it has traveled so far as distance.
 *		 |canHaveAsDistance(this.getDistanceTraveleld())
 */
public class Planetoid extends MinorPlanet {
	
	/**
	 * Creates a new planetoid with a given position,velocity,radius and distance it has traveled.
	 * 
	 * @param x
	 * 		  The x coordinate of the planetoid.
	 * @param y
	 * 		  The y coordinate of the planetoid.
	 * @param xVel
	 * 		  The x velocity of the planetoid.
	 * @param yVel
	 * 		  The y velocity of the planetoid.
	 * @param radius
	 * 		  The radius of the planetoid.
	 * @param distanceTraveled
	 * 		  The distance the planetoid has traveled.
	 * @effect Creates a new MinorPlanet with a given position, velocity,radius and mass.
	 * 		  |MinorPlanet(x,y,xVel,yVel,radius,(4.0/3.0)*Math.PI*Math.pow(radius, 3)*getDensity())
	 * @post The initial radius of the planetoid is set to the radius parameter.
	 * 		  |this.getInitialRadius() == radius
	 * @post The traveled distance of the planetoid is set to distanceTraveled.
	 * 		  |this.getDistanceTraveled() == distanceTraveled
	 */
	public Planetoid(double x, double y, double xVel,double yVel,double radius, double distanceTraveled){
		super(x,y,xVel,yVel,radius,(4.0/3.0)*Math.PI*Math.pow(radius, 3)*getDensity());
		this.initialRadius = radius;
		this.setDistanceTraveled(distanceTraveled);
	}
	
	/**
	 * Terminates the planetoid.
	 * 
	 * @post The planetoid is terminated
	 * 		 |this.isTerminated() == true
	 * @effect If the planetoid is positioned on a world, it is removed from that world.
	 * 		  |if(this.getWorld() != null) then this.getWorld().remove(this) 
	 * @effect If the radius of the planetoid is greater then or equal to 30 , 
	 * 		   it spawns 2 asteroids with half the radius of the planetoid.
	 * 		  |@see implementation
	 * 
	 */
	@Override
	public void terminate(){
		if(this.getWorld() != null){
			World world = this.getWorld();
			if(this.getRadius()>=30){
				double direction = Math.random();
				double otherDirection = Math.sqrt(1-Math.pow(direction,2));
				world.remove(this);
				world.add(
						new Asteroid(this.getPosX()+direction*this.getRadius()/2,this.getPosY()+otherDirection*this.getRadius()/2,
								1.5*this.getVelVector().length()*direction,1.5*this.getVelVector().length()*otherDirection,this.getRadius()/2));
				world.add(
						new Asteroid(this.getPosX()-direction*this.getRadius()/2,this.getPosY()-otherDirection*this.getRadius()/2,
								-1.5*this.getVelVector().length()*direction,-1.5*this.getVelVector().length()*otherDirection,this.getRadius()/2));
			}
			else
				world.remove(this);
		}
		this.setTerminated(true);
	}
	
	private static double density = 0.917E12;
	
	/**
	 * Returns the density of the planetoids.
	 * 
	 * @return The density of the planetoids.
	 * 		  |result == density
	 */
	public static double getDensity(){
		return density;
	}
	
	/**
	 * Sets the density of the planetoids to a new density.
	 * 
	 * @param newDensity
	 * 		  The new density of the planetoids.
	 * @post If the new density is greater then zero and not NaN, 
	 * 		 the density of the planetoids is set to new density.
	 * 		 |getDensity() == newDensity
	 */
	public static void setDensity(double newDensity){
		if(newDensity>0 && !Double.isNaN(newDensity))
			density = newDensity;
	}
	
	private final double initialRadius;
	
	/**
	 * Returns the initial radius of the planetoid.
	 * 
	 * @return The initial radius of the planetoid
	 * 		   |result == this.initialRadius
	 */
	public final double getInitialRadius(){
		return this.initialRadius;
	}
	
	private double distanceTraveled;
	
	/**
	 * Checks if the planetoid can have a given distance as traveled distance.
	 * 
	 * @param newDistance
	 * 		  The new traveled distance.
	 * @return True if the radius decay the traveled distance causes doesn't make the radius smaller
	 * 		   then the minimum radius.
	 * 		  |result == getMinRadius()<=(this.getInitialRadius()-0.000001*newDistance)
	 * 				
	 */
	private boolean canHaveAsDistance(double newDistance){
		return getMinRadius()<=(this.getInitialRadius()-0.000001*newDistance);
	}
	
	/**
	 * Sets the distance the planetoid has traveled to a given distance.
	 * 
	 * @param newDistance
	 * 		  The new traveled distance.
	 * @effect If the planetoid can not have the distance as traveled distance, it is terminated.
	 * 		   Otherwise its radius is corrected.
	 * 		   |if(!this.canHaveAsDistance(newDistance)) then this.terminate()
	 * 		   |else then this.setRadius(this.getInitialRadius()-0.000001*this.getDistanceTraveled());
	 * @post If the planetoid can have the distance as traveled distance, the traveled distance is set to newDistance.
	 * 		 |this.getDistanceTraveled() == newDistance
	 */
	public void setDistanceTraveled(double newDistance){
		if(!this.canHaveAsDistance(newDistance))
			this.terminate();
		else{
			this.distanceTraveled = newDistance;
			this.setRadius(this.getInitialRadius()-0.000001*this.getDistanceTraveled());
		}
	}
	
	/**
	 * Returns the distance the planetoid has traveled.
	 * 
	 * @return The distance the planetoid has traveled.
	 * 		   |result == this.distanceTraveled.
	 */
	public double getDistanceTraveled(){
		return this.distanceTraveled;
	}
	
	/**
	 * Updates the distance the planetoid has traveled during a given time.
	 * 
	 * @param dt
	 * 		  The amount of time the planetoid moves.
	 * @effect The distance the planetoid has traveled is set to the sum of
	 * 		   the length of the velocity vector multiplied with the time and
	 * 		   the current distance.
	 * 		   |this.setDistanceTraveled(this.getDistanceTraveled()+this.getVelVector().multiply(dt).length())
	 */
	public void updateDistanceTraveled(double dt) {
		if(dt<0)
			dt=0;
		double newDistance = this.getDistanceTraveled()+this.getVelVector().multiply(dt).length();
		this.setDistanceTraveled(newDistance);
	}
	
	/**
	 * Do a collision between a planetoid and a ship.
	 * 
	 * @effect The ship is teleported to a random location in the world.
	 * 		   |ship.teleportToRandomLocation()
	 * @effect If ship collides with an object in the world after teleportation, it collides with that object.
	 * 		   |if ship.collides(object) for Object object in this.getWorld().getWorldEntities() then ship.collision(object)
	 * @throws NullPointerException
	 * 		   The ship is null
	 * 		   |ship == null
	 * @throws IllegalArgumentException
	 * 		   The ship and planetoid are not positioned in the same world.
	 * 		   |this.getWorld() != ship.getWorld()
	 */
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
							ship.collision((MinorPlanet)object);
						break;
					}	
				}
			}
		}
	}
	
}
