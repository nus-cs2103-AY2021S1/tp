package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_CLEAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_CLEAN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.LECTURE;
import static seedu.address.testutil.TypicalEvents.MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventBuilder;

public class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event event = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> event.getTags().remove(0));
    }

    @Test
    public void isSameTodo() {
        // same object -> returns true
        assertTrue(LECTURE.isSameEvent(LECTURE));

        // null -> returns false
        assertFalse(LECTURE.isSameEvent(null));

        // different description and start datetime -> returns false
        Event editedLecture = new EventBuilder(LECTURE).withDescription(VALID_DESCRIPTION_CLEAN)
                .withStartDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(LECTURE.isSameEvent(editedLecture));

        // different description -> returns false
        editedLecture = new EventBuilder(LECTURE).withDescription(VALID_DESCRIPTION_CLEAN).build();
        assertFalse(LECTURE.isSameEvent(editedLecture));

        // different start datetime -> returns false
        editedLecture = new EventBuilder(LECTURE).withStartDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(LECTURE.isSameEvent(editedLecture));

        // different end datetime -> returns false
        editedLecture = new EventBuilder(LECTURE).withEndDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(LECTURE.isSameEvent(editedLecture));
    }

    @Test
    public void equals() {
        // exact same object -> returns true
        assertTrue(LECTURE.equals(LECTURE));

        // null -> returns false
        assertFalse(LECTURE.equals(null));

        // different type -> returns false
        assertFalse(LECTURE.equals(5));

        // different todo -> returns false
        assertFalse(LECTURE.equals(MEETING));

        // different description and datetime -> returns false
        Event editedLecture = new EventBuilder(LECTURE).withDescription(VALID_DESCRIPTION_CLEAN)
                .withStartDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(LECTURE.equals(editedLecture));

        // different description -> returns false
        editedLecture = new EventBuilder(LECTURE).withDescription(VALID_DESCRIPTION_CLEAN).build();
        assertFalse(LECTURE.equals(editedLecture));

        // different start date time -> returns false
        editedLecture = new EventBuilder(LECTURE).withStartDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(LECTURE.equals(editedLecture));

        // different end date time -> returns false
        editedLecture = new EventBuilder(LECTURE).withEndDateTime(VALID_DATE_TIME_CLEAN).build();
        assertFalse(LECTURE.equals(editedLecture));
    }

}
