package adventofcode.week2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Day10Test
{
	@Test
	public void example()
	{
		assertEquals(12, Day10.assignment(4, new int[] { 3, 4, 1, 5 }));
	}

	@Test
	public void assignment()
	{
		System.out.println(Day10.assignment(255, ASSIGNMENT));
	}

	@Test
	public void getASCIICodeTest()
	{
		assertArrayEquals(new Integer[] { 49, 44, 50, 44, 51 }, Day10.getASCIICodes("1,2,3"));
	}

	@Test
	public void getLengthsTest()
	{
		assertArrayEquals(new Integer[] { 49, 44, 50, 44, 51, 17, 31, 73, 47, 23 }, Day10.getLengths("1,2,3"));
	}

	@Test
	public void denseHashTest()
	{
		assertEquals(new Integer(64), Day10.denseHash(new Integer[] { 65, 27, 9, 1, 4, 3, 40, 50, 91, 7, 6, 0, 2, 5, 68, 22 }, 1));
	}

	@Test
	public void toHexTest()
	{
		assertEquals("40", Day10.toHex(64));
		assertEquals("07", Day10.toHex(7));
		assertEquals("ff", Day10.toHex(255));
	}

	@Test
	public void bonusTest()
	{
		assertEquals("a2582a3a0e66e6e86e3812dcb672a272", Day10.bonus(""));
		assertEquals("33efeb34ea91902bb2f59c9920caa6cd", Day10.bonus("AoC 2017"));
		assertEquals("3efbe78a8d82f29979031a4aa0b16a9d", Day10.bonus("1,2,3"));
		assertEquals("63960835bcdc130f0b66d7ff4f6a5a8e", Day10.bonus("1,2,4"));
	}

	@Test
	public void bonus()
	{
		System.out.println(Day10.bonus("106,118,236,1,130,0,235,254,59,205,2,87,129,25,255,118"));
	}

	private static int[] ASSIGNMENT = new int[] { 106, 118, 236, 1, 130, 0, 235, 254, 59, 205, 2, 87, 129, 25, 255, 118 };
}
