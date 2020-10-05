package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.address.testutil.EditProjectDescriptorBuilder;

public class EditProjectDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditProjectDescriptor descriptorWithSameValues = new EditProjectDescriptor(DESC_A);
        assertTrue(DESC_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_A.equals(DESC_A));

        // null -> returns false
        assertFalse(DESC_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_A.equals(DESC_B));

        // different name -> returns false
        EditProjectDescriptor editedAmy = new EditProjectDescriptorBuilder(DESC_A)
            .withProjectName(VALID_NAME_BOB).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withDeadline(VALID_DEADLINE_B).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withProjectDescription(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different tasks -> return false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withTasks(VALID_TASK_DG).build();
        assertFalse(DESC_A.equals(editedAmy));
    }
}
