package seedu.fma.storage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fma.model.util.SampleDataUtil.getSampleExercises;
import static seedu.fma.storage.JsonAdaptedLog.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.fma.testutil.Assert.assertThrows;
import static seedu.fma.testutil.TypicalLogs.LOG_A;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.fma.commons.exceptions.IllegalValueException;
import seedu.fma.model.LogBook;
import seedu.fma.model.exercise.Exercise;
import seedu.fma.model.log.Comment;
import seedu.fma.model.log.Rep;

public class JsonAdaptedLogTest {
    private static final String INVALID_EXERCISE = "Sitting in front of my laptop";
    private static final String INVALID_REPS = "221b";

    private static final String VALID_EXERCISE = LOG_A.getExercise().getName().toString();
    private static final String VALID_DATETIME = LOG_A.getDateTime().toString();
    private static final String VALID_REPS = LOG_A.getReps().toString();
    private static final String VALID_COMMENT = LOG_A.getComment().toString();

    private final LogBook logBook = new LogBook();

    @BeforeEach
    void setup() {
        logBook.setExercises(Arrays.asList(getSampleExercises()));
    }

    @Test
    public void toModelType_validLogDetails_returnsLog() throws Exception {
        JsonAdaptedLog log = new JsonAdaptedLog(LOG_A);
        assertEquals(LOG_A, log.toModelType(logBook));
    }

    @Test
    public void toModelType_invalidExercise_throwsIllegalValueException() {
        JsonAdaptedLog log =
                new JsonAdaptedLog(INVALID_EXERCISE, VALID_DATETIME, VALID_REPS, VALID_COMMENT);
        String expectedMessage = Exercise.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> log.toModelType(logBook));
    }

    @Test
    public void toModelType_nullExercise_throwsIllegalValueException() {
        JsonAdaptedLog log = new JsonAdaptedLog(null, VALID_DATETIME, VALID_REPS, VALID_COMMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Exercise.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> log.toModelType(logBook));
    }

    @Test
    public void toModelType_invalidReps_throwsIllegalValueException() {
        JsonAdaptedLog log =
                new JsonAdaptedLog(VALID_EXERCISE, VALID_DATETIME, INVALID_REPS, VALID_COMMENT);
        String expectedMessage = Rep.NUMBER_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, () -> log.toModelType(logBook));
    }

    @Test
    public void toModelType_nullReps_throwsIllegalValueException() {
        JsonAdaptedLog log = new JsonAdaptedLog(VALID_EXERCISE, VALID_DATETIME, null, VALID_COMMENT);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Rep.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> log.toModelType(logBook));
    }

    @Test
    public void toModelType_nullComment_throwsIllegalValueException() {
        JsonAdaptedLog log = new JsonAdaptedLog(VALID_EXERCISE, VALID_DATETIME, VALID_REPS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Comment.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, () -> log.toModelType(logBook));
    }
}
