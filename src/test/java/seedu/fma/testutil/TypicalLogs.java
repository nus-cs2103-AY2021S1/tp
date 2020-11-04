package seedu.fma.testutil;

import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;
import static seedu.fma.testutil.TypicalExercises.getTypicalExercises;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.fma.model.LogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Log;

/**
 * A utility class containing a list of {@code Log} objects to be used in tests.
 */
public class TypicalLogs {
    public static final Exercise[] SAMPLE_EXERCISES = getSampleExercises();

    public static final Log LOG_A = new LogBuilder()
            .withExercise(new ExerciseBuilder(SAMPLE_EXERCISES[0]).build())
            .withDateTime(LocalDateTime.of(2020, 1, 1, 1, 1))
            .withReps(1)
            .withComment("This")
            .build();
    public static final Log LOG_B = new LogBuilder()
            .withExercise(new ExerciseBuilder(SAMPLE_EXERCISES[1]).build())
            .withDateTime(LocalDateTime.of(2020, 2, 2, 2, 2))
            .withReps(2)
            .withComment("is")
            .build();
    public static final Log LOG_C = new LogBuilder()
            .withExercise(new ExerciseBuilder(SAMPLE_EXERCISES[2]).build())
            .withDateTime(LocalDateTime.of(2020, 3, 3, 3, 3))
            .withReps(34)
            .withComment("a")
            .build();
    public static final Log LOG_D = new LogBuilder()
            .withExercise(new ExerciseBuilder(SAMPLE_EXERCISES[3]).build())
            .withDateTime(LocalDateTime.of(2020, 4, 4, 4, 4))
            .withReps(56)
            .withComment("typical")
            .build();
    public static final Log LOG_E = new LogBuilder()
            .withExercise(new ExerciseBuilder(SAMPLE_EXERCISES[4]).build())
            .withDateTime(LocalDateTime.of(2020, 5, 5, 5, 5))
            .withReps(67)
            .withComment("log")
            .build();
    public static final Log LOG_F = new LogBuilder()
            .withExercise(new ExerciseBuilder(SAMPLE_EXERCISES[5]).build())
            .withDateTime(LocalDateTime.of(2020, 6, 6, 6, 6))
            .withReps(89)
            .withComment("book!")
            .build();

    private TypicalLogs() {} // prevents instantiation

    /**
     * Returns an {@code LogBook} with all the typical logs.
     */
    public static LogBook getTypicalLogBook() {
        LogBook ab = new LogBook();
        for (Exercise exercise : getTypicalExercises()) {
            ab.addExercise(exercise);
        }
        for (Log log : getTypicalLogs()) {
            ab.addLog(log);
        }
        return ab;
    }

    public static List<Log> getTypicalLogs() {
        return new ArrayList<>(Arrays.asList(LOG_A, LOG_B, LOG_C, LOG_D));
    }
}
