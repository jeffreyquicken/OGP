package asteroids.model;
import java.util.*;

import asteroids.part2.CollisionListener;

public class World {
	
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
	
	public void terminate() throws IllegalArgumentException{
		if(this.isTerminated())
			throw new IllegalArgumentException();
		else
			this.terminated = true;
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
	
	public double getWidth(){
		return this.width;
	}
	
	private HashMap<double[],Ship> ships = new HashMap<>();
	private HashMap<double[],Bullet> bullets = new HashMap<>();
	
	private boolean isWithinWorldBounds(Circle circle){
		return ((circle.getPosX()>=0.99*circle.getRadius())&&(circle.getPosX()+0.99*circle.getRadius()<=this.width)&&
				(circle.getPosY()-0.99*circle.getRadius()>=0)&&(circle.getPosY()+0.99*circle.getRadius()<=this.height));
	}
	
	public Set<Ship> getWorldShips(){
		return new HashSet<Ship>(ships.values());
				
	}
	
	public Set<Bullet> getWorldBullets(){
		return new HashSet<Bullet>(bullets.values());
	}
	
	public Set<Object> getWorldEntities(){
		Set<Object> entitySet = new HashSet<Object>();
		entitySet.addAll(this.bullets.values());
		entitySet.addAll(this.ships.values());
		return entitySet;
	}
	
	public void add(Circle newCircle) throws NullPointerException,IllegalArgumentException{
		if(newCircle == null)
			throw new NullPointerException();
		else if(!this.isWithinWorldBounds(newCircle))
			throw new IllegalArgumentException();
		else if(newCircle.isTerminated())
			throw new IllegalArgumentException();
		Set<Circle> circles = new HashSet<>(ships.values());
		circles.addAll(bullets.values());
		for(Circle circle:circles){
			if(circle.overlaps(newCircle))
				throw new IllegalArgumentException();
		}
		double[] posArray = {newCircle.getPosX(),newCircle.getPosY()};
		newCircle.setWorld(this);
		if(newCircle instanceof Ship)
			ships.put(posArray, (Ship)newCircle);
		else if(newCircle instanceof Bullet)
			bullets.put(posArray, (Bullet)newCircle);
	}
	
	public void remove(Circle circle) throws NullPointerException,IllegalArgumentException{
		if(circle == null)
			throw new NullPointerException();
		if(this.getWorldEntities().contains(circle)){
			double[] posArray = {circle.getPosX(),circle.getPosY()};
			if( circle instanceof Ship)
				ships.remove(posArray);
			else if(circle instanceof Bullet)
				bullets.remove(posArray);
			circle.terminate();
			circle.setWorld(null);
		}
		}
	
	public Object getEntityAtPos(double x, double y){
		if((x<0) || (x>this.getWidth()))
			return null;
		if((y<0) || (y>this.getHeight()))
			return null;
		double[] posArray = {x,y};
		if(bullets.containsKey(posArray))
			return (Object)bullets.get(posArray);
		else if(ships.containsKey(posArray))
			return (Object)ships.get(posArray);
		else
			return null;
	}
	
	private void collision(Circle circle){
		circle.bounce(this);
		if(circle instanceof Bullet){
			((Bullet)circle).increaseAmountOfBounces();
		}
	}
	
	private Set<Circle> getWorldCircles(){
		Set<Circle> circles = new HashSet<>(ships.values());
		circles.addAll(bullets.values());
		return circles;
	}
	
	public void evolve(double dt, CollisionListener collisionListener) throws IllegalArgumentException{
		if (dt<0)
			throw new IllegalArgumentException();
		Object[] collisionArray = this.getFirstCollisionArray();
		Object collisionObject1 = collisionArray[1];
		Object collisionObject2 = collisionArray[2];
		double shortest = (double)collisionArray[0];
		if(shortest<=dt){
			this.moveForward(shortest);
			this.resolveCollision(collisionObject1, collisionObject2, collisionListener);
			evolve(dt-shortest,collisionListener);
		}
		this.moveForward(dt);
	}
	
	public void moveForward(double time){
		for(Circle circle:this.getWorldCircles()){
			circle.move(time);
			if(circle instanceof Ship){
				if(((Ship)circle).getThrusterStatus() == true)
					((Ship)circle).accelerate(time);
			}
		}
	}
	
	public void resolveCollision(Object collisionObject1, Object collisionObject2, CollisionListener collisionListener){
		if(collisionObject2 instanceof World){
			((World)collisionObject2).collision((Circle)collisionObject1);
			double[] collisionPosition = ((Circle)collisionObject1).getCollisionPosition((World)collisionObject2);
			collisionListener.boundaryCollision(collisionObject1, collisionPosition[0], collisionPosition[1]);
		}
		else if(collisionObject2 instanceof Bullet){
			((Circle)collisionObject1).collision((Bullet)collisionObject2);
			double[] collisionPosition = ((Circle)collisionObject1).getCollisionPosition((Bullet)collisionObject2);
			collisionListener.objectCollision(collisionObject1, collisionObject2,collisionPosition[0], collisionPosition[1]);
			}
		else if(collisionObject2 instanceof Ship){
			((Circle)collisionObject1).collision((Ship)collisionObject2);
			double[] collisionPosition = ((Circle)collisionObject1).getCollisionPosition((Ship)collisionObject2);
			collisionListener.objectCollision(collisionObject1, collisionObject2,collisionPosition[0], collisionPosition[1]);
			}
	}
	
	/**
	 * 
	 * @return An array with the time to the first collision and the two colliding objects.
	 */
	public Object[] getFirstCollisionArray(){
		double shortest = Double.POSITIVE_INFINITY;
		Set<Circle> circles = this.getWorldCircles();
		Object collisionObject1 = null;
		Object collisionObject2 = null;
		Set<Circle> uncheckedCircles = new HashSet<>(circles);
		for(Circle circle:circles){
			double worldCollisionTime = circle.getTimeToCollision(this);
			if(worldCollisionTime<shortest){
				shortest = worldCollisionTime;
				collisionObject1 = circle;
				collisionObject2 = this;
			}
			uncheckedCircles.remove(circle);
			for(Circle secondCircle:uncheckedCircles){
				double time = circle.getTimeToCollision(secondCircle);
				if(time<shortest){
					shortest = time;
					collisionObject1 = circle;
					collisionObject2 = secondCircle;
				}
			}
		}

		Object[] returnArray = {shortest,collisionObject1,collisionObject2};
		return returnArray;
	}

}
