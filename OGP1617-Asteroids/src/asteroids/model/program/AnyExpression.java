package asteroids.model.program;
import asteroids.model.*;
public class AnyExpression extends Expression<Circle> {
	
	@Override
	public Circle getValue(){
		int smallestIndex = 0;
		Ship user = this.getProgram().getUser();
		for(int i=0;i<user.getNearestArray().length;i++){
			if((double)user.getNearestArray()[i][1]<(double)user.getNearestArray()[smallestIndex][1])
				smallestIndex = i;
		}
		return (Circle)user.getNearestArray()[smallestIndex][0];
	}

}
