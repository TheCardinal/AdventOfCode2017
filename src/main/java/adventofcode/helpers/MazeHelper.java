package adventofcode.helpers;

import java.awt.Point;

public class MazeHelper
{
	public static Point getLocationDown(Point currentLocation)
	{
		return new Point((int) currentLocation.getX() + 1, (int) currentLocation.getY());
	}

	public static Point getLocationRight(Point currentLocation)
	{
		return new Point((int) currentLocation.getX(), (int) currentLocation.getY() + 1);
	}

	public static Point getLocationUp(Point currentLocation)
	{
		return new Point((int) currentLocation.getX() - 1, (int) currentLocation.getY());
	}

	public static Point getLocationLeft(Point currentLocation)
	{
		return new Point((int) currentLocation.getX(), (int) currentLocation.getY() - 1);
	}
}