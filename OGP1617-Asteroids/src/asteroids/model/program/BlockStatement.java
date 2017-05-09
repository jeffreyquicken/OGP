package asteroids.model.program;
import java.util.*;

public class BlockStatement extends Statement{
	public BlockStatement(List<Statement> statements){
		this.statements = statements;
	}
	
	private List<Statement> statements;
	
	public boolean execute(){
		for(Statement statement:statements){
			if(!statement.evaluate())
				return false;
		}
		return true;
	}
}
