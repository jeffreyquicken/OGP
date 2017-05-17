package asteroids.model.program;

public class VariableReadExpression extends Expression<Object>{

	public VariableReadExpression(String name) {
		this.name = name;
	}
	
	private String name;
	
	public Object getValue(){
		if(this.getFunction() == null)
			return this.getProgram().getVariable(name).getValue();
		else{
			return this.getFunction().getVariable(name).getValue();
		}
	}
	
}
