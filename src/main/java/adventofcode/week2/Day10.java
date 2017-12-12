package adventofcode.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import adventofcode.helpers.PrintHelper;

public class Day10
{
	public static Integer[] getInput(int size)
	{
		List<Integer> myList = new ArrayList<>();
		for (int x = 0; x <= size; x++)
		{
			myList.add(x);
		}
		return myList.toArray(new Integer[0]);
	}

	public static int assignment(int maxNumber, int[] lengths)
	{
		Integer[] list = getInput(maxNumber);
		PrintHelper.printList("Start: ", list);
		int currentPosition = 0;
		int skipSize = 0;

		for (int length : lengths)
		{
			int takeAt = currentPosition;
			if (takeAt == list.length)
			{
				takeAt -= list.length;
			}

			List<Integer> selection = new ArrayList<>();
			for (int takeCount = 0; takeCount < length; takeCount++)
			{
				selection.add(list[takeAt]);
				takeAt++;
				if (takeAt == list.length)
				{
					takeAt = 0;
				}
			}

			// PrintHelper.printList("selection", selection);
			List<Integer> reversedSelection = reverse(selection);
			// PrintHelper.printList("reversed", reversedSelection);
			int placeAt = currentPosition;
			if (placeAt == list.length)
			{
				placeAt -= list.length;
			}
			for (int placeCount = 0; placeCount < length; placeCount++)
			{
				list[placeAt] = reversedSelection.get(placeCount);
				placeAt++;
				if (placeAt == list.length)
				{
					placeAt = 0;
				}
			}

			PrintHelper.printList("-> ", list);

			currentPosition += length + skipSize;
			if (currentPosition >= list.length)
			{
				currentPosition -= list.length;
			}
			System.out.println("new Curr pos: " + currentPosition);
			skipSize++;
		}

		return list[0] * list[1];
	}

	public static List<Integer> reverse(List<Integer> input)
	{
		Iterator<Integer> reversedStream = input.stream().collect(Collectors.toCollection(LinkedList::new))
			.descendingIterator();
		return StreamSupport.stream(
			Spliterators.spliteratorUnknownSize(reversedStream,
				Spliterator.ORDERED),
			false).collect(
				Collectors.<Integer> toList());
	}

	public static Integer[] getASCIICodes(String input)
	{
		List<Integer> list = new ArrayList<>();
		for (char c : input.toCharArray())
		{
			list.add((int) c);
		}
		return list.toArray(new Integer[0]);
	}

	public static Integer[] lengthsPostfix = new Integer[] { 17, 31, 73, 47, 23 };

	public static Integer[] getLengths(String input)
	{
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(getASCIICodes(input)));
		list.addAll(Arrays.asList(lengthsPostfix));
		return list.toArray(new Integer[0]);
	}

	public static String bonus(String input)
	{
		Integer[] lengths = getLengths(input);
		Integer[] list = getInput(255);
		// printList("Start: ", list);
		int currentPosition = 0;
		int skipSize = 0;

		for (int iteration = 1; iteration <= 64; iteration++)
		{
			System.out.println("Iteration " + iteration);
			for (int length : lengths)
			{
				int takeAt = currentPosition;
				while (takeAt >= list.length)
				{
					takeAt -= list.length;
				}

				List<Integer> selection = new ArrayList<>();
				for (int takeCount = 0; takeCount < length; takeCount++)
				{
					selection.add(list[takeAt]);
					takeAt++;
					if (takeAt == list.length)
					{
						takeAt = 0;
					}
				}

				// printList("selection", selection);
				List<Integer> reversedSelection = reverse(selection);
				// printList("reversed", reversedSelection);
				int placeAt = currentPosition;
				while (placeAt >= list.length)
				{
					placeAt -= list.length;
				}
				for (int placeCount = 0; placeCount < length; placeCount++)
				{
					list[placeAt] = reversedSelection.get(placeCount);
					placeAt++;
					if (placeAt == list.length)
					{
						placeAt = 0;
					}
				}

				// printList("-> ", list);

				currentPosition += length + skipSize;
				if (currentPosition >= list.length)
				{
					currentPosition -= list.length;
				}
				// System.out.println("new Curr pos: " + currentPosition);
				skipSize++;
			}
		}

		Integer[] denseHashes = new Integer[16];
		for (int denseHashCount = 1; denseHashCount <= 16; denseHashCount++)
		{
			denseHashes[denseHashCount - 1] = denseHash(list, denseHashCount);
		}

		String knotHash = "";
		for (Integer denseHash : denseHashes)
		{
			knotHash += toHex(denseHash);
		}
		return knotHash;
	}

	public static Integer denseHash(Integer[] input, int hashNumber)
	{
		int offSet = (hashNumber - 1) * 16;
		return input[offSet + 0] ^ input[offSet + 1] ^ input[offSet + 2] ^ input[offSet + 3] ^ input[offSet + 4] ^ input[offSet + 5]
			^ input[offSet + 6] ^ input[offSet + 7] ^ input[offSet + 8] ^ input[offSet + 9] ^ input[offSet + 10]
			^ input[offSet + 11] ^ input[offSet + 12] ^ input[offSet + 13] ^ input[offSet + 14] ^ input[offSet + 15];
	}

	public static String toHex(Integer input)
	{
		return String.format("%02X", input).toLowerCase();
	}
}
