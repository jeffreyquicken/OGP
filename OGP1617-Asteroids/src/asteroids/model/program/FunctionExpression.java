package asteroids.model.program;
import java.util.*;

public class FunctionExpression extends Expression<Object>{

	public FunctionExpression(String functionName, List<Expression<?>> arguments) {
		this.function = this.getProgram().getFunction(functionName);
		this.arguments = arguments;
	}
	
	private Function function;
	private List<Expression<?>> arguments;
	
	public Function getFunction(){
		return this.function;
	}
	
	public List<Expression<?>> getArguments(){
		return this.arguments;
	}
	
	public void execute(){
		this.getFunction().execute(arguments);
	}

}
