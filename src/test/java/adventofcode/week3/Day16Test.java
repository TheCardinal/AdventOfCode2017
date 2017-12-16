package adventofcode.week3;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import adventofcode.DayTester;

public class Day16Test extends DayTester<Day16, List<String>>
{
	public Day16Test()
	{
		super(Day16.class);
	}

	@Test
	public void spinTest()
	{
		Day16 day = getDay();
		day.setDancers(5);
		day.spin(3);
		assertEquals("cdeab", new String(day.getDancers()));
	}

	@Test
	public void spinTest2()
	{
		Day16 day = getDay();
		day.setDancers(5);
		day.spin(1);
		assertEquals("eabcd", new String(day.getDancers()));
	}

	@Test
	public void spinTest3()
	{
		Day16 day = getDay();
		day.setDancers(10);
		day.spin(10);
		assertEquals("abcdefghij", new String(day.getDancers()));
	}

	@Test
	public void exchangeTest()
	{
		Day16 day = getDay();
		day.setDancers(10);
		day.exchange(0, 9);
		assertEquals("jbcdefghia", new String(day.getDancers()));
	}

	@Test
	public void partnerTest()
	{
		Day16 day = getDay();
		day.setDancers(10);
		day.partner('a', 'j');
		assertEquals("jbcdefghia", new String(day.getDancers()));
	}
}
