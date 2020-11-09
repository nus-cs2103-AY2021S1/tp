package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GoalTargetTest {

    @Test
    public void isValidGoal() {
        // between 1 to 6 (inclusive) -> return true
        assertTrue(GoalTarget.isValidGoal(1));
        assertTrue(GoalTarget.isValidGoal(6));
        assertFalse(GoalTarget.isValidGoal(0));
        assertFalse(GoalTarget.isValidGoal(-1));
        assertFalse(GoalTarget.isValidGoal(7));
    }

    @Test
    public void equals() {
        GoalTarget goalTarget = new GoalTarget(1);
        // same values -> returns true
        assertTrue(goalTarget.equals(new GoalTarget(1)));

        // same object -> returns true
        assertTrue(goalTarget.equals(goalTarget));

        // null -> returns false
        assertFalse(goalTarget.equals(null));

        // different type -> returns false
        assertFalse(goalTarget.equals(1));

        // different GoalTarget -> returns false
        assertFalse(goalTarget.equals(new GoalTarget(2)));
    }

    @Test
    public void getUserGoalGrade() {
        GoalTarget goalTargetFour = new GoalTarget(4);
        GoalTarget goalTargetFive = new GoalTarget(5);

        assertEquals(GoalTarget.HONOURS_CAP, GoalTarget.getUserGoalGrade(goalTargetFour));
        assertEquals(GoalTarget.PASS_CAP, GoalTarget.getUserGoalGrade(goalTargetFive));
    }

    @Test
    public void toStringTest() {
        GoalTarget goalTargetTwo = new GoalTarget(2);
        GoalTarget goalTargetThree = new GoalTarget(3);
        GoalTarget goalTargetFour = new GoalTarget(4);
        GoalTarget goalTargetFive = new GoalTarget(5);
        GoalTarget goalTargetSix = new GoalTarget(6);
        GoalTarget goalTargetDefault = new GoalTarget();

        assertEquals(GoalTarget.DISTINCTION, goalTargetTwo.toString());
        assertEquals(GoalTarget.MERIT, goalTargetThree.toString());
        assertEquals(GoalTarget.HONOURS, goalTargetFour.toString());
        assertEquals(GoalTarget.PASS, goalTargetFive.toString());
        assertEquals(GoalTarget.FAIL, goalTargetSix.toString());
        assertEquals(GoalTarget.MESSAGE_CONSTRAINTS, goalTargetDefault.toString());

    }
}
