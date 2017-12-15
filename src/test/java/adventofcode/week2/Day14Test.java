package adventofcode.week2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import adventofcode.DayTester;

public class Day14Test extends DayTester<Day14, String>
{
	public Day14Test()
	{
		super(Day14.class);
	}

	@Test
	public void hexToBinTest()
	{
		Day14 day = getDay();
		assertEquals("0000", day.hexToBin("0"));
		assertEquals("0001", day.hexToBin("1"));
		assertEquals("1110", day.hexToBin("e"));
		assertEquals("1111", day.hexToBin("f"));
	}

	@Test
	public void countRegionsTest1()
	{
		Day14 day = getDay();
		int[][] array = new int[][] { { 1, 0, 1 }, { 1, 0, 1 }, { 1, 0, 1 } };
		assertEquals(2, day.countRegions(array));
	}

	@Test
	public void countRegionsTest2()
	{
		Day14 day = getDay();
		int[][] array = new int[][] { { 1, 0, 1 }, { 1, 1, 1 }, { 1, 0, 1 } };
		assertEquals(1, day.countRegions(array));
	}

}
