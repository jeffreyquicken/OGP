package asteroids.facade;

import java.util.*;

import asteroids.model.*;
//import asteroids.part1.facade.IFacade;
import asteroids.part2.facade.*;
import asteroids.part2.CollisionListener;
import asteroids.util.ModelException;

public class Facade implements IFacade {
	
	/**
	 * @return new Ship()
	 * 		  Returns a new default ship.
	 * 		  |result == new Ship()
	 */
	public Ship createShip()
			throws ModelException{
		try{return new Ship(0,0,0,0,1,0);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @return new Ship(x,y,xVelocity,yVelocity,radius,orientation)
	 * 		  Returns a ship with a specified position,velocity,radius and orientation.
	 * 		  |result == new Ship(x,y,xVelocity,yVelocity,radius,orientation)
	 */
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException{
		try{return new Ship(x,y,xVelocity,yVelocity,radius,orientation);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation, double mass){
		return new Ship(x,y,xVelocity,yVelocity,radius,orientation,mass);
	}
	
	/**
	 * @param ship
	 * 		  The ship of which the coordinates are returned.
	 * @return positionArray
	 * 		  Returns a position array of the x and y coordinates of the ship
	 * 		  |result == {ship.getPosX(),ship.getPosY()}
	 */
	public double[] getShipPosition(Ship ship){
		return ship.getPosVector().array();
	}
	
	/**
	 * @param ship
	 * 		  The ship of which the velocities are returned.
	 * @return velocityArray
	 * 		  Returns a velocity array of the x and y velocities of the ship
	 * 		  |result == {ship.getVelX(),ship.getVelY()}
	 * 			
	 */
	public double[] getShipVelocity(Ship ship){
		return ship.getVelVector().array();
	}
	
	/**
	 * @param ship
	 * 		  The ship of which the radius is returned.
	 * @return ship.getRadius()
	 * 		  Returns the radius of the ship in kilometers.
	 * 		  |result == ship.getRadius()
	 */
	public double getShipRadius(Ship ship){
		return ship.getRadius();
	}
	
	/**
	 * @param ship
	 * 		  The ship of which the orientation is returned.
	 * @return ship.getOrientation()
	 * 		  Returns the orientation of the ship in radians.
	 * 		  |result == ship.getOrientation()
	 */
	public double getShipOrientation(Ship ship){
		return ship.getOrientation();
	}
	
	/**
	 * @param ship
	 * 		  The ship that has to move.
	 * @param dt
	 * 		  The amount of time the ship is moving.
	 * @effect ship.move(dt)
	 * 		   Moves the ship around during a specified amount of time.
	 */
	public void move(Ship ship, double dt) throws ModelException{
		try{ship.move(dt);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @param ship
	 * 		  The ship that has to be propelled forward.
	 * @param amount
	 * 		  The amount of acceleration of the ship.
	 * @effect ship.thrust(amount)
	 * 		  Thrusts the ship forward with a specified amount.
	 * 		 
	 */
	public void thrust(Ship ship,double amount){
		ship.thrust(amount);
	}
	
	/**
	 * @param ship
	 * 		  The ship that has to turn.
	 * @param angle
	 * 		  The amount of radians to be added to the current orientation of the ship.
	 * @effect ship.turn(angle)
	 * 		  Turns the ship with a specified angle.
	 */
	public void turn(Ship ship, double angle){
		ship.turn(angle);
	}
	
	/**
	 * @param ship1
	 * 		  The first ship of which the distance between the second ship is returned
	 * @param ship2
	 * 		  The second ship of which the distance between the first ship is returned
	 * @return ship1.getDistanceBetween(ship2)
	 * 		  Returns the distance in kilometers between ship1 and ship2.
	 * 		  |result == ship1.getDistanceBetween(ship2)
	 */
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException{
		try{return ship1.getDistanceBetween(ship2);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @param ship1
	 * 		  The first ship of which is checked if it overlaps with the second ship.
	 * @param ship2
	 * 		  The second ship of which is checked if it overlaps with the first ship.
	 * @return ship1.overlaps(ship2)
	 * 		  Returns true if the two ships overlap, returns false otherwise.
	 * 		  |result == ship1.overlaps(ship2)
	 */
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException{
		try{return ship1.overlaps(ship2);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
	}
	
	/**
	 * @param ship1
	 * 		  The first ship of which the collision time with the second ship is returned.
	 * @param ship2
	 * 		  The second ship of which the collision time with the first ship is returned.
	 * @return ship1.getTimeToCollision(ship2)
	 * 		   Returns the time in seconds when the two ships will collide.
	 * 		   Returns Double.POSITIVE_INFINITY if they will never collide.
	 * 		   |result == ship1.getTimeToCollision(ship2)
	 */
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException{
		try{return ship1.getTimeToCollision(ship2);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	/**
	 * @param ship1
	 * 		  The first ship of which the collision coordinates with the second ship are returned.
	 * @param ship2
	 * 		  The second ship of which the collision coordinates with the first ship are returned.
	 * @return ship1.getCollisionPosition(ship2)
	 * 		   Returns the position array where the ships will collide.
	 * 		   Returns null if they will never collide.
	 * 		   |result == ship1.getCollisionPosition(ship2)
	 */
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException{
		try{return ship1.getCollisionPosition(ship2);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	public void terminateShip(Ship ship) throws ModelException{
		try{ship.terminate();}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	public boolean isTerminatedShip(Ship ship){
		return ship.isTerminated();
	}
	
	public double getShipMass(Ship ship){
		return ship.getTotalMass();
	}
	
	public World getShipWorld(Ship ship){
		return ship.getWorld();
	}
	
	public boolean isShipThrusterActive(Ship ship){
		return ship.getThrusterStatus();
	}
	
	public void setThrusterActive(Ship ship, boolean active){
		ship.setThruster(active);
	}
	
	public double getShipAcceleration(Ship ship){
		return ship.getAcceleration();
	}
	/////BULLET CODE//////
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException{
		try{return new Bullet(x,y,xVelocity,yVelocity,radius);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	public void terminateBullet(Bullet bullet) throws ModelException{
		try{bullet.terminate();}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	public boolean isTerminatedBullet(Bullet bullet){
		return bullet.isTerminated();
	}
	
	public double[] getBulletPosition(Bullet bullet){
		return bullet.getPosVector().array();
	}
	
	public double[] getBulletVelocity(Bullet bullet){
		return bullet.getVelVector().array();
	}
	
	public double getBulletRadius(Bullet bullet){
		return bullet.getRadius();
	}
	
	public double getBulletMass(Bullet bullet){
		return bullet.getMass();
	}
	
	public World getBulletWorld(Bullet bullet){
		return bullet.getWorld();
	}
	
	public Ship getBulletShip(Bullet bullet){
		return bullet.getShip();
	}
	
	public Ship getBulletSource(Bullet bullet){
		return bullet.getOwner();
	}
	
	/////// WORLD CODE///////////////////:
	public World createWorld(double width, double height){
		return new World(height,width);
	}
	
	public void terminateWorld(World world) throws ModelException{
		try{world.terminate();}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
	}
	
	public boolean isTerminatedWorld(World world){
		return world.isTerminated();
	}
	
	public double[] getWorldSize(World world){
		double[] sizeArray = {world.getWidth(),world.getHeight()};
		return sizeArray;
	}
	
	public Set<? extends Ship> getWorldShips(World world){
		return world.getWorldShips();
	}
	
	public Set<? extends Bullet> getWorldBullets(World world){
		return world.getWorldBullets();
	}
	
	public void addShipToWorld(World world, Ship ship) throws ModelException{
		try{world.add(ship);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	public void removeShipFromWorld(World world,Ship ship) throws ModelException{
		try{world.remove(ship);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException{
		try{world.add(bullet);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	public void removeBulletFromWorld(World world,Bullet bullet) throws ModelException{
		try{world.remove(bullet);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	//// SHIP AND BULLET CODE//////////////////
	
	
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) {
		return ship.getBullets();
	}
	
	public int getNbBulletsOnShip(Ship ship) {
		return ship.getNbBullets();
	}
	
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException{
		try{ship.addBullet(bullet);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException{
		try{ship.addBullet(bullets);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException{
		try{ship.removeBullet(bullet);}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	public void fireBullet(Ship ship) throws ModelException{
		try{ship.fireBullet();}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	///COLLISIONS//////////////////////////////////
	public double getTimeCollisionBoundary(Object object) throws ModelException{
		if(object instanceof Circle){
			try{return ((Circle)object).getTimeToCollision(((Circle)object).getWorld());}
			catch(NullPointerException e){
				throw new ModelException(e);
			}
			catch(IllegalArgumentException d){
				throw new ModelException(d);
			}
		}
		else 
			return Double.POSITIVE_INFINITY;
	}
	
	public double[] getPositionCollisionBoundary(Object object) throws ModelException{
		if(object instanceof Circle){
			try{return ((Circle)object).getCollisionPosition(((Circle)object).getWorld());}
			catch(NullPointerException e){
				throw new ModelException(e);
			}
			catch(IllegalArgumentException d){
				throw new ModelException(d);
			}
		}
		else
			return null;
	}
	
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException{
		try{if(entity1 instanceof Circle && entity2 instanceof World )
			return ((Circle)entity1).getTimeToCollision(((World)entity2));
		else if(entity1 instanceof World && entity2 instanceof Circle)
			return getTimeCollisionEntity(entity2,entity1);
		else if(entity1 instanceof Circle && entity2 instanceof Circle)
			return ((Circle)entity1).getTimeToCollision(((Circle)entity2));
		else
			return Double.POSITIVE_INFINITY;}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}

	}
	
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException{
		try{if(entity1 instanceof Circle && entity2 instanceof World )
			return ((Circle)entity1).getCollisionPosition(((World)entity2));
		else if(entity1 instanceof World && entity2 instanceof Circle)
			return getPositionCollisionEntity(entity2,entity1);
		else if(entity1 instanceof Circle && entity2 instanceof Circle)
			return ((Circle)entity1).getCollisionPosition(((Circle)entity2));
		else
			return null;}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
	}
	
	public double getTimeNextCollision(World world){
		return world.getFirstCollisionTime();
	}
	
	public double[] getPositionNextCollision(World world) throws ModelException{
		Object[] collisionArray = world.getFirstCollisionArray();
		Object collisionObject1 = collisionArray[1];
		Object collisionObject2 = collisionArray[2];
		try{if(collisionObject1!= null && collisionObject2 !=null){
			if(collisionObject2 instanceof World)
				return ((Circle)collisionObject1).getCollisionPosition((World)collisionObject2);
			else if(collisionObject2 instanceof Circle)
				return ((Circle)collisionObject1).getCollisionPosition((Circle)collisionObject2);
		}}
		catch(NullPointerException e){
			throw new ModelException(e);
		}
		catch(IllegalArgumentException d){
			throw new ModelException(d);
		}
		return null;
		}
	
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException{
		try{world.evolve(dt, collisionListener);}
		catch(IllegalArgumentException e){
			throw new ModelException(e);
		}
		catch(NullPointerException d){
			throw new ModelException(d);
		}
	}
	
	
	public Object getEntityAt(World world, double x, double y){
		return world.getEntityAtPos(x, y);
	}
	
	public Set<? extends Object> getEntities(World world){
		return world.getWorldEntities();
	}
	
	
	


	
	
}
