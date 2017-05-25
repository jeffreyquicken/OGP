package asteroids.model;
import java.util.*;

public class FunctionExpression extends Expression<Object>{

	public FunctionExpression(String functionName, List<Expression<?>> arguments) {
		this.functionName = functionName;
		this.arguments = arguments;
	}
	
	private final String functionName;
	private final List<Expression<?>> arguments;
	
	public Function getFunction(){
		return this.getProgram().getFunction(this.functionName);
	}
	
	public String getFunctionName(){
		return this.functionName;
	}
	
	
	public List<Expression<?>> getArguments(){
		return this.arguments;
	}
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		if(this.arguments != null){
			for(Expression<?> e: this.arguments){
				e.setFunction(newFunction);
			}
		}
	}
	
	private HashMap<String,Object> localVariables = new HashMap<String,Object>();
	private HashMap<String,Object> parameters= new HashMap<String,Object>();
	
	public HashMap<String,Object> getParameters(){
		return this.parameters;
	}
	
	public HashMap<String,Object> getLocalVariables(){
		return this.localVariables;
	}
	
	public void setParameter(String name, Object value){
		this.parameters.put(name, value);
	}
	
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		if(this.arguments!=null){
			for(Expression<?> e: this.arguments){
				e.setProgram(newProgram);
			}
		}
	}
	
	public Object getParameter(String name){
		return this.getParameters().get(name);
	}
	
	private FunctionExpression previous;
	
	public FunctionExpression getPrevious(){
		return this.previous;
	}
	
	@Override
	public Object getValue() throws BreakException{
		for(int i=0;i<arguments.size();i++){
			String name = "$" + Integer.toString((i+1));
			arguments.get(i).setProgram(this.getProgram());
			arguments.get(i).setFunction(super.getFunction());
			this.setParameter(name, arguments.get(i).getValue());
		}
		this.getFunction().addExpression(this);
		try{this.getFunction().evaluate(arguments);}
		catch(ReturnedException r){
			Object value = r.getValue();
			System.out.println("t = " + value);
			return value;
		}
		catch(BreakException b){
			throw b;
		}
		catch(AssertionError a){
			throw a;
		}
		throw new AssertionError();
	}
}
