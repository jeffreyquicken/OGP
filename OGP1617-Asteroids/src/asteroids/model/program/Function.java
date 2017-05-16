package asteroids.model.program;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
import java.util.*;

public class Function{
	
	public Function(String name, Statement body){
		this.name = name;
		this.body = body;
	}
	private String name;
	private Statement body;
	private Program program;
	private List<Expression<?>> arguments = new ArrayList<Expression<?>>();
	private Map<String,Expression<?>> localVariables = new HashMap<String,Expression<?>>();
	
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
	
	
	public void setVariable(String name, Expression<?> value){
		if(name.startsWith("$")){
			int index = Integer.parseInt(name.substring(1));
			if(index<this.arguments.size())
				this.arguments.set(index,value);
			else
				throw new IllegalArgumentException();
		}
		else if(this.localVariables.containsKey(name))
				this.localVariables.put(name, value);
		else
			this.getProgram().setVariable(name, value);
	}
	
	public Expression<?> getVariable(String name){
		if(name.startsWith("$")){
			int index = Integer.parseInt(name.substring(1));
			if(index<this.arguments.size())
				return this.arguments.get(index);
			else
				throw new IllegalArgumentException();
		}
		else if(this.localVariables.containsKey(name))
				return this.localVariables.get(name);
		else
			return this.getProgram().getVariable(name);
	}
	
	public void evaluate(List<Expression<?>> variables) throws ReturnedException,BreakException{
		this.arguments = variables;
		try{body.evaluate(Double.POSITIVE_INFINITY);}
		catch(BreakException b){
			throw b;
		}
		catch(ReturnedException r){
			throw r;
		}
		catch(NotEnoughTimeException n){
			throw new IllegalArgumentException(n);
		}
	}
}
