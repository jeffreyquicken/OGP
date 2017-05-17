package asteroids.model.program;

public class AssignmentStatement extends Statement {
	public AssignmentStatement(String name, Expression<?> expression){
		this.name = name;
		this.expression = expression;
	}
	
	private String name;
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
	
	public void evaluate(double time) throws NotEnoughTimeException{
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		if(this.getFunction() == null){
			if( (this.getProgram().getVariable(name) == null || this.getProgram().getVariable(this.name).getValue().getClass().equals(this.expression.getValue().getClass())
					&& !this.getProgram().containsFunction(name)))
				this.getProgram().setVariable(name,expression);
			else
				throw new AssertionError();
			}
		else{
			if(this.getFunction().getParameter(name) == null||this.getFunction().getVariable(name) == null || this.getFunction().getVariable(this.name).getValue().getClass().equals(this.expression.getValue().getClass()))
				this.getFunction().setVariable(name,expression);
			else
				throw new AssertionError();
			}
	}
}
