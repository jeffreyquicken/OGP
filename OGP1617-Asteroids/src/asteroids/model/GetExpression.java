package asteroids.model;
import asteroids.model.*;

public class GetExpression extends Expression<Double> {

	public GetExpression(Getter getter, Expression<?> expression) {
		this.getter = getter;
		this.expression = expression;
	}
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		this.expression.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		this.expression.setProgram(newProgram);
	}
	
	private Expression<?> expression;
	
	private Getter getter;
	
	@Override
	public Double getValue(){
		this.expression.setFunction(this.getFunction());
		this.expression.setProgram(this.getProgram());
		Object value = expression.getValue();
		if(value instanceof Circle && value != null){
			switch(this.getter){
			case GETX:
				return ((Circle)value).getPosX();
			case GETY:
				return ((Circle)value).getPosY();
			case GETVX:
				return ((Circle)value).getVelX();
			case GETVY:
				return ((Circle)value).getVelY();
			case GETRADIUS:
				return ((Circle)value).getRadius();
			}
			throw new AssertionError();
		}
		else
			throw new IllegalArgumentException();
	}
	
	
}
