package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import asteroids.model.Ship;
/**
 * A class collecting test for the class of ships.
 * @author Senne Gielen & Jeffrey Quicken
 *
 */
public class ShipTests {
	
	/**
	 * Variables referencing ships with standard values.
	 */
	private static Ship standardValueShip;
	private static Ship collisionShip1;
	private static Ship collisionShip2;
	private static Ship nullShip;
	private static Ship distanceShip1;
	private static Ship distanceShip2;
	private static final double EPSILON = 0.0001;

	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * Set up a mutable test fixture
	 * 
	 * @post The variable standardValue references a new ship with all arguments initiated to 0 except for radius, which will be initiated to 10 since this is the lower limit..
	 * 
	 */
	@Before
	public void setUpMutableFixture(){
		standardValueShip = new Ship(0,0,0,0,11,0,100);
		collisionShip1 = new Ship(10,30,10,10,10,0,100);
		collisionShip2 = new Ship(20,40,10,10,10,2,100);
		distanceShip1 = new Ship(0,0,0,0,10,0,100);
		distanceShip2 = new Ship(25,0,0,0,10,0,100);
	}
	
	@Test
	public void setPosX_LegalCase(){
		standardValueShip.setPosX(20);
		assertEquals(20, standardValueShip.getPosX(),EPSILON);
	}
	
	@Test
	public void setPosX_NegativeValue(){
		exception.expect(IllegalArgumentException.class);
		standardValueShip.setPosX(Double.NaN);
		assertEquals(20, standardValueShip.getPosX(),EPSILON);
	}
	
	@Test
	public void setPosY_LegalCase(){
		standardValueShip.setPosY(20);
		assertEquals(20, standardValueShip.getPosY(),EPSILON);
	}
	
	@Test
	public void setPosY_NegativeValue(){
		exception.expect(IllegalArgumentException.class);
		standardValueShip.setPosY(Double.NaN);
		assertEquals(20, standardValueShip.getPosY(),EPSILON);
	}
	
	@Test
	public void getPosX_LegalCase(){
		standardValueShip.setPosX(0);
		assertEquals(0, standardValueShip.getPosX(),EPSILON);
	}

	@Test
	public void getPosY_LegalCase(){
		standardValueShip.setPosY(0);
		assertEquals(0, standardValueShip.getPosY(),EPSILON);
	}
			
	@Test
	public void setOrientation_LegalCase(){
		standardValueShip.setOrientation(1*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation(),EPSILON);
	}
	
	@Test
	public void setOrientation_BelowZero(){
		exception.expect(AssertionError.class);
		standardValueShip.setOrientation(-2*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation(),EPSILON);
	}

	@Test
	public void setOrientation_Above2PI(){
		exception.expect(AssertionError.class);
		standardValueShip.setOrientation(3*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation(),EPSILON);
	}
		
	@Test
	public void getOrientation_LegalCase(){
		standardValueShip.setOrientation(0);
		assertEquals(0, standardValueShip.getOrientation(),EPSILON);
	}
	
	@Test
	public void setRadius_LegalCase(){
		standardValueShip.setRadius(30);
		assertEquals(30, standardValueShip.getRadius(),EPSILON);
	}
	
	@Test
	public void setRadius_BelowLimit(){
		exception.expect(IllegalArgumentException.class);
		standardValueShip.setRadius(5);
		assertEquals(30, standardValueShip.getRadius(),EPSILON);
	}
	
	@Test
	public void getRadius_LegalCase(){
		standardValueShip.setRadius(10);
		assertEquals(10, standardValueShip.getRadius(),EPSILON);		
	}
	
	@Test
	public void move_LegalCase(){
		standardValueShip.setPosX(10);
		standardValueShip.setVel(10,standardValueShip.getVelY());
		standardValueShip.setPosY(10);
		standardValueShip.setVel(standardValueShip.getVelX(),10);
		standardValueShip.setVel(10,10);
		standardValueShip.setPosY(10);
		standardValueShip.move(10);
		assertEquals(110, standardValueShip.getPosX(),EPSILON);
		assertEquals(110, standardValueShip.getPosY(),EPSILON);
	}
	
	@Test
	public void move_NegativeTime(){
		exception.expect(IllegalArgumentException.class);
		standardValueShip.setPosX(10);
		standardValueShip.setVel(10,standardValueShip.getVelY());
		standardValueShip.setPosY(10);
		standardValueShip.setVel(standardValueShip.getVelX(),10);
		standardValueShip.move(-10);
		assertEquals(10, standardValueShip.getPosX(),EPSILON);
		assertEquals(10, standardValueShip.getPosY(),EPSILON);
	}
	
