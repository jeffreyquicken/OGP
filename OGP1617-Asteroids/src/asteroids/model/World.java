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
	
	private boolean isWithinWorldBounds(Ship ship){
		return ((ship.getPosX()-ship.getRadius()>=0)&&(ship.getPosX()+ship.getRadius()<=this.width)&&
				(ship.getPosY()-ship.getRadius()>=0)&&(ship.getPosY()+ship.getRadius()<=this.height));
	}
	
	private boolean isWithinWorldBounds(Bullet bullet){
		return ((bullet.getPosX()-bullet.getRadius()>=0)&&(bullet.getPosX()+bullet.getRadius()<=this.width)&&
				(bullet.getPosY()-bullet.getRadius()>=0)&&(bullet.getPosY()+bullet.getRadius()<=this.height));
	}
	
	public Set<Ship> getWorldShips(){
		return new HashSet<Ship>(this.ships.values());
	}
	
	public Set<Bullet> getWorldBullets(){
		return new HashSet<Bullet>(this.bullets.values());
	}
	
	public void add(Ship newShip) throws NullPointerException,IllegalArgumentException{
		if(newShip == null)
			throw new NullPointerException();
		else if(!this.isWithinWorldBounds(newShip))
			throw new IllegalArgumentException();
		else if(newShip.isTerminated())
			throw new IllegalArgumentException();
		for(Ship ship:this.getWorldShips()){
			if(ship.overlaps(newShip))
				throw new IllegalArgumentException();
		}
		for(Bullet bullet:this.getWorldBullets()){
			if(newShip.overlaps(bullet))
				throw new IllegalArgumentException();
		}
		double[] posArray = {newShip.getPosX(),newShip.getPosY()};
		this.ships.put(posArray, newShip);
	}
	
	public void add(Bullet newBullet) throws NullPointerException,IllegalArgumentException{
		if(newBullet == null)
			throw new NullPointerException();
		else if(!this.isWithinWorldBounds(newBullet))
			throw new IllegalArgumentException();
		else if(newBullet.isTerminated())
			throw new IllegalArgumentException();
		for(Ship ship:this.getWorldShips()){
			if(ship.overlaps(newBullet))
				throw new IllegalArgumentException();
		}
		for(Bullet bullet:this.getWorldBullets()){
			if(newBullet.overlaps(bullet))
				throw new IllegalArgumentException();
		}
		double[] posArray = {newBullet.getPosX(),newBullet.getPosY()};
		this.bullets.put(posArray, newBullet);
	}
	
	public void remove(Ship ship) throws NullPointerException,IllegalArgumentException{
		if(ship == null)
			throw new NullPointerException();
		if(this.getWorldShips().contains(ship)){
			double[] posArray = {ship.getPosX(),ship.getPosY()};
			this.ships.remove(posArray);
			ship.terminate();
		}
		}
	
	public void remove(Bullet bullet) throws NullPointerException,IllegalArgumentException{
		if(bullet == null)
			throw new NullPointerException();
		if(this.getWorldBullets().contains(bullet)){
			double[] posArray = {bullet.getPosX(),bullet.getPosY()};
			this.bullets.remove(posArray);
			bullet.terminate();
		}
		}
	
	public Object getEntityAtPos(double x, double y){
		if((x<0) || (x>maxWidth))
			return null;
		if((y<0) || (y>maxHeight))
			return null;
		double[] posArray = {x,y};
		if(ships.containsKey(posArray))
			return ships.get(posArray);
		else if(bullets.containsKey(posArray))
			return bullets.get(posArray);
		else
			return null;
	}

}
