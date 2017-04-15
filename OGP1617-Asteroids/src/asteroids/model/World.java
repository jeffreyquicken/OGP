package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
import java.util.*;

import asteroids.part2.CollisionListener;
/**
 * A class of world.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 *
 */
public class World {
	
	/**
	 * Creates a new world with a given width and height.
	 * 
	 * @param height
	 * 		  The height of the world.
	 * @param width
	 * 		  The width of the world.
	 * @post If the height is not a positive number, the height is set to 0.
	 * 		 If the height doesn't exceed the maximum height, the height is set to height
	 * 		 Otherwise the height is set to maxHeight.
	 * 	     |if(height<0) then this.height == 0
	 * 		 |else if(height>getMaxHeight()) then this.height == getMaxHeight()
	 * 		 |else then this.height == height
	 * 
	 * @post If the width is not a positive number, the width is set to 0.
	 * 		 If the width doesn't exceed the maximum width, the height is set to width
	 * 		 Otherwise the height is set to maxWidth.
	 * 	     |if(width<0) then this.width == 0
	 * 		 |else if(width>getMaxWidth()) then this.width == getMaxWidth()
	 * 		 |else then this.width == width
	 */
	@Raw
	public World(double height, double width){
		if(height<0)
			this.height = 0;
		else if(height >getMaxHeight())
			this.height = getMaxHeight();
		else
			this.height = height;
		
		if(width<0)
			this.width = 0;
		else if(width >getMaxWidth())
			this.width = getMaxWidth();
		else
			this.width = width;
	}
	
	private boolean terminated = false;
	
	/**
	 * Terminates this world.
	 * 
	 * @throws IllegalArgumentException
	 * 		   Throws IllegalArgumentException if the world is already terminated.
	 * 		   |this.isTerminated()
	 * 
	 * @post This world is terminated.
	 * 		 |this.isTerminated()
	 * @post All of the worlds objects are terminated.
	 * 		 @see implementation
	 */
	public void terminate() throws IllegalArgumentException{
		if(this.isTerminated())
			throw new IllegalArgumentException();
		else
			this.terminated = true;
			for(Object object: this.getWorldEntities()){
				if(object instanceof Circle)
					((Circle)object).terminate();
					((Circle)object).setWorld(null);
			}
	}
	
	/**
	 * Returns whether a world is terminated or not.
	 * @return
	 * 		  True if the world is terminated
	 */
	public boolean isTerminated(){
		return this.terminated;
	}
	
	private final double height;
	private final double width;
	
	private static double maxHeight = Double.MAX_VALUE;
	private static double maxWidth = Double.MAX_VALUE;
	
	/**
	 * Returns the maximum height of the worlds.
	 * @return
	 * 		  The maximum height of the worlds.
	 */
	@Basic
	@Immutable
	public static double getMaxHeight(){
		return maxHeight;
	}
	
	/**
	 * Returns the maximum width of the worlds.
	 * @return
	 * 		  The maximum width of the worlds.
	 */
	@Basic
	@Immutable
	public static double getMaxWidth(){
		return maxWidth;
	}
	
	/**
	 * 
	 * @see implementation
	 */
	public double getHeight(){
		return this.height;
	}
	
	/**
	 * 
	 * @see implementation
	 */
	public double getWidth(){
		return this.width;
	}
	
	private HashMap<Vector2D,Circle> circles = new HashMap<>();
	
	/**
	 * Checks whether a circle lies within the worlds bounds.
	 * 
	 * @param circle
	 * 		  The circle of which has to be checked whether it lies within the worlds bounds.
	 * @return
	 * 		  True if the circle lies within the worlds bounds (With a 1% correction factor)
	 * 		  |result == ((circle.getPosX()>=0.99*circle.getRadius())&&(circle.getPosX()+0.99*circle.getRadius()<=this.width)&&
				(circle.getPosY()-0.99*circle.getRadius()>=0)&&(circle.getPosY()+0.99*circle.getRadius()<=this.height))
	 */		  
	public boolean isWithinWorldBounds(Circle circle){
		return ((circle.getPosX()>=0.99*circle.getRadius())&&(circle.getPosX()+0.99*circle.getRadius()<=this.width)&&
				(circle.getPosY()-0.99*circle.getRadius()>=0)&&(circle.getPosY()+0.99*circle.getRadius()<=this.height));
	}
	
