package adventofcode.helpers;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class PointHelper
{
	public static Point getLocationDown(Point currentLocation)
	{
		return new Point((int) currentLocation.getX(), (int) currentLocation.getY() - 1);
	}

	public static Point getLocationBottomRight(Point currentLocation)
	{
		return getLocationRight(getLocationDown(currentLocation));
	}

	public static Point getLocationRight(Point currentLocation)
	{
		return new Point((int) currentLocation.getX() + 1, (int) currentLocation.getY());
	}

	public static Point getLocationTopRight(Point currentLocation)
	{
		return getLocationRight(getLocationUp(currentLocation));
	}

	public static Point getLocationUp(Point currentLocation)
	{
		return new Point((int) currentLocation.getX(), (int) currentLocation.getY() + 1);
	}

	public static Point getLocationTopLeft(Point currentLocation)
	{
		return getLocationLeft(getLocationUp(currentLocation));
	}

	public static Point getLocationLeft(Point currentLocation)
	{
		return new Point((int) currentLocation.getX() - 1, (int) currentLocation.getY());
	}

	public static Point getLocationBottomLeft(Point currentLocation)
	{
		return getLocationLeft(getLocationDown(currentLocation));
	}

	public static Point2D.Double getNorth(Point2D.Double currentLocation)
	{
		return new Point2D.Double(currentLocation.getX(), currentLocation.getY() + 1);
	}

	public static Point2D.Double getSouth(Point2D.Double currentLocation)
	{
		return new Point2D.Double(currentLocation.getX(), currentLocation.getY() - 1);
	}

	public static Point2D.Double getNorthEast(Point2D.Double currentLocation)
	{
		return new Point2D.Double(currentLocation.getX() + 0.5, currentLocation.getY() + 0.5);
	}

	public static Point2D.Double getSouthEast(Point2D.Double currentLocation)
	{
		return new Point2D.Double(currentLocation.getX() + 0.5, currentLocation.getY() - 0.5);
	}

	public static Point2D.Double getNorthWest(Point2D.Double currentLocation)
	{
		return new Point2D.Double(currentLocation.getX() - 0.5, currentLocation.getY() + 0.5);
	}

	public static Point2D.Double getSouthWest(Point2D.Double currentLocation)
	{
		return new Point2D.Double(currentLocation.getX() - 0.5, currentLocation.getY() - 0.5);
	}

	public static List<Point> getSurroundingPoints(Point currentLocation)
	{
		List<Point> result = new ArrayList<Point>();
		result.add(getLocationRight(currentLocation));
		result.add(getLocationTopRight(currentLocation));
		result.add(getLocationUp(currentLocation));
		result.add(getLocationTopLeft(currentLocation));
		result.add(getLocationLeft(currentLocation));
		result.add(getLocationBottomLeft(currentLocation));
		result.add(getLocationDown(currentLocation));
		result.add(getLocationBottomRight(currentLocation));
		return result;
	}
}