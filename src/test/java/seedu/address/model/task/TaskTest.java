package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.HOMEWORK;
import static seedu.address.testutil.TypicalTasks.WASH;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(HOMEWORK.isSameTask(HOMEWORK));

        // null -> returns false
        assertFalse(HOMEWORK.isSameTask(null));

        // different phone and email -> returns false
        Task editedAlice = new TaskBuilder(HOMEWORK).withPhone(VALID_PHONE_WASH).withEmail(VALID_EMAIL_WASH).build();
        assertFalse(HOMEWORK.isSameTask(editedAlice));

        // different title -> returns false
        editedAlice = new TaskBuilder(HOMEWORK).withTitle(VALID_TITLE_WASH).build();
        assertFalse(HOMEWORK.isSameTask(editedAlice));

        // same title, same phone, different attributes -> returns true
        editedAlice = new TaskBuilder(HOMEWORK).withEmail(VALID_EMAIL_WASH).withAddress(VALID_ADDRESS_WASH)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(HOMEWORK.isSameTask(editedAlice));

        // same title, same email, different attributes -> returns true
        editedAlice = new TaskBuilder(HOMEWORK).withPhone(VALID_PHONE_WASH).withAddress(VALID_ADDRESS_WASH)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(HOMEWORK.isSameTask(editedAlice));

        // same title, same phone, same email, different attributes -> returns true
        editedAlice = new TaskBuilder(HOMEWORK).withAddress(VALID_ADDRESS_WASH).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(HOMEWORK.isSameTask(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task aliceCopy = new TaskBuilder(HOMEWORK).build();
        assertTrue(HOMEWORK.equals(aliceCopy));

        // same object -> returns true
        assertTrue(HOMEWORK.equals(HOMEWORK));

        // null -> returns false
        assertFalse(HOMEWORK.equals(null));

        // different type -> returns false
        assertFalse(HOMEWORK.equals(5));

        // different task -> returns false
        assertFalse(HOMEWORK.equals(WASH));

        // different title -> returns false
        Task editedAlice = new TaskBuilder(HOMEWORK).withTitle(VALID_TITLE_WASH).build();
        assertFalse(HOMEWORK.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new TaskBuilder(HOMEWORK).withPhone(VALID_PHONE_WASH).build();
        assertFalse(HOMEWORK.equals(editedAlice));

        // different email -> returns false
        editedAlice = new TaskBuilder(HOMEWORK).withEmail(VALID_EMAIL_WASH).build();
        assertFalse(HOMEWORK.equals(editedAlice));

        // different address -> returns false
        editedAlice = new TaskBuilder(HOMEWORK).withAddress(VALID_ADDRESS_WASH).build();
        assertFalse(HOMEWORK.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new TaskBuilder(HOMEWORK).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(HOMEWORK.equals(editedAlice));
    }
}
