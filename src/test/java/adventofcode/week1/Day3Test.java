package adventofcode.week1;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import adventofcode.week1.Day3;

public class Day3Test
{
	@Test
	public void Example1()
	{
		assertTrue(Day3.calculate(1) == 0);
	}

	@Test
	public void Example2()
	{
		assertTrue(Day3.calculate(12) == 3);
	}

	@Test
	public void Example3()
	{
		assertTrue(Day3.calculate(23) == 2);
	}

	@Test
	public void Example4()
	{
		assertTrue(Day3.calculate(1024) == 31);
	}

	@Test
	public void Assignment()
	{
		System.out.println(Day3.calculate(325489));
	}

	@Test
	public void Bonus()
	{
		System.out.println("BONUS: " + Day3.CalculateBonus(325489));
	}
}