	@Test
	public void turn_LegalCase(){
		standardValueShip.turn(1*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation(),EPSILON);
	}
	
	@Test
	public void turn_IllegalAngle(){
		exception.expect(AssertionError.class);
		standardValueShip.turn(6*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation(),EPSILON);
	}
	
	@Test 
	public void thrust_LegalCase(){
		standardValueShip.setVel(0,0);
		standardValueShip.setOrientation(1);
		standardValueShip.thrust(10);
		assertEquals(10*Math.cos(1), standardValueShip.getVelX(),EPSILON);
		assertEquals(10*Math.sin(1), standardValueShip.getVelY(),EPSILON);
	}
	
	@Test 
	public void thrust_NegativeAcceleration(){
		standardValueShip.setVel(0,0);
		standardValueShip.setOrientation(1);
		standardValueShip.thrust(-10);
		assertEquals(0, standardValueShip.getVelX(),EPSILON);
		assertEquals(0, standardValueShip.getVelY(),EPSILON);
	}
	
	@Test
	public void getDistanceBetween_LegalCase(){
		assertEquals(5,distanceShip1.getDistanceBetween(distanceShip2),EPSILON);
	}
	
	@Test
	public void getDistanceBetween_NullCase(){
		exception.expect(NullPointerException.class);
		collisionShip1.getDistanceBetween(nullShip);
	}
	
	@Test
	public void overlaps_LegalCase(){
		collisionShip1.setPosX(0);
		collisionShip1.setPosY(0);
		collisionShip2.setPosX(0);
		collisionShip2.setPosY(0);
		assertTrue(collisionShip1.overlaps(collisionShip2));
	}
	
	@Test
	public void overlaps_NullCase(){
		exception.expect(NullPointerException.class);
		collisionShip1.overlaps(nullShip);
	}
	 
	@Test
	public void getTimeToCollision_LegalCase(){
		distanceShip1.setPosY(0);
		distanceShip2.setPosY(0);
		distanceShip1.setVel(5,0);
		distanceShip2.setVel(distanceShip2.getVelX(),0);
		assertEquals(1, distanceShip1.getTimeToCollision(distanceShip2), EPSILON);
	}

	@Test 
	public void getTimeToCollision_ScalarProdGreaterthan0(){
		distanceShip1.setVel(distanceShip1.getVelX(),10);
		distanceShip2.setVel(distanceShip2.getVelX(),10);
		assertEquals(Double.POSITIVE_INFINITY, distanceShip1.getTimeToCollision(distanceShip2), EPSILON);
	}
	
	@Test
	public void getTimeToCollision_dSmallerThen0(){
		distanceShip1.setVel(10, 10);
		distanceShip2.setVel(0,20);
		distanceShip1.setPosX(20);
		distanceShip1.setPosY(101);
		distanceShip2.setPosX(10);
		distanceShip2.setPosY(1);
		assertEquals(Double.POSITIVE_INFINITY, distanceShip1.getTimeToCollision(distanceShip2), EPSILON);
	}
	
	@Test
	public void getTimeToCollision_NullCase(){
		exception.expect(NullPointerException.class);
		collisionShip1.getTimeToCollision(nullShip);
	}
	
	
	@Test
	public void getCollisionPosition_LegalCase(){
		distanceShip1.setVel(5, 0);
		distanceShip2.setVel(0, 0);
		distanceShip1.setPosX(0);
		distanceShip1.setPosY(0);
		distanceShip2.setPosX(25);
		distanceShip2.setPosY(0);
		assertEquals(15,distanceShip1.getCollisionPosition(distanceShip2)[0],EPSILON);
		assertEquals(0,distanceShip1.getCollisionPosition(distanceShip2)[1],EPSILON);
	}
	
	@Test
	public void getCollisionPosition_deltaTInfinity(){
		distanceShip1.setVel(0, 10);
		distanceShip2.setVel(0, 10);
		assertNull(distanceShip1.getCollisionPosition(distanceShip2));
	}
	
	@Test
	public void getCollisionPosition_NullCase(){
		exception.expect(NullPointerException.class);
		collisionShip1.getCollisionPosition(nullShip);
	}
}
