package asteroids.model.program;

public class VariableExpression extends Expression<Double> {
	public VariableExpression(String name, double value){
		this.name = name;
		this.setValue(value);
	}
	
	private String name;
	
	public String getName(){
		return this.name;
	}
	
}
