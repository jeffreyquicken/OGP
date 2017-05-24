package asteroids.model;
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
	private HashMap<String,Object> localVariables = new HashMap<String,Object>();
	private HashMap<String,Object> parameters= new HashMap<String,Object>();
		
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
	
	public HashMap<String,Object> getParameters(){
		return this.expressionList.get(recursiveIndex-1).getParameters();
	}
	
	public HashMap<String,Object> getLocalVariables(){
		return this.expressionList.get(recursiveIndex-1).getLocalVariables();
	}
	
	public void setParameter(String name, Expression<?> value){
		this.getParameters().put(name, value.getValue());
	}
	
	public void setParameter(String name, Object value){
		this.getParameters().put(name, value);
	}
	
	public Object getParameter(String name){
		return this.getParameters().get(name);
	}
	
	public Object getParameter(String name, int index){
		return this.expressionList.get(index).getParameter(name);
	}
	
	public void setVariable(String name, Expression<?> value){
		if(name.startsWith("$"))
			this.setParameter(name, value.getValue());
		else{
			this.getLocalVariables().put(name, value.getValue());
		}
	}
	
	public void setVariable(String name, Object value){
		if(name.startsWith("$"))
			this.setParameter(name, value);
		else{
			this.getLocalVariables().put(name, value);
		}
	}
	
	public Object getVariable(String name){
		if(name.startsWith("$"))
			return this.getParameter(name);
		if(this.getLocalVariables().containsKey(name))
			return this.getLocalVariables().get(name);
		else
			return this.getProgram().getVariable(name);
	}
	
	public Object getVariable(String name,int i){
		if(this.expressionList.get(i).getLocalVariables().containsKey(name))
			return this.expressionList.get(i).getLocalVariables().get(name);
		else
			return this.getProgram().getVariable(name);
	}
	
	private ArrayList<FunctionExpression> expressionList = new ArrayList<FunctionExpression>();
	
	private int recursiveIndex = 0;
	
	public int getRecursiveIndex(){
		return this.recursiveIndex;
	}
	public void increaseRecursiveIndex(){
		this.recursiveIndex+=1;
	}
	
	public void decreaseRecursiveIndex(){
		this.recursiveIndex-=1;
	}
	
	public void addExpression(FunctionExpression exp){
		this.increaseRecursiveIndex();
		if(this.expressionList.size()<=this.getRecursiveIndex())
			this.expressionList.add(exp);
		else	
			this.expressionList.set(recursiveIndex-1, exp);
	}
	
	
	public void evaluate(List<Expression<?>> variables) throws ReturnedException,BreakException{
		this.body.setFunction(this);
		try{body.evaluate(Double.POSITIVE_INFINITY);}
		catch(BreakException b){
			this.decreaseRecursiveIndex();
			throw b;
		}
		catch(ReturnedException r){
			this.decreaseRecursiveIndex();
			throw r;
		}
		catch(NotEnoughTimeException n){
			this.decreaseRecursiveIndex();
			throw new UnsupportedOperationException(n);
		}
		catch(AssertionError a){
			this.decreaseRecursiveIndex();
			throw a;
		}
		this.decreaseRecursiveIndex();
		throw new AssertionError();
	}
}
