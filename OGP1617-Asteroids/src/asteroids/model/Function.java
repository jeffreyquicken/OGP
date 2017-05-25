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
	private final String name;
	private final Statement body;
	private Program program;
		
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
		return this.expressionList.get(this.expressionList.size()-1).getParameters();
	}
	
	public HashMap<String,Object> getLocalVariables(){
		return this.expressionList.get(this.expressionList.size()-1).getLocalVariables();
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
	
	private ArrayList<FunctionExpression> expressionList = new ArrayList<FunctionExpression>();
	

	public void addExpression(FunctionExpression exp){
		this.expressionList.add(exp);
	}
	
	public void removeLastExp(){
		this.expressionList.remove(this.expressionList.size()-1);
	}
	
	public void evaluate(List<Expression<?>> arguments) throws ReturnedException,BreakException{
		this.body.setFunction(this);
		try{body.evaluate(Double.POSITIVE_INFINITY);}
		catch(BreakException b){
//			this.decreaseRecursiveIndex();
			this.removeLastExp();
			throw b;
		}
		catch(ReturnedException r){
//			this.decreaseRecursiveIndex();
			this.removeLastExp();
			throw r;
		}
		catch(NotEnoughTimeException n){
//			this.decreaseRecursiveIndex();
			this.removeLastExp();
			throw new UnsupportedOperationException(n);
		}
		catch(AssertionError a){
//			this.decreaseRecursiveIndex();
			this.removeLastExp();
			throw a;
		}
//		this.decreaseRecursiveIndex();
		this.removeLastExp();
		throw new AssertionError();
	}
}
