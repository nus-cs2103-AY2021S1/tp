package seedu.fma.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.model.LogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Log;
import seedu.fma.model.log.Rep;

class SampleDataUtilTest {

    private final LogBook logBook = new LogBook();

    @BeforeEach
    void setup() {
        logBook.setExercises(Arrays.asList(getSampleExercises()));
    }

    @Test
    void testGetSampleLogs() {
        Log[] sampleLogs = {
            new Log(logBook.getExercise(new Name("Sit ups")), new Rep("30"), new Comment("My abs hurt :(")),
            new Log(logBook.getExercise(new Name("Pull ups")), new Rep("10"), new Comment("-"))
        };

        for (int i = 0; i < SampleDataUtil.getSampleLogs(logBook).length; i++) {
            assertEquals(sampleLogs[i].getExercise(), SampleDataUtil.getSampleLogs(logBook)[i].getExercise());
        }
    }

    @Test
    void testGetSampleExercises() {
        Exercise[] sampleExercises = {
            new Exercise(new Name("Flying kicks"), new Calories(15)),
            new Exercise(new Name("Sit ups"), new Calories(30)),
            new Exercise(new Name("Pull ups"), new Calories(20)),
            new Exercise(new Name("Jumping jacks"), new Calories(15)),
            new Exercise(new Name("Run 100m"), new Calories(30)),
            new Exercise(new Name("Stretch"), new Calories(20)),
        };
        for (int i = 0; i < getSampleExercises().length; i++) {
            assertEquals(sampleExercises[i], getSampleExercises()[i]);
        }
    }

    @Test
    void testGetSampleLogBook() {
        LogBook newLogBook = new LogBook();
        for (int i = 0; i < newLogBook.getLogList().size(); i++) {
            assertTrue(newLogBook.getLogList().contains(SampleDataUtil.getSampleLogs(newLogBook)[i]));
        }
    }
}
