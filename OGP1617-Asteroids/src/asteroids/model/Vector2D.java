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
	
	@Raw
	public Vector2D(double x, double y){
		this.setX(x);
		this.setY(y);
	}
	
	@Raw
	public Vector2D(double[] vectorArray){
		this.setX(vectorArray[0]);
		this.setY(vectorArray[1]);
	}
	
	private double xComponent;
	private double yComponent;
	
	public void setX(double newX){
		this.xComponent = newX;
	}
	
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
	
	public Vector2D substract(Vector2D other){
		return new Vector2D(this.getX()-other.getX(),this.getY()-other.getY());
	}
	public Vector2D multiply(double scalar){
		return new Vector2D(this.getX()*scalar,this.getY()*scalar);
	}
	public Vector2D add(Vector2D other){
		return new Vector2D(this.getX()+other.getX(),this.getY()+other.getY());
	}
	
	@Override
	public boolean equals(Object other){
		if(other instanceof Vector2D)
			return this.getX() == ((Vector2D)other).getX() && this.getY() == ((Vector2D)other).getY();
		else
			return false;
	}

}
