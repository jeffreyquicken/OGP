package asteroids.model.program;

import java.util.List;

import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression<?>,Statement,Function,Program> {

	@Override
	public Program createProgram(List<Function> functions, Statement main) {
		return new Program(functions,main);
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation sourceLocation) {
		return new Function(functionName,body);
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression<?> value,
			SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName,value);
	}
	
	@Override
	public Statement createWhileStatement(Expression<?> condition, Statement body, SourceLocation sourceLocation) {
		if(condition.getValue() instanceof Boolean)
			return new WhileStatement((Expression<Boolean>)condition,body);
		else
			throw new AssertionError();
	}

	@Override
	public Statement createBreakStatement(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public Statement createReturnStatement(Expression<?> value, SourceLocation sourceLocation) {
		return new ReturnStatement(value);
	}

	@Override
	public Statement createIfStatement(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		if(condition.getValue() instanceof Boolean)
			return new IfElseStatement((Expression<Boolean>)condition,ifBody,elseBody);
		else
			throw new AssertionError();
	}

	@Override
	public Statement createPrintStatement(Expression<?> value, SourceLocation sourceLocation) {
		return new PrintStatement(value);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation sourceLocation) {
		return new BlockStatement(statements);
	}

	@Override
	public Expression<?> createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		return new VariableReadExpression(variableName);
	}

	@Override
	public Expression<?> createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		return new ParameterReadExpression(parameterName);
	}

	@Override
	public Expression<?> createFunctionCallExpression(String functionName, List<Expression<?>> actualArgs,
			SourceLocation sourceLocation) {
		return new FunctionExpression(functionName,actualArgs);
	}

	@Override
	public Expression<?> createChangeSignExpression(Expression<?> expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createNotExpression(Expression<?> expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createDoubleLiteralExpression(double value, SourceLocation location) {
		return new LiteralExpression<Double>(value);
	}

	@Override
	public Expression<?> createNullExpression(SourceLocation location) {
		return new LiteralExpression<Object>(null);
	}

	@Override
	public Expression<?> createSelfExpression(SourceLocation location) {
		// DOE IETS
		return null;
	}

	@Override
	public Expression<?> createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<?> createGetXExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETX);
	}

	@Override
	public Expression<?> createGetYExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETY);
	}

	@Override
	public Expression<?> createGetVXExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETVX);
	}

	@Override
	public Expression<?> createGetVYExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETVY);
	}

	@Override
	public Expression<?> createGetRadiusExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETRADIUS);
	}

	@Override
	public Expression<?> createLessThanExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		if(e1.getValue() instanceof Double && e2.getValue() instanceof Double)
			return new LogicalExpression(Logical.SMALLER,(Expression<Double>)e1,(Expression<Double>)e2);
		else
			throw new AssertionError();
	}

	@Override
	public Expression<?> createEqualityExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		if(e1.getValue() instanceof Double && e2.getValue() instanceof Double)
			return new LogicalExpression(Logical.EQUAL,(Expression<Double>)e1,(Expression<Double>)e2);
		else
			throw new AssertionError();
	}

	@Override
	public Expression<?> createAdditionExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		if(e1.getValue() instanceof Double && e2.getValue() instanceof Double)
			return new MathematicalExpression(Mathematical.ADDITION,(Expression<Double>)e1,(Expression<Double>)e2);
		else
			throw new AssertionError();
	}

	@Override
	public Expression<?> createMultiplicationExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		if(e1.getValue() instanceof Double && e2.getValue() instanceof Double)
			return new MathematicalExpression(Mathematical.MULTIPLICATION,(Expression<Double>)e1,(Expression<Double>)e2);
		else
			throw new AssertionError();
	}

	@Override
	public Expression<?> createSqrtExpression(Expression<?> e, SourceLocation location) {
		if(e.getValue() instanceof Double)
			return new MathematicalExpression(Mathematical.SQUAREROOT,(Expression<Double>)e,null);
		else
			throw new AssertionError();
	}

	@Override
	public Expression<?> createGetDirectionExpression(SourceLocation location) {
		return new GetExpression(Getter.GETDIRECTION);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new ActionStatement(Action.THRUST_ON);
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		return new ActionStatement(Action.THRUST_OFF);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new ActionStatement(Action.FIRE);
	}

	@Override
	public Statement createTurnStatement(Expression<?> angle, SourceLocation location) {
		if(angle.getValue() instanceof Double)
			return new TurnStatement((Expression<Double>)angle);
		else
			throw new AssertionError();
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}
}
