package adventofcode.week1;

public class Day2
{
	public static int calculate(int[][] input)
	{
		int result = 0;
		for (int[] row : input)
		{
			result += getMinMaxDifference(row);
		}
		return result;
	}

	private static int getMinMaxDifference(int[] numbers)
	{
		int currMax = Integer.MIN_VALUE;
		int currMin = Integer.MAX_VALUE;
		for (int currNumber : numbers)
		{
			if (currNumber > currMax)
			{
				currMax = currNumber;
			}

			if (currNumber < currMin)
			{
				currMin = currNumber;
			}
		}
		return currMax - currMin;
	}

	public static int calculateBonus(int[][] input)
	{
		int result = 0;
		for (int[] row : input)
		{
			result += getSumOfEvenDivisible(row);
		}
		return result;
	}

	private static int getSumOfEvenDivisible(int[] numbers)
	{
		int sumForRow = 0;

		for (int x = 0; x < numbers.length; x++)
		{
			for (int y = 0; y < numbers.length; y++)
			{
				if (y == x)
				{
					continue;
				}
				if (numbers[x] % numbers[y] == 0)
				{
					System.out.println(numbers[x] + " and " + numbers[y] + " add: " + (numbers[x] / numbers[y]));
					sumForRow += numbers[x] / numbers[y];
				}
			}
		}
		return sumForRow;
	}
}
