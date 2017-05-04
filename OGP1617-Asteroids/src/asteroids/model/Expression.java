package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public class Expression<T> {
	
	public Expression(T var){
		this.value = var;
	}
	private T value;
	
	@Override
	public String toString(){
		return this.getValue().toString();
	}
	
	public T getValue(){
		return value;
	}
	
	@Override
	public boolean equals(Object other){
		if(other == null && this != null)
			return false;
		else if(other instanceof Expression<?>)
			return this.getValue().equals(((Expression<?>)other).getValue());
		else
			return false;
	}
}

