package asteroids.model;
import be.kuleuven.cs.som.taglet.*;
import be.kuleuven.cs.som.annotate.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import asteroids.model.Circle;

/**
 * A class of ships.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 * 
 * @invar The orientation of the ship is a valid orientation.
 * 		  |isValidOrientation(orientation)
 * @invar If a ship has a bullet loaded, the ship of the bullet is this ship.
 * 		  |bullet.getShip() == this for Bullet bullet in this.getBullets()
 * @invar The ship can have all of its bullets.
 * 		  |this.canHaveAsBullet(bullet) for Bullet bullet in this.getBullets()
 *
 */
public class Ship extends Circle {
	
	/**
	 * Initializes a Ship with a given x-coordinate,y-coordinate,x-velocity,y-velocity,radius and orientation.
	 * 
	 * @param x
	 * 		  The initial X-coordinate of this ship.
	 * @param y
	 * 		  The initial Y-coordinate of this ship.
	 * @param xVelocity
	 * 		  The initial velocity in the X direction of this ship.
	 * @param yVelocity
	 * 		  The initial velocity in the Y direction of this ship.
	 * @param radius
	 * 		  The initial radius of this ship.
	 * @param orientation
	 * 		  The initial orientation of the ship.
	 * 
	 * @effect Creates a new Circle with a given position,velocity, radius and mass.
	 * 		  |return new Circle(x,y,xVelocity,yVelocity,radius,mass)
	 * @effect The orientation of the ship is set to orientation.
	 * 		  |setOrientation(orientation)
	 * 
	 */
	
	@Raw
	public Ship(double x, double y, double xVelocity, double yVelocity, double radius,double orientation, double mass){
		super(x,y,xVelocity,yVelocity,radius,mass);
		this.setOrientation(orientation);
	}
	
	/**
	 * Terminate the ship and all of its bullets.
	 * @post The ship is terminated.
	 * 		 |new.isTerminated()
	 * @post All of the ships bullets are terminated and they don't have a ship.
	 * 		 |(bullet.isTerminated() && bullet.getShip() == null) for Bullet bullet in this.getBullets()
	 * @post The collection of bullets of the ship is empty.
	 * 		 |new.getBullets().isEmpty()
	 */
	@Override
	@Basic
	public void terminate(){
		this.setTerminated(true);
		HashSet<Bullet> IteratorSet = (HashSet<Bullet>)this.bullets.clone();
		for(Bullet bullet: IteratorSet){
			bullet.terminate();
			bullet.setShip(null);
		}
		bullets.clear();
		if(this.getWorld() != null)
			this.getWorld().remove(this);
	}
	
	private double orientation;
	
	/**
	 * Checks if a given orientation is a valid orientation.
	 * 
	 * @param orient
	 * 		The orientation of which is checked if it is a valid orientation.
	 * @return
	 * 		Returns true if the orientation is greater or equal then zero and
	 * 		smaller or equal then 2*PI.
	 * 		|result == ((orient>=0) && (orient<=2*Math.PI))
	 */
	private static boolean isValidOrientation(double orient){
		return ((orient>=0) && (orient<=2*Math.PI));
	}
	
	/**
	 * Returns the orientation of this ship.
	 * 
	 * @return
	 * 		  Returns the orientation of this ship.
	 * 		  |result == this.orientation
	 */
	@Basic
	public double getOrientation(){
		return this.orientation;
	}
	
	/**
	 * Sets the orientation of this ship to a new given orientation.
	 * 
	 * @param newOrientation
	 * 		The new orientation of this ship.
	 * @pre 
	 * 		The orientation is a valid orientation.
	 * 		|isValidOrientation(newOrientation)
	 * @post
	 * 		The new orientation of this ship is equal to the given orientation.
	 * 		|new.getOrientation() == newOrientation
	 */
	@Basic
	public void setOrientation(double newOrientation){
		assert isValidOrientation(newOrientation);
		this.orientation = newOrientation;
	}
	
	private static double minRadius = 10.0;
	
