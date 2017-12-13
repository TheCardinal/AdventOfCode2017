package adventofcode.helpers;

public class Scanner
{
	private int depth;

	private int range;

	public Scanner(int depth, int range)
	{
		this.depth = depth;
		this.range = range;
	}

	public int getDepth()
	{
		return depth;
	}

	public int getSeverity()
	{
		return depth * range;
	}

	public int getIndexAtTime(int time)
	{
		int count = range - 1;
		int help = 2 * count;
		while (time >= help)
		{
			time -= help;
		}
		if (time < range)
		{
			return time;
		}
		return count - (time - count);
	}

	public boolean atTop(int time)
	{
		return getIndexAtTime(time) == 0;
	}
}
