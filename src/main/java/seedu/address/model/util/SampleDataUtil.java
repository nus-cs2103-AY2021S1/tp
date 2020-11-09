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
            new Lesson(new Name("MA1101R"), getTagSet("easy")),
            new Lesson(new Name("MA1102R"), getTagSet("chill", "webcast")),
            new Lesson(new Name("CS2103T"), getTagSet("project")),
            new Lesson(new Name("CS2100"), getTagSet("priority")),
            new Lesson(new Name("CS3243"), getTagSet("fun")),
            new Lesson(new Name("CS3244"), getTagSet("hard"))
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

        for (Exercise sampleExercise : getSampleExercise()) {
            sampleFitNus.addExercise(sampleExercise);
        }

        for (Routine sampleRoutine : getSampleRoutines()) {
            sampleFitNus.addRoutine(sampleRoutine);
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
