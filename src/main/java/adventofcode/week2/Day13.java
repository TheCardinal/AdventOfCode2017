package adventofcode.week2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import adventofcode.AbstractDay;
import adventofcode.helpers.Scanner;

public class Day13 extends AbstractDay<List<Scanner>>
{
	@Override
	public void run(List<Scanner> scanners)
	{
		int severity = 0;
		int myPosition = 0;
		int maxDepth = maxDepth(scanners);
		while (myPosition <= maxDepth)
		{
			if (scannerAtDepth(scanners, myPosition))
			{
				Scanner scanner = getScannerAtDepth(scanners, myPosition);
				if (scanner.atTop(myPosition))
				{
					severity += scanner.getSeverity();
				}
			}
			myPosition++;
		}

		System.out.println("Solution: " + severity);
	}

	public int maxDepth(List<Scanner> scanners)
	{
		return scanners.stream().map(scanner -> scanner.getDepth()).max(Comparator.comparing(i -> i)).get();
	}

	public boolean scannerAtDepth(List<Scanner> scanners, int depth)
	{
		return scanners.stream().anyMatch(scanner -> scanner.getDepth() == depth);
	}

	public Scanner getScannerAtDepth(List<Scanner> scanners, int depth)
	{
		return scanners.stream().filter(scanner -> scanner.getDepth() == depth).findFirst().get();
	}

	@Override
	public void bonus(List<Scanner> input)
	{
		int x = 0;
		for (;; x++)
		{
			if (noScannersAtTop(input, x))
			{
				break;
			}
		}
		System.out.println("Solution: " + x);
	}

	public boolean noScannersAtTop(List<Scanner> scanners, int delay)
	{
		return !scanners.stream().anyMatch(scanner -> scanner.atTop(scanner.getDepth() + delay));
	}

	@Override
	public List<Scanner> getExampleInput()
	{
		List<Scanner> scanners = new ArrayList<>();
		scanners.add(new Scanner(0, 3));
		scanners.add(new Scanner(1, 2));
		scanners.add(new Scanner(4, 4));
		scanners.add(new Scanner(6, 4));
		return scanners;
	}

	@Override
	public List<Scanner> getAssignmentInput()
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
