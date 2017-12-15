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

	// TODO
	@Override
	public void bonus(String input)
	{
		String asBinary = inputToBinary(input);
		System.out.println(asBinary);
		int[][] array = toArray(asBinary);
		int regions = countRegions(array);
		printArray(array);
		System.out.println(regions);

		// 4663 too high
	}

	public int countRegions(int[][] array)
	{
		int counter = 1;
		for (int x = 0; x < array.length; x++)
		{
			for (int y = 0; y < array[0].length; y++)
			{
				if (array[x][y] == 1)
				{
					int neighborRegion = neighborRegion(array, x, y);
					if (neighborRegion > 0)
					{
						array[x][y] = neighborRegion;
					}
					else
					{
						array[x][y] = counter++;
					}
				}
			}
		}
		return counter - 1;
	}

	private void printArray(int[][] array)
	{
		for (int x = 0; x < array.length; x++)
		{
			for (int y = 0; y < array[0].length; y++)
			{
				System.out.print(array[x][y]);
			}
			System.out.println();
		}
	}

	// TODO: klopt niet want een H-structuur komt er niet goed door
	private int neighborRegion(int[][] array, int x, int y)
	{
		int boven = x > 0 ? array[x - 1][y] : 0;
		int links = y > 0 ? array[x][y - 1] : 0;
		return boven > 0 ? boven : links > 0 ? links : 0;
	}

	private int[][] toArray(String input)
	{
		int[][] array = new int[128][128];
		for (int x = 0; x < 128; x++)
		{
			for (int y = 0; y < 128; y++)
			{
				int charIndex = x + y;
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
			// System.out.println("CurrentInput: " + currentInput + " hash: " + knotHash);
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