	/**
	 * Returns a set of the ships of the world.
	 * 
	 * @return
	 * 		  A set of the ships of the world.
	 */
	public Set<Ship> getWorldShips(){
		Set<Ship> ships = new HashSet<>();
		for(Circle circle:circles.values()){
			if(circle instanceof Ship)
				ships.add((Ship)circle);
		}
		return ships;
	}
	
	/**
	 * Returns a set of the bullets of the world.
	 * @return
	 * 		  A set of the bullets of the world.
	 */
	public Set<Bullet> getWorldBullets(){
		Set<Bullet> bullets = new HashSet<>();
		for(Circle circle:circles.values()){
			if(circle instanceof Bullet)
				bullets.add((Bullet)circle);
		}
		return bullets;
	}
	
	/**
	 * Returns a set of all the objects of the world
	 * @return
	 * 		  A set of objects of the world.
	 */
	public Set<Object> getWorldEntities(){
		Set<Object> entitySet = new HashSet<Object>();
		entitySet.addAll(circles.values());
		return entitySet;
	}
	
	/**
	 * Adds a new Circle to the world.
	 * 
	 * @param newCircle
	 * 		  The circle which has to be added to the world.
	 * 
	 * @throws NullPointerException
	 * 		   The circle is null.
	 * 		   |newCircle == null
	 * 
	 * @throws IllegalArgumentException
	 * 		   The circle isn't situated within the world bounds.
	 * 		   |!this.isWithinWorldBounds(newCircle)
	 * @throws IllegalArgumentException
	 * 		   The circle is terminated.
	 * 		   |newCircle.isTerminated()
	 * @throws IllegalArgumentException
	 * 		   The circle overlaps with a circle that already belongs to this world.
	 * 		   |if newCircle.overlaps(bullet) for Bullet bullet in this.getWorldBullets()
	 * 		   | || if newCircle.overlaps(ship) for Ship ship in this.getWorldShips()
	 * @post The circle is added to the world.
	 * 		 |if(newCircle instanceof Bullet) then new.getWorldBullets().contains(newCircle)
	 * 		 |else if(newCircle instanceof Ship) then new.getWorldShips().contains(newCircle)
	 * @post The world of the circle is this world.
	 * 		 |newCircle.getWorld() == this
	 */
	public void add(Circle newCircle) throws NullPointerException,IllegalArgumentException{
		if(newCircle == null)
			throw new NullPointerException();
		else if(!this.isWithinWorldBounds(newCircle))
			throw new IllegalArgumentException();
		else if(newCircle.isTerminated())
			throw new IllegalArgumentException();
		for(Circle circle :circles.values()){
			if(circle.overlaps(newCircle))
				throw new IllegalArgumentException();
		}
		newCircle.setWorld(this);
		circles.put(newCircle.getPosVector(), newCircle);
	}

	/**
	 * Removes a circle from this world.
	 * 
	 * @param circle
	 * 		  The circle which has to be removed from this world.
	 * @throws NullPointerException
	 * 		  The circle is null.
	 * 		  |circle == null
	 * @throws IllegalArgumentException
	 * 		  The world doesn't contain the circle.
	 * 		  |!this.getWorldEntities().contains(circle)
	 * @post The world doesn't contain the circle.
	 * 		  |!this.getWorldEntities().contains(circle)
	 * @post The world of the circle is set to null.
	 * 		  |circle.getWorld == null
	 */
	public void remove(Circle circle) throws NullPointerException,IllegalArgumentException{
		if(circle == null)
			throw new NullPointerException();
		if(!this.circles.containsValue(circle))
			throw new IllegalArgumentException();
		Vector2D posVector = circle.getPosVector();
		circles.remove(posVector);
		circle.setWorld(null);
		}
	
