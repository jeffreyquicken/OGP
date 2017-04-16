package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.ExpectedException;

import asteroids.model.Bullet;
import asteroids.model.Vector2D;
import asteroids.model.World;

/**
 * A class collecting test for the more complicated methods from the class of Vector2D.
 * @author Senne Gielen & Jeffrey Quicken
 *
 */
public class Vector2DTests {
	
	private static Vector2D vector1;
	private static Vector2D vector2;
	private static final double EPSILON = 0.0001;
	
	@Before
	public void setUpMutableFixture(){
		vector1 = new Vector2D(10,5);
		vector2 = new Vector2D(10,20);
		}
	
	@Test
	public void ScalarProduct() {
		assertEquals(200, vector1.scalarProduct(vector2),EPSILON);
	}
	
	@Test
	public void Equals_TrueCase() {
		vector2.setY(5);
		assertTrue(vector1.equals(vector2));
	}
	
	@Test
	public void Equals_FalseCase() {
		vector2.setY(10);
		assertFalse(vector1.equals(vector2));
	}

}
