package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_LAB;
import static seedu.address.testutil.TypicalPlanus.DEADLINE1;
import static seedu.address.testutil.TypicalPlanus.EVENT_TEST;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeadlineBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new DeadlineBuilder().build();
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(DEADLINE1.isSameTask(DEADLINE1));

        // null -> returns false
        assertFalse(DEADLINE1.isSameTask(null));

        // different dateTime and description -> returns false
        Task editedAlice = new DeadlineBuilder(DEADLINE1).withDeadlineDateTime(VALID_DATETIME_LAB)
                                                 .withDescription(VALID_DESC_LAB)
                                                 .build();
        assertFalse(DEADLINE1.isSameTask(editedAlice));

        // different title -> returns false
        editedAlice = new DeadlineBuilder(DEADLINE1).withTitle(VALID_TITLE_LAB).build();
        assertFalse(DEADLINE1.isSameTask(editedAlice));

        // same title, same dateTime, different attributes -> returns false
        editedAlice = new DeadlineBuilder(DEADLINE1).withDescription(VALID_DESC_LAB)
                .withTag(VALID_TAG_LAB).build();
        assertFalse(DEADLINE1.isSameTask(editedAlice));


        // same title, same dateTime, different attributes -> returns false
        editedAlice = new DeadlineBuilder(DEADLINE1).withTag(VALID_TAG_LAB).build();
        assertFalse(DEADLINE1.isSameTask(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new DeadlineBuilder(DEADLINE1).build();
        assertTrue(DEADLINE1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(DEADLINE1.equals(DEADLINE1));

        // null -> returns false
        assertFalse(DEADLINE1.equals(null));

        // different type -> returns false
        assertFalse(DEADLINE1.equals(5));

        // different task -> returns false
        assertFalse(DEADLINE1.equals(EVENT_TEST));

        // different title -> returns false
        Task editedAlice = new DeadlineBuilder(DEADLINE1).withTitle(VALID_TITLE_LAB).build();
        assertFalse(DEADLINE1.equals(editedAlice));

        // different description -> returns false
        editedAlice = new DeadlineBuilder(DEADLINE1).withDescription(VALID_DESC_LAB).build();
        assertFalse(DEADLINE1.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new DeadlineBuilder(DEADLINE1).withTag(VALID_TAG_LAB).build();
        assertFalse(DEADLINE1.equals(editedAlice));
    }
}
