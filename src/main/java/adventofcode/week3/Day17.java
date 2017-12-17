package adventofcode.week3;

import java.util.ArrayList;
import java.util.List;

import adventofcode.AbstractDay;

public class Day17 extends AbstractDay<Integer>
{
	private int insertionCount = 2017;

	private int currentIndex;

	private List<Integer> buffer;

	@Override
	protected void initialize(boolean isExample)
	{
		super.initialize(isExample);
		buffer = new ArrayList<>();
		buffer.add(0);
		currentIndex = buffer.indexOf(0);
	}

	@Override
	public void run(Integer movement)
	{
		fillBuffer(movement);
		// printBuffer();
		int indexOf2017 = buffer.indexOf(2017);
		Integer numberFollowing2017 = buffer.get(indexOf2017 + 1);
		System.out.println("Number following 2017: " + numberFollowing2017);
	}

	private void fillBuffer(Integer movement)
	{
		for (int insertion = 1; insertion <= insertionCount; insertion++)
		{
			int indexAfterMove = (currentIndex + movement) % buffer.size();
			currentIndex = indexAfterMove + 1;
			buffer.add(currentIndex, insertion);
		}
	}

	private void printBuffer()
	{
		System.out.print("[");
		buffer.forEach(x -> System.out.print(x + (buffer.indexOf(x) != buffer.size() - 1 ? ", " : "")));
		System.out.println("]");
	}

	@Override
	public void bonus(Integer input)
	{
		insertionCount = 50000000;
		Integer valueAfter0 = fillVirtualBuffer(input);
		System.out.println("Number following 0: " + valueAfter0);
	}

	private Integer fillVirtualBuffer(Integer movement)
	{
		Integer valueAfter0 = null;
		int bufferSize = 1;
		for (int insertion = 1; insertion <= insertionCount; insertion++)
		{
			int indexAfterMove = (currentIndex + movement) % bufferSize;
			currentIndex = indexAfterMove + 1;
			if (currentIndex == 1)
			{
				valueAfter0 = insertion;
			}
			bufferSize++;
		}

		return valueAfter0;
	}

	@Override
	public Integer getExampleInput()
	{
		return 3;
	}

	@Override
	public Integer getAssignmentInput()
	{
		return 356;
	}

}
