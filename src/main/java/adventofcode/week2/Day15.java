package adventofcode.week2;

import adventofcode.AbstractDay;
import adventofcode.helpers.Generator;
import adventofcode.helpers.GeneratorPair;

public class Day15 extends AbstractDay<GeneratorPair>
{
	@Override
	public void run(GeneratorPair generators)
	{
		int matchCount = 0;
		for (int x = 0; x < 40000000; x++)
		{
			generators.generate();
			if (generators.lower16BitsMatch())
			{
				matchCount++;
			}
		}
		System.out.println("matchCount: " + matchCount);
	}

	@Override
	public void bonus(GeneratorPair generators)
	{
		int matchCount = 0;
		for (int x = 0; x < 5000000; x++)
		{
			generators.generateSpecific();
			if (generators.lower16BitsMatch())
			{
				matchCount++;
			}
		}
		System.out.println("matchCount: " + matchCount);
	}

	@Override
	public GeneratorPair getExampleInput()
	{
		Generator a = new Generator(65, 16807, 4);
		Generator b = new Generator(8921, 48271, 8);
		return new GeneratorPair(a, b);
	}

	@Override
	public GeneratorPair getAssignmentInput()
	{
		Generator a = new Generator(277, 16807, 4);
		Generator b = new Generator(349, 48271, 8);
		return new GeneratorPair(a, b);
	}

}
