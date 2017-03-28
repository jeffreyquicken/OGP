package asteroids.model;
import java.util.*;

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
	
	private HashMap<double[],Circle> circles = new HashMap<>();
	
	private boolean isWithinWorldBounds(Circle circle){
		return ((circle.getPosX()-circle.getRadius()>=0)&&(circle.getPosX()+circle.getRadius()<=this.width)&&
				(circle.getPosY()-circle.getRadius()>=0)&&(circle.getPosY()+circle.getRadius()<=this.height));
	}
	
	public Set<Ship> getWorldShips(){
		Set<Ship> ships = new HashSet<>();
		for(Circle circle: circles.values())
			if(circle instanceof Ship)
				ships.add((Ship)circle);
		return ships;
				
	}
	
	public Set<Bullet> getWorldBullets(){
		Set<Bullet> bullets = new HashSet<>();
		for(Circle circle: circles.values())
			if(circle instanceof Bullet)
				bullets.add((Bullet)circle);
		return bullets;
	}
	
	public Set<Object> getWorldEntities(){
		Set<Object> entitySet = new HashSet<Object>();
		entitySet.addAll(this.circles.values());
		return entitySet;
	}
	
	public void add(Circle newCircle) throws NullPointerException,IllegalArgumentException{
		if(newCircle == null)
			throw new NullPointerException();
		else if(!this.isWithinWorldBounds(newCircle))
			throw new IllegalArgumentException();
		else if(newCircle.isTerminated())
			throw new IllegalArgumentException();
		for(Circle circle:circles.values()){
			if(circle.overlaps(newCircle))
				throw new IllegalArgumentException();
		}
		double[] posArray = {newCircle.getPosX(),newCircle.getPosY()};
		circles.put(posArray, newCircle);
	}
	
	public void remove(Circle circle) throws NullPointerException,IllegalArgumentException{
		if(circle == null)
			throw new NullPointerException();
		if(this.getWorldEntities().contains(circle)){
			double[] posArray = {circle.getPosX(),circle.getPosY()};
			circles.remove(posArray);
			circle.terminate();
		}
		}
	
	public Object getEntityAtPos(double x, double y){
		if((x<0) || (x>this.getWidth()))
			return null;
		if((y<0) || (y>this.getHeight()))
			return null;
		double[] posArray = {x,y};
		return circles.get(posArray);
	}
	
	public void evolve(double dt){
		double shortest = Double.POSITIVE_INFINITY;
		Circle collisionCircle1;
		Circle collisionCircle2;
		Set<Circle> checkedCircles = new HashSet<>();
		for(Circle circle:circles.values()){
			double worldCollisionTime = circle.getDistanceBetween(this);
			if(worldCollisionTime<shortest){
				shortest = worldCollisionTime;
				collisionCircle1 = circle;
				collisionCircle2 = null;
			}
			checkedCircles.add(circle);
			for(Circle secondCircle:circles.values()){
				if(!checkedCircles.contains(circle)){
					double time = circle.getTimeToCollision(secondCircle);
					if(time<shortest){
						shortest = time;
						collisionCircle1 = circle;
						collisionCircle2 = secondCircle;
					}
				}
			}
		}
		if(shortest<=dt){
			for(Circle circle:circles.values()){
				circle.move(shortest);
			}
			///////////////////////COLLISION CODE/////////////////////////////////////////
			evolve(dt-shortest);
		}
		
		for(Circle circle:circles.values()){
			circle.move(dt);
		}
			
	}

}
