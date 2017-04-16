package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import asteroids.model.World;
import asteroids.model.Circle;
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
	private static Circle circle1;
	private static Circle circle2;
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setUpMutableFixture(){
		world1 = new World(100,100);
		world2 = new World(200,200);
		circle1 = new Bullet(50,50,10,10,20);
		circle2 = new Bullet(50,50,10,10,20);
		}
	
	@Test
	public void addCircle_LegalCase() {
		world1.add(circle1);
	}
	
	@Test
	public void addCircle_NullCase() {
		exception.expect(NullPointerException.class);
		world1.add(null);
	}
	
	@Test
	public void addCircle_NotWithinBoundsCase() {
		circle1.setPosX(15);
		exception.expect(IllegalArgumentException.class);
		world1.add(circle1);
	}
	
	@Test
	public void addCircle_TerminatedCase() {
		circle1.setPosX(50);
		circle1.terminate();
		exception.expect(IllegalArgumentException.class);
		world1.add(circle1);
		
		
	}
	
	@Test
	public void addCircle_OverlapsCase() {
		world1.add(circle1);
		exception.expect(IllegalArgumentException.class);
		world1.add(circle2);
	}
	
	@Test
	public void move_LegalCase() {
		
	}
	
	@Test
	public void evolve_dtSmallerThanZero() {
		
	}
	
	@Test
	public void evolve_CollisionCase() {
		
	}
	
	
	
}
