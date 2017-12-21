package adventofcode.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

import adventofcode.AbstractDay;

public class Day21 extends AbstractDay<Map<String, String>>
{
	private String currentGrid;

	private Map<String, String> rules;

	@Override
	protected void initialize(boolean isExample)
	{
		super.initialize(isExample);
		currentGrid = ".#./..#/###";
	}

	public boolean isDivisebleBy(String grid, int diviser)
	{
		return grid.substring(0, grid.indexOf("/")).length() % diviser == 0;
	}

	@Override
	public void run(Map<String, String> rules)
	{
		this.rules = rules;

		// styledPrint(currentGrid);
		takeSteps(5);
		System.out.println("Result: " + StringUtils.countMatches(currentGrid, "#"));
	}

	@Override
	public void bonus(Map<String, String> rules)
	{
		this.rules = rules;

		// styledPrint(currentGrid);
		takeSteps(18);
		System.out.println("Result: " + StringUtils.countMatches(currentGrid, "#"));
	}

	private void takeSteps(int numberOfSteps)
	{
		for (int x = 1; x <= numberOfSteps; x++)
		{
			if (getSideLength() < 4)
			{
				// System.out.println("mappableOnRule");
				currentGrid = findReplacement(currentGrid);
			}
			else
			{
				String[] split = null;
				if (isDivisebleBy(currentGrid, 2))
				{
					// System.out.println("isDivisebleBy2");
					split = splitIn2by2(currentGrid);
					String[] replaced = Arrays.asList(split).stream().map(part -> findReplacement(part)).collect(Collectors.toList())
						.toArray(new String[0]);
					currentGrid = join3by3(replaced);
				}
				else if (isDivisebleBy(currentGrid, 3))
				{
					// System.out.println("isDivisebleBy3");
					split = splitIn3by3(currentGrid);
					String[] replaced = Arrays.asList(split).stream().map(part -> findReplacement(part)).collect(Collectors.toList())
						.toArray(new String[0]);
					currentGrid = join4by4(replaced);
				}
			}
			// styledPrint(currentGrid);
		}
	}

	private int getSideLength()
	{
		return currentGrid.split("/")[0].length();
	}

	private String findReplacement(String grid)
	{
		if (rules.containsKey(grid))
		{
			return rules.get(grid);
		}
		String flipped = getFlipped(grid);
		if (rules.containsKey(flipped))
		{
			return rules.get(flipped);
		}

		for (int rotation = 1; rotation < 4; rotation++)
		{
			String rotated = getRotated(grid);

			if (rules.containsKey(rotated))
			{
				return rules.get(rotated);
			}
			flipped = getFlipped(rotated);
			if (rules.containsKey(flipped))
			{
				return rules.get(flipped);
			}

			grid = rotated;
		}
		return null;
	}

	private String getFlipped(String grid)
	{
		String[] lines = grid.split("/");
		String[] temp = new String[lines.length];
		for (int x = 0; x < lines.length; x++)
		{
			temp[x] = StringUtils.reverse(lines[x]);
		}
		return StringUtils.join(temp, "/");
	}

	private String getRotated(String grid)
	{
		if (isDivisebleBy(grid, 2))
		{
			return getRotated2(grid);
		}
		return getRotated3(grid);
	}

	// Charindexes in grid
	// 0 1 (/) => 2 0 (/)
	// 3 4 ...... 3 1
	private String getRotated2(String grid)
	{
		return new String(new char[] { grid.charAt(3), grid.charAt(0), '/', grid.charAt(4), grid.charAt(1) });
	}

	// Charindexes in grid
	// 0 1 2 (/) => 8 4 0 (/)
	// 4 5 6 (/) .. 9 5 1 (/)
	// 8 9 X ...... X 6 2
	private String getRotated3(String grid)
	{
		return new String(new char[] {
			grid.charAt(8), grid.charAt(4), grid.charAt(0), '/',
			grid.charAt(9), grid.charAt(5), grid.charAt(1), '/',
			grid.charAt(10), grid.charAt(6), grid.charAt(2)
		});
	}

	private String[] splitIn2by2(String grid)
	{
		String[] lines = grid.split("/");
		List<String> tops = new ArrayList<>();
		List<String> bottoms = new ArrayList<>();

		for (int y = 0; y < lines.length; y += 2)
		{
			for (int x = 0; x < lines[y].length(); x += 2)
			{
				tops.add(lines[y].substring(x, x + 2));
				bottoms.add(lines[y + 1].substring(x, x + 2));
			}
		}

		List<String> results = new ArrayList<>();
		for (int z = 0; z < tops.size(); z++)
		{
			results.add(tops.get(z) + "/" + bottoms.get(z));
		}
		return results.toArray(new String[0]);
	}

