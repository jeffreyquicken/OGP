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
	
	@Raw
	public World(double height, double width){
		if(height<0)
			this.height = 0;
		else if(height >maxHeight)
			this.height = maxHeight;
		else
			this.height = height;
		
		if(width<0)
			this.width = 0;
		else if(width >maxWidth)
			this.width = maxWidth;
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
	 * @post
	 * 		 This world is terminated.
	 * 		 |this.isTerminated()
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
	
	public boolean isTerminated(){
		return this.terminated;
	}
	
	private final double height;
	private final double width;
	
	private static double maxHeight = Double.MAX_VALUE;
	private static double maxWidth = Double.MAX_VALUE;
	
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
	
	public boolean isWithinWorldBounds(Circle circle){
		return ((circle.getPosX()>=0.99*circle.getRadius())&&(circle.getPosX()+0.99*circle.getRadius()<=this.width)&&
				(circle.getPosY()-0.99*circle.getRadius()>=0)&&(circle.getPosY()+0.99*circle.getRadius()<=this.height));
	}
	
	public Set<Ship> getWorldShips(){
		Set<Ship> ships = new HashSet<>();
		for(Circle circle:circles.values()){
			if(circle instanceof Ship)
				ships.add((Ship)circle);
		}
		return ships;
	}
	
	public Set<Bullet> getWorldBullets(){
		Set<Bullet> bullets = new HashSet<>();
		for(Circle circle:circles.values()){
			if(circle instanceof Bullet)
				bullets.add((Bullet)circle);
		}
		return bullets;
	}
	
	public Set<Object> getWorldEntities(){
		Set<Object> entitySet = new HashSet<Object>();
		entitySet.addAll(circles.values());
		return entitySet;
	}
	
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
	
	public void remove(Circle circle) throws NullPointerException,IllegalArgumentException{
		if(circle == null)
			throw new NullPointerException();
		Vector2D posVector = circle.getPosVector();
		circles.remove(posVector);
		circle.setWorld(null);
		}
	
	public Object getEntityAtPos(double x, double y){
		if((x<0) || (x>this.getWidth()))
			return null;
		if((y<0) || (y>this.getHeight()))
			return null;
		Vector2D posVector = new Vector2D(x,y);
		return circles.get(posVector);
	}
	
	private void collision(Circle circle){
		circle.bounce(this);
		if(circle instanceof Bullet){
			((Bullet)circle).increaseAmountOfBounces();
		}
	}
	
	public void evolve(double dt, CollisionListener collisionListener) throws IllegalArgumentException{
		if (dt<0)
			//throw new IllegalArgumentException();
			dt=0;
		Object[] collisionArray = this.getFirstCollisionArray();
		Object collisionObject1 = collisionArray[1];
		Object collisionObject2 = collisionArray[2];
		double shortest = (double)collisionArray[0];
		double[] collisionPosition = (double[])collisionArray[3];
		if(shortest<dt && collisionPosition != null){
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
	
	public void moveForward(double time) throws IllegalArgumentException{
		if(time<0)
			//throw new IllegalArgumentException();
			time = 0;
		for(Circle circle:circles.values()){
			circle.move(time);
			if(circle instanceof Ship){
				if(((Ship)circle).getThrusterStatus())
					((Ship)circle).accelerate(time);
			}
		}
		this.updateCirclesMap();
	}
	
	
	public void resolveCollision(Object collisionObject1, Object collisionObject2, CollisionListener collisionListener, double[] collisionPosition){
		if(collisionObject2 instanceof World){
			((World)collisionObject2).collision((Circle)collisionObject1);
			collisionListener.boundaryCollision(collisionObject1, collisionPosition[0], collisionPosition[1]);
			}
		else if(collisionObject2 instanceof Bullet){
			((Circle)collisionObject1).collision((Bullet)collisionObject2);
			collisionListener.objectCollision(collisionObject1, collisionObject2,collisionPosition[0], collisionPosition[1]);
			}
		else if(collisionObject2 instanceof Ship){
			((Circle)collisionObject1).collision((Ship)collisionObject2);
			collisionListener.objectCollision(collisionObject1, collisionObject2,collisionPosition[0], collisionPosition[1]);
			}
		else
			throw new AssertionError();
	}
	
	/**
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
			updatedCircles.put(circle.getPosVector(), circle);
		}
		this.circles.clear();
		this.circles = updatedCircles;
	}
}

