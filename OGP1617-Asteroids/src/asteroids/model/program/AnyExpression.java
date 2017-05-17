package asteroids.model.program;
import asteroids.model.*;
public class AnyExpression extends Expression<Circle> {
	
	@Override
	public Circle getValue(){
		return this.getProgram().getUser().getNearestCircle();
	}

}
