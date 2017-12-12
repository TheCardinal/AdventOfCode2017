package adventofcode.week2;

import org.junit.Test;

public class Day8Test
{
	@Test
	public void TestExample()
	{
		int maxInRegisters = Day8.RunExample();
		System.out.println(maxInRegisters);
	}

	@Test
	public void TestAssignment()
	{
		int maxInRegisters = Day8.RunAssignment();
		System.out.println(maxInRegisters);
	}
}
