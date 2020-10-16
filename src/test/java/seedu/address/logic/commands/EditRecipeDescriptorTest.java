package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.DESC_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.testutil.EditRecipeDescriptorBuilder;

public class EditRecipeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditRecipeDescriptor descriptorWithSameValues = new EditRecipeDescriptor(DESC_NOODLE);
        assertTrue(DESC_NOODLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_NOODLE.equals(DESC_NOODLE));

        // null -> returns false
        assertFalse(DESC_NOODLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_NOODLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_NOODLE.equals(DESC_MARGARITAS));

        // different name -> returns false
        EditRecipeDescriptor editedNoodle = new EditRecipeDescriptorBuilder(DESC_NOODLE)
                        .withName(VALID_NAME_MARGARITAS)
                        .build();
        assertFalse(DESC_NOODLE.equals(editedNoodle));

        // different ingredients -> returns false
        editedNoodle =
                new EditRecipeDescriptorBuilder(DESC_NOODLE)
                        .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
                        .build();
        assertFalse(DESC_NOODLE.equals(editedNoodle));

        // different tags -> returns false
        // editedNoodle = new EditRecipeDescriptorBuilder(DESC_NOODLE).build();
        // assertFalse(DESC_NOODLE.equals(editedNoodle));
    }
}
