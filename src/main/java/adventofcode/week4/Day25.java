package adventofcode.week4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adventofcode.AbstractDay;
import adventofcode.helpers.State;
import adventofcode.helpers.StateAction;

public class Day25 extends AbstractDay<List<State>>
{
	private Map<Long, Integer> positionValue;

	private String currentState;

	private Long currentPosition;

	@Override
	public void run(List<State> states)
	{
		currentState = "A";
		currentPosition = 0L;
		positionValue = new HashMap<Long, Integer>();
		positionValue.put(currentPosition, 0);

		int numberOfSteps = isExample() ? 6 : 12586542;
		for (int step = 0; step < numberOfSteps; step++)
		{
			State s = states.stream().filter(state -> state.name.equals(currentState)).findFirst().get();
			Integer valueAtPosition = positionValue.containsKey(currentPosition) ? positionValue.get(currentPosition) : 0;

			StateAction action = valueAtPosition == 0 ? s.actionOn0 : s.actionOn1;
			// executeAction
			positionValue.put(new Long(currentPosition), action.valueToWrite);
			currentPosition = action.goLeft ? currentPosition - 1 : currentPosition + 1;
			currentState = action.continueState;
		}

		int diagnosticChecksum = positionValue.values().stream().mapToInt(Integer::intValue).sum();
		System.out.println("Diagnostic checksum after " + numberOfSteps + " steps: " + diagnosticChecksum);
	}

	@Override
	public void bonus(List<State> states)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public List<State> getExampleInput()
	{
		StateAction a0 = new StateAction(1, false, "B");
		StateAction a1 = new StateAction(0, true, "B");
		State a = new State("A", a0, a1);

		StateAction b0 = new StateAction(1, true, "A");
		StateAction b1 = new StateAction(1, false, "A");
		State b = new State("B", b0, b1);

		return Arrays.asList(new State[] { a, b });
	}

	@Override
	public List<State> getAssignmentInput()
	{
		// In state A:
		StateAction a0 = new StateAction(1, false, "B");
		StateAction a1 = new StateAction(0, true, "B");
		State a = new State("A", a0, a1);

		// In state B:
		StateAction b0 = new StateAction(0, false, "C");
		StateAction b1 = new StateAction(1, true, "B");
		State b = new State("B", b0, b1);

		// In state C:
		StateAction c0 = new StateAction(1, false, "D");
		StateAction c1 = new StateAction(0, true, "A");
		State c = new State("C", c0, c1);

		// In state D:
		StateAction d0 = new StateAction(1, true, "E");
		StateAction d1 = new StateAction(1, true, "F");
		State d = new State("D", d0, d1);

		// In state E:
		StateAction e0 = new StateAction(1, true, "A");
		StateAction e1 = new StateAction(0, true, "D");
		State e = new State("E", e0, e1);

		// In state F:
		StateAction f0 = new StateAction(1, false, "A");
		StateAction f1 = new StateAction(1, true, "E");
		State f = new State("F", f0, f1);

		return Arrays.asList(new State[] { a, b, c, d, e, f });
	}

}
