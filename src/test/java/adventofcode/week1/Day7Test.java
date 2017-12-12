package adventofcode.week1;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import adventofcode.helpers.Tower;

public class Day7Test
{
	private Day7 day7 = new Day7();

	@Test
	public void testExample()
	{
		Tower root = day7.getRoot(Day7.getExampleTowers());
		System.out.println("Found root: " + root);
	}

	@Test
	public void testAssignment()
	{
		Tower root = day7.getRoot(Day7.getAssignmentTowers());
		System.out.println("Found root: " + root);
	}

	@Test
	public void weightExample()
	{
		runBonus(Day7.getExampleTowers());
	}

	@Test
	public void weightAssignment()
	{
		// 2255 is te hoog
		runBonus(Day7.getAssignmentTowers());
	}

	private void runBonus(List<Tower> towers)
	{
		for (Tower t : towers)
		{
			if (t.parentPresent(towers))
			{
				List<Tower> children = towers.stream().filter(tower -> t.getNames().contains(tower.getName())).collect(Collectors.toList());
				boolean childrenBalanced = children.stream().map(child -> child.calculateWeight(towers)).distinct().collect(Collectors.toList())
					.size() == 1;
				List<Tower> siblings = t.getParent(towers).get().getChildren(towers);
				siblings.remove(t);
				List<Integer> sibWeights = siblings.stream().map(sibling -> sibling.calculateWeight(towers)).distinct().collect(Collectors.toList());
				int myWeight = t.calculateWeight(towers);

				if (!t.getParent(towers).get().isBalanced(towers) && childrenBalanced && !sibWeights.contains(myWeight))
				{
					System.out.println(t);
					System.out.println(myWeight + " should be " + sibWeights.get(0));
				}
			}
			/// Optional<Tower> parent = t.getParent(towers);
			// System.out.println("Parent of " + t.getName() + " = " + (parent.isPresent() ? parent.get().getName() : "-"));
			// if (t.isBalanced(towers) && t.getParent(towers).isPresent() && !t.getParent(towers).get().isBalanced(towers)
			// && !t.hasSiblingOfSameWeight())
			// {
			// System.out.println("Tower " + t.getName() + " is unbalanced!");
			// }
		}
	}
}
