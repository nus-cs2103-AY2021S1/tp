package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.DESC_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.DESC_FOOD;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_AMOUNT_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DATE_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BUS;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;

import org.junit.jupiter.api.Test;

import seedu.expense.testutil.EditExpenseDescriptorBuilder;

public class EditExpenseDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditExpenseDescriptor descriptorWithSameValues = new EditCommand.EditExpenseDescriptor(DESC_FOOD);
        assertTrue(DESC_FOOD.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_FOOD.equals(DESC_FOOD));

        // null -> returns false
        assertFalse(DESC_FOOD.equals(null));

        // different types -> returns false
        assertFalse(DESC_FOOD.equals(5));

        // different values -> returns false
        assertFalse(DESC_FOOD.equals(DESC_BUS));

        // different name -> returns false
        EditCommand.EditExpenseDescriptor editedAmy = new EditExpenseDescriptorBuilder(DESC_FOOD)
                .withDescription(VALID_DESCRIPTION_BUS).build();
        assertFalse(DESC_FOOD.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditExpenseDescriptorBuilder(DESC_FOOD).withAmount(VALID_AMOUNT_BUS).build();
        assertFalse(DESC_FOOD.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditExpenseDescriptorBuilder(DESC_FOOD).withDate(VALID_DATE_BUS).build();
        assertFalse(DESC_FOOD.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditExpenseDescriptorBuilder(DESC_FOOD).withTags(VALID_TAG_TRANSPORT).build();
        assertFalse(DESC_FOOD.equals(editedAmy));
    }
}
