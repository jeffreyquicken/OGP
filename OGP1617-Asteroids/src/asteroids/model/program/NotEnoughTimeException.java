package asteroids.model.program;

public class NotEnoughTimeException extends Exception {
	public NotEnoughTimeException(){
		super();
		this.time = 0;
	}
	
	public NotEnoughTimeException(double restingTime){
		super();
		this.time = restingTime;
	}
	
	private double time;
	
	public double getTime(){
		return this.time;
	}
}
