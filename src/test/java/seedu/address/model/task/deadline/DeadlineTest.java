package seedu.address.model.task.deadline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;
import static seedu.address.testutil.TypicalTasks.DEADLINE1;
import static seedu.address.testutil.TypicalTasks.EVENT_TEST;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;
import seedu.address.testutil.DeadlineBuilder;

public class DeadlineTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(DEADLINE1.isSameTask(DEADLINE1));

        // null -> returns false
        assertFalse(DEADLINE1.isSameTask(null));

        // different title -> returns false
        Deadline editedAlice = new DeadlineBuilder(DEADLINE1).withTitle(VALID_TITLE_BOB).build();
        assertFalse(DEADLINE1.isSameTask(editedAlice));

        // same title, same dateTime, same type, different attributes -> returns true
        editedAlice = new DeadlineBuilder(DEADLINE1).withTag(VALID_TAG_HUSBAND).build();
        assertTrue(DEADLINE1.isSameTask(editedAlice));
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
        Task editedAlice = new DeadlineBuilder(DEADLINE1).withTitle(VALID_TITLE_BOB).build();
        assertFalse(DEADLINE1.equals(editedAlice));

        // different description -> returns false
        editedAlice = new DeadlineBuilder(DEADLINE1).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(DEADLINE1.equals(editedAlice));

        // different tag -> returns false
        editedAlice = new DeadlineBuilder(DEADLINE1).withTag(VALID_TAG_HUSBAND).build();
        assertFalse(DEADLINE1.equals(editedAlice));
    }
}
