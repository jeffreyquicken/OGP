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
	private Map<String,Expression<?>> parameters= new HashMap<String,Expression<?>>();
	
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
		this.parameters.put(name, value);
	}
	
	public Expression<?> getParameter(String name){
		return this.parameters.get(name);
	}
	
	
	public void setVariable(String name, Expression<?> value){
		if(name.startsWith("$"))
			this.setParameter(name, value);
		else
			this.localVariables.put(name, value);
	}
	
	public Expression<?> getVariable(String name){
		if(this.localVariables.containsKey(name))
				return this.localVariables.get(name);
		else
			return this.getProgram().getVariable(name);
	}
	
	public void evaluate(List<Expression<?>> variables) throws ReturnedException,BreakException{
		this.body.setFunction(this);
		this.body.setProgram(this.getProgram());
		for(int i=0;i<this.parameters.size();i++){
			String name = "$" + (i+1);
			variables.get(i).setFunction(this);
			variables.get(i).setProgram(this.getProgram());
			this.parameters.put(name,variables.get(i));
		}
		try{body.evaluate(Double.POSITIVE_INFINITY);}
		catch(BreakException b){
			throw new UnsupportedOperationException(b);
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
