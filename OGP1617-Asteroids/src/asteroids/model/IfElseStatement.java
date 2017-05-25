package asteroids.model;

public class IfElseStatement extends Statement {
	public IfElseStatement(Expression<?> condition, Statement ifStatement, Statement elseStatement){
		this.condition = condition;
		this.ifStatement = ifStatement;
		this.elseStatement = elseStatement;
	}
	
	private final Expression<?> condition;
	private final Statement ifStatement;
	private final Statement elseStatement;
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		condition.setFunction(newFunction);
		ifStatement.setFunction(newFunction);
		if(elseStatement != null)
			elseStatement.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		condition.setProgram(newProgram);
		ifStatement.setProgram(newProgram);
		if(elseStatement != null)
			elseStatement.setProgram(newProgram);
	}
	
	public void evaluate(double time) throws NotEnoughTimeException,ReturnedException,BreakException{
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		Object cond = condition.getValue();
		if(cond instanceof Boolean){
			if(((Boolean)cond) == true){
				try{ifStatement.evaluate(time);}
				catch(NotEnoughTimeException n){
					throw n;
				}
				catch(BreakException b){
					throw b;
				}
				catch(ReturnedException r){
					throw r;
				}
			}
			else{
				if(elseStatement != null){
					try{elseStatement.evaluate(time);}
					catch(NotEnoughTimeException n){
						throw n;
					}
					catch(BreakException b){
						throw b;
					}
					catch(ReturnedException r){
						throw r;
					}
				}
			}
		}
		else
			throw new IllegalArgumentException();
	}
}
