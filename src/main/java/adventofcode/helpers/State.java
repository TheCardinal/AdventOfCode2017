package adventofcode.helpers;

public class State
{
	public String name;

	public StateAction actionOn0;

	public StateAction actionOn1;

	public State(String name, StateAction actionOn0, StateAction actionOn1)
	{
		this.name = name;
		this.actionOn0 = actionOn0;
		this.actionOn1 = actionOn1;
	}
}
