package asteroids.model;

public class AssignmentStatement extends Statement {
	public AssignmentStatement(String name, Expression<?> expression){
		this.name = name;
		this.expression = expression;
	}
	
	private final String name;
	private final Expression<?> expression;
	
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
		Object value = this.expression.getValue();
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		if(this.getFunction() == null){
			if( (this.getProgram().getVariable(name) == null || this.getProgram().getVariable(name).getClass().equals(value.getClass()))
					&& !this.getProgram().containsFunction(name))
				this.getProgram().setVariable(name,value);
			else
				throw new IllegalArgumentException();
			}
		else{
			if(this.getFunction().getParameter(name) == null||this.getFunction().getVariable(name) == null || this.getFunction().getVariable(this.name).getClass().equals(value.getClass()))
				this.getFunction().setVariable(name,value);
			else
				throw new IllegalArgumentException();
			}
	}
}
