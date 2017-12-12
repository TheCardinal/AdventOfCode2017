package adventofcode.helpers;

public class Instruction
{
	private String targetRegister;

	private boolean increase;

	private int value;

	private String condition;

	public Instruction(String targetRegister, String action, int value, String condition)
	{
		this.targetRegister = targetRegister;
		this.increase = (action == "inc");
		this.value = value;
		this.condition = condition;
	}

	public String getTargetRegister()
	{
		return targetRegister;
	}

	public boolean isIncrease()
	{
		return increase;
	}

	public int getValue()
	{
		return value;
	}

	public String getCondition()
	{
		return condition;
	}
}
