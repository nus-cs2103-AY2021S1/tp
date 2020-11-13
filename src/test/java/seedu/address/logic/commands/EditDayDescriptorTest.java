package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditDayDescriptor;
import seedu.address.testutil.EditDayDescriptorBuilder;

public class EditDayDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDayDescriptor descriptorWithSameValues = new EditDayDescriptor(DESC_1);
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
        EditDayDescriptor editedAmy = new EditDayDescriptorBuilder(DESC_1).withDate(VALID_DATE_2).build();
        assertFalse(DESC_1.equals(editedAmy));

        // different weight -> returns false
        editedAmy = new EditDayDescriptorBuilder(DESC_1).withWeight(VALID_WEIGHT_2).build();
        assertFalse(DESC_1.equals(editedAmy));
    }
}
