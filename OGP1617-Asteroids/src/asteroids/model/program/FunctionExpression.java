package asteroids.model.program;
import java.util.*;

public class FunctionExpression extends Expression<Object>{

	public FunctionExpression(String functionName, List<Expression<?>> arguments) {
		this.functionName = functionName;
		this.arguments = arguments;
	}
	
	private String functionName;
	private List<Expression<?>> arguments;
	
	public Function getFunction(){
		return this.getProgram().getFunction(this.functionName);
	}
	
	public List<Expression<?>> getArguments(){
		return this.arguments;
	}
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		for(Expression<?> e: this.arguments){
			e.setFunction(newFunction);
		}
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		for(Expression<?> e: this.arguments){
			e.setProgram(newProgram);
		}
	}
	
	@Override
	public Object getValue(){
		try{this.getFunction().evaluate(arguments);}
		catch(ReturnedException r){
			return r.getValue();
		}
		catch(BreakException b){
			throw new UnsupportedOperationException(b);
		}
		throw new AssertionError();
	}

}
