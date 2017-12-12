package adventofcode.week1;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public class Day4
{
	public static long bonus(String[] input)
	{
		return Arrays.asList(input).stream().filter(passphrase -> isValidBonusPassphrase(passphrase)).count();
	}

	public static long numberOfValidPassphrases2(String[] input)
	{
		return Arrays.asList(input).stream().filter(passphrase -> isValidPassphrase(passphrase)).count();
	}

	// TODO: Java8 Lambda's would make this easier!
	public static int numberOfValidPassphrases(String[] input)
	{

		int counter = 0;
		for (String s : input)
		{
			if (isValidPassphrase(s))
			{
				counter++;
			}
		}
		return counter;
	}

	public static boolean isValidPassphrase(String input)
	{
		String[] parts = input.split(" ");
		input = " " + input + " ";

		for (String part : parts)
		{
			// System.out.println("Looking for: " + part);
			String wordToFind = " " + part + " ";
			int firstIndex = input.indexOf(wordToFind);
			// System.out.println("First found at: " + firstIndex);
			int secondIndex = input.indexOf(wordToFind, firstIndex + 1);
			if (secondIndex > 0)
			{
				// System.out.println("Found" + wordToFind + "at: " + firstIndex + " and " + secondIndex);
				return false;
			}
		}
		return true;
	}

	public static boolean isValidBonusPassphrase(String input)
	{
		String[] parts = input.split(" ");
		for (int x = 0; x < parts.length; x++)
		{
			char[] charArray = parts[x].toCharArray();
			Arrays.sort(charArray);
			parts[x] = new String(charArray);
		}

		return isValidPassphrase(StringUtils.join(parts, ' '));
	}
}
