package adventofcode.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tower
{
	public String name;

	public List<String> names = new ArrayList<>();

	public int weight;

	public Tower(String name, int weight)
	{
		this(name, weight, null);
	}

	public Tower(String name, int weight, String[] names)
	{
		this.name = name;
		this.weight = weight;
		if (names != null)
		{
			this.names = Arrays.asList(names);
		}
	}

	public Optional<Tower> getParent(List<Tower> totalTower)
	{
		return totalTower.stream().filter(tower -> tower.getNames().contains(getName())).findFirst();
	}

	public boolean parentPresent(List<Tower> totalTower)
	{
		return getParent(totalTower).isPresent();
	}

	public String getName()
	{
		return name;
	}

	public List<String> getNames()
	{
		return names;
	}

	public int getWeight()
	{
		return weight;
	}

	@Override
	public String toString()
	{
		String result = getName() + " (" + getWeight() + ")";
		if (!getNames().isEmpty())
		{
			result += " -> ";
			for (String name : getNames())
			{
				result += name + ", ";
			}
		}
		return result;
	}

	public List<Tower> getChildren(List<Tower> towers)
	{
		return towers.stream().filter(tower -> getNames().contains(tower.getName())).collect(Collectors.toList());
	}

	public int calculateWeight(List<Tower> towers)
	{
		if (getNames().isEmpty())
		{
			return getWeight();
		}
		return getChildren(towers).stream().map(child -> child.calculateWeight(towers)).mapToInt(i -> i.intValue()).sum() + getWeight();
	}

	public boolean isBalanced(List<Tower> towers)
	{
		List<Integer> childWeights = getChildren(towers).stream().map(child -> child.calculateWeight(towers)).collect(Collectors.toList());
		return childWeights.stream().distinct().count() == 1;
	}

	public boolean isBalanced2()
	{
		return false;
	}
}
