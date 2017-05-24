package asteroids.model;

public class EqualExpression extends Expression<Boolean> {
	public EqualExpression(Expression<?> e1, Expression<?> e2){
		this.firstExpression=e1;
		this.secondExpression = e2;
	}
	
	private final Expression<?> firstExpression;
	private final Expression<?> secondExpression;
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		firstExpression.setFunction(newFunction);
		secondExpression.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		firstExpression.setProgram(newProgram);
		secondExpression.setProgram(newProgram);
	}
	
	@Override
	public void setIndex(int i){
		super.setIndex(i);
		this.firstExpression.setIndex(i);
		this.secondExpression.setIndex(i);
	}
	
	@Override
	public Boolean getValue(){
		firstExpression.setFunction(this.getFunction());
		secondExpression.setFunction(this.getFunction());
		firstExpression.setProgram(this.getProgram());
		secondExpression.setProgram(this.getProgram());
		Object value1 = firstExpression.getValue();
		Object value2 = secondExpression.getValue();
		if(value1.getClass().equals(value2.getClass()) 
				|| (value1 == null && value2 == null))
			return (value1 == value2);
		else
			return false;
	}
}
