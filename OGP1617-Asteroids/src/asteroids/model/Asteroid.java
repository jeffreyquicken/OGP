package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
/**
 * A class of asteroids.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 * 
 *
 */
public class Asteroid extends MinorPlanet{
	/**
	 * Creates a new asteroid with a given position, velocity and radius.
	 * 
	 * @param x
	 * 		  The x coordinate of the asteroid.
	 * @param y
	 * 		  The y coordinate of the asteroid.
	 * @param xVel
	 * 		  The x velocity of the asteroid.
	 * @param yVel
	 * 		  The y velocity of the asteroid.
	 * @param radius
	 * 		  The radius of the asteroid.
	 * @effect Creates a new MinorPlanet with a given position, velocity,radius and mass.
	 * 		  |MinorPlanet(x,y,xVel,yVel,radius,(4.0/3.0)*Math.PI*Math.pow(radius, 3)*getDensity())
	 */
	public Asteroid(double x, double y, double xVel,double yVel,double radius)throws IllegalArgumentException{
		super(x,y,xVel,yVel,radius,(4.0/3.0)*Math.PI*Math.pow(radius, 3)*getDensity());
	}
	
	/**
	 * Terminates the asteroid.
	 * 
	 * @post The asteroid is terminated.
	 * 		 |this.isTerminated() == true
	 * @effect If the asteroid is positioned on a world, it is removed from that world.
	 * 		  |if(this.getWorld() != null) then this.getWorld().remove(this)
	 */
	@Override
	public void terminate(){
		this.setTerminated(true);
		if(this.getWorld() != null){
			this.getWorld().remove(this);
		}
	}
	
	private static double density = 2.65E12;
	
	/**
	 * Returns the density of the asteroid.
	 * 
	 * @return The density of the asteroid.
	 * 		  |result == density
	 */
	public static double getDensity(){
		return density;
	}
	
	/**
	 * Sets the density of the asteroids to a new density.
	 * 
	 * @param newDensity
	 * 		  The new density of the asteroids.
	 * @post If the new density is greater then zero and not NaN, 
	 * 		 the density of the asteroids is set to new density.
	 * 		 |getDensity() == newDensity
	 */
	public static void setDensity(double newDensity){
		if(newDensity>0 && !Double.isNaN(newDensity))
			density = newDensity;
	}
	
	/**
	 * Do a collision between this asteroid and a ship.
	 * 
	 * @effect The ship is terminated.
	 * 		   |ship.terminate()
	 * @throws NullPointerException
	 * 		   The ship is null
	 * 		   |ship == null
	 * @throws IllegalArgumentException
	 * 		   The ship and asteroid are not positioned in the same world.
	 * 		   |this.getWorld() != ship.getWorld()
	 */
	public void collision(Ship ship) throws NullPointerException,IllegalArgumentException{
		if(ship == null){
			throw new NullPointerException();
		}
		else if (this.getWorld() != ship.getWorld())
			throw new IllegalArgumentException();
		else{
			if(this.getWorld().getWorldShips().contains(ship))
				ship.terminate();
		}
	}
}
