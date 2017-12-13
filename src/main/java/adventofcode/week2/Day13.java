package adventofcode.week2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adventofcode.helpers.Scanner;

public class Day13
{
	public static int START_DELAY = 0;

	private static boolean CAUGHT = false;

	public static boolean noScannersAtTop(List<Scanner> scanners, int delay)
	{
		boolean result = !scanners.stream().anyMatch(scanner -> scanner.atTop(scanner.getDepth() + delay));
		if (!result)
		{
			// List<Scanner> onTop = scanners.stream().filter(scanner -> scanner.atTop(scanner.getDepth() + delay)).collect(Collectors.toList());
			// System.out.println("" + onTop);
		}
		return result;
	}

	public static int getFreeRunTime(List<Scanner> scanners)
	{
		int delay = START_DELAY;
		int maxDepth = maxDepth(scanners);
		int severity = moveThroughFirewall(scanners, delay);

		while (severity > 0)
		{
			// System.out.print(".");
			delay++;
			severity = moveThroughFirewall(scanners, delay);
			if (delay % 1000 == 0)
			{
				System.out.print(delay + " ");
			}
			if (delay % 20000 == 0)
			{
				System.out.println("");
			}

			if (severity == 0 && CAUGHT)
			{
				CAUGHT = false;
				severity += 1;
				System.out.println("");
				System.out.println("Delay = " + delay + ": No severity, but was caught!");
			}
		}
		System.out.println("");
		System.out.println("Delay = " + delay + " MaxDepth = " + maxDepth);
		return delay + maxDepth;
	}

	public static int moveThroughFirewall(List<Scanner> scanners, int delay)
	{
		int severity = 0;
		int myPosition = 0;
		int maxDepth = maxDepth(scanners);
		while (myPosition <= maxDepth)
		{
			if (scannerAtDepth(scanners, myPosition))
			{
				Scanner scanner = getScannerAtDepth(scanners, myPosition);
				if (scanner.atTop(myPosition + delay))
				{
					CAUGHT = true;
					severity += scanner.getSeverity();
				}
			}
			myPosition++;
		}

		return severity;
	}

	public static boolean scannerAtDepth(List<Scanner> scanners, int depth)
	{
		return scanners.stream().anyMatch(scanner -> scanner.getDepth() == depth);
	}

	public static Scanner getScannerAtDepth(List<Scanner> scanners, int depth)
	{
		return scanners.stream().filter(scanner -> scanner.getDepth() == depth).findFirst().get();
	}

	public static int maxDepth(List<Scanner> scanners)
	{
		return scanners.stream().map(scanner -> scanner.getDepth()).max(Comparator.comparing(i -> i)).get();
	}

	public static List<Scanner> getScannersExample()
	{
		List<Scanner> scanners = new ArrayList<>();
		scanners.add(new Scanner(0, 3));
		scanners.add(new Scanner(1, 2));
		scanners.add(new Scanner(4, 4));
		scanners.add(new Scanner(6, 4));
		return scanners;
	}

	public static List<Scanner> getScannersAssignment()
	{
		List<Scanner> scanners = new ArrayList<>();
		scanners.add(new Scanner(0, 3));
		scanners.add(new Scanner(1, 2));
		scanners.add(new Scanner(2, 6));
		scanners.add(new Scanner(4, 4));
		scanners.add(new Scanner(6, 4));
		scanners.add(new Scanner(8, 8));
		scanners.add(new Scanner(10, 6));
		scanners.add(new Scanner(12, 8));
		scanners.add(new Scanner(14, 5));
		scanners.add(new Scanner(16, 6));
		scanners.add(new Scanner(18, 8));
		scanners.add(new Scanner(20, 8));
		scanners.add(new Scanner(22, 12));
		scanners.add(new Scanner(24, 6));
		scanners.add(new Scanner(26, 9));
		scanners.add(new Scanner(28, 8));
		scanners.add(new Scanner(30, 12));
		scanners.add(new Scanner(32, 12));
		scanners.add(new Scanner(34, 17));
		scanners.add(new Scanner(36, 12));
		scanners.add(new Scanner(38, 8));
		scanners.add(new Scanner(40, 12));
		scanners.add(new Scanner(42, 12));
		scanners.add(new Scanner(44, 10));
		scanners.add(new Scanner(46, 12));
		scanners.add(new Scanner(48, 12));
		scanners.add(new Scanner(50, 12));
		scanners.add(new Scanner(52, 14));
		scanners.add(new Scanner(54, 14));
		scanners.add(new Scanner(56, 10));
		scanners.add(new Scanner(58, 14));
		scanners.add(new Scanner(60, 12));
		scanners.add(new Scanner(62, 14));
		scanners.add(new Scanner(64, 14));
		scanners.add(new Scanner(66, 14));
		scanners.add(new Scanner(68, 14));
		scanners.add(new Scanner(70, 14));
		scanners.add(new Scanner(72, 14));
		scanners.add(new Scanner(74, 14));
		scanners.add(new Scanner(76, 14));
		scanners.add(new Scanner(86, 14));
		scanners.add(new Scanner(94, 20));
		scanners.add(new Scanner(96, 18));
		return scanners;
	}
}