	/**
	 * Returns the entity at the position (x,y)
	 * 
	 * @param x
	 * 		  The x coordinate of the entity.
	 * @param y
	 * 		  The y coordinate of the entity.
	 * @return
	 * 		  The entity at position (x,y).
	 * 		  Returns null if the position is not within the world bounds or if there is no entity at (x,y).
	 */
	public Object getEntityAtPos(double x, double y){
		if((x<0) || (x>this.getWidth()))
			return null;
		if((y<0) || (y>this.getHeight()))
			return null;
		Vector2D posVector = new Vector2D(x,y);
		return circles.get(posVector);
	}
	
	/**
	 * Resolves the collision between a world and a circle.
	 * 
	 * @param circle
	 * 		  The circle which collides with this world.
	 * @effect circle.bounce(this)
	 * 		  The circle bounces of the world.
	 * @effect ((Bullet)circle).increaseAmountOfBounces()
	 * 		 If the circle is a bullet, the amount of bounces is increased with 1.
	 * 		 |if(circle instanceof Bullet) then ((Bullet)circle).increaseAmountOfBounces()
	 */
	private void collision(Circle circle){
		circle.bounce(this);
		if(circle instanceof Bullet){
			((Bullet)circle).increaseAmountOfBounces();
		}
	}
	
	/**
	 * Evolves the world
	 * 
	 * @param dt
	 * 		  The amount of time the world evolves.
	 * 
	 * @param collisionListener
	 * 		  The collisionListener used by the GUI.
	 * 
	 * @throws IllegalArgumentException
	 * 		   The amount of time the world evolves is a negative number.
	 * 		   |dt<0
	 */
	public void evolve(double dt, CollisionListener collisionListener) throws IllegalArgumentException{
		if (dt<0)
			throw new IllegalArgumentException();
		Object[] collisionArray = this.getFirstCollisionArray();
		Object collisionObject1 = collisionArray[1];
		Object collisionObject2 = collisionArray[2];
		double shortest = (double)collisionArray[0];
		double[] collisionPosition = (double[])collisionArray[3];
		if(shortest<dt && collisionPosition != null && collisionObject1 != null && collisionObject2 != null){
			this.moveForward(shortest);
			this.resolveCollision(collisionObject1, collisionObject2, collisionListener,collisionPosition);
			this.evolve(dt-shortest,collisionListener);
		}
		else{
			this.moveForward(dt);
		}
	}
	
	/*public void evolve(double dt, CollisionListener collisionListener) throws IllegalArgumentException{
		if (dt<0)
			throw new IllegalArgumentException();
		Object[] collisionArray = this.getFirstCollisionArray();
		double shortest = (double)collisionArray[0];
		Object collisionObject1 = collisionArray[1];
		Object collisionObject2 = collisionArray[2];
		double[] collisionPosition = (double[])collisionArray[3];
		while(shortest<dt && collisionObject1 != null && collisionObject2 != null && collisionPosition != null){
			this.moveForward(shortest);
			this.resolveCollision(collisionObject1, collisionObject2, collisionListener,collisionPosition);
			dt = dt-shortest;
			collisionArray = this.getFirstCollisionArray();
			shortest = (double)collisionArray[0];
			collisionObject1 = collisionArray[1];
			collisionObject2 = collisionArray[2];
			collisionPosition = (double[])collisionArray[3];
		}
		this.moveForward(dt);
	}*/
	
