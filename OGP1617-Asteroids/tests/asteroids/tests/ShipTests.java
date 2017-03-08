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
	 * Variable referencing a ship with standard values.
	 */
	private static Ship standardValueShip;
	private static Ship collisionShip1;
	private static Ship collisionShip2;
	private static Ship nullShip;
	
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
		standardValueShip = new Ship(0,0,0,0,10,0);
		collisionShip1 = new Ship(10,30,10,10,10,0);
		collisionShip2 = new Ship(20,40,10,10,10,2);
	}
	
	@Test
	public void setPosX_LegalCase(){
		standardValueShip.setPosX(20);
		assertEquals(20, standardValueShip.getPosX());
	}
	
	@Test
	public void setPosX_NegativeValue(){
		standardValueShip.setPosX(-20);
		assertEquals(20, standardValueShip.getPosX());
	}
	
	@Test
	public void setPosY_LegalCase(){
		standardValueShip.setPosY(20);
		assertEquals(20, standardValueShip.getPosY());
	}
	
	@Test
	public void setPosY_NegativeValue(){
		standardValueShip.setPosY(-20);
		assertEquals(20, standardValueShip.getPosY());
	}
	
	@Test
	public void getPosX_LegalCase(){
		standardValueShip.setPosX(0);
		assertEquals(0, standardValueShip.getPosX());
	}

	@Test
	public void getPosY_LegalCase(){
		standardValueShip.setPosY(0);
		assertEquals(0, standardValueShip.getPosY());
	}
	
	@Test
	public void setVelX_LegalCase(){
		standardValueShip.setVelX(10);
		assertEquals(10, standardValueShip.getVelX());
	}
	
	@Test
	public void setVelX_SpeedOverflow(){
		standardValueShip.setVelX(400000);
		assertEquals(300000, standardValueShip.getVelX());
	}
	
	@Test
	public void setVelY_LegalCase(){
		standardValueShip.setVelX(0);
		standardValueShip.setVelY(10);
		assertEquals(10, standardValueShip.getVelY());
	}
	
	@Test
	public void setVelY_SpeedOverflow(){
		standardValueShip.setVelY(400000);
		assertEquals(300000, standardValueShip.getVelY());
	}
	
	@Test
	public void getVelX_LegalCase(){
		standardValueShip.setVelX(0);
		assertEquals(0, standardValueShip.getVelX());
	}
	
	@Test
	public void getVelY_LegalCase(){
		standardValueShip.setVelY(0);
		assertEquals(0, standardValueShip.getVelY());
	}
	
	@Test
	public void setOrientation_LegalCase(){
		standardValueShip.setOrientation(1*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation());
	}
	
	@Test
	public void setOrientation_BelowZero(){
		standardValueShip.setOrientation(-2*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation());
	}

	@Test
	public void setOrientation_Above2PI(){
		standardValueShip.setOrientation(3*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation());
	}
	
	@Test
	public void getOrientation_LegalCase(){
		standardValueShip.setOrientation(0);
		assertEquals(0, standardValueShip.getOrientation());
	}
	
	@Test
	public void setRadius_LegalCase(){
		standardValueShip.setRadius(30);
		assertEquals(30, standardValueShip.getRadius());
	}
	
	@Test
	public void setRadius_BelowLimit(){
		standardValueShip.setRadius(5);
		assertEquals(30, standardValueShip.getRadius());
	}
	
	@Test
	public void getRadius_LegalCase(){
		standardValueShip.setRadius(10);
		assertEquals(10, standardValueShip.getRadius());		
	}
	
	@Test
	public void move_LegalCase(){
		standardValueShip.setPosX(10);
		standardValueShip.setVelX(10);
		standardValueShip.setPosY(10);
		standardValueShip.setVelY(10);
		standardValueShip.move(10);
		assertEquals(110, standardValueShip.getPosX());
		assertEquals(110, standardValueShip.getPosY());
	}
	
	@Test
	public void move_NegativeTime(){
		standardValueShip.setPosX(10);
		standardValueShip.setVelX(10);
		standardValueShip.setPosY(10);
		standardValueShip.setVelY(10);
		standardValueShip.move(-10);
		assertEquals(10, standardValueShip.getPosX());
		assertEquals(10, standardValueShip.getPosY());
	}
	
	@Test
	public void turn_LegalCase(){
		standardValueShip.turn(1*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation());
	}
	
	@Test
	public void turn_IllegalAngle(){
		standardValueShip.turn(6*Math.PI);
		assertEquals(1*Math.PI, standardValueShip.getOrientation());
	}
	
	@Test 
	public void thrust_LegalCase(){
		standardValueShip.setVelX(0);
		standardValueShip.setVelY(0);
		standardValueShip.setOrientation(1);
		standardValueShip.thrust(10);
		assertEquals(10*Math.cos(1), standardValueShip.getVelX());
		assertEquals(10*Math.sin(1), standardValueShip.getVelY());
	}
	
	@Test 
	public void thrust_NegativeAcceleration(){
		standardValueShip.setVelX(0);
		standardValueShip.setVelY(0);
		standardValueShip.setOrientation(1);
		standardValueShip.thrust(-10);
		assertEquals(0, standardValueShip.getVelX());
		assertEquals(0, standardValueShip.getVelY());
	}
	
	@Test
	public void getDistanceBetween_LegalCase(){
		assertEquals(Math.sqrt(200),collisionShip1.getDistanceBetween(collisionShip2));
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
	public void getTimeToCollision_NullCase(){
		exception.expect(NullPointerException.class);
		collisionShip1.getTimeToCollision(nullShip);
	}
	
	@Test
	public void getTimeToCollision_IllegalArgumentCase(){
		exception.expect(IllegalArgumentException.class);
		collisionShip1.getTimeToCollision(nullShip);
	}
}
