package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import java.util.*;

public class Program {
	
	public Program(List<Function> functions, Statement body){
	this.functions = functions;
	this.body = body;
	}
	
	private List<Function> functions;
	private Statement body;
	
	private boolean onHold = false;
	private double previousTime = 0;
	
	public void execute(double time){
		if(time<0.2){
			this.previousTime+=time;
			this.onHold = true;
		}
		else
			this.onHold = false;
		//DOE IETS
		previousTime += (time-body.execute(functions));
	}
}
