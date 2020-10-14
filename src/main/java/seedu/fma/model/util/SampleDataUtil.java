package seedu.fma.model.util;

import seedu.fma.model.LogBook;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;

/**
 * Contains utility methods for populating {@code LogBook} with sample data.
 */
public class SampleDataUtil {

    public static Log[] getSampleLogs() {
        return new Log[] {
            new Log(Exercise.find(new Name("Sit ups")),
                    new Rep("30"), new Comment("My abs hurt :(")),
            new Log(Exercise.find(new Name("Pull ups")),
                    new Rep("10"), new Comment("-"))
        };
    }

    public static Exercise[] getSampleExercises() {
        return new Exercise[] {
            new Exercise(new Name("Flying kicks"), 15),
            new Exercise(new Name("Sit ups"), 30),
            new Exercise(new Name("Pull ups"), 20),
            new Exercise(new Name("Jumping jacks"), 15),
            new Exercise(new Name("Run 100m"), 30),
            new Exercise(new Name("Stretch"), 20),
        };
    }

    public static ReadOnlyLogBook getSampleLogBook() {
        LogBook sampleLogbook = new LogBook();
        for (Log sampleLog : getSampleLogs()) {
            sampleLogbook.addLog(sampleLog);
        }
        return sampleLogbook;
    }
}
