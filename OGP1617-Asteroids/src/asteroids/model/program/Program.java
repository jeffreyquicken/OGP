package asteroids.model.program;
import be.kuleuven.cs.som.annotate.*;
import java.util.*;

import asteroids.model.*;

public class Program {
	
	public Program(List<Function> functions, Statement body){
		this.body = body;
		for(Function function:functions){
			this.functions.put(function.getName(), function);
		}
	}
	
	public void setVariable(String name, Expression<?> value){
		this.variables.put(name, value);
	}
	
	public Expression<?> getVariable(String name){
		return this.variables.get(name);
	}
	
	public void setParameter(String name, Expression<?> value){
		this.parameters.put(name, value);
	}
	
	public Expression<?> getParameter(String name){
		return this.parameters.get(name);
	}
	
	public Function getFunction(String name){
		return this.functions.get(name);
	}
	
	private Ship user;
	
	public Ship getUser(){
		return this.user;
	}
	
	public void setUser(Ship newUser){
		this.user = newUser;
	}
	
	
	private Statement body;
	private HashMap<String,Expression<?>> variables = new HashMap<String, Expression<?>>();
	private HashMap<String,Expression<?>> parameters = new HashMap<String, Expression<?>>();
	private HashMap<String,Function> functions = new HashMap<String,Function>();
	private List<Object> printedValues = new ArrayList<Object>();
	private boolean onHold = false;
	private double previousTime = 0;
	
	public void addPrintedValue(Object value){
		this.printedValues.add(value);
	}
	
	public List<Object> execute(double time){
		if(time<0.2){
			this.previousTime+=time;
			this.onHold = true;
		}
		else
			this.onHold = false;
		//DOE IETS
		return new ArrayList<Object>();
		}
}