	/**
	 * Sets the minimum radius of the ships to newMinRadius
	 * @param newMinRadius
	 * 		  The new minimum radius of the ships.
	 * @post The minimum radius of the ship is newMinRadius.
	 * 		 |getMinRadius() == newMinRadius
	 */
	@Basic
	public static void setMinRadius(double newMinRadius){
		minRadius = newMinRadius;
	}
	
	/**
	 * Checks if the given radius is a valid radius.
	 * @param newRadius
	 * 		  The radius that has to be validated.
	 * @return True if the radius is greater then or equal to minRadius.
	 * 		   |result == newRadius>=minRadius
	 */
	protected boolean isValidRadius(double newRadius){
		return newRadius>=minRadius;
	}
	
	/**
	 * Returns the minimum radius of the ships.
	 * @return
	 * 		  The minimum radius of the ships.
	 * 		  |result == minRadius
	 */
	@Immutable
	@Basic
	public static double getMinRadius(){
		return minRadius;
	}
	
	/**
	 * Corrects the mass of the ship.
	 * @param newMass
	 * 		  The mass that has to be corrected.
	 * @return The corrected mass.
	 * 		   |@see implementation
	 */
	@Override
	protected double massCorrection(double newMass){
		if(newMass<	(4.0/3.0)*Math.PI*Math.pow(this.getRadius(),3)*getDensity())
			return 	(4.0/3.0)*Math.PI*Math.pow(this.getRadius(),3)*getDensity();
		else
			return newMass;

	}
	private static double density = 1.42E12;
	
	/**
	 * Returns the minimum density of the ships.
	 * @return
	 * 		  The minimum density of the ships.
	 * 		  |result == density
	 */
	@Immutable
	@Basic
	public static double getDensity(){
		return density;
	}
	
	/**
	 * Sets the density of the ships to newDensity.
	 * @param newDensity
	 * 		  The new density of the ships.
	 * 		  |getDensity() == newDensity
	 */
	@Basic
	public static void setDensity(double newDensity){
		density = newDensity;
	}
	
	/**
	 * Returns the total mass of the ship (including all loaded bullets)
	 * @return
	 * 	     The total mass of the ship.
	 * 		 | result == this.getMass() + bullet.getMass() for Bullet bullet in this.getBullets()
	 */
	 @Basic
	 public double getTotalMass(){
		double totalMass = this.getMass();
		if(!this.getBullets().isEmpty()){
			for(Bullet bullet:this.getBullets()){
				totalMass+=bullet.getMass();
			}
		}
		return totalMass;
	}
	
	
	/**
	 * Increases the orientation of this ship with a given angle.
	 * 
	 * @param angle
	 * 		  	   The angle to be added to the current angle of this ship.
	 * @pre 
	 * 		The sum of the current orientation and the added angle is a valid orientation.
	 * 		|isValidOrientation(this.getOrientation()+angle)
	 *@post 
	 *		The orientation of this ship is set to the sum of the current orientation and the added angle.
	 *		|new.getOrientation() == (this.getOrientation() + angle)
	 */
	public void turn(double angle){
		assert isValidOrientation(this.getOrientation()+angle);
		this.setOrientation(this.getOrientation()+angle);
	}
	
	/**
	 * Thrusts this ship forward with a given acceleration.
	 * @param amount
	 * 		  The amount of acceleration of this ship.
	 * @post If the amount is a negative number, the amount is set to zero.
	 * 		 Then the X-velocity and Y-velocity of this ship is increased with a given amount of acceleration.
	 * 		 |if(amount<0) then amount = 0
	 * @effect this.setVel(this.getVelX()+acceleration*Math.cos(this.getOrientation()),this.getVelY()+acceleration*Math.sin(this.getOrientation()))
	 *
	 */
	public void thrust(double amount){
		if(amount < 0)
			amount = 0;
		this.setVel(this.getVelX()+amount*Math.cos(this.getOrientation()), this.getVelY()+amount*Math.sin(this.getOrientation()));
	}
	
	private boolean thrusterActive = false;
	
	/**
	 * Sets the thruster status of the ship to newStatus
	 * @param newStatus
	 * 		  The new thruster status of the ship.
	 * @post The thruster status of the ship is newStatus.
	 * 		 |new.getThrusterStatus() == newStatus
	 */
	@Basic
	public void setThruster(boolean newStatus){
		this.thrusterActive = newStatus;
	}
		
	
	private double thrusterForce = 1.1E18;
	
