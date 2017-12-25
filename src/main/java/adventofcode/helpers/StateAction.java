package adventofcode.helpers;

public class StateAction
{
	public int valueToWrite;

	public boolean goLeft;

	public String continueState;

	public StateAction(int valueToWrite, boolean goLeft, String continueState)
	{
		this.valueToWrite = valueToWrite;
		this.goLeft = goLeft;
		this.continueState = continueState;
	}
}
