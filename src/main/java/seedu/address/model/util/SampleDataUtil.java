package seedu.address.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.exercise.Exercise;
import seedu.address.model.lesson.Lesson;
import seedu.address.model.routine.Routine;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FitNus} with sample data.
 */
public class SampleDataUtil {
    public static Lesson[] getSampleLessons() {
        return new Lesson[] {
            new Lesson(new Name("GER1000"), getTagSet("easy")),
            new Lesson(new Name("GEQ2000"), getTagSet("chill", "webcasted")),
            new Lesson(new Name("GEH3000"), getTagSet("hard")),
            new Lesson(new Name("GET4000"), getTagSet("priority")),
            new Lesson(new Name("GES5000"), getTagSet("fun")),
            new Lesson(new Name("GEX6000"), getTagSet("hard"))
        };
    }

    public static Routine[] getSampleRoutines() {
        return new Routine[] {
                new Routine(new Name("Leg Day")),
                new Routine(new Name("Upper Body Workout")),
                new Routine(new Name("Arm Routine"))
        };
    }

    public static Exercise[] getSampleExercise() {
        return new Exercise[] {
                new Exercise(new Name("Bench Press"), new HashSet<>()),
                new Exercise(new Name("Shoulder Press"), new HashSet<>()),
                new Exercise(new Name("Squats"), new HashSet<>())
        };
    }

    public static ReadOnlyFitNus getSampleFitNus() {
        FitNus sampleFitNus = new FitNus();
        for (Lesson sampleLesson : getSampleLessons()) {
            sampleFitNus.addLesson(sampleLesson);
        }
        return sampleFitNus;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }
}
