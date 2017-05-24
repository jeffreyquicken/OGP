package asteroids.model;

public class ParameterReadExpression extends Expression<Object>{

	public ParameterReadExpression(String name) {
		this.name = name;
	}
	
	private final String name;
	
	
	@Override
	public Object getValue(){
		if(this.getFunction().getParameter(name) == null)
			return null;
		else
			return this.getFunction().getParameter(name);
	}

}
