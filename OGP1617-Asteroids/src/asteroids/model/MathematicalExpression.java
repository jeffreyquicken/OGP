package asteroids.model;

public class MathematicalExpression {
	
	public MathematicalExpression(String type, Expression<?> firstArgument, Expression<?>secondArgument){
		this.type = type;
		this.firstArgument = firstArgument;
		this.secondArgument = secondArgument;

	}
	
	private double value;
	private String type;
	private Expression<?> firstArgument;
	private Expression<?> secondArgument;
	
	switch (type){
	case "multiplication":
		this.value = firstArgument.getValue()*secondArgument.getValue();
		break;
	}
}
}
