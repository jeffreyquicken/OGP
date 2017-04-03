package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

@Value
public class Vector2D {
	
	public Vector2D(double x, double y) throws IllegalArgumentException{
		this.setX(x);
		this.setY(y);
	}
	
	private double xComponent;
	private double yComponent;
	
	public void setX(double newX) throws IllegalArgumentException{
		if(Double.isNaN(newX))
			throw new IllegalArgumentException();
		else
			this.xComponent = newX;
	}
	
	public void setY(double newY) throws IllegalArgumentException{
		if(Double.isNaN(newY))
			throw new IllegalArgumentException();
		else
			this.yComponent = newY;
	}
	
	public double getX(){
		return this.xComponent;
	}
	
	public double getY(){
		return this.yComponent;
	}
	
	public double length(){
		return Math.sqrt(Math.pow(this.getX(),2)+Math.pow(this.getY(), 2));
	}
	
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

}
