package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_IRAS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MAYBANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VACANCY_MAYBANK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditJobCommand.EditJobDescriptor;
import seedu.address.testutil.EditJobDescriptorBuilder;

public class EditJobDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditJobDescriptor descriptorWithSameValues = new EditJobDescriptor(DESC_IRAS);
        assertTrue(DESC_IRAS.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_IRAS.equals(DESC_IRAS));

        // null -> returns false
        assertFalse(DESC_IRAS.equals(null));

        // different types -> returns false
        assertFalse(DESC_IRAS.equals(5));

        // different values -> returns false
        assertFalse(DESC_IRAS.equals(DESC_MAYBANK));

        // different name -> returns false
        EditJobDescriptor editedIras = new EditJobDescriptorBuilder(DESC_IRAS)
                .withJobTitle(VALID_JOB_TITLE_MAYBANK).build();
        assertFalse(DESC_IRAS.equals(editedIras));

        // different phone -> returns false
        editedIras = new EditJobDescriptorBuilder(DESC_IRAS).withPhone(VALID_PHONE_MAYBANK).build();
        assertFalse(DESC_IRAS.equals(editedIras));

        // different email -> returns false
        editedIras = new EditJobDescriptorBuilder(DESC_IRAS).withEmail(VALID_EMAIL_MAYBANK).build();
        assertFalse(DESC_IRAS.equals(editedIras));

        // different address -> returns false
        editedIras = new EditJobDescriptorBuilder(DESC_IRAS).withAddress(VALID_ADDRESS_MAYBANK).build();
        assertFalse(DESC_IRAS.equals(editedIras));

        // different priority -> returns false
        editedIras = new EditJobDescriptorBuilder(DESC_IRAS).withPriority(VALID_PRIORITY_MAYBANK).build();
        assertFalse(DESC_IRAS.equals(editedIras));

        // different vacancy -> returns false
        editedIras = new EditJobDescriptorBuilder(DESC_IRAS).withVacancy(VALID_VACANCY_MAYBANK).build();
        assertFalse(DESC_IRAS.equals(editedIras));

        // different tags -> returns false
        editedIras = new EditJobDescriptorBuilder(DESC_IRAS).withTags(VALID_TAG_MAYBANK).build();
        assertFalse(DESC_IRAS.equals(editedIras));
    }
}
