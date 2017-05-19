package asteroids.model.program;
import java.util.*;

public class FunctionExpression extends Expression<Object>{

	public FunctionExpression(String functionName, List<Expression<?>> arguments) {
		this.functionName = functionName;
		this.arguments = arguments;
	}
	
	private String functionName;
	private List<Expression<?>> arguments;
	private boolean recursiveCall = false;
	
	public Function getFunction(){
		if(this.recursiveCall == false)
			return this.getProgram().getFunction(this.functionName);
		else
			return super.getFunction();
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
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		if(this.arguments!=null){
			for(Expression<?> e: this.arguments){
				e.setProgram(newProgram);
			}
		}
	}
	
	@Override
	public Object getValue() throws BreakException{
//		if(super.getFunction() == this.getFunction()){
//			this.recursiveCall = true;
//			this.setFunction((Function)super.getFunction().clone());
//		}
//		else
//			this.recursiveCall = false;
		try{this.getFunction().evaluate(arguments);}
		catch(ReturnedException r){
			return r.getValue();
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
