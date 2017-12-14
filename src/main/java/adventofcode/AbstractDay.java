package adventofcode;

public abstract class AbstractDay<T>
{
	public abstract void run(T input);

	public abstract void bonus(T input);

	public abstract T getExampleInput();

	public abstract T getAssignmentInput();
}
