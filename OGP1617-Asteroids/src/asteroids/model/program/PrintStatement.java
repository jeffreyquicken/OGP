package asteroids.model.program;

public class PrintStatement extends Statement {
	public PrintStatement(Expression<?> expression){
		this.expression = expression;
	}
	
	private Expression<?> expression;
	
	public boolean evaluate(){
		System.out.println(expression.toString());
		this.getProgram().addPrintedValue(this.expression.getValue());
		return true;
	}
}
