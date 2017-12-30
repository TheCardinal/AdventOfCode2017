package adventofcode.helpers;

public enum OperationType
{
	SOUND("snd"),
	SET("set"),
	ADD("add"),
	MULTIPLY("mul"),
	MODULO("mod"),
	RECOVER("rcv"),
	JUMP_GREATER_ZERO("jgz"),
	SEND("snd2"),
	RECEIVE("rcv2"),
	SUBSTRACT("sub"),
	JUMP_NOT_ZERO("jnz");

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
		if (JUMP_GREATER_ZERO.abreviation.equals(abreviation))
		{
			return JUMP_GREATER_ZERO;
		}
		if (JUMP_NOT_ZERO.abreviation.equals(abreviation))
		{
			return JUMP_NOT_ZERO;
		}
		if (SUBSTRACT.abreviation.equals(abreviation))
		{
			return SUBSTRACT;
		}
		throw new IllegalArgumentException();
	}

	@Override
	public String toString()
	{
		return abreviation;
	}
}