	/**
	 * Returns the thruster force.
	 * 
	 * @see implementation
	 */
	@Basic
	public double getThrusterForce(){
		return this.thrusterForce;
	}
	
	/**
	 * Sets the thruster force to a new Force
	 * @param newForce
	 * 		  The new Thruster force.
	 * @post The thruster force is the new thruster force.
	 * 		 |this.getThrusterForce() == newForce
	 */
	@Basic
	public void setThrusterForce(double newForce){
		this.thrusterForce = newForce;
	}
	
	/**
	 * Returns the thruster status.
	 * 
	 * @see implementation
	 */
	@Basic
	public boolean getThrusterStatus(){
		return thrusterActive;
	}
	
	/**
	 * Returns the amount of acceleration of the ship.
	 * @return
	 * 		  The amount of acceleration of the ship. It is 0 if the thruster is not activated.
	 * 		  |if(thrusterStatus()) then result == this.getThrusterForce()/this.getTotalMass()
	 * 		  |else then result == 0
	 */
	@Basic
	public double getAcceleration(){
		if(this.getThrusterStatus())
			return this.getThrusterForce()/this.getTotalMass();
		else
			return 0;
	}
	
	/**
	 * Accelerates the ship for a given amount of time.
	 * @param t
	 * 		  The amount of time the ship is thrusted forward.
	 * @effect this.thrust(this.getAcceleration*t)
	 * 		   Thrusts the ship forward with the acceleration derived from getAcceleration() with a duration t.
	 */
	public void accelerate(double t){
		if(t<0)
			t=0;
		this.thrust(this.getAcceleration()*t);
	}
	
	
	private HashSet<Bullet> bullets = new HashSet<Bullet>();
	
	/**
	 * Adds a bullet to the ships collection of bullets.
	 * @param bullet
	 * 		  The bullet to be added to the ship.
	 * @post The bullets center is located at the same position as the ships center.
	 * 		 |(this.getPosX() == bullet.getPosX()&&(this.getPosY() == bullet.getPosY())
	 * @post The collection of bullets of the ship contains the bullet.
	 * 		 |this.getBullets.contains(bullet)
	 * @post The owner of the bullet is this ship
	 * 		 |bullet.getOwner() == this
	 * @post The ship of the bullet is this ship
	 * 		 |bullet.getShip() == this
	 * @post The bullet is not in a world.
	 * 		 |bullet.getWorld() == null
	 * @throws NullPointerException
	 * 		  The bullet is null.
	 * 		  |bullet == null
	 * @throws IllegalArgumentException
	 * 		   The ship can not have this bullet as a bullet.
	 * 		   |!canHaveAsBullet(bullet)
	 */
	@Raw
	public void addBullet(Bullet bullet)throws NullPointerException,IllegalArgumentException{
		if(bullet == null)
			throw new NullPointerException();
		if(!canHaveAsBullet(bullet))
			throw new IllegalArgumentException();
		else{
			bullet.setPosX(this.getPosX());
			bullet.setPosY(this.getPosY());
			this.bullets.add(bullet);
			bullet.setOwner(this);
			bullet.setShip(this);
			bullet.setWorld(null);
		}
	}
	
	/**
	 * Checks if the ship can have a bullet as bullet.
	 * 
	 * @param bullet
	 * 		  The bullet to be validated.
	 * @see implementation
	 */
	private boolean canHaveAsBullet(Bullet bullet){
		return (!bullet.isTerminated() && !this.isTerminated()
				&& this.withinThisCircle(bullet) && (bullet.getWorld() == null || this.getWorld() == bullet.getWorld()) );
	}
	
	/**
	 * Adds a collection of bullets to this ship.
	 * @param bulletCollection
	 * 		  The collection of bullets.
	 * @effect this.addBullet(bullet) for Bullet bullet in bulletCollection
	 * 		  Adds every bullet in bulletCollection.
	 */
	@Raw
	public void addBullet(Collection<Bullet> bulletCollection){
		for(Bullet bullet:bulletCollection){
			if(!canHaveAsBullet(bullet))
				throw new IllegalArgumentException();
		}
		for(Bullet bullet:bulletCollection){
			this.addBullet(bullet);
		}
	}
	
