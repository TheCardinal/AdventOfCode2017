package adventofcode.week4;

import java.awt.Point;

import org.apache.commons.lang.StringUtils;

import adventofcode.AbstractDay;
import adventofcode.helpers.Direction;
import adventofcode.helpers.PointHelper;

public class Day22 extends AbstractDay<String[]>
{
	private String[] grid;

	private Point currentPosition;

	private int numberOfInfections;

	private Direction direction;

	private boolean isBonus = false;

	@Override
	protected void initialize(boolean isExample)
	{
		super.initialize(isExample);
		numberOfInfections = 0;
		direction = Direction.UP;
	}

	@Override
	public void run(String[] grid)
	{
		this.grid = grid;
		currentPosition = isExample() ? new Point(1, 1) : new Point(12, 12);

		for (int burst = 1; burst <= 10000; burst++)
		{
			turn();
			changeNodeStatus();
			move();
			expandGridWhenNeeded();
		}
		System.out.println("Number of infections: " + numberOfInfections);
	}

	private void turn()
	{
		if (isBonus && getCurrent() == 'F')
		{
			switch (direction)
			{
			case DOWN:
				direction = Direction.UP;
				break;
			case LEFT:
				direction = Direction.RIGHT;
				break;
			case RIGHT:
				direction = Direction.LEFT;
				break;
			case UP:
				direction = Direction.DOWN;
				break;
			}
			return;
		}
		else if (isBonus && getCurrent() == 'W')
		{
			return;
		}

		boolean turnLeft = currentIsClean();
		switch (direction)
		{
		case DOWN:
			direction = turnLeft ? Direction.RIGHT : Direction.LEFT;
			break;
		case LEFT:
			direction = turnLeft ? Direction.DOWN : Direction.UP;
			break;
		case RIGHT:
			direction = turnLeft ? Direction.UP : Direction.DOWN;
			break;
		case UP:
			direction = turnLeft ? Direction.LEFT : Direction.RIGHT;
			break;
		}
		// System.out.println("Now facing: " + direction);
	}

	private void changeNodeStatus()
	{
		char[] currentRow = grid[(int) currentPosition.getY()].toCharArray();
		if (!isBonus && currentIsClean())
		{
			numberOfInfections++;
		}
		char newStatus = '?'; // to avoid initialization error
		char current = getCurrent();
		switch (current)
		{
		case '.':
			newStatus = isBonus ? 'W' : '#';
			if (!isBonus)
			{
				numberOfInfections++;
			}
			break;
		case '#':
			newStatus = isBonus ? 'F' : '.';
			break;
		case 'W':
			newStatus = '#';
			numberOfInfections++;
			break;
		case 'F':
			newStatus = '.';
			break;
		}
		currentRow[(int) currentPosition.getX()] = newStatus;
		grid[(int) currentPosition.getY()] = new String(currentRow);
	}

	private boolean currentIsClean()
	{
		return getCurrent() == '.';
	}

	private char getCurrent()
	{
		String currentRow = grid[(int) currentPosition.getY()];
		return currentRow.charAt((int) currentPosition.getX());
	}

	private void move()
	{
		switch (direction)
		{
		case DOWN:
			currentPosition = PointHelper.getLocationUp(currentPosition); // UGLY
			break;
		case LEFT:
			currentPosition = PointHelper.getLocationLeft(currentPosition);
			break;
		case RIGHT:
			currentPosition = PointHelper.getLocationRight(currentPosition);
			break;
		case UP:
			currentPosition = PointHelper.getLocationDown(currentPosition); // UGLY
			break;
		}
		// System.out.println("Now at position: [" + currentPosition.getX() + "," + currentPosition.getY() + "]");
	}

	private void expandGridWhenNeeded()
	{
		int currentSideLength = grid.length;
		int currentX = (int) currentPosition.getX();
		int currentY = (int) currentPosition.getY();
		if (currentX < 0 || currentX >= currentSideLength || currentY < 0 || currentY >= currentSideLength)
		{
			System.out.println("Expanding...");
			String newRow = StringUtils.repeat(".", currentSideLength + 2);
			String[] newGrid = new String[grid.length + 2];
			newGrid[0] = newRow;
			for (int x = 1; x <= grid.length; x++)
			{
				newGrid[x] = "." + grid[x - 1] + ".";
			}
			newGrid[currentSideLength + 1] = newRow;
			currentPosition = new Point(currentX + 1, currentY + 1);
			// System.out.println("Now at position: [" + currentPosition.getX() + "," + currentPosition.getY() + "]");
			grid = new String[newGrid.length];
			System.arraycopy(newGrid, 0, grid, 0, newGrid.length);
		}
	}

	@Override
	public void bonus(String[] grid)
	{
		isBonus = true;

		this.grid = grid;
		currentPosition = isExample() ? new Point(1, 1) : new Point(12, 12);

		for (int burst = 1; burst <= 10000000; burst++)
		{
			turn();
			changeNodeStatus();
			move();
			expandGridWhenNeeded();
		}
		System.out.println("Number of infections: " + numberOfInfections);
	}

	@Override
	public String[] getExampleInput()
	{
		return new String[] {
			"..#",
			"#..",
			"..."
		};
	}

	@Override
	public String[] getAssignmentInput()
	{
		return new String[] {
			"###.#######...#####.#..##",
			".####...###.##...#..#....",
			".#.#...####.###..##..##.#",
			"########.#.#...##.#.##.#.",
			"..#.#...##..#.#.##..####.",
			"..#.#.....#....#####..#..",
			"#.#..##...#....#.##...###",
			".#.##########...#......#.",
			".#...#..##...#...###.#...",
			"......#.###.#..#...#.####",
			".#.###.##...###.###.###.#",
			".##..##...#.#.#####.#...#",
			"#...#..###....#.##.......",
			"####.....######.#.##..#..",
			"..#...#..##.####.#####.##",
			"#...#.#.#.#.#...##..##.#.",
			"#####.#...#.#.#.#.##.####",
			"....###...#.##.#.##.####.",
			".#....###.#####...#.....#",
			"#.....#....#####.#..#....",
			".#####.#....#..##.#.#.###",
			"####.#..#..##..#.#..#.###",
			".##.##.#.#.#.#.#..####.#.",
			"#####..##.#.#..#..#...#..",
			"#.#..#.###...##....###.##" };
	}

}
