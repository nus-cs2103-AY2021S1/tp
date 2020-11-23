package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESC_1;
import static seedu.tr4cker.logic.commands.CommandTestUtil.DESC_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DEADLINE_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_DESCRIPTION_2;
import static seedu.tr4cker.logic.commands.CommandTestUtil.VALID_NAME_2;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.tr4cker.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditCommand.EditTaskDescriptor(DESC_1);
        assertTrue(DESC_1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_1.equals(DESC_1));

        // null -> returns false
        assertFalse(DESC_1.equals(null));

        // different types -> returns false
        assertFalse(DESC_1.equals(5));

        // different values -> returns false
        assertFalse(DESC_1.equals(DESC_2));

        // different name -> returns false
        EditCommand.EditTaskDescriptor editedAmy =
                new EditTaskDescriptorBuilder(DESC_1).withName(VALID_NAME_2).build();
        assertFalse(DESC_1.equals(editedAmy));

        // different deadline -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_1).withDeadline(VALID_DEADLINE_2).build();
        assertFalse(DESC_1.equals(editedAmy));

        // different description -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_1).withTaskDescription(VALID_DESCRIPTION_2).build();
        assertFalse(DESC_1.equals(editedAmy));
    }
}
