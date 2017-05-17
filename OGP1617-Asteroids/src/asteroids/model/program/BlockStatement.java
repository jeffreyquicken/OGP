package asteroids.model.program;
import java.util.*;

public class BlockStatement extends Statement{
	public BlockStatement(List<Statement> statements){
		this.statements = statements;
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		for(Statement statement:this.getStatements()){
			statement.setProgram(newProgram);
		}
	}
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		for(Statement statement:this.getStatements()){
			statement.setFunction(newFunction);
		}
	}
	
	private double usedTime = 0;
	
	public double getUsedTime(){
		return this.usedTime;
	}
	
	private int stoppedStatementIndex = 0;
	
	private List<Statement> statements;
	
	public void evaluate(double time) throws NotEnoughTimeException,ReturnedException,BreakException{
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		for(int i=stoppedStatementIndex;i<statements.size();i++){
			Statement statement = statements.get(i);
			try{statement.evaluate(time);}
			catch(NotEnoughTimeException n){
				stoppedStatementIndex = i;
				throw n;
			}
			catch(ReturnedException r){
				throw r;
			}
			catch(BreakException b){
				throw b;
			}
			catch(UnsupportedOperationException u){
				break;
			}
			catch(AssertionError a){
				throw a;
			}
			if(statement instanceof ActionStatement || statement instanceof TurnStatement){
				time-=0.2;
				usedTime+=0.2;
			}
			else if(statement instanceof BlockStatement){
				time-=((BlockStatement)statement).getUsedTime();
			}
		}
		stoppedStatementIndex = 0;
	}
	
	public List<Statement> getStatements(){
		return this.statements;
	}
}
