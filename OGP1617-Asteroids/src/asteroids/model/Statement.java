package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
import java.util.*;

public abstract class Statement {
	
	private Program program;
	
	public Program getProgram(){
		return this.program;
	}
	
	private Function function;
	
	public Function getFunction(){
		return this.function;
	}
	
	public void setFunction(Function newFunction){
		this.function = newFunction;
	}
	
	public void setProgram(Program newProgram){
		this.program = newProgram;
	}
	
	public abstract void evaluate(double time) throws NotEnoughTimeException,BreakException,ReturnedException;
}
