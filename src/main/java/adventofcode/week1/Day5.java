package adventofcode.week1;

public class Day5
{
	public static int calculate(int[] input)
	{
		int currentIndex = 0, steps = 0;

		while (currentIndex < input.length)
		{
			steps++;
			int newIndex = currentIndex + input[currentIndex];
			input[currentIndex] = input[currentIndex] + 1;
			currentIndex = newIndex;
		}
		return steps;
	}

	public static int calculateBonus(int[] input)
	{
		int currentIndex = 0, steps = 0;

		while (currentIndex < input.length)
		{
			steps++;
			int newIndex = currentIndex + input[currentIndex];
			input[currentIndex] = (input[currentIndex] + (input[currentIndex] >= 3 ? -1 : 1));
			currentIndex = newIndex;
		}
		return steps;
	}
}
