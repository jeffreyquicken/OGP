package asteroids.model.program;

import asteroids.model.*;

public class DirectionExpression extends Expression<Double> {
	
	public Double getValue(){
		return this.getProgram().getUser().getOrientation();
		
	}

}
