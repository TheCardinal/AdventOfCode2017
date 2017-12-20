package adventofcode.helpers;

public class Coordinate
{
	private long x, y, z;

	public Coordinate(long x, long y, long z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void move(Coordinate movement)
	{
		this.x += movement.getX();
		this.y += movement.getY();
		this.z += movement.getZ();
	}

	public void move(long x, long y, long z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public long getX()
	{
		return x;
	}

	public long getY()
	{
		return y;
	}

	public long getZ()
	{
		return z;
	}

	public long getManhattanDistance()
	{
		return Math.abs(x) + Math.abs(y) + Math.abs(z);
	}

	public void printLocationAndDistance()
	{
		System.out.println(this + " " + getManhattanDistance());
	}

	public boolean isEqualTo(Coordinate c)
	{
		return x == c.getX() && y == c.getY() && z == c.getZ();
	}

	@Override
	public String toString()
	{
		return "<" + getX() + "," + getY() + "," + getZ() + ">";
	}
}
