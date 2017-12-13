package adventofcode.week2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import adventofcode.helpers.Scanner;

public class Day13Test
{
	@Test
	public void exampleTest()
	{
		int severity = Day13.moveThroughFirewall(Day13.getScannersExample(), 0);
		assertEquals(24, severity);
	}

	@Test
	public void assignment()
	{
		int severity = Day13.moveThroughFirewall(Day13.getScannersAssignment(), 0);
		System.out.println(severity);
	}

	@Test
	public void exampleBonus2()
	{
		assertEquals(10, Day13.getFreeRunDelay(Day13.getScannersExample()));
	}

	@Test
	public void assignmentBonus()
	{
		System.out.println(Day13.getFreeRunDelay(Day13.getScannersAssignment()));
		// Answer: 3870382
	}

	@Test
	public void allScannersStartAtTheTop()
	{
		List<Scanner> scanners = Day13.getScannersExample();
		for (Scanner scanner : scanners)
		{
			assertEquals(0, scanner.getIndexAtTime(0));
			assertTrue(scanner.atTop(0));
		}
	}

	@Test
	public void allScannersMove1AtATime()
	{
		List<Scanner> scanners = Day13.getScannersExample();
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
		List<Scanner> scanners = Day13.getScannersExample();
		assertTrue(Day13.scannerAtDepth(scanners, 0));
		assertTrue(Day13.scannerAtDepth(scanners, 1));
		assertFalse(Day13.scannerAtDepth(scanners, 2));
		assertFalse(Day13.scannerAtDepth(scanners, 3));
		assertTrue(Day13.scannerAtDepth(scanners, 4));
		assertFalse(Day13.scannerAtDepth(scanners, 5));
		assertTrue(Day13.scannerAtDepth(scanners, 6));
	}

	@Test
	public void maxDepthTest()
	{
		assertEquals(6, Day13.maxDepth(Day13.getScannersExample()));
	}
}
