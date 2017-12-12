package adventofcode.helpers;

import java.util.List;

public class PrintHelper
{
	public static void printList(String prefix, Integer[] list)
	{
		System.out.print(prefix + ": ");
		for (int x = 0; x < list.length; x++)
		{
			System.out.print(list[x] + ",");
		}
		System.out.println();
	}

	public static void printList(String prefix, List<Integer> list)
	{
		printList(prefix, list.toArray(new Integer[0]));
	}
}
