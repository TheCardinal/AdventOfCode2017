package adventofcode;

public abstract class AbstractDay<T>
{
	private boolean example = false;

	protected boolean isExample()
	{
		return example;
	}

	protected void initialize(boolean isExample)
	{
		example = isExample;
	}

	protected void parseInput(T input)
	{
	}

	public abstract void run(T input);

	public abstract void bonus(T input);

	public abstract T getExampleInput();

	public abstract T getAssignmentInput();
}
