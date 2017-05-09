package asteroids.model.program;

public interface ProgramInteraction {
	
	public void editVariable(String name, Expression<?> value, Program program);

}
