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
		return new WhileStatement((Expression<Boolean>)condition,body);
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
		return new IfElseStatement(condition,ifBody,elseBody);
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
		return new SingleMathematicalExpression(SingleMathematical.NEGATE,expression);
	}

	@Override
	public Expression<?> createNotExpression(Expression<?> expression, SourceLocation sourceLocation) {
		return new NotExpression(expression);
	}

	@Override
	public Expression<?> createDoubleLiteralExpression(double value, SourceLocation location) {
		return new LiteralExpression<Double>(value);
	}

	@Override
	public Expression<?> createNullExpression(SourceLocation location) {
		return new NullExpression();
	}

	@Override
	public Expression<?> createSelfExpression(SourceLocation location) {
		return new SelfExpression();
	}

	@Override
	public Expression<?> createShipExpression(SourceLocation location) {
		return new ShipExpression();
	}

	@Override
	public Expression<?> createAsteroidExpression(SourceLocation location) {
		return new AsteroidExpression();
	}

	@Override
	public Expression<?> createPlanetoidExpression(SourceLocation location) {
		return new PlanetoidExpression();
	}

	@Override
	public Expression<?> createBulletExpression(SourceLocation location) {
		return new BulletExpression();
	}

	@Override
	public Expression<?> createPlanetExpression(SourceLocation location) {
		return new PlanetExpression();
	}

	@Override
	public Expression<?> createAnyExpression(SourceLocation location) {
		return new AnyExpression();
	}

	@Override
	public Expression<?> createGetXExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETX,e);
	}

	@Override
	public Expression<?> createGetYExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETY,e);
	}

	@Override
	public Expression<?> createGetVXExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETVX,e);
	}

	@Override
	public Expression<?> createGetVYExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETVY,e);
	}

	@Override
	public Expression<?> createGetRadiusExpression(Expression<?> e, SourceLocation location) {
		return new GetExpression(Getter.GETRADIUS,e);
	}

	@Override
	public Expression<?> createLessThanExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		return new SmallerExpression(e1,e2);
	}

	@Override
	public Expression<?> createEqualityExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		return new EqualExpression(e1,e2);
	}

	@Override
	public Expression<?> createAdditionExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		return new MathematicalExpression(Mathematical.ADDITION,e1,e2);
	}

	@Override
	public Expression<?> createMultiplicationExpression(Expression<?> e1, Expression<?> e2, SourceLocation location) {
		return new MathematicalExpression(Mathematical.MULTIPLICATION,e1,e2);
	}

	@Override
	public Expression<?> createSqrtExpression(Expression<?> e, SourceLocation location) {
		return new SingleMathematicalExpression(SingleMathematical.SQRT,e);
	}

	@Override
	public Expression<?> createGetDirectionExpression(SourceLocation location) {
		return new DirectionExpression();
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
		return new TurnStatement(angle);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		return new ActionStatement(Action.SKIP);
	}
}
