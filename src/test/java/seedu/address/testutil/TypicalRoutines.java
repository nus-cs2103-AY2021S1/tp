package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.model.FitNus;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.routine.Routine;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.Name;

/**
 * A utility class containing a list of {@code Routine} objects to be used in tests.
 */
public class TypicalRoutines {

    public static final Routine LEG_DAY = new Routine(new Name("Leg Day"));
    public static final Routine UPPER_BODY = new Routine(new Name("Upper Body"));

    private TypicalRoutines() {
    } // prevents instantiation

    /**
     * Returns an {@code FitNus} with all the typical routines.
     */
    public static FitNus getTypicalFitNus() {
        FitNus fn = new FitNus();
        for (Routine routine : getTypicalRoutines()) {
            fn.addRoutine(routine);
        }
        return fn;
    }


    /**
     * Returns an {@code FitNus} with all the typical routines.
     */
    public static FitNus getPopulatedFitNus() {
        FitNus fn = new FitNus();
        Set<Tag> typicalTags = new HashSet<>();
        typicalTags.add(new Tag("Dumbbell"));
        Exercise squats = new Exercise(new Name("Squats"), new HashSet<>());
        Exercise bicep = new Exercise(new Name("Bicep Curls"), typicalTags);

        if (!LEG_DAY.hasExercise(squats)) {
            LEG_DAY.addExercise(squats);
        }

        if (!UPPER_BODY.hasExercise(bicep)) {
            UPPER_BODY.addExercise(bicep);
        }

        fn.addRoutine(LEG_DAY);
        fn.addRoutine(UPPER_BODY);
        return fn;
    }
    public static List<Routine> getTypicalRoutines() {
        return new ArrayList<>(Arrays.asList(LEG_DAY, UPPER_BODY));
    }
}
