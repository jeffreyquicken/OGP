package asteroids.model.program;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
import java.util.*;

public class Function{
	
	public Function(String name, Statement body){
		this.name = name;
		this.body = body;
	}
	private String name;
	private Statement body;
	
	public String getName(){
		return this.name;
	}
	
	public void execute(List<Expression<?>> variables){
		body.evaluate();
	}
}
