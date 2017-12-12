package adventofcode.week1;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import adventofcode.helpers.PointHelper;

public class Day3
{
	public static int calculate(int input)
	{
		int[] ringAndDiagonal = calculateCircle(input);
		int ringNumber = ringAndDiagonal[0];
		int diagonal = ringAndDiagonal[1];
		int firstNumberOnRing = (int) Math.pow(Math.sqrt(diagonal) - 2, 2) + 1;

		System.out.println("Input: " + input);
		int sideSize = (int) Math.sqrt(diagonal);
		System.out
			.println("\tis on ring " + ringNumber + "\t\t\tContains numbers " + firstNumberOnRing + "-" + diagonal + " and sides are: " + sideSize);
		int rechtsboven = firstNumberOnRing + (sideSize - 2);
		int linksboven = rechtsboven + (sideSize - 1);
		int linksonder = linksboven + (sideSize - 1);
		String hoeken = " RB: " + rechtsboven + " LB: " + linksboven + " LO: " + linksonder + " RO (D): " + diagonal;
		System.out.println("\t" + hoeken);

		String side;
		int numberOnSide = -1;
		if (input >= firstNumberOnRing && input <= rechtsboven)
		{
			side = "RIGHT";
			numberOnSide = rechtsboven - (sideSize / 2);
		}
		else if (input >= rechtsboven && input <= linksboven)
		{
			side = "TOP";
			numberOnSide = linksboven - (sideSize / 2);
		}
		else if (input >= linksboven && input <= linksonder)
		{
			side = "LEFT";
			numberOnSide = linksonder - (sideSize / 2);
		}
		else
		{
			side = "BOTTOM";
			numberOnSide = diagonal - (sideSize / 2);
		}

		System.out.println("\tsteps to " + side + " side = " + ringNumber + " and you'll reach: " + numberOnSide);
		int fromSideToInput = (int) (Math.max(numberOnSide, input) - Math.min(numberOnSide, input));
		System.out.println("\tfrom there it's " + fromSideToInput + " steps to " + input);
		return ringNumber + fromSideToInput;
	}

	/**
	 * Calculate the ring in which the number subsides
	 * 
	 * @param input
	 * @return
	 */
	private static int[] calculateCircle(int input)
	{
		int ringNumber = 1;
		int x;
		for (x = 1; x * x < input; x += 2)
		{
			// System.out.println("x: " + x + " Ring: " + ringNumber + " Diagonal: " + (x * x));
			ringNumber++;
		}
		ringNumber--;
		// x -= 2;
		return new int[] { ringNumber, ((x * x)) };
	}

	// #### BONUS SECTION ###

	private static int limit = 0;

	private static boolean done = false;

	private static Point currentLocation = new Point(0, 0);

	// Visited coordinates in grid with their values
	private static Map<Point, Integer> gridValues = new HashMap<Point, Integer>();

	public static int CalculateBonus(int limit)
	{
		Day3.limit = limit;
		currentLocation = new Point(0, 0);
		gridValues.put(currentLocation, 1);
		System.out.println("[" + ((int) currentLocation.getX()) + "," + ((int) currentLocation.getY()) + "] = " + getCurrentValue());
		moveRight();
		while (!done)
		{
			while (!done && !canGoLeft())
			{
				moveUp();
			}
			while (!done && !canGoDown())
			{
				moveLeft();
			}
			while (!done && !canGoRight())
			{
				moveDown();
			}
			while (!done && !canGoUp())
			{
				moveRight();
			}
		}
		System.out.println("Done");
		return getCurrentValue();
	}

	private static int getCurrentValue()
	{
		return getValueAt(currentLocation);
	}

	private static int getValueAt(Point p)
	{
		if (gridValues.keySet().contains(p))
		{
			return gridValues.get(p);
		}
		return 0;
	}

	private static void storeValue()
	{
		gridValues.put(currentLocation, PointHelper.getSurroundingPoints(currentLocation).stream().mapToInt(point -> getValueAt(point)).sum());
		if (getCurrentValue() > limit)
		{
			done = true;
		}
		System.out.println("[" + ((int) currentLocation.getX()) + "," + ((int) currentLocation.getY()) + "] = " + getCurrentValue());
	}

	private static boolean canGoRight()
	{
		return !gridValues.containsKey(PointHelper.getLocationRight(currentLocation));
	}

	private static void moveRight()
	{
		currentLocation = PointHelper.getLocationRight(currentLocation);
		storeValue();
	}

	private static boolean canGoUp()
	{
		return !gridValues.containsKey(PointHelper.getLocationUp(currentLocation));
	}

	private static void moveUp()
	{
		currentLocation = PointHelper.getLocationUp(currentLocation);
		storeValue();
	}

	private static boolean canGoLeft()
	{
		return !gridValues.containsKey(PointHelper.getLocationLeft(currentLocation));
	}

	private static void moveLeft()
	{
		currentLocation = PointHelper.getLocationLeft(currentLocation);
		storeValue();
	}

	private static boolean canGoDown()
	{
		return !gridValues.containsKey(PointHelper.getLocationDown(currentLocation));
	}

	private static void moveDown()
	{
		currentLocation = PointHelper.getLocationDown(currentLocation);
		storeValue();
	}

}
