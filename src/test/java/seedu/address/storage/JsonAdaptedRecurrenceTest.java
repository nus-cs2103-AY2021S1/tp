package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Recurrence;

public class JsonAdaptedRecurrenceTest {
    public static final Recurrence VALID_RECURRENCE = new Recurrence(1,
            "day");
    public static final int INVALID_INTEGER_INPUT = -1;
    public static final ChronoUnit VALID_UNIT_INPUT = ChronoUnit.DAYS;

    @Test
    public void toModelType_validRecurrenceDetails_returnsRecurrence() throws Exception {
        JsonAdaptedRecurrence recurrence = new JsonAdaptedRecurrence(VALID_RECURRENCE);
        assertEquals(VALID_RECURRENCE, recurrence.toModelType());
    }
    @Test
    public void toModelType_invalidRecurrenceDetails_returnsRecurrence() throws Exception {
        JsonAdaptedRecurrence recurrence = new JsonAdaptedRecurrence(INVALID_INTEGER_INPUT, VALID_UNIT_INPUT);
        assertThrows(AssertionError.class , recurrence::toModelType);
    }
}
