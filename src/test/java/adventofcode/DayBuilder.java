package adventofcode;

public class DayBuilder<T extends AbstractDay<?>>
{
	private Class<T> clazz;

	public DayBuilder(Class<T> clazz)
	{
		this.clazz = clazz;
	}

	public T buildOne()
	{
		try
		{
			return clazz.newInstance();
		}
		catch (InstantiationException | IllegalAccessException e)
		{
			System.out.println("error building day");
		}
		return null;
	}
}
