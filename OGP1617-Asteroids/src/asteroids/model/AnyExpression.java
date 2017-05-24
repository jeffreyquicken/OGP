package asteroids.model;
import asteroids.model.*;
public class AnyExpression extends Expression<Circle> {
	
	public Circle getValue(){
		return this.getProgram().getUser().getNearestCircle();
	}

}
