package asteroids.model.program;

public class PrintStatement extends Statement {
	public PrintStatement(Expression<?> expression){
		this.expression = expression;
	}
	
	private Expression<?> expression;
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		expression.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		expression.setProgram(newProgram);
	}
	
	public void evaluate(double time) throws NotEnoughTimeException,BreakException{
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		System.out.println(expression.toString());
		this.getProgram().addPrintedValue(this.expression.getValue());
	}
}
