package asteroids.model;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
import java.util.*;

public class Function{
	
	public Function(String name, Statement body){
		this.name = name;
		this.body = body;
	}
	private final String name;
	private Statement body;
	
	public void execute(List<Object> variables){
		
	}
}
