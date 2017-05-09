package asteroids.model.program;
import be.kuleuven.cs.som.annotate.*;
import be.kuleuven.cs.som.taglet.*;
import java.util.*;

public abstract class Statement {
	
	private Program program;
	
	public Program getProgram(){
		return this.program;
	}
	
	public boolean evaluate(){
		return true;
	}
}
