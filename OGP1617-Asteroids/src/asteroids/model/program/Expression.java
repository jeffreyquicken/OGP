package asteroids.model.program;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;

public abstract class Expression<T> {
	
	private T value;
	
	@Override
	public String toString(){
		if(this.getValue() != null)
			return this.getValue().toString();
		else
			return null;
	}
	
	public T getValue(){
		return value;
	}
	
	public void setValue(T newValue){
		this.value = newValue;
	}
	
	private Function function;
	
	public Function getFunction(){
		return this.function;
	}
	
	public void setFunction(Function newFunction){
		this.function = newFunction;
	}
	
	private Program program;
	
	public Program getProgram(){
		return this.program;
	}
	
	public void setProgram(Program newProgram){
		this.program = newProgram;
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

