// 7493 is het antwoord
package adventofcode.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

import adventofcode.AbstractDay;
import adventofcode.helpers.RegisterInstruction;

public class Day18 extends AbstractDay<List<String>>
{
	private Map<String, Long> playedSounds;

	private Day18Thread t0, t1;

	private Queue<Long> queueWhereP0Reads, queueWhereP1Reads;

	private boolean p0Receiving = false, p1Receiving = false;

	private int p1Sends = 0;

	@Override
	public void run(List<String> input)
	{
		// executeInstructions(registersP0, instructionsP0, instructionIndexP0, true);
		// 2147483647 = too high
		// 3423 = correct

		// Initialize
		playedSounds = new HashMap<>();
		// parse the input
		List<RegisterInstruction> instructions = input.stream().map(i -> new RegisterInstruction(i)).collect(Collectors.toList());
		List<String> registerNames = instructions.stream().map(ri -> ri.registerName).collect(Collectors.toList());
		Map<String, Long> registers = new HashMap<>();
		for (String registerName : registerNames)
		{
			if (registerName.matches("[a-z]{1}"))
			{
				registers.put(registerName, 0L);
			}
		}

		t0 = new Day18Thread(instructions, registers, true);
		t0.start();
		while (t0.isAlive())
		{
			// Wait till thread stops
		}
	}

	@Override
	public void bonus(List<String> input)
	{
		// parse the input
		List<RegisterInstruction> instructions = input.stream().map(i -> new RegisterInstruction(i, true)).collect(Collectors.toList());
		List<String> registerNames = instructions.stream().map(ri -> ri.registerName).collect(Collectors.toList());
		Map<String, Long> registers = new HashMap<>();
		for (String registerName : registerNames)
		{
			if (registerName.matches("[a-z]{1}"))
			{
				registers.put(registerName, 0L);
			}
		}

		queueWhereP0Reads = new LinkedList<>();
		queueWhereP1Reads = new LinkedList<>();

		t0 = new Day18Thread(instructions, registers, true);
		registers.put("p", 1L);
		t1 = new Day18Thread(instructions, registers, false);

		t1.start();
		t0.start();

		while (t0.isAlive() || t1.isAlive())
		{
			// wait for threads to stop
		}
		System.out.println("result: " + p1Sends);
	}

	@Override
	public List<String> getExampleInput()
	{
		return Arrays.asList(new String[] {
			"set a 1",
			"add a 2",
			"mul a a",
			"mod a 5",
			"snd a",
			"set a 0",
			"rcv a",
			"jgz a -1",
			"set a 1",
			"jgz a -2"
		});
	}

	@Override
	public List<String> getAssignmentInput()
	{
		return Arrays.asList(new String[] {
			"set i 31",
			"set a 1",
			"mul p 17",
			"jgz p p",
			"mul a 2",
			"add i -1",
			"jgz i -2",
			"add a -1",
			"set i 127",
			"set p 618",
			"mul p 8505",
			"mod p a",
			"mul p 129749",
			"add p 12345",
			"mod p a",
			"set b p",
			"mod b 10000",
			"snd b",
			"add i -1",
			"jgz i -9",
			"jgz a 3",
			"rcv b",
			"jgz b -1",
			"set f 0",
			"set i 126",
			"rcv a",
			"rcv b",
			"set p a",
			"mul p -1",
			"add p b",
			"jgz p 4",
			"snd a",
			"set a b",
			"jgz 1 3",
			"snd b",
			"set f 1",
			"add i -1",
			"jgz i -11",
			"snd a",
			"jgz f -16",
			"jgz a -19"
		});
	}

	private class Day18Thread extends Thread
	{
		private int instructionIndex;

		private List<RegisterInstruction> instructions;

		private Map<String, Long> registers;

		private final boolean isP0;

		private boolean keepGoing = true;

		public Day18Thread(List<RegisterInstruction> instructions, Map<String, Long> registers, boolean isP0)
		{
			this.instructionIndex = 0;
			this.instructions = new ArrayList<>(instructions);
			this.registers = new HashMap<>(registers);
			this.isP0 = isP0;
		}

		@Override
		public void run()
		{
			while (keepGoing)
			{
				executeInstructions(registers, instructions, isP0);
			}
			System.out.println("Stopped going");
			// stop();
		}

		private int executeInstructions(Map<String, Long> registers, List<RegisterInstruction> instructions, boolean isP0)
		{
			// boolean keepGoing = true;
			int instructionsExecuted = 0;
			while (keepGoing && instructionIndex >= 0 && instructionIndex < instructions.size())
			{
				RegisterInstruction instruction = instructions.get(instructionIndex);
				keepGoing = execute(registers, instruction, isP0);
				instructionsExecuted++;
			}

			return instructionsExecuted;
		}

		private boolean execute(Map<String, Long> registers, RegisterInstruction instruction, boolean isP0)
		{
			boolean keepGoing = true;
			boolean moveToNext = true;
			long valueToUse = instruction.valueFromOtherRegister() ? registers.get(instruction.valueRegisterName) : instruction.value;
			// if (instruction.operationType != OperationType.RECEIVE)
			// {
			System.out.println((isP0 ? "p0" : "p1") + " " + instruction.operationType);
			// }
			switch (instruction.operationType)
			{
			case ADD:
				registers.put(instruction.registerName, registers.get(instruction.registerName) + valueToUse);
				break;
			case JUMP:
				if (getJumpCheckValue(registers, instruction.registerName) > 0)
				{
					instructionIndex += valueToUse;
					moveToNext = false;
				}
				break;
			case MODULO:
				registers.put(instruction.registerName, registers.get(instruction.registerName) % valueToUse);
				break;
			case MULTIPLY:
				registers.put(instruction.registerName, registers.get(instruction.registerName) * valueToUse);
				break;
			case RECOVER:
				if (registers.get(instruction.registerName) > 0)
				{
					if (playedSounds.containsKey(instruction.registerName))
					{
						System.out
							.println("The last sound played from " + instruction.registerName + " is: " + playedSounds.get(instruction.registerName));
						// instructionIndex = -1;
						keepGoing = false;
						moveToNext = false;
					}
				}
				break;
			case SET:
				registers.put(instruction.registerName, valueToUse);
				break;
			case SOUND:
				System.out.println((isP0 ? "p0" : "p1") + " sound " + valueToUse);
				playedSounds.put(instruction.registerName, registers.get(instruction.registerName));
				break;
			case SEND:
				System.out.println((isP0 ? "p0" : "p1") + " send " + valueToUse);
				if (isP0)
				{
					queueWhereP1Reads.add(valueToUse);
				}
				else
				{
					p1Sends++;
					queueWhereP0Reads.add(valueToUse);
				}
				break;
			case RECEIVE:
				if (isP0)
				{
					p0Receiving = true;
					if (queueWhereP0Reads.isEmpty())
					{
						// System.out.println("P0 receive, but queue empty");
						moveToNext = false;
						if (p1Receiving)
						{
							System.out.println("P0 receive, but P1 also, so stop");
							keepGoing = false;
						}
					}
					else
					{
						p0Receiving = false;
						registers.put(instruction.registerName, queueWhereP0Reads.remove());
					}
				}
				else
				{
					p1Receiving = true;
					if (queueWhereP1Reads.isEmpty())
					{
						// System.out.println("P1 receive, but queue empty");
						moveToNext = false;
						if (p0Receiving)
						{
							System.out.println("P1 receive, but P0 also, so stop");
							keepGoing = false;
						}
					}
					else
					{
						p1Receiving = false;
						registers.put(instruction.registerName, queueWhereP1Reads.remove());
					}
				}
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
}