	private String[] splitIn3by3(String grid)
	{
		String[] lines = grid.split("/");
		List<String> tops = new ArrayList<>();
		List<String> middles = new ArrayList<>();
		List<String> bottoms = new ArrayList<>();

		for (int y = 0; y < lines.length; y += 3)
		{
			for (int x = 0; x < lines[y].length(); x += 3)
			{
				tops.add(lines[y].substring(x, x + 3));
				middles.add(lines[y + 1].substring(x, x + 3));
				bottoms.add(lines[y + 2].substring(x, x + 3));
			}
		}

		List<String> results = new ArrayList<>();
		for (int z = 0; z < tops.size(); z++)
		{
			results.add(tops.get(z) + "/" + middles.get(z) + "/" + bottoms.get(z));
		}
		return results.toArray(new String[0]);
	}

	private String join3by3(String[] parts)
	{
		int numberOfParts = parts.length;
		int partsPerSide = (int) Math.sqrt(numberOfParts);

		String total = "";
		for (int partIndex = 0; partIndex < numberOfParts; partIndex += partsPerSide)
		{
			String top = "";
			String middle = "";
			String bottom = "";
			for (int y = 0; y < partsPerSide; y++)
			{
				top += parts[partIndex + y].split("/")[0];
				middle += parts[partIndex + y].split("/")[1];
				bottom += parts[partIndex + y].split("/")[2];
			}
			total += top + "/" + middle + "/" + bottom + "/";
		}
		return total.substring(0, total.length() - 1);
	}

	private String join4by4(String[] parts)
	{
		int numberOfParts = parts.length;
		int partsPerSide = (int) Math.sqrt(numberOfParts);

		String total = "";
		for (int partIndex = 0; partIndex < numberOfParts; partIndex += partsPerSide)
		{
			String l1 = "";
			String l2 = "";
			String l3 = "";
			String l4 = "";
			for (int y = 0; y < partsPerSide; y++)
			{
				l1 += parts[partIndex + y].split("/")[0];
				l2 += parts[partIndex + y].split("/")[1];
				l3 += parts[partIndex + y].split("/")[2];
				l4 += parts[partIndex + y].split("/")[3];
			}
			total += l1 + "/" + l2 + "/" + l3 + "/" + l4 + "/";
		}
		return total.substring(0, total.length() - 1);
	}

	private void styledPrint(String grid)
	{
		System.out.println(grid.replaceAll("/", "\n"));
		System.out.println();
	}

	@Override
	public Map<String, String> getExampleInput()
	{
		Map<String, String> rules = new HashMap<String, String>();
		rules.put("../.#", "##./#../...");
		rules.put(".#./..#/###", "#..#/..../..../#..#");
		return rules;
	}

