package asteroids.model.program;

public class ParameterReadExpression extends Expression<Object>{

	public ParameterReadExpression(String name) {
		this.name = name;
	}
	
	private String name;
	
	
	@Override
	public Object getValue(){
		return this.getFunction().getParameter(name);
	}

}
