package seedu.studybananas.storage.schedulestorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.studybananas.storage.schedulestorage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.studybananas.testutil.Assert.assertThrows;
import static seedu.studybananas.testutil.SampleTasks.CS2103T_WEEK8_QUIZ;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.studybananas.commons.exceptions.IllegalValueException;
import seedu.studybananas.model.task.Title;

public class JsonAdaptedTaskTest {
    private static final Optional<String> VALID_DESCRIPTION =
            Optional.ofNullable((CS2103T_WEEK8_QUIZ.getDescription().get().toString()));
    private static final Optional<String> VALID_DATE_TIME =
            CS2103T_WEEK8_QUIZ.getDateTime().map(dateTime -> dateTime.toString());
    private static final Optional<Integer> VALID_DURATION =
            CS2103T_WEEK8_QUIZ.getDuration().map(dur -> dur.duration);

    @Test
    public void toModelType_validTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask task = new JsonAdaptedTask(CS2103T_WEEK8_QUIZ);
        assertEquals(CS2103T_WEEK8_QUIZ, task.toModelType());
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedTask task = new JsonAdaptedTask(null, VALID_DESCRIPTION, VALID_DATE_TIME, VALID_DURATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, task::toModelType);
    }

}
