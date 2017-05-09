package asteroids.model.program;

public class AssignmentStatement extends Statement {
	public AssignmentStatement(String name, Expression<?> expression){
		this.name = name;
		this.expression = expression;
	}
	
	private String name;
	private Expression<?> expression;
	
	public boolean evaluate(){
		this.getProgram().setVariable(name,expression);
		return true;
	}
}
