package asteroids.model.program;

public class WhileStatement extends Statement {
	
	public WhileStatement(Expression<?> checkExpression,Statement body){
		this.checkExpression = checkExpression;
		this.body = body;
	}
	
	@Override
	public void setFunction(Function newFunction){
		super.setFunction(newFunction);
		checkExpression.setFunction(newFunction);
		body.setFunction(newFunction);
	}
	
	@Override
	public void setProgram(Program newProgram){
		super.setProgram(newProgram);
		checkExpression.setProgram(newProgram);
		body.setProgram(newProgram);
	}
	
	private Expression<?> checkExpression;
	private Statement body;
	
	public void evaluate(double time) throws NotEnoughTimeException,ReturnedException {
		checkExpression.setFunction(this.getFunction());
		body.setFunction(this.getFunction());
		checkExpression.setProgram(this.getProgram());
		body.setProgram(this.getProgram());
		if(checkExpression.getValue() instanceof Boolean){
			if(time<0.2)
				throw new NotEnoughTimeException(time);
			boolean check = (Boolean)checkExpression.getValue();
			while(check == true){
				try{body.evaluate(time);}
				catch(BreakException b){
					break;
				}
				catch(NotEnoughTimeException n){
					throw n;
				}
				catch(ReturnedException r){
					throw r;
				}
				check = (Boolean)checkExpression.getValue();
			}
		}
		else
			throw new IllegalArgumentException();
	}

}
