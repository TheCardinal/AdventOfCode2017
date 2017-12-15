package adventofcode.helpers;

public class Generator
{
	private long currentValue = 0;

	private final long factor;

	private final long devider = 2147483647;

	private final int multipleChecker;

	public Generator(long startingValue, long factor)
	{
		this(startingValue, factor, 0);
	}

	public Generator(long startingValue, long factor, int multipleChecker)
	{
		this.currentValue = startingValue;
		this.factor = factor;
		this.multipleChecker = multipleChecker;
	}

	public long getNextValue()
	{
		this.currentValue = (currentValue * factor) % devider;
		return currentValue;
	}

	public long getNextSpecificValue()
	{
		getNextValue();
		while (currentValue % multipleChecker != 0)
		{
			getNextValue();
		}
		return currentValue;
	}

	public String getBitValue()
	{
		return Long.toBinaryString(currentValue);
	}

	public String getLowest16Bits()
	{
		String bitValue = getBitValue();
		return bitValue.substring(Math.max(bitValue.length() - 16, 0), bitValue.length());
	}
}
