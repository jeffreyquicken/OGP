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
	
	private HashMap<double[],Ship> ships = new HashMap<>();
	private HashMap<double[],Bullet> bullets = new HashMap<>();
	
	private boolean isWithinWorldBounds(Circle circle){
		return ((circle.getPosX()-circle.getRadius()>=0)&&(circle.getPosX()+circle.getRadius()<=this.width)&&
				(circle.getPosY()-circle.getRadius()>=0)&&(circle.getPosY()+circle.getRadius()<=this.height));
	}
	
	public Set<Ship> getWorldShips(){
		return new HashSet<Ship>(this.ships.values());
	}
	
	public Set<Bullet> getWorldBullets(){
		return new HashSet<Bullet>(this.bullets.values());
	}
	
	public Set<Object> getWorldEntities(){
		Set<Object> entitySet = new HashSet<Object>();
		entitySet.addAll(this.ships.values());
		entitySet.addAll(this.bullets.values());
		return entitySet;
	}
	
	public void add(Circle newCircle) throws NullPointerException,IllegalArgumentException{
		if(newCircle == null)
			throw new NullPointerException();
		else if(!this.isWithinWorldBounds(newCircle))
			throw new IllegalArgumentException();
		else if(newCircle.isTerminated())
			throw new IllegalArgumentException();
		for(Ship ship:this.getWorldShips()){
			if(ship.overlaps(newCircle))
				throw new IllegalArgumentException();
		}
		for(Bullet bullet:this.getWorldBullets()){
			if(newCircle.overlaps(bullet))
				throw new IllegalArgumentException();
		}
		double[] posArray = {newCircle.getPosX(),newCircle.getPosY()};
		if(newCircle instanceof Ship)
			this.ships.put(posArray, (Ship)newCircle);
		else if(newCircle instanceof Bullet)
			this.bullets.put(posArray, (Bullet)newCircle);
	}
	
	public void remove(Circle circle) throws NullPointerException,IllegalArgumentException{
		if(circle == null)
			throw new NullPointerException();
		if(this.getWorldEntities().contains(circle)){
			double[] posArray = {circle.getPosX(),circle.getPosY()};
			if(circle instanceof Ship)
				this.ships.remove(posArray);
			else if(circle instanceof Bullet)
				this.bullets.remove(posArray);
			circle.terminate();
		}
		}
	
	public Object getEntityAtPos(double x, double y){
		if((x<0) || (x>this.getWidth()))
			return null;
		if((y<0) || (y>this.getHeight()))
			return null;
		double[] posArray = {x,y};
		if(ships.containsKey(posArray))
			return ships.get(posArray);
		else if(bullets.containsKey(posArray))
			return bullets.get(posArray);
		else
			return null;
	}
	
	public void evolve(double dt){
		
	}

}
