package seedu.address.model.util;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.exceptions.CaloriesOverflow;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ExerciseBook;
import seedu.address.model.GoalBook;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.ReadOnlyGoalBook;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.MuscleTag;
import seedu.address.model.goal.Goal;

/**
 * Contains utility methods for populating {@code ExerciseBook} with sample data.
 */
public class SampleDataUtil {
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<ExerciseTag> getExerciseTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(ExerciseTag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a muscleTag set containing the list of strings given.
     */
    public static Set<MuscleTag> getMuscleTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(MuscleTag::new)
                .collect(Collectors.toSet());
    }

    private static Exercise[] getSampleExercises() throws ParseException {
        Exercise[] result;
        try {
            result = new Exercise[]{
                new Exercise(new seedu.address.model.exercise.Name("Push Up"),
                    new Description("Did 52 within 60 seconds"), new Date("01-10-2020"), new Calories("100"),
                    getMuscleTagSet("chest"), getExerciseTagSet("gym")),
                new Exercise(new seedu.address.model.exercise.Name("Sit Up"), new Description("Did 50"),
                    new Date("01-10-2020"), new Calories("120"), getMuscleTagSet("chest"),
                    getExerciseTagSet("gym")),
                new Exercise(new seedu.address.model.exercise.Name("2 4km"), new Description("11:30"),
                    new Date("04-10-2020"), new Calories("100"), getMuscleTagSet("chest"),
                    getExerciseTagSet("gym")),
                new Exercise(new seedu.address.model.exercise.Name("Pull Up"),
                    new Description("20 with Added Weight: 5 kg "), new Date("05-10-2020"),
                    new Calories("100"), getMuscleTagSet("chest"), getExerciseTagSet("gym"))
            };
        } catch (CaloriesOverflow err) {
            throw new ParseException("Sample Util contains invalid Calories");
        }
        return result;
    }

    public static ReadOnlyExerciseBook getSampleExerciseBook() throws ParseException {
        ExerciseBook eb = new ExerciseBook();
        for (Exercise e : getSampleExercises()) {
            eb.addExercise(e);
        }
        return eb;
    }

    public static ReadOnlyGoalBook getSampleGoalBook() {
        GoalBook gb = new GoalBook();
        for (Map.Entry<Date, Goal> entry: gb.getGoalMap().entrySet()) {
            gb.addGoal(entry.getValue());
        }
        return gb;
    }
}
