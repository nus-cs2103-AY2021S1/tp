package seedu.fma.model.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.fma.model.LogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;

class SampleDataUtilTest {

    @Test
    void testGetSampleLogs() {
        Log[] sampleLogs = {
            new Log(Exercise.find(new Name("Sit ups")), new Rep("30"), new Comment("My abs hurt :(")),
            new Log(Exercise.find(new Name("Pull ups")), new Rep("10"), new Comment("-"))
        };

        for (int i = 0; i < SampleDataUtil.getSampleLogs().length; i++) {
            // TODO: Need to check date time for logs
            assertTrue(SampleDataUtil.getSampleLogs()[i].getExercise().equals(sampleLogs[i].getExercise()));
        }
    }

    @Test
    void testGetSampleExercises() {
        Exercise[] sampleExercises = {
            new Exercise(new Name("Flying kicks"), 15),
            new Exercise(new Name("Sit ups"), 30),
            new Exercise(new Name("Pull ups"), 20),
            new Exercise(new Name("Jumping jacks"), 15),
            new Exercise(new Name("Run 100m"), 30),
            new Exercise(new Name("Stretch"), 20),
        };
        for (int i = 0; i < SampleDataUtil.getSampleExercises().length; i++) {
            assertTrue(SampleDataUtil.getSampleExercises()[i].equals(sampleExercises[i]));
        }
    }

    @Test
    void testGetSampleLogBook() {
        LogBook newLogBook = new LogBook();
        for (int i = 0; i < newLogBook.getLogList().size(); i++) {
            assertTrue(newLogBook.getLogList().contains(SampleDataUtil.getSampleLogs()[i]));
        }
    }
}
