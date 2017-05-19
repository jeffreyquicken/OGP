package asteroids.model.program;

public class LiteralExpression extends Expression<Double> {
	public LiteralExpression(double value){
		this.setValue(value);
	}
}
