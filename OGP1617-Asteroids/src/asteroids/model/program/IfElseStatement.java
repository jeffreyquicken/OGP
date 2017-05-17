package asteroids.model.program;

public class IfElseStatement extends Statement {
	public IfElseStatement(Expression<?> condition, Statement ifStatement, Statement elseStatement){
		this.condition = condition;
		this.ifStatement = ifStatement;
		this.elseStatement = elseStatement;
	}
	
	private Expression<?> condition;
	private Statement ifStatement;
	private Statement elseStatement;
	
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
	
	public void evaluate(double time) throws NotEnoughTimeException,ReturnedException{
		if(time<0.2)
			throw new NotEnoughTimeException(time);
		if(condition.getValue() instanceof Boolean){
			if(((Boolean)condition.getValue()) == true){
				try{ifStatement.evaluate(time);}
				catch(NotEnoughTimeException n){
					throw n;
				}
				catch(BreakException b){
					throw new IllegalArgumentException(b);
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
						throw new IllegalArgumentException(b);
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
