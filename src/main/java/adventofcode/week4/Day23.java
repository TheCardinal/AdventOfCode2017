package adventofcode.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import adventofcode.AbstractDay;
import adventofcode.helpers.RegisterInstruction;

public class Day23 extends AbstractDay<List<RegisterInstruction>>
{

	private int mulCalls = 0;

	@Override
	public void run(List<RegisterInstruction> instructions)
	{
		if (instructions != null)
		{
			Map<String, Long> registers = new HashMap<>();
			registers.put("a", 0L);
			registers.put("b", 0L);
			registers.put("c", 0L);
			registers.put("d", 0L);
			registers.put("e", 0L);
			registers.put("f", 0L);
			registers.put("g", 0L);
			registers.put("h", 0L);

			Day23Thread t = new Day23Thread(instructions, registers);

			t.start();

			while (t.isAlive())
			{
				// wait till ready
			}
			System.out.println("Multiply calls: " + mulCalls);
			// 9409
		}
	}

	@Override
	public void bonus(List<RegisterInstruction> instructions)
	{
		if (instructions != null)
		{
			Map<String, Long> registers = new HashMap<>();
			registers.put("a", 1L);
			registers.put("b", 0L);
			registers.put("c", 0L);
			registers.put("d", 0L);
			registers.put("e", 0L);
			registers.put("f", 0L);
			registers.put("g", 0L);
			registers.put("h", 0L);

			Day23Thread t = new Day23Thread(instructions, registers);

			t.start();

			while (t.isAlive())
			{
				// wait till ready
			}
			System.out.println("Multiply calls: " + mulCalls);

		}
	}

	private class Day23Thread extends Thread
	{
		private int instructionIndex;

		private List<RegisterInstruction> instructions;

		private Map<String, Long> registers;

		private boolean keepGoing = true;

		public Day23Thread(List<RegisterInstruction> instructions, Map<String, Long> registers)
		{
			this.instructionIndex = 0;
			this.instructions = new ArrayList<>(instructions);
			this.registers = new HashMap<>(registers);
		}

		@Override
		public void run()
		{
			while (keepGoing)
			{
				executeInstructions(registers, instructions);
			}
			System.out.println("Stopped going");
		}

		private int executeInstructions(Map<String, Long> registers, List<RegisterInstruction> instructions)
		{
			int instructionsExecuted = 0;
			while (keepGoing && instructionIndex >= 0 && instructionIndex < instructions.size())
			{
				RegisterInstruction instruction = instructions.get(instructionIndex);
				keepGoing = execute(registers, instruction);
				instructionsExecuted++;
			}

			keepGoing = false;
			return instructionsExecuted;
		}

		private boolean execute(Map<String, Long> registers, RegisterInstruction instruction)
		{
			boolean keepGoing = true;
			boolean moveToNext = true;
			long valueToUse = instruction.valueFromOtherRegister() ? registers.get(instruction.valueRegisterName) : instruction.value;
			if (instruction.registerName.equals("h"))
			{
				System.out.println("Something with H");
			}
			switch (instruction.operationType)
			{
			case SUBSTRACT:
				registers.put(instruction.registerName, registers.get(instruction.registerName) - valueToUse);
				break;
			case JUMP_NOT_ZERO:
				if (getJumpCheckValue(registers, instruction.registerName) != 0)
				{
					instructionIndex += valueToUse;
					moveToNext = false;
				}
				break;
			case MULTIPLY:
				mulCalls++;
				registers.put(instruction.registerName, registers.get(instruction.registerName) * valueToUse);
				break;
			case SET:
				registers.put(instruction.registerName, valueToUse);
				break;
			default:
				break;
			}

			if (moveToNext)
			{
				instructionIndex++;
			}

			return keepGoing;
		}

		private long getJumpCheckValue(Map<String, Long> registers, String input)
		{
			long value = 0;
			try
			{
				value = Long.parseLong(input);
			}
			catch (NumberFormatException e)
			{
				value = registers.get(input);
			}
			return value;
		}
	}

	@Override
	public List<RegisterInstruction> getExampleInput()
	{
		return null;
	}

	@Override
	public List<RegisterInstruction> getAssignmentInput()
	{
		List<String> instructionStrings = Arrays.asList(new String[] {
			"set b 99",
			"set c b",
			"jnz a 2",
			"jnz 1 5",
			"mul b 100",
			"sub b -100000",
			"set c b",
			"sub c -17000",
			"set f 1",
			"set d 2",
			"set e 2",
			"set g d",
			"mul g e",
			"sub g b",
			"jnz g 2",
			"set f 0",
			"sub e -1",
			"set g e",
			"sub g b",
			"jnz g -8",
			"sub d -1",
			"set g d",
			"sub g b",
			"jnz g -13",
			"jnz f 2",
			"sub h -1",
			"set g b",
			"sub g c",
			"jnz g 2",
			"jnz 1 3",
			"sub b -17",
			"jnz 1 -23" });

		List<RegisterInstruction> instructions = instructionStrings.stream().map(i -> new RegisterInstruction(i)).collect(Collectors.toList());
		return instructions;
	}

}