	/**
	 * Removes a bullet from this ship.
	 * 
	 * @param bullet
	 * 		  The bullet that has to be removed.
	 * @throws IllegalArgumentException
	 * 		   The ship doesn't have this bullet loaded
	 * 		   |!this.getBullets().contains(bullet)
	 * @throws NullPointerException
	 * 		   The bullet is null
	 * 		   |bullet == null
	 * @post The ship does not have this bullet loaded.
	 * 		 |!this.getBullets().contains(bullet)
	 * @post The bullet does not have a ship.
	 * 		 |bullet.getShip() == null
	 */
	@Raw
	public void removeBullet(Bullet bullet) throws IllegalArgumentException ,NullPointerException{
		if(bullet == null)
			throw new NullPointerException();
		else if(!this.getBullets().contains(bullet))
			throw new IllegalArgumentException();
		else{
			this.bullets.remove(bullet);
			bullet.setShip(null);
		}
	}
	
	/**
	 * Returns the set of bullets loaded on the ship.
	 * @see implementation
	 */
	@Basic
	public HashSet<Bullet> getBullets(){
		return this.bullets;
	}
	
	/**
	 * Returns the number of bullets loaded on the ship.
	 * @return
	 * 		  The number of bullets loaded on the ship.
	 * 		  |result == this.getBullets().size
	 */
	@Basic
	public int getNbBullets(){
		return this.getBullets().size();
	}
	
	private static double initialBulletSpeed = 250.0;
	
	/**
	 * Returns the initial bullet speed.
	 * 
	 * @see implementation
	 */
	@Basic
	public static double getInitialBulletSpeed(){
		return initialBulletSpeed;
	}
	
	/**
	 * Sets the initial bullet speed to a new speed.
	 * @param newSpeed
	 * 		  The new speed of the bullets.
	 * @see implementation
	 */
	@Basic
	public static void setInitialBulletSpeed(double newSpeed){
		initialBulletSpeed = newSpeed;
	}
	
