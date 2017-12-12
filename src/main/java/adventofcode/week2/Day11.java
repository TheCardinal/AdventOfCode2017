package adventofcode.week2;

import java.awt.geom.Point2D;

import adventofcode.helpers.PointHelper;

public class Day11
{
	private static final String NORTH = "n";

	private static final String NORTHEAST = "ne";

	private static final String SOUTHEAST = "se";

	private static final String SOUTH = "s";

	private static final String SOUTHWEST = "sw";

	private static final String NORTHWEST = "nw";

	public static int calculateSteps(Point2D.Double target)
	{
		System.out.println("Calculating to: " + target);

		int steps = 0;
		Point2D.Double currentLocation = new Point2D.Double(0, 0);
		while (currentLocation.getX() != target.getX() || currentLocation.getY() != target.getY())
		{
			String direction = "";

			if (target.getX() == currentLocation.getX() && target.getY() < currentLocation.getY())
			{
				// south: x gelijk, y kleiner
				currentLocation = PointHelper.getSouth(currentLocation);
				steps++;
				direction = "S";
			}
			else if (target.getX() == currentLocation.getX() && target.getY() > currentLocation.getY())
			{
				// north = x gelijk, y groter
				currentLocation = PointHelper.getNorth(currentLocation);
				steps++;
				direction = "N";
			}
			else if (target.getX() > currentLocation.getX() && target.getY() <= currentLocation.getY())
			{
				// southEast: x groter, y kleiner
				currentLocation = PointHelper.getSouthEast(currentLocation);
				steps++;
				direction = "SE";
			}
			else if (target.getX() < currentLocation.getX() && target.getY() <= currentLocation.getY())
			{
				// southWest: x kleiner, y kleiner
				currentLocation = PointHelper.getSouthWest(currentLocation);
				steps++;
				direction = "SW";
			}
			else if (target.getX() > currentLocation.getX() && target.getY() >= currentLocation.getY())
			{
				// northEast: x groter, y groter
				currentLocation = PointHelper.getNorthEast(currentLocation);
				steps++;
				direction = "NE";
			}
			else if (target.getX() < currentLocation.getX() && target.getY() > currentLocation.getY())
			{
				// northWest = x kleiner, y groter
				currentLocation = PointHelper.getNorthWest(currentLocation);
				steps++;
				direction = "NW";
			}
			System.out.print(direction + "-");
		}

		System.out.println("");
		return steps;
	}

	public static int maxStepsAway;

	public static Point2D.Double getLocation(String input)
	{
		Point2D.Double location = new Point2D.Double(0, 0);
		String[] steps = input.split(",");
		int count = 1;
		for (String step : steps)
		{
			System.out.println("Step " + count + "/" + steps.length);
			switch (step)
			{
			case NORTH:
				location = PointHelper.getNorth(location);
				break;
			case NORTHEAST:
				location = PointHelper.getNorthEast(location);
				break;
			case SOUTHEAST:
				location = PointHelper.getSouthEast(location);
				break;
			case SOUTH:
				location = PointHelper.getSouth(location);
				break;
			case SOUTHWEST:
				location = PointHelper.getSouthWest(location);
				break;
			case NORTHWEST:
				location = PointHelper.getNorthWest(location);
				break;
			default:
				break;
			}
			int stepsAway = calculateSteps(location);
			if (stepsAway > maxStepsAway)
			{
				maxStepsAway = stepsAway;
			}
			count++;
		}

		return location;
	}

	public static boolean isWholeNumber(double d)
	{
		try
		{
			return d == (int) d;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
