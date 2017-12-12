package adventofcode.helpers;

import java.util.List;

public class Program
{
	private int name;

	private int[] connectedTo;

	public Program(int name, int[] connectedTo)
	{
		this.name = name;
		this.connectedTo = connectedTo;
	}

	public int getName()
	{
		return name;
	}

	public boolean canCommunicateWith(int program)
	{
		return name == program || connectedToContains(program);
	}

	public boolean canCommunicateWith(List<Integer> programs)
	{
		return programs.stream().anyMatch(pName -> canCommunicateWith(pName));
	}

	private boolean connectedToContains(int item)
	{
		for (int n : connectedTo)
		{
			if (item == n)
			{
				return true;
			}
		}
		return false;
	}
}
