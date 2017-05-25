package asteroids.model;

public class VariableReadExpression extends Expression<Object>{

	public VariableReadExpression(String name) {
		this.name = name;
	}
	
	private final String name;
	
	public Object getValue(){
		if(this.getFunction() == null){
			if(this.getProgram().getVariable(name) == null)
				throw new IllegalArgumentException();
			return this.getProgram().getVariable(name);
		}
		else{
			if(this.getFunction().getVariable(name) == null)
				throw new IllegalArgumentException();
			return this.getFunction().getVariable(name);
		}
	}
	
}
