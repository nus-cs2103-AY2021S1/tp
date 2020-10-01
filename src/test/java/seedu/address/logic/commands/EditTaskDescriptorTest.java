package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_COOK;
import static seedu.address.logic.commands.CommandTestUtil.DESC_WASH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_COOK);
        assertTrue(DESC_COOK.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_COOK.equals(DESC_COOK));

        // null -> returns false
        assertFalse(DESC_COOK.equals(null));

        // different types -> returns false
        assertFalse(DESC_COOK.equals(5));

        // different values -> returns false
        assertFalse(DESC_COOK.equals(DESC_WASH));

        // different title -> returns false
        EditTaskDescriptor editedCook = new EditTaskDescriptorBuilder(DESC_COOK).withTitle(VALID_TITLE_BOB).build();
        assertFalse(DESC_COOK.equals(editedCook));

        // different phone -> returns false
        editedCook = new EditTaskDescriptorBuilder(DESC_COOK).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_COOK.equals(editedCook));

        // different email -> returns false
        editedCook = new EditTaskDescriptorBuilder(DESC_COOK).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_COOK.equals(editedCook));

        // different address -> returns false
        editedCook = new EditTaskDescriptorBuilder(DESC_COOK).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_COOK.equals(editedCook));

        // different tags -> returns false
        editedCook = new EditTaskDescriptorBuilder(DESC_COOK).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_COOK.equals(editedCook));
    }
}
