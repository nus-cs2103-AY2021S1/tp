package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ExerciseBook;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.exercise.Calories;
import seedu.address.model.exercise.Date;
import seedu.address.model.exercise.Description;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.exercise.ExerciseTag;
import seedu.address.model.exercise.MuscleTag;

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

    private static Exercise[] getSampleExercises() {
        return new Exercise[]{
            new Exercise(new seedu.address.model.exercise.Name("Push Up"), new Description("Did 52 within 60 seconds"),
                    new Date("01-10-2020"), new Calories("100"), getMuscleTagSet("chest"), getExerciseTagSet("gym")),
            new Exercise(new seedu.address.model.exercise.Name("Sit Up"), new Description("Did 50"),
                    new Date("01-10-2020"), new Calories("120"), getMuscleTagSet("chest"), getExerciseTagSet("gym")),
            new Exercise(new seedu.address.model.exercise.Name("2 4km"), new Description("11:30"),
                    new Date("04-10-2020"), new Calories("100"), getMuscleTagSet("chest"), getExerciseTagSet("gym")),
            new Exercise(new seedu.address.model.exercise.Name("Pull Up"),
                    new Description("20 with Added Weight: 5 kg "), new Date("05-10-2020"),
                    new Calories("100"), getMuscleTagSet("chest"), getExerciseTagSet("gym"))
        };
    }

    public static ReadOnlyExerciseBook getSampleExerciseBook() {
        ExerciseBook eb = new ExerciseBook();
        for (Exercise e : getSampleExercises()) {
            eb.addExercise(e);
        }
        return eb;
    }
}
