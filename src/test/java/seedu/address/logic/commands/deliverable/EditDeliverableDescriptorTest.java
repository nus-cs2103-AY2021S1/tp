package seedu.address.logic.commands.deliverable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.DESC_DELIVERABLE_A;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.DESC_DELIVERABLE_B;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_CONTACTS_B;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_DEADLINE_B;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_MILESTONE_B;
import static seedu.address.logic.commands.deliverable.CommandTestUtil.VALID_TITLE_B;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deliverable.EditCommand.EditDeliverableDescriptor;
import seedu.address.testutil.EditDeliverableDescriptorBuilder;

public class EditDeliverableDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDeliverableDescriptor descriptorWithSameValues = new EditDeliverableDescriptor(DESC_DELIVERABLE_A);
        assertTrue(DESC_DELIVERABLE_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_DELIVERABLE_A.equals(DESC_DELIVERABLE_A));

        // null -> returns false
        assertFalse(DESC_DELIVERABLE_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_DELIVERABLE_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_DELIVERABLE_A.equals(DESC_DELIVERABLE_B));

        // different name -> returns false
        EditDeliverableDescriptor editedA = new EditDeliverableDescriptorBuilder(
                DESC_DELIVERABLE_A).withTitle(VALID_TITLE_B).build();
        assertFalse(DESC_DELIVERABLE_A.equals(editedA));

        // different milestone -> returns false
        editedA = new EditDeliverableDescriptorBuilder(DESC_DELIVERABLE_A).withMilestone(VALID_MILESTONE_B).build();
        assertFalse(DESC_DELIVERABLE_A.equals(editedA));

        // different deadline -> returns false
        editedA = new EditDeliverableDescriptorBuilder(DESC_DELIVERABLE_A).withDeadline(VALID_DEADLINE_B).build();
        assertFalse(DESC_DELIVERABLE_A.equals(editedA));

        // different description -> returns false
        editedA = new EditDeliverableDescriptorBuilder(DESC_DELIVERABLE_A).withDescription(VALID_DESCRIPTION_B).build();
        assertFalse(DESC_DELIVERABLE_A.equals(editedA));

        // different contacts -> returns false
        editedA = new EditDeliverableDescriptorBuilder(DESC_DELIVERABLE_A).withContacts(VALID_CONTACTS_B).build();
        assertFalse(DESC_DELIVERABLE_A.equals(editedA));
    }
}
