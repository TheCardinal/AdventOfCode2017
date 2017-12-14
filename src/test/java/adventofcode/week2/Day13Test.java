package adventofcode.week2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import adventofcode.DayTester;
import adventofcode.helpers.Scanner;

public class Day13Test extends DayTester<Day13>
{
	public Day13Test()
	{
		super(Day13.class);
	}

	@Test
	public void allScannersStartAtTheTop()
	{
		List<Scanner> scanners = getDay().getExampleInput();
		for (Scanner scanner : scanners)
		{
			assertEquals(0, scanner.getIndexAtTime(0));
			assertTrue(scanner.atTop(0));
		}
	}

	@Test
	public void allScannersMove1AtATime()
	{
		List<Scanner> scanners = getDay().getExampleInput();
		for (Scanner scanner : scanners)
		{
			assertEquals(1, scanner.getIndexAtTime(1));
		}
	}

	@Test
	public void scannerMovementTest()
	{
		Scanner scanner = new Scanner(0, 6);
		int[] scannerPosition = new int[] { 0, 1, 2, 3, 4, 5, 4, 3, 2, 1 };

		for (int x = 0; x < scannerPosition.length; x++)
		{
			assertEquals(scannerPosition[x], scanner.getIndexAtTime(x));
		}
	}

	@Test
	public void scannerAtDepthTest()
	{
		Day13 day = getDay();
		List<Scanner> scanners = day.getExampleInput();
		assertTrue(day.scannerAtDepth(scanners, 0));
		assertTrue(day.scannerAtDepth(scanners, 1));
		assertFalse(day.scannerAtDepth(scanners, 2));
		assertFalse(day.scannerAtDepth(scanners, 3));
		assertTrue(day.scannerAtDepth(scanners, 4));
		assertFalse(day.scannerAtDepth(scanners, 5));
		assertTrue(day.scannerAtDepth(scanners, 6));
	}

	@Test
	public void maxDepthTest()
	{
		Day13 day = getDay();
		assertEquals(6, day.maxDepth(day.getExampleInput()));
	}
}
