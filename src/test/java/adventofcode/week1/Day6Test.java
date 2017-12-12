package adventofcode.week1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adventofcode.week1.Day6;

public class Day6Test
{
	@Test
	public void Example1()
	{
		assertEquals(5, Day6.reallocate(new Integer[] { 0, 2, 7, 0 }));
	}

	@Test
	public void Assignment()
	{
		// 1837 too low
		System.out.println(Day6.reallocate(new Integer[] { 0, 5, 10, 0, 11, 14, 13, 4, 11, 8, 8, 7, 1, 4, 12, 11 }));
	}

	@Test
	public void AssignmentKoen()
	{
		// gok: 47
		System.out.println(Day6.reallocate(new Integer[] { 4, 10, 4, 1, 8, 4, 9, 14, 5, 1, 14, 15, 0, 15, 3, 5 }));

	}
}
