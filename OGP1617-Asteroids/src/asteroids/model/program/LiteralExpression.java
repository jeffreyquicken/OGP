package asteroids.model.program;

public class LiteralExpression<T> extends Expression<T> {
	public LiteralExpression(T value){
		this.setValue(value);
	}
}
