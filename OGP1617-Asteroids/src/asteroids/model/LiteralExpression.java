package asteroids.model;

public class LiteralExpression extends Expression<Double> {
	public LiteralExpression(double value){
		this.value = value;
	}
	
	public Double getValue(){
		return this.value;
	}
	
	private final double value;
}
