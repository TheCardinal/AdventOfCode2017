package adventofcode.helpers;

public enum OperationType
{
	SOUND("snd"),
	SET("set"),
	ADD("add"),
	MULTIPLY("mul"),
	MODULO("mod"),
	RECOVER("rcv"),
	JUMP("jgz"),
	SEND("snd2"),
	RECEIVE("rcv2");

	private final String abreviation;

	OperationType(String abreviation)
	{
		this.abreviation = abreviation;
	}

	public static OperationType fromString(String abreviation)
	{
		if (SOUND.abreviation.equals(abreviation))
		{
			return SOUND;
		}
		if (SET.abreviation.equals(abreviation))
		{
			return SET;
		}
		if (ADD.abreviation.equals(abreviation))
		{
			return ADD;
		}
		if (MULTIPLY.abreviation.equals(abreviation))
		{
			return MULTIPLY;
		}
		if (MODULO.abreviation.equals(abreviation))
		{
			return MODULO;
		}
		if (RECOVER.abreviation.equals(abreviation))
		{
			return RECOVER;
		}
		if (JUMP.abreviation.equals(abreviation))
		{
			return JUMP;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public String toString()
	{
		return abreviation;
	}
}
