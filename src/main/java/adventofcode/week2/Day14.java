package adventofcode.week2;

import java.math.BigInteger;

import org.apache.commons.lang.StringUtils;

import adventofcode.AbstractDay;

public class Day14 extends AbstractDay<String>
{

	@Override
	public void run(String input)
	{
		String asBinary = inputToBinary(input);
		System.out.println(asBinary);
		System.out.println(countUsed(asBinary));
	}

	@Override
	public void bonus(String input)
	{
		String asBinary = inputToBinary(input);
		int[][] array = toArray(asBinary);
		int regions = countRegions(array);
		System.out.println(regions);
	}

	public int countRegions(int[][] array)
	{
		int counter = 0;
		for (int x = 0; x < array.length; x++)
		{
			for (int y = 0; y < array[x].length; y++)
			{
				if (array[x][y] == 1)
				{
					counter++;
					clearRegion(array, x, y);
				}
			}
		}
		return counter;
	}

	public void printArray(int[][] array)
	{
		for (int x = 0; x < array.length; x++)
		{
			for (int y = 0; y < array[x].length; y++)
			{
				System.out.print(array[x][y]);
			}
			System.out.println();
		}
	}

	// Zet alle 1 om in 0 voor een bepaalde region
	private void clearRegion(int[][] array, int x, int y)
	{
		array[x][y] = 0;
		// boven
		if (x > 0 && array[x - 1][y] == 1)
		{
			clearRegion(array, x - 1, y);
		}
		// links
		if (y > 0 && array[x][y - 1] == 1)
		{
			clearRegion(array, x, y - 1);
		}
		// onder
		if (x < array.length - 1 && array[x + 1][y] == 1)
		{
			clearRegion(array, x + 1, y);
		}
		// rechts
		if (y < array[x].length - 1 && array[x][y + 1] == 1)
		{
			clearRegion(array, x, y + 1);
		}
	}

	private int[][] toArray(String input)
	{
		input = input.replace("\n", "");
		int[][] array = new int[128][128];
		for (int x = 0; x < 128; x++)
		{
			for (int y = 0; y < 128; y++)
			{
				int charIndex = (x * 128) + y;
				array[x][y] = (input.charAt(charIndex) == '1' ? 1 : 0);
			}
		}
		return array;
	}

	private String inputToBinary(String input)
	{
		String totalBinary = StringUtils.EMPTY;
		for (int x = 0; x < 128; x++)
		{
			String currentInput = input + "-" + x;
			String knotHash = Day10.bonus(currentInput);
			totalBinary += knotHashToBin(knotHash) + "\n";
		}
		return totalBinary;
	}

	public int countUsed(String input)
	{
		int result = 0;
		for (int i = 0; i < input.length(); i++)
		{
			if (input.charAt(i) == '1')
			{
				result++;
			}
		}
		return result;
	}

	public String knotHashToBin(String knotHash)
	{
		String result = StringUtils.EMPTY;

		for (int i = 0; i < knotHash.length(); i++)
		{
			result += hexToBin("" + knotHash.charAt(i));
		}

		return result;
	}

	public String hexToBin(String input)
	{
		return StringUtils.leftPad(new BigInteger(input, 16).toString(2), 4, "0");
	}

	@Override
	public String getExampleInput()
	{
		return "flqrgnkx";
	}

	@Override
	public String getAssignmentInput()
	{
		return "jzgqcdpd";
	}

}
