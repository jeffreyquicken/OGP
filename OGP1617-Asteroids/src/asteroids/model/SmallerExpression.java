package asteroids.model;

public class SmallerExpression extends Expression<Boolean> {
	public SmallerExpression(Expression<?> e1, Expression<?> e2){
		this.firstExpression=e1;
		this.secondExpression = e2;
	}
	
	private Expression<?> firstExpression;
	private Expression<?> secondExpression;
	
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
		firstExpression.setIndex(i);
		secondExpression.setIndex(i);
	}
	
	@Override
	public Boolean getValue(){
		Object value1 = firstExpression.getValue();
		Object value2 = secondExpression.getValue();
		if(value1 instanceof Double && value2 instanceof Double)
			return (Double)value1<(Double)value2;
		else
			throw new IllegalArgumentException();
	}
}
