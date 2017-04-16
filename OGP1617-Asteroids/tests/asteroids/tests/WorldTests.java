package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import asteroids.model.World;
import asteroids.part2.CollisionListener;
import asteroids.model.Ship;
import asteroids.model.Bullet;


/**
 * A class collecting test for the more complicated methods from the class of worlds.
 * @author Senne Gielen & Jeffrey Quicken
 *
 */
public class WorldTests {
	
	/**
	 * Variables referencing worlds & circles with standard values.
	 */
	private static World world1;
	private static World world2;
	private static Ship ship1;
	private static Bullet bullet1;
	private static CollisionListener collisionListener;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUpMutableFixture(){
		world1 = new World(100,100);
		world2 = new World(200,200);
		ship1 = new Ship(50,50,10,10,20,Math.PI,10);
		bullet1 = new Bullet(50,50,10,10,20);
		}
	
	@Test
	public void addCircle_LegalCase() {
		world1.add(ship1);
	}
	
	@Test
	public void addCircle_NullCase() {
		exception.expect(NullPointerException.class);
		world1.add(null);
	}
	
	@Test
	public void addCircle_NotWithinBoundsCase() {
		ship1.setPosX(15);
		exception.expect(IllegalArgumentException.class);
		world1.add(ship1);
	}
	
	@Test
	public void addCircle_TerminatedCase() {
		ship1.setPosX(50);
		ship1.terminate();
		exception.expect(IllegalArgumentException.class);
		world1.add(ship1);
		
		
	}
	
	@Test
	public void addCircle_OverlapsCase() {
		world1.add(ship1);
		exception.expect(IllegalArgumentException.class);
		world1.add(bullet1);
	}
	
		
	@Test
	public void evolve_dtSmallerThanZero() {
		exception.expect(IllegalArgumentException.class);
		world1.evolve(-10, collisionListener);
	}
	
	@Test
	public void evolve_CollisionCase() {
		
	}
	
	
	
}
