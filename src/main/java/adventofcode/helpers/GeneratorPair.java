package adventofcode.helpers;

public class GeneratorPair
{
	public final Generator a;

	public final Generator b;

	public GeneratorPair(Generator a, Generator b)
	{
		this.a = a;
		this.b = b;
	}

	public boolean lower16BitsMatch()
	{
		return a.getLowest16Bits().equals(b.getLowest16Bits());
	}

	public void generate()
	{
		a.getNextValue();
		b.getNextValue();
	}

	public void generateSpecific()
	{
		a.getNextSpecificValue();
		b.getNextSpecificValue();
	}
}
