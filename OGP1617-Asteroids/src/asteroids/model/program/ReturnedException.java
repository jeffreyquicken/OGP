package asteroids.model.program;

public class ReturnedException extends Exception{
	public ReturnedException(){
		super();
		this.value = null;
	}
	
	public ReturnedException(Object value){
		super();
		this.value = value;
	}
	
	private final Object value;
	public Object getValue(){
		return this.value;
	}
}
