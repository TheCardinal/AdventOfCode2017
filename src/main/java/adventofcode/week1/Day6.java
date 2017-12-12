package adventofcode.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Day6
{
	private static List<String> seenConfigurations;

	public static int reallocate(Integer[] blocks)
	{
		seenConfigurations = new ArrayList<String>();
		List<Integer> blockList = Arrays.asList(blocks);
		String configuration = toString(blocks);
		System.out.println(configuration);
		seenConfigurations.add(configuration);

		while (seenConfigurations.stream().distinct().count() == seenConfigurations.size())
		{
			int max = blockList.stream().max(Comparator.comparing(i -> i)).get();
			int indexOfMax = blockList.indexOf(max);
			int toDistribute = blocks[indexOfMax];
			blocks[indexOfMax] = 0;

			int startIndex = indexOfMax + 1;
			if (startIndex >= blocks.length)
			{
				startIndex -= blocks.length;
			}
			redistribute(blocks, toDistribute, startIndex);
			configuration = toString(blocks);
			System.out.println(configuration);
			seenConfigurations.add(configuration);
		}
		System.out.println("Loops between matches: " + (seenConfigurations.size() - seenConfigurations.indexOf(configuration) - 1));
		return (seenConfigurations.size() - 1);
	}

	private static String toString(Integer[] blocks)
	{
		return StringUtils.join(Arrays.asList(blocks).stream().map(element -> element.toString()).toArray(), "-");
	}

	private static void redistribute(Integer[] blocks, int toDistribute, int startIndex)
	{
		int index = startIndex;
		while (toDistribute > 0)
		{
			blocks[index] = blocks[index] + 1;
			toDistribute--;
			index++;
			if (index >= blocks.length)
			{
				index -= blocks.length;
			}
		}
	}
}
