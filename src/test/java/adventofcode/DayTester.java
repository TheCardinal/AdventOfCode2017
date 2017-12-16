package adventofcode;

import org.junit.Test;

public class DayTester<T extends AbstractDay<Y>, Y>
{
	private Class<T> clazz;

	public DayTester(Class<T> clazz)
	{
		this.clazz = clazz;
	}

	protected T getDay()
	{
		DayBuilder<T> x = new DayBuilder<T>(clazz);
		T day = x.buildOne();
		return day;
	}

	@Test
	public void runExampleTest()
	{
		System.out.println("Run example: ");
		T day = getDay();
		day.initialize(true);
		day.run(day.getExampleInput());
	}

	@Test
	public void runAssignmentTest()
	{
		System.out.println("Run assignment: ");
		T day = getDay();
		day.initialize(false);
		day.run(day.getAssignmentInput());
	}

	@Test
	public void bonusExampleTest()
	{
		System.out.println("Bonus example: ");
		T day = getDay();
		day.initialize(true);
		day.bonus(day.getExampleInput());
	}

	@Test
	public void bonusAssignmentTest()
	{
		System.out.println("Bonus assignment: ");
		T day = getDay();
		day.initialize(false);
		day.bonus(day.getAssignmentInput());
	}
}
