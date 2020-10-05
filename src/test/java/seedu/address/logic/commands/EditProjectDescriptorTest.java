package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_A;
import static seedu.address.logic.commands.CommandTestUtil.DESC_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_DESCRIPTION_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_NAME_BOT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_DG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_TAG_HANG;

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
            .withProjectName(VALID_PROJECT_NAME_BOT).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withDeadline(VALID_DEADLINE_BOT).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withEmail(VALID_EMAIL_BOT).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withProjectDescription(
            VALID_PROJECT_DESCRIPTION_BOT).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withTags(VALID_PROJECT_TAG_HANG).build();
        assertFalse(DESC_A.equals(editedAmy));

        // different tasks -> return false
        editedAmy = new EditProjectDescriptorBuilder(DESC_A).withTasks(VALID_PROJECT_TAG_DG).build();
        assertFalse(DESC_A.equals(editedAmy));
    }
}
