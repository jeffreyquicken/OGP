package asteroids.model.program;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
import java.util.*;

public class Function{
	
	public Function(String name, Statement body){
		this.name = name;
		this.body = body;
		this.body.setFunction(this);
	}
	private String name;
	private Statement body;
	private Program program;
	private List<Expression<?>> arguments = new ArrayList<Expression<?>>();
	private Map<String,Object> localVariables = new HashMap<String,Object>();
	private Map<String,Object> parameters= new HashMap<String,Object>();
		
	public void setProgram(Program newProgram){
		this.program = newProgram;
		this.body.setProgram(newProgram);
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setParameter(String name, Expression<?> value){
		this.parameters.put(name, value.getValue());
	}
	
	public void setParameter(String name, Object value){
		this.parameters.put(name, value);
	}
	
	public Object getParameter(String name){
		return this.parameters.get(name);
	}
	
	public void setVariable(String name, Expression<?> value){
		if(name.startsWith("$"))
			this.setParameter(name, value);
		else
			this.localVariables.put(name, value.getValue());
	}
	
	public void setVariable(String name, Object value){
		if(name.startsWith("$"))
			this.setParameter(name, value);
		else
			this.localVariables.put(name, value);
	}
	
	public Object getVariable(String name){
		if(this.localVariables.containsKey(name))
			return this.localVariables.get(name);
		else
			return this.getProgram().getVariable(name);
	}
	
	public boolean hasRecursiveCalls(){
		return true;
	}
	
	public Object clone(){
		Function function = new Function(this.name,this.body);
		function.setProgram(this.getProgram());
		for(String name:this.localVariables.keySet()){
			function.setVariable(name, this.getVariable(name));
		}
		return function;
	}
	
	public void evaluate(List<Expression<?>> variables) throws ReturnedException,BreakException{
		for(int i=0;i<variables.size();i++){
			String name = "$" + Integer.toString((i+1));
			variables.get(i).setProgram(this.getProgram());
			variables.get(i).setFunction(this);
			this.parameters.put(name,variables.get(i).getValue());
		}
		this.body.setFunction(this);
		try{body.evaluate(Double.POSITIVE_INFINITY);}
		catch(BreakException b){
			throw b;
		}
		catch(ReturnedException r){
			throw r;
		}
		catch(NotEnoughTimeException n){
			throw new UnsupportedOperationException(n);
		}
		catch(AssertionError a){
			throw a;
		}
		throw new AssertionError();
	}
}
