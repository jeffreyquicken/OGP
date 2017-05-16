package asteroids.model.program;
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
		if(expression.getValue() instanceof Circle && expression.getValue() != null){
//			switch(this.getter){
//			case GETX:
//				return ((Expression<Circle>)expression).getValue().getPosX();
//			case GETY:
//				return ((Expression<Circle>)expression).getValue().getPosY();
//			case GETVX:
//				return ((Expression<Circle>)expression).getValue().getVelX();
//			case GETVY:
//				return ((Expression<Circle>)expression).getValue().getVelY();
//			case GETRADIUS:
//				return ((Expression<Circle>)expression).getValue().getRadius();
//			}
			switch(this.getter){
			case GETX:
				return ((Circle)expression.getValue()).getPosX();
			case GETY:
				return ((Circle)expression.getValue()).getPosY();
			case GETVX:
				return ((Circle)expression.getValue()).getVelX();
			case GETVY:
				return ((Circle)expression.getValue()).getVelY();
			case GETRADIUS:
				return ((Circle)expression.getValue()).getRadius();
			}
			throw new AssertionError();
		}
		else
			throw new UnsupportedOperationException();
	}
	
	
}
