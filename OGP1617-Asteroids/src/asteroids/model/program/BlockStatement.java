package asteroids.model.program;
import java.util.*;

public class BlockStatement extends Statement{
	public BlockStatement(List<Statement> statements){
		this.statements = statements;
		this.stoppedStatementIndex = 0;
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
	
	private double remainingTime = 0;
	
	public double getRemainingTime(){
		return this.remainingTime;
	}
	
	private int stoppedStatementIndex;
	
	private List<Statement> statements;
	
	public boolean hasFunctionCall(Function f){
		return true;
	}
	
	public void evaluate(double time) throws NotEnoughTimeException,ReturnedException,BreakException{
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		this.remainingTime = time;
		for(int i=stoppedStatementIndex;i<statements.size();i++){
			Statement statement = statements.get(i);
			try{statement.evaluate(remainingTime);}
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
				remainingTime-=0.2;
			}
			else if(statement instanceof BlockStatement){
				remainingTime=((BlockStatement)statement).getRemainingTime();
			}
		}
		stoppedStatementIndex = 0;
	}
	
	public List<Statement> getStatements(){
		return this.statements;
	}
}