	@Override
	public Map<String, String> getAssignmentInput()
	{
		Map<String, String> rules = new HashMap<String, String>();

		rules.put("../..", ".../.##/.##");
		rules.put("#./..", ".#./.#./##.");
		rules.put("##/..", "##./.../..#");
		rules.put(".#/#.", "#../..#/##.");
		rules.put("##/#.", ".../.#./..#");
		rules.put("##/##", "#.#/.##/.##");
		rules.put(".../.../...", "##../.#../##../#..#");
		rules.put("#../.../...", "..#./##.#/#.##/....");
		rules.put(".#./.../...", "####/#.##/..../...#");
		rules.put("##./.../...", "####/...#/.###/..##");
		rules.put("#.#/.../...", "..#./..#./##../##.#");
		rules.put("###/.../...", "..#./..#./##../...#");
		rules.put(".#./#../...", "##.#/###./###./#..#");
		rules.put("##./#../...", ".#../..##/#.#./#.#.");
		rules.put("..#/#../...", ".##./..../...#/.###");
		rules.put("#.#/#../...", "##../#..#/#..#/....");
		rules.put(".##/#../...", "..../#.../..##/##..");
		rules.put("###/#../...", "####/#.../.##./#...");
		rules.put(".../.#./...", "####/#.../.###/###.");
		rules.put("#../.#./...", "#.#./.###/#.../##.#");
		rules.put(".#./.#./...", ".##./##.#/..##/.#..");
		rules.put("##./.#./...", "..##/.#../..##/##.#");
		rules.put("#.#/.#./...", ".##./.#.#/.#.#/....");
		rules.put("###/.#./...", "..../##.#/#.#./.###");
		rules.put(".#./##./...", "..#./#.../#.../..##");
		rules.put("##./##./...", "##.#/##.#/#.##/#...");
		rules.put("..#/##./...", ".#../.#.#/#.##/####");
		rules.put("#.#/##./...", "..#./#.##/..../.##.");
		rules.put(".##/##./...", "#.##/..##/...#/....");
		rules.put("###/##./...", "..#./#.../#.##/.#.#");
		rules.put(".../#.#/...", "..##/#.#./##../#...");
		rules.put("#../#.#/...", "#.#./..#./.#../..##");
		rules.put(".#./#.#/...", "#.#./.#.#/.#../..##");
		rules.put("##./#.#/...", "###./##.#/#..#/####");
		rules.put("#.#/#.#/...", "##.#/..##/#.../...#");
		rules.put("###/#.#/...", "##.#/..##/###./##..");
		rules.put(".../###/...", "..../...#/##../.###");
		rules.put("#../###/...", ".##./##.#/..../#...");
		rules.put(".#./###/...", "###./..##/.##./#...");
		rules.put("##./###/...", ".##./#..#/.###/.#..");
		rules.put("#.#/###/...", "..../#.#./#.../#..#");
		rules.put("###/###/...", ".#../#.#./#.##/##.#");
		rules.put("..#/.../#..", "##../...#/.#../###.");
		rules.put("#.#/.../#..", "#..#/.#../#.#./..#.");
		rules.put(".##/.../#..", "#.##/.#../...#/.#.#");
		rules.put("###/.../#..", ".#.#/#.../.#.#/.#..");
		rules.put(".##/#../#..", "..#./..../###./#...");
		rules.put("###/#../#..", ".##./##../.#.#/##.#");
		rules.put("..#/.#./#..", "###./.##./###./.###");
		rules.put("#.#/.#./#..", "..../..../#.##/.#..");
		rules.put(".##/.#./#..", ".#.#/.#.#/#.../####");
		rules.put("###/.#./#..", "#.../####/#.##/#.#.");
		rules.put(".##/##./#..", "#.../#.##/#.../###.");
		rules.put("###/##./#..", "...#/.##./#.../.##.");
		rules.put("#../..#/#..", "##../##../..##/....");
		rules.put(".#./..#/#..", "#.#./##../.###/#.##");
		rules.put("##./..#/#..", "#.#./####/.###/...#");
		rules.put("#.#/..#/#..", "#..#/##.#/.#../..#.");
		rules.put(".##/..#/#..", ".###/.#../#.##/.##.");
		rules.put("###/..#/#..", ".###/#.##/..#./..##");
		rules.put("#../#.#/#..", "####/#.../####/##.#");
		rules.put(".#./#.#/#..", ".###/####/####/.#..");
		rules.put("##./#.#/#..", "##.#/...#/..../##.#");
		rules.put("..#/#.#/#..", ".#../..#./.##./.#..");
		rules.put("#.#/#.#/#..", "...#/###./..##/.###");
		rules.put(".##/#.#/#..", "####/##../#..#/##..");
		rules.put("###/#.#/#..", ".#.#/..##/.###/##..");
		rules.put("#../.##/#..", "#..#/#.##/#..#/.###");
		rules.put(".#./.##/#..", "##../.###/..../###.");
		rules.put("##./.##/#..", ".###/.###/##../.##.");
		rules.put("#.#/.##/#..", "..#./.##./##../#.#.");
		rules.put(".##/.##/#..", "####/#..#/..#./....");
		rules.put("###/.##/#..", "#.../.#../#..#/.#..");
		rules.put("#../###/#..", "..../.#../.##./.#.#");
		rules.put(".#./###/#..", "..../####/#.##/###.");
		rules.put("##./###/#..", "...#/.#../#.../##.#");
		rules.put("..#/###/#..", "####/###./###./....");
		rules.put("#.#/###/#..", ".#../.###/##.#/.###");
		rules.put(".##/###/#..", "#.##/##../##../.#..");
		rules.put("###/###/#..", ".###/###./#..#/.#.#");
		rules.put(".#./#.#/.#.", "###./.###/.###/.##.");
		rules.put("##./#.#/.#.", ".#.#/##../###./..#.");
		rules.put("#.#/#.#/.#.", ".#.#/##../###./#.##");
		rules.put("###/#.#/.#.", "..#./.#../.#../..#.");
		rules.put(".#./###/.#.", "#..#/..##/#.#./#.#.");
		rules.put("##./###/.#.", ".#../#..#/#.#./.##.");
		rules.put("#.#/###/.#.", ".#.#/.##./.###/....");
		rules.put("###/###/.#.", "#.#./#.#./##../.#..");
		rules.put("#.#/..#/##.", ".#.#/.#.#/#..#/.#.#");
		rules.put("###/..#/##.", "#.#./##.#/.#../#.##");
		rules.put(".##/#.#/##.", "#.##/#.##/#.##/##.#");
		rules.put("###/#.#/##.", "###./##../.#.#/#...");
		rules.put("#.#/.##/##.", "##.#/.#.#/.#.#/.#.#");
		rules.put("###/.##/##.", ".#.#/#.##/####/....");
		rules.put(".##/###/##.", "#.../####/###./.###");
		rules.put("###/###/##.", ".##./#.#./#.##/##..");
		rules.put("#.#/.../#.#", "#.../##.#/#.##/##.#");
		rules.put("###/.../#.#", "#.#./#.##/##.#/.##.");
		rules.put("###/#../#.#", "##../.#.#/##.#/#...");
		rules.put("#.#/.#./#.#", ".##./.#../#.../.#.#");
		rules.put("###/.#./#.#", "#.#./..##/###./..##");
		rules.put("###/##./#.#", ".###/..##/..#./..#.");
		rules.put("#.#/#.#/#.#", ".#../##.#/.#.#/.#.#");
		rules.put("###/#.#/#.#", "##.#/.#.#/...#/...#");
		rules.put("#.#/###/#.#", "##.#/.#../####/#..#");
		rules.put("###/###/#.#", "...#/..##/##../#..#");
		rules.put("###/#.#/###", "..##/.##./.##./#.##");
		rules.put("###/###/###", "#.#./.#.#/#.../.##.");

		return rules;
	}

}
