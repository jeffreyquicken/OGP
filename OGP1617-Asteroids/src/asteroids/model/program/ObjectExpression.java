package asteroids.model.program;

import asteroids.model.*;

public class ObjectExpression<T> extends Expression<Object> {

	public ObjectExpression() {
		this.user = this.getProgram().getUser();
	}
	
	private Ship user;
	Class<T> type;
	
	public T getValue(){
		double smallest = Double.MAX_VALUE;
		T returnObject = null;
		for(Object object: user.getWorld().getWorldEntities()){
			if(type.isInstance(object) && !type.isAssignableFrom(Circle.class)){
				double distance = user.getDistanceBetween((Circle)object);
				if(distance<smallest){
					smallest = distance;
					returnObject = (T)object;
				}
			}
			else if(type.isInstance(object) && !type.isAssignableFrom(World.class)){
				double distance = user.getDistanceBetween((World)object);
				if(distance<smallest){
					smallest = distance;
					returnObject = (T)object;
				}
			}
		}
		return returnObject;
	}
}
