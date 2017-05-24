package asteroids.model;

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
	
	@Override
	public void setIndex(int i){
		super.setIndex(i);
		body.setIndex(i);
		checkExpression.setIndex(i);
	}
	
	private Expression<?> checkExpression;
	private Statement body;
	
	public void evaluate(double time) throws NotEnoughTimeException,ReturnedException, BreakException {
		checkExpression.setFunction(this.getFunction());
		body.setFunction(this.getFunction());
		checkExpression.setProgram(this.getProgram());
		body.setProgram(this.getProgram());
		Object checkObject = checkExpression.getValue();
		if(checkObject instanceof Boolean){
			if(time<0.2)
				throw new NotEnoughTimeException(time);
			boolean check = (Boolean)checkObject;
			while(check == true){
				if(time<0.2)
					throw new NotEnoughTimeException(time);
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
				catch(UnsupportedOperationException u){
					break;
				}
				if(body instanceof ActionStatement || body instanceof TurnStatement)
					time-=0.2;
				else if(body instanceof BlockStatement)
					time= ((BlockStatement)body).getRemainingTime();
				check = (Boolean)checkExpression.getValue();
			}
		}
		else
			throw new IllegalArgumentException();
	}

}
