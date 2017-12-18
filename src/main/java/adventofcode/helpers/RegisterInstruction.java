package adventofcode.helpers;

import org.apache.commons.lang.StringUtils;

public class RegisterInstruction
{
	public String registerName;

	public OperationType operationType;

	public String valueRegisterName = null;

	public int value;

	public RegisterInstruction(String instructionString)
	{
		String[] parts = instructionString.split(" ");
		operationType = OperationType.fromString(parts[0]);
		registerName = parts[1];
		if (parts.length == 3)
		{
			// Parse 3rd argument
			try
			{
				value = Integer.parseInt(parts[2]);
			}
			catch (NumberFormatException e)
			{
				valueRegisterName = parts[2];
			}
		}
	}

	public boolean valueFromOtherRegister()
	{
		return StringUtils.isNotBlank(valueRegisterName);
	}

	@Override
	public String toString()
	{
		return operationType + " " + registerName + " " + value;
	}
}
