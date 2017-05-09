package asteroids.model.program;

public class VariableReadExpression extends Expression<Object>{

	public VariableReadExpression(String name) {
		this.setValue(this.getProgram().getVariable(name));
	}
	
}
