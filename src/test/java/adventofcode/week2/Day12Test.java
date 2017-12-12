package adventofcode.week2;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import adventofcode.helpers.Program;

public class Day12Test
{
	@Ignore
	@Test
	public void examplePrograms()
	{
		// List<Program> programs = Day12.getAssignmentPrograms();
		List<Program> programs = Day12.getTestPrograms();
		List<Program> connectedPrograms = Day12.getConnectedPrograms(programs, 0);
		assertEquals(6, connectedPrograms.size());
	}

	@Ignore
	@Test
	public void exampleCount()
	{
		// List<Program> programs = Day12.getAssignmentPrograms();
		List<Program> programs = Day12.getTestPrograms();
		int count = Day12.countGroups(programs);
		assertEquals(2, count);
	}

	@Test
	public void assignment()
	{
		// List<Program> programs = Day12.getAssignmentPrograms();
		List<Program> programs = Day12.getAssignmentPrograms();
		List<Program> connectedPrograms = Day12.getConnectedPrograms(programs, 0);
		System.out.println(connectedPrograms.size());
	}

	@Test
	public void assignmentCount()
	{
		// List<Program> programs = Day12.getAssignmentPrograms();
		List<Program> programs = Day12.getAssignmentPrograms();
		int count = Day12.countGroups(programs);
		System.out.println(count);
		;
	}
}
