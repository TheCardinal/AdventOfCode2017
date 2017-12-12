package adventofcode.week1;

public class Day1
{
	public static int calculate(String input)
	{
		int sum = 0;
		char[] pieces = input.toCharArray();
		for (int x = 0; x < pieces.length; x++)
		{
			int compareIndex = x + 1;
			if (compareIndex == pieces.length)
			{
				compareIndex = 0;
			}

			if (pieces[x] == pieces[compareIndex])
			{
				sum += Integer.parseInt("" + pieces[x]);
			}
		}
		return sum;
	}

	public static int calculateBonus(String input)
	{
		int sum = 0;
		char[] pieces = input.toCharArray();
		for (int x = 0; x < pieces.length; x++)
		{
			int compareIndex = x + (pieces.length / 2);
			if (compareIndex >= pieces.length)
			{
				compareIndex -= pieces.length;
			}

			if (pieces[x] == pieces[compareIndex])
			{
				sum += Integer.parseInt("" + pieces[x]);
			}
		}
		return sum;
	}
}