	/**
	 * Moves all of the entities of the world forward with a given amount of time.
	 * 
	 * @param time
	 * 		  The amount of time.
	 * 
	 * @throws IllegalArgumentException
	 * 		   The amount of time is negative.
	 * @effect circle.move(time) for Circle circle in this.getWorldCircles()
	 * 		   The circle moves forward during the time.
	 * @effect ((Ship)circle).accelerate(time) for Circle circle in this.getWorldCircles()
	 * 		   if(circle instanceof Ship && ((Ship)circle).getThrusterStatus())
	 * 		   If the circle is a ship and the thruster is active,
	 * 		   the ship accelerates during the time.
	 * @effect this.updateCirclesMap()
	 * 		   Updates the circles HashMap after moving the circles forward.
	 */
	public void moveForward(double time) throws IllegalArgumentException{
		if(time<0)
			throw new IllegalArgumentException();
		for(Circle circle:this.getWorldCircles()){
			circle.move(time);
			if(circle instanceof Ship){
				if(((Ship)circle).getThrusterStatus())
					((Ship)circle).accelerate(time);
			}
		}
		this.updateCirclesMap();
	}
	
	/**
	 * Resolves a collision between two objects with a given collisionListener and a given collisionPosition.
	 * 
	 * @param collisionObject1
	 * 		  The first colliding object. It can be either a Circle or null.
	 * @param collisionObject2
	 * 		  The second colliding object. It can be either a Circle, a World or null.
	 * @param collisionListener
	 * 		  The collisionListener used by the GUI.
	 * @param collisionPosition
	 * 		  The position where the two objects collide.
	 * 
	 */
	public void resolveCollision(Object collisionObject1, Object collisionObject2, CollisionListener collisionListener, double[] collisionPosition){
		if(collisionObject2 instanceof World){
			((World)collisionObject2).collision((Circle)collisionObject1);
			collisionListener.boundaryCollision(collisionObject1, collisionPosition[0], collisionPosition[1]);
		}
		else if(collisionObject2 instanceof Bullet){
			((Circle)collisionObject1).collision((Bullet)collisionObject2);
			if(collisionObject1 instanceof Ship){
				if(!((Ship)collisionObject1 == ((Bullet)collisionObject2).getOwner()))
					collisionListener.objectCollision(collisionObject1, collisionObject2,collisionPosition[0], collisionPosition[1]);
			}
		}
		else if(collisionObject2 instanceof Ship){
			((Circle)collisionObject1).collision((Ship)collisionObject2);
			if(collisionObject1 instanceof Bullet){
				if(!((Ship)collisionObject2 == ((Bullet)collisionObject1).getOwner()))
					collisionListener.objectCollision(collisionObject1, collisionObject2,collisionPosition[0], collisionPosition[1]);
			}
		}
	}
	
	/**
	 * Returns an array with the time to the first collision,the two colliding objects and the collision position.
	 * 
	 * @return An array with the time to the first collision,the two colliding objects and the collision position.
	 */

	public Object[] getFirstCollisionArray(){
		double shortest = Double.POSITIVE_INFINITY;
		Object collisionObject1 = null;
		Object collisionObject2 = null;
		double[] collisionPosition = null;
		Collection<Circle> circleCollection = circles.values();
		for(Circle circle:circleCollection){
			double worldCollisionTime = circle.getTimeToCollision(this);
			if(worldCollisionTime<shortest){
				shortest = worldCollisionTime;
				collisionObject1 = circle;
				collisionObject2 = this;
				collisionPosition = circle.getCollisionPosition(this);
			}
			for(Circle secondCircle:circleCollection){
				if(secondCircle != circle){
					double time = circle.getTimeToCollision(secondCircle);
					if(time<shortest){
						shortest = time;
						collisionObject1 = circle;
						collisionObject2 = secondCircle;
						collisionPosition = circle.getCollisionPosition(secondCircle);
					}
				}
			}
		}
		Object[] returnArray = {shortest,collisionObject1,collisionObject2,collisionPosition};
		return returnArray;
		}
	
	/**
	 * Updates the keys of the circles map
	 */
	private void updateCirclesMap(){
		HashMap<Vector2D,Circle> updatedCircles = new HashMap<Vector2D,Circle>();
		for(Circle circle:this.circles.values()){
			if(!circle.isTerminated() && circle.getWorld() == this)
				updatedCircles.put(circle.getPosVector(), circle);
		}
		this.circles.clear();
		this.circles = updatedCircles;
	}
	
	private Collection<Circle> getWorldCircles(){
		return this.circles.values();
	}
}

