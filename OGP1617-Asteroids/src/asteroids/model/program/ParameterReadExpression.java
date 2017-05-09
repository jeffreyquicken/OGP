package asteroids.model.program;

public class ParameterReadExpression extends Expression<Object>{

	public ParameterReadExpression(String name) {
		this.setValue(this.getProgram().getParameter(name));
	}

}
