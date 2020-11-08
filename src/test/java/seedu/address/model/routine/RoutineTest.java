package seedu.address.model.routine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalExercises.BENCH_PRESS;
import static seedu.address.testutil.TypicalExercises.SQUATS;
import static seedu.address.testutil.TypicalRoutines.LEG_DAY;
import static seedu.address.testutil.TypicalRoutines.UPPER_BODY;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.exercise.Exercise;
import seedu.address.model.util.Name;

public class RoutineTest {

    @Test
    public void isSameRoutine() {
        // same object -> returns true
        assertTrue(UPPER_BODY.isSameActivity(UPPER_BODY));
        assertTrue(LEG_DAY.isSameActivity(LEG_DAY));
        assertEquals(UPPER_BODY.hashCode(), UPPER_BODY.hashCode());
        assertNotEquals(UPPER_BODY.hashCode(), LEG_DAY.hashCode());

        //different object -> returns false
        assertFalse(LEG_DAY.isSameActivity(UPPER_BODY));

        // null -> returns false
        assertFalse(UPPER_BODY.isSameActivity(null));
        assertFalse(LEG_DAY.isSameActivity(null));

        // same Name, one has Exercise and the other does not -> returns true
        Routine editedRoutine = LEG_DAY;
        Name editedName = new Name("Leg Extensions");
        Exercise typicalExercise = new Exercise(editedName, new HashSet<>());
        editedRoutine.addExercise(typicalExercise);
        assertTrue(LEG_DAY.isSameActivity(editedRoutine));
    }

    @Test
    public void isSameExercise() {
        Routine newRoutine = new Routine(new Name("Abs"));
        assertFalse(newRoutine.hasExercise(SQUATS));
        newRoutine.addExercise(SQUATS);
        assertTrue(newRoutine.hasExercise(SQUATS));
        assertFalse(newRoutine.hasExercise(BENCH_PRESS));

        newRoutine.deleteExercise(SQUATS);
        assertFalse(newRoutine.hasExercise(SQUATS));
    }
    @Test
    public void equals() {

        Routine newRoutine = new Routine(new Name("Leg Day"));

        // same values -> returns true
        Name copyName = new Name("Leg Day");
        Routine copyRoutine = new Routine(copyName);
        assertTrue(newRoutine.equals(copyRoutine));

        // same object -> returns true
        assertTrue(LEG_DAY.equals(LEG_DAY));
        assertTrue(UPPER_BODY.equals(UPPER_BODY));

        // null -> returns false
        assertFalse(LEG_DAY.equals(null));

        // different type -> returns false
        assertFalse(LEG_DAY.equals(5));

        // different routine -> returns false
        assertFalse(LEG_DAY.equals(UPPER_BODY));

        // different name -> returns false
        assertFalse(UPPER_BODY.equals(copyRoutine));

        // different exercises -> returns false
        Name editedName = new Name("Leg Extensions");
        Exercise typicalExercise = new Exercise(editedName, new HashSet<>());
        copyRoutine.addExercise(typicalExercise);
        assertFalse(LEG_DAY.equals(copyRoutine));

        assertFalse(LEG_DAY.toString().equals(UPPER_BODY.toString()));
    }
}
