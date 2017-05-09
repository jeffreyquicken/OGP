package asteroids.model.program;

public class ParameterExpression<T> extends Expression<T>{
	public ParameterExpression(String name, T value){
		this.name = name;
	}
	
	private String name;
	
	public String getName(){
		return this.name;
	}
	
	@Override
	public void setValue(T newValue){
	}
}
