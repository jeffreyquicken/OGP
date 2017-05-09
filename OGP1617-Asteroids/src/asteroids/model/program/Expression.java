package asteroids.model.program;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public abstract class Expression<T> {
	
	private T value;
	
	@Override
	public String toString(){
		return this.getValue().toString();
	}
	
	public T getValue(){
		return value;
	}
	
	public void setValue(T newValue){
		this.value = newValue;
	}
	
	private Program program;
	
	public Program getProgram(){
		return this.program;
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

