package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import asteroids.model.Bullet;

public class BulletTests {
	
	/**
	 * Variables referencing bullets with standard values.
	 */
	private static Bullet standardValueBullet;
	private static Bullet collisionBullet1;
	private static Bullet collisionBullet2;
	private static Bullet nullBullet;
	private static Bullet distanceBullet1;
	private static Bullet distanceBullet2;
	private static final double EPSILON = 0.0001;

	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	/**
	 * Set up a mutable test fixture
	 * 
	 * @post The variable standardValue references a new bullet with all arguments initiated to 0 except for radius, which will be initiated to 10 since this is the lower limit..
	 * 
	 */
	@Before
	public void setUpMutableFixture(){
		standardValueBullet = new Bullet(0,0,0,0,11);
		collisionBullet1 = new Bullet(10,30,10,10,10);
		collisionBullet2 = new Bullet(20,40,10,10,10);
		distanceBullet1 = new Bullet(0,0,0,0,10);
		distanceBullet2 = new Bullet(25,0,0,0,10);
	}
	
	@Test
	public void setPosX_LegalCase(){
		standardValueBullet.setPosX(20);
		assertEquals(20, standardValueBullet.getPosX(),EPSILON);
	}
	
	@Test
	public void setPosX_NegativeValue(){
		exception.expect(IllegalArgumentException.class);
		standardValueBullet.setPosX(Double.NaN);
		assertEquals(20, standardValueBullet.getPosX(),EPSILON);
	}
	
	@Test
	public void setPosY_LegalCase(){
		standardValueBullet.setPosY(20);
		assertEquals(20, standardValueBullet.getPosY(),EPSILON);
	}
	
	@Test
	public void setPosY_NegativeValue(){
		exception.expect(IllegalArgumentException.class);
		standardValueBullet.setPosY(Double.NaN);
		assertEquals(20, standardValueBullet.getPosY(),EPSILON);
	}
	
	@Test
	public void getPosX_LegalCase(){
		standardValueBullet.setPosX(0);
		assertEquals(0, standardValueBullet.getPosX(),EPSILON);
	}

	@Test
	public void getPosY_LegalCase(){
		standardValueBullet.setPosY(0);
		assertEquals(0, standardValueBullet.getPosY(),EPSILON);
	}
			
	@Test
	public void setRadius_LegalCase(){
		standardValueBullet.setRadius(30);
		assertEquals(30, standardValueBullet.getRadius(),EPSILON);
	}
	
	@Test
	public void setRadius_BelowLimit(){
		exception.expect(IllegalArgumentException.class);
		standardValueBullet.setRadius(0.5);
		assertEquals(30, standardValueBullet.getRadius(),EPSILON);
	}
	
	@Test
	public void getRadius_LegalCase(){
		standardValueBullet.setRadius(10);
		assertEquals(10, standardValueBullet.getRadius(),EPSILON);		
	}
	
	@Test
	public void move_LegalCase(){
		standardValueBullet.setPosX(10);
		standardValueBullet.setVel(10,standardValueBullet.getVelY());
		standardValueBullet.setPosY(10);
		standardValueBullet.setVel(standardValueBullet.getVelX(),10);
		standardValueBullet.setVel(10,10);
		standardValueBullet.setPosY(10);
		standardValueBullet.move(10);
		assertEquals(110, standardValueBullet.getPosX(),EPSILON);
		assertEquals(110, standardValueBullet.getPosY(),EPSILON);
	}
	
	@Test
	public void move_NegativeTime(){
		exception.expect(IllegalArgumentException.class);
		standardValueBullet.setPosX(10);
		standardValueBullet.setVel(10,standardValueBullet.getVelY());
		standardValueBullet.setPosY(10);
		standardValueBullet.setVel(standardValueBullet.getVelX(),10);
		standardValueBullet.move(-10);
		assertEquals(10, standardValueBullet.getPosX(),EPSILON);
		assertEquals(10, standardValueBullet.getPosY(),EPSILON);
	}
	
	@Test
	public void getDistanceBetween_LegalCase(){
		assertEquals(5,distanceBullet1.getDistanceBetween(distanceBullet2),EPSILON);
	}
	
	@Test
	public void getDistanceBetween_NullCase(){
		exception.expect(NullPointerException.class);
		collisionBullet1.getDistanceBetween(nullBullet);
	}
	
	@Test
	public void overlaps_LegalCase(){
		collisionBullet1.setPosX(0);
		collisionBullet1.setPosY(0);
		collisionBullet2.setPosX(0);
		collisionBullet2.setPosY(0);
		assertTrue(collisionBullet1.overlaps(collisionBullet2));
	}
	
	@Test
	public void overlaps_NullCase(){
		exception.expect(NullPointerException.class);
		collisionBullet1.overlaps(nullBullet);
	}
	 
	@Test
	public void getTimeToCollision_LegalCase(){
		distanceBullet1.setPosY(0);
		distanceBullet2.setPosY(0);
		distanceBullet1.setVel(5,0);
		distanceBullet2.setVel(distanceBullet2.getVelX(),0);
		assertEquals(1, distanceBullet1.getTimeToCollision(distanceBullet2), EPSILON);
	}

	@Test 
	public void getTimeToCollision_ScalarProdGreaterthan0(){
		distanceBullet1.setVel(distanceBullet1.getVelX(),10);
		distanceBullet2.setVel(distanceBullet2.getVelX(),10);
		assertEquals(Double.POSITIVE_INFINITY, distanceBullet1.getTimeToCollision(distanceBullet2), EPSILON);
	}
	
	@Test
	public void getTimeToCollision_dSmallerThen0(){
		distanceBullet1.setVel(10, 10);
		distanceBullet2.setVel(0,20);
		distanceBullet1.setPosX(20);
		distanceBullet1.setPosY(101);
		distanceBullet2.setPosX(10);
		distanceBullet2.setPosY(1);
		assertEquals(Double.POSITIVE_INFINITY, distanceBullet1.getTimeToCollision(distanceBullet2), EPSILON);
	}
	
	@Test
	public void getTimeToCollision_NullCase(){
		exception.expect(NullPointerException.class);
		collisionBullet1.getTimeToCollision(nullBullet);
	}
	
	
	@Test
	public void getCollisionPosition_LegalCase(){
		distanceBullet1.setVel(5, 0);
		distanceBullet2.setVel(0, 0);
		distanceBullet1.setPosX(0);
		distanceBullet1.setPosY(0);
		distanceBullet2.setPosX(25);
		distanceBullet2.setPosY(0);
		assertEquals(15,distanceBullet1.getCollisionPosition(distanceBullet2)[0],EPSILON);
		assertEquals(0,distanceBullet1.getCollisionPosition(distanceBullet2)[1],EPSILON);
	}
	
	@Test
	public void getCollisionPosition_deltaTInfinity(){
		distanceBullet1.setVel(0, 10);
		distanceBullet2.setVel(0, 10);
		assertNull(distanceBullet1.getCollisionPosition(distanceBullet2));
	}
	
	@Test
	public void getCollisionPosition_NullCase(){
		exception.expect(NullPointerException.class);
		collisionBullet1.getCollisionPosition(nullBullet);
	}

}