	/**
	 * Picks a random bullet of this ship and fires it.
	 * @effect this.removeBullet(bullet)
	 * 		 Removes the bullet from this ship
	 * @post The bullet is located next to the ship.
	 * 		 |bullet.getPosX() == this.getPosX()+2*distance*Math.cos(this.getOrientation())
	 * 		 |&& bullet.getPosY() == this.getPosX()+2*distance*Math.sin(this.getOrientation())
	 * @post The initial bullet speed is initialBulletSpeed in the direction of the circle.
	 * 		 |bullet.getVelX() == Math.cos(this.getOrientation())*getInitialBulletSpeed
	 * 		 |&& bullet.getVelY() == Math.sin(this.getOrientation())*getInitialBulletSpeed
	 * @post If the bullet is within the world bounds and it doesn't overlap with any of the world entities
	 * 		 it is placed in the world
	 * 		 |if(this.getWorld().isWithinWorldBounds(bullet) && !circle.overlaps(bullet) for Circle circle in this.getWorld().getWorldCircles())
	 * 		 |then bullet.getWorld() == this.getWorld() && this.getWorld().getWorldEntities().contains(bullet)
	 * @effect if(bullet.overlaps(circle) for Circle circle in this.getWorld().getWorldCircles()
	 * 		   then bullet.terminate() && this.getWorld().remove((Circle)object) && ((Circle)object).terminate();
	 * 		  If the bullet overlaps with any of the world circles. The bullet and the circle are terminated.
	 * @post If the bullet doesn't lie within the world bounds, it is terminated and its ship is set to null.
	 * 		 |if(!this.getWorld().isWithinWorldBounds(bullet))
	 * 		 |then bullet.isTerminated() && bullet.getShip() == null
	 */
	@Raw
	public void fireBullet(){
		if(!this.getBullets().isEmpty()){
			ArrayList<Bullet> bulletList = new ArrayList<Bullet>(this.getBullets());
			Bullet bullet = bulletList.get(0);
			if(bullet != null && this.getWorld() != null && !this.isTerminated()){
				double distance = this.getRadius()+bullet.getRadius();
				bullet.setPosX(this.getPosX()+distance*Math.cos(this.getOrientation()));
				bullet.setPosY(this.getPosY()+distance*Math.sin(this.getOrientation()));
				bullet.setVel(initialBulletSpeed*Math.cos(this.getOrientation()), initialBulletSpeed*Math.sin(this.getOrientation()));
				this.removeBullet(bullet);
				if(!this.getWorld().isWithinWorldBounds(bullet)){
					bullet.terminate();
				}
				else{
					try{this.getWorld().add(bullet);}
					catch(IllegalArgumentException i){
						for(Object object: this.getWorld().getWorldEntities()){
							if(bullet.overlaps((Circle)object)){
								bullet.terminate();
								this.getWorld().remove((Circle)object);
								((Circle)object).terminate();
								break;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Resolves the collision of a ship and a bullet.
	 * @param bullet
	 * 	      The bullet that collides with the ship.
	 * @throws NullPointerException
	 * 		   The bullet is null
	 * 		   |bullet == null
	 * @post If this ship is the owner of the bullet then the bullet is reloaded on the ship.
	 * 		 |if(this == bullet.getOwner()) then this.getBullets.contains(bullet) && bullet.getShip == this
	 * @post The bullet is removed from the world.
	 * 		 |!this.getWorld().getWorldBullets().contains(bullet) && bullet.getWorld() == null
	 * @post If this ship isn't the owner of the bullet , this ship and the bullet are terminated and the ship is removed from the world.
	 * 		 |if(this != bullet.getOwner()) then bullet.isTerminated() && this.isTerminated()
	 * 		 |!this.getWorld().getWorldShips().contains(this) && this.getWorld() == null
	 */
	@Raw
	public void collision(Bullet bullet) throws NullPointerException{
		if (bullet == null)
			throw new NullPointerException();
		if(bullet.getWorld() != this.getWorld())
			throw new IllegalArgumentException();
		if(this == bullet.getOwner()){
			this.getWorld().remove(bullet);
			bullet.setPosX(this.getPosX());
			bullet.setPosY(this.getPosY());
			bullet.setShip(this);
			this.bullets.add(bullet);
		}
		else{
			bullet.terminate();
			this.terminate();
		}
	}
	
	/**
	 * Resolves the collision between a ship and another ship.
	 * @param ship
	 * 		  The ship with which this ship collides.
	 * @throws IllegalArgumentException
	 * 		   The other ship is the same as this ship or they are not positioned in the same world.
	 * 		   |ship == this || this.getWorld() != ship.getWorld()
	 * @throws NullPointerException
	 * 		   The other ship is null
	 * 		   |ship == null
	 * @post This ship its speed is corrected.	
	 *		   |new.getVelX() == this.getVeX()-Jx/this.getTotalMass() && new.getVelY() == this.getVelY()-Jy/this.getTotalMass()
	 * @post The other ship its speed is corrected.
	 * 		   |((new)ship).getVelX() == ship.getVelX()+Jx/this.getTotalMass() && ((new)ship).getVelY() == this.getVelY()+Jy/this.getTotalMass()
	 * 
	 */
	@Raw
	public void collision(Ship ship) throws IllegalArgumentException, NullPointerException{
		if(ship == null)
			throw new NullPointerException();
		else if(this == ship || this.getWorld() != ship.getWorld())
			throw new IllegalArgumentException();
		else{
			Vector2D deltaV = (this.getVelVector().substract(ship.getVelVector()));
			Vector2D deltaR = (this.getPosVector().substract(ship.getPosVector()));
			double sigma = this.getRadius() + ship.getRadius();
			double J = (2*this.getTotalMass()*ship.getTotalMass()*deltaV.scalarProduct(deltaR))/((this.getTotalMass()+ship.getTotalMass())*sigma);
			double Jx = J*(deltaR.getX())/sigma;
			double Jy = J*(deltaR.getY())/sigma;
			this.setVel(this.getVelX()-(Jx/this.getTotalMass()), this.getVelY()-(Jy/this.getTotalMass()));
			ship.setVel(ship.getVelX()+(Jx/ship.getTotalMass()), ship.getVelY()+(Jy/ship.getTotalMass()));
		}
	}
	
	/**
	 * Resolves the collision between a ship and a object.
	 * @see implementation
	 */
	public void collision(Object object){
		if(object == null)
			throw new NullPointerException();
		if(object == this)
			throw new IllegalArgumentException();
		if(object instanceof Circle){
			Circle objectCast = (Circle)object;
			if(this.getWorld() != objectCast.getWorld())
				throw new IllegalArgumentException();
			if(objectCast instanceof Ship)
				this.collision((Ship)objectCast);
			else if(objectCast instanceof Bullet)
				this.collision((Bullet)objectCast);
			else if(objectCast instanceof MinorPlanet)
				((MinorPlanet)objectCast).collision(this);
		}
	}
	
	/**
	 * Teleports the ship to a random location in the world
	 * @see implementation
	 */
	public void teleportToRandomLocation(){
		if(this.getWorld()!= null){
			this.setPosX(this.getRadius()+Math.random()*(this.getWorld().getWidth()-2*this.getRadius()));
			this.setPosY(this.getRadius()+Math.random()*(this.getWorld().getHeight()-2*this.getRadius()));
			this.getWorld().updateCirclesLibrary();
		}
	}
	
	private Program program;
	
	/**
	 * Sets the program to a new program
	 * @param newProgram
	 * 		  The new program of the ship
	 * @post The program is the new program.
	 * 		 |new.getProgram() == newProgram
	 */
	public void setProgram(Program newProgram){
		this.program = newProgram;
		this.program.setUser(this);
	}
	
	/**
	 * Returns the program of the ship.
	 * @return The program of the ship.
	 * 		  |result == this.program
	 */
	public Program getProgram(){
		return this.program;
	}
	
	/**
	 * Searches the nearest ship of this ship in the world
	 * @return The nearest ship in the world, null if there are no other ships.
	 * 		  |@see implementation
	 */
	public Ship getNearestShip(){
		return this.getWorld().getWorldShips().stream().filter(s -> s != this).min(Comparator.comparing((Ship i) -> i.getDistanceBetween(this))).orElse(null);

	}
	
	/**
	 * Searches the nearest bullet this ship has fired of this ship in the world
	 * @return The nearest bullet in the world, null if there are no bullets it has fired.
	 * 		  |@see implementation
	 */
	public Bullet getNearestBullet(){
		return this.getWorld().getWorldBullets().stream().filter(s -> s.getOwner()==this).min(Comparator.comparing((Bullet i) -> i.getDistanceBetween(this))).orElse(null);
	}
	
	/**
	 * Searches the nearest asteroid to this ship in the world
	 * @return The nearest asteroid in the world, null if there are no asteroids.
	 * 		  |@see implementation
	 */
	public Asteroid getNearestAsteroid(){
		return this.getWorld().getWorldAsteroids().stream().min(Comparator.comparing((Asteroid i) -> i.getDistanceBetween(this))).orElse(null);
	}
	
	/**
	 * Searches the nearest planetoid to this ship in the world
	 * @return The nearest planetoid in the world, null if there are no planetoids.
	 * 		  |@see implementation
	 */
	public Planetoid getNearestPlanetoid(){
		return this.getWorld().getWorldPlanetoids().stream().min(Comparator.comparing((Planetoid i) -> i.getDistanceBetween(this))).orElse(null);
	}
	
	/**
	 * Searches the nearest minorplanet to this ship in the world
	 * @return The nearest minorplanet in the world, null if there are no other minorplanets.
	 * 		  |@see implementation
	 */
	public MinorPlanet getNearestPlanet(){
		return this.getWorld().getWorldEntities().stream().filter(i->i instanceof MinorPlanet).map(i -> (MinorPlanet)i).min(Comparator.comparing((MinorPlanet i) -> i.getDistanceBetween(this))).orElse(null);

	}
	
	/**
	 * Searches the nearest circle to this ship in the world
	 * @return The nearest circle in the world, null if there are no other circles.
	 * 		  |@see implementation
	 */
	public Circle getNearestCircle(){
		return this.getWorld().getWorldEntities().stream().map(i -> (Circle)i).min(Comparator.comparing((Circle i) -> i.getDistanceBetween(this))).orElse(null);

	}
}

