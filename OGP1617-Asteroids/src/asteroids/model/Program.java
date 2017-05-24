package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.util.*;

import asteroids.model.*;

public class Program {
	
	public Program(List<Function> functions, Statement body){
		this.body = body;
		for(Function function:functions){
			this.functions.put(function.getName(), function);
			function.setProgram(this);
		}
		this.body.setProgram(this);
	}
	
	public void setVariable(String name, Expression<?> value){
		this.variables.put(name, value.getValue());
	}
	
	public void setVariable(String name, Object value){
		this.variables.put(name,value);
	}
	
	public Object getVariable(String name){
		return this.variables.get(name);
	}
	
	public boolean containsVariable(String name){
		return this.variables.containsKey(name);
	}
	
	public Function getFunction(String name){
		return this.functions.get(name);
	}
	
	public boolean containsFunction(String name){
		return this.functions.containsKey(name);
	}
	
	private Ship user;
	
	public Ship getUser(){
		return this.user;
	}
	
	public void setUser(Ship newUser){
		this.user = newUser;
	}
	
	
	private Statement body;
	private HashMap<String,Object> variables = new HashMap<String, Object>();
	private HashMap<String,Function> functions = new HashMap<String,Function>();
	private List<Object> printedValues = new ArrayList<Object>();
	private double previousTime = 0;
	
	public void addPrintedValue(Object value){
		this.printedValues.add(value);
	}
	
	public List<Object> execute(double time){
		time+=previousTime;
		if(time<0.2){
			this.previousTime = time;
			return null;
		}
		if(this.body instanceof ActionStatement || this.body instanceof TurnStatement){
			try{this.body.evaluate(time);}
			catch(NotEnoughTimeException n){
				this.previousTime=n.getTime();
				return null;
			}
			catch(ReturnedException r){
				throw new IllegalArgumentException(r);
			}
			catch(BreakException b){
				throw new IllegalArgumentException(b);
			}
			catch(AssertionError a){
				throw new IllegalArgumentException(a);
			}
			catch(UnsupportedOperationException u){
				return printedValues;
			}
			time-=0.2;
		}
		else{
			try{this.body.evaluate(time);}
			catch(NotEnoughTimeException n){
				this.previousTime=n.getTime();
				return null;
			}
			catch(ReturnedException r){
				throw new IllegalArgumentException(r);
			}
			catch(BreakException b){
				throw new IllegalArgumentException(b);
			}
			catch(AssertionError a){
				throw new IllegalArgumentException(a);
			}
			catch(UnsupportedOperationException u){
				return printedValues;
			}
		}
		this.previousTime = 0;
		return printedValues;
		}
}
