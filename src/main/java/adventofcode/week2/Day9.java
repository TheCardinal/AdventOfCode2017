package adventofcode.week2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day9
{

	private final static String ESCAPED_ESCAPE = "!!";

	private final static String ESCAPE = "!.";

	private final static String GARBAGE = "<[^>]*>";

	public static int run(String input)
	{
		System.out.println("input: " + input);
		String noEscapedEscapes = input.replaceAll(ESCAPED_ESCAPE, "");
		String noEscapes = noEscapedEscapes.replaceAll(ESCAPE, "");
		String noGarbage = noEscapes.replaceAll(GARBAGE, "");
		System.out.println("noGarbage: " + noGarbage);

		int result = 0;
		int currValue = 1;
		for (char c : noGarbage.toCharArray())
		{
			if (c == '{')
			{
				result += currValue;
				currValue++;
			}
			if (c == '}')
			{
				currValue--;
			}
		}
		System.out.println("result: " + result);
		System.out.println("##################################################");
		return result;
	}

	public static int garbageCollector(String input)
	{
		String noEscapedEscapes = input.replaceAll(ESCAPED_ESCAPE, "");
		String noEscapes = noEscapedEscapes.replaceAll(ESCAPE, "");

		Pattern pattern = Pattern.compile(GARBAGE);
		Matcher matcher = pattern.matcher(noEscapes);
		int length = 0;
		while (matcher.find())
		{
			String match = matcher.group(0);
			System.out.println("found: " + match);
			length += match.length() - 2;
		}
		System.out.println("result length: " + length);
		return length;
	}

	public static String[] splitToGroups(String input)
	{
		return input.split("\\{.*\\},");
	}
}
