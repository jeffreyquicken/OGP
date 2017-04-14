package asteroids.model;
import java.util.*;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

/**
 * A class of 2-dimensional vectors.
 * 
 * @author Senne Gielen & Jeffrey Quicken
 *
 */
@Value
public class Vector2D {
	
	/**
	 * Creates a new 2 dimensional vector.
	 * @param x
	 * 		  The x component of the new vector.
	 * @param y
	 * 		  The y component of the new vector.
	 * @effect this.setX(x)
	 * 		   Sets the x component of the vector to x.
	 * @effect this.setY(y)
	 * 		   Sets the y component of the vector to y.
	 */
	@Raw
	public Vector2D(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	
	/**
	 * Creates a new 2 dimensional vector with an array as argument.
	 * 
	 * @param vectorArray
	 * 		  The array which contains the new x and y components of the vector.
	 * @effect Vector2D(vectorArray[0],vectorArray[1])
	 * 		  Creates a new 2 dimensional vector with a given x and y coordinate.
	 */
	@Raw
	public Vector2D(double[] vectorArray){
		this.setX(vectorArray[0]);
		this.setY(vectorArray[1]);
	}
	
	private double xComponent;
	private double yComponent;
	
	/**
	 * Sets the x component of this vector to newX
	 * @param newX
	 * 		  The new x component of the vector.
	 */
	public void setX(double newX){
		this.xComponent = newX;
	}
	
	/**
	 * Sets the y component of this vector to newY
	 * @param newY
	 * 		  The new y component of the vector.
	 */
	public void setY(double newY){
		this.yComponent = newY;
	}
	
	/**
	 * Returns the x component of the vector.
	 * @return the x component of the vector.
	 * 		   |result == this.xComponent
	 */
	public double getX(){
		return this.xComponent;
	}
	
	/**
	 * Returns the y component of the vector.
	 * @return the y component of the vector.
	 * 		   |result == this.yComponent
	 */
	public double getY(){
		return this.yComponent;
	}
	
	/**
	 * Returns the length of the vector.
	 * @return
	 * 		  The length of the vector.
	 * 		  |result == Math.sqrt(Math.pow(this.getX(),2)+Math.pow(this.getY(), 2))
	 */
	public double length(){
		return Math.sqrt(Math.pow(this.getX(),2)+Math.pow(this.getY(), 2));
	}
	
	/**
	 * Converts the vector to an array.
	 * 
	 * @return An array with elements.
	 * 		  |result == {this.getX(),this.getY()}
	 */
	public double[] array(){
		double[] array = {this.getX(),this.getY()};
		return array;
	}
	
	/**
	 * Calculates the scalar product of two 2-dimensional vectors.
	 * 
	 * @param vect1 
	 * 		  The first 2-dimensional vector
	 * @param vect2 
	 * 		  The second 2-dimensional vector
	 * @return 
	 * 		  Returns the scalar product of the 2-dimensional vectors.
	 * 		  result == vect1[0]*vect2[0] + vect1[1]*vect2[1]
	 */
	public double scalarProduct(Vector2D other){
		return this.getX()*other.getX() + this.getY()*other.getY();
	}
	
	
	public double angle(){
		return Math.atan2(this.getY(), this.getX());
	}
	
	/**
	 * Subtracts another vector from this vector.
	 * 
	 * @param other
	 * 		  The vector which is subtracted from this vector.
	 * @return
	 * 		  The resulting vector.
	 * 		  |result == new Vector2D(this.getX()-other.getX(),this.getY()-other.getY())
	 */
	public Vector2D substract(Vector2D other){
		return new Vector2D(this.getX()-other.getX(),this.getY()-other.getY());
	}
	
	/**
	 * Multiplies a vector with a scalar
	 * 
	 * @param scalar
	 * 		  The scalar with which the vector has to be multiplied.
	 * @return
	 * 		  The resulting vector.
	 * 		  |result == new Vector2D(this.getX()*scalar,this.getY()*scalar)
	 */
	public Vector2D multiply(double scalar){
		return new Vector2D(this.getX()*scalar,this.getY()*scalar);
	}
	
	/**
	 * Adds a vector to this vector
	 * 
	 * @param other
	 * 		  The vector to be added to this vector.
	 * @return
	 * 		  The resulting vector.
	 * 		  |new Vector2D(this.getX()+other.getX(),this.getY()+other.getY())
	 */
	public Vector2D add(Vector2D other){
		return new Vector2D(this.getX()+other.getX(),this.getY()+other.getY());
	}
	
	/**
	 * Checks if this vector and an object are equal.
	 * @param other
	 * 		  The object of which has to be checked if it is equal to the vector.
	 * @return
	 * 		  True if the object is a vector and the x component and y component are equal.
	 * 		  |result == (other instanceof Vector2D && this.getX() == ((Vector2D)other).getX() && this.getY() == ((Vector2D)other).getY())
	 */
	@Override
	public boolean equals(Object other){
		if(other instanceof Vector2D)
			return this.getX() == ((Vector2D)other).getX() && this.getY() == ((Vector2D)other).getY();
		else
			return false;
	}

}
