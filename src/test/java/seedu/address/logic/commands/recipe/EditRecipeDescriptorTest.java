package seedu.address.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTOR_NOODLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_MARGARITAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_MARGARITAS;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.recipe.EditRecipeCommand.EditRecipeDescriptor;
import seedu.address.testutil.EditRecipeDescriptorBuilder;

public class EditRecipeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditRecipeDescriptor descriptorWithSameValues = new EditRecipeDescriptor(VALID_DESCRIPTOR_NOODLE);
        assertTrue(VALID_DESCRIPTOR_NOODLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(VALID_DESCRIPTOR_NOODLE.equals(VALID_DESCRIPTOR_NOODLE));

        // null -> returns false
        assertFalse(VALID_DESCRIPTOR_NOODLE.equals(null));

        // different types -> returns false
        assertFalse(VALID_DESCRIPTOR_NOODLE.equals(5));

        // different values -> returns false
        assertFalse(VALID_DESCRIPTOR_NOODLE.equals(VALID_DESCRIPTOR_MARGARITAS));

        // different name -> returns false
        EditRecipeDescriptor editedNoodle = new EditRecipeDescriptorBuilder(VALID_DESCRIPTOR_NOODLE)
                        .withName(VALID_NAME_MARGARITAS)
                        .build();
        assertFalse(VALID_DESCRIPTOR_NOODLE.equals(editedNoodle));

        // different ingredients -> returns false
        editedNoodle =
                new EditRecipeDescriptorBuilder(VALID_DESCRIPTOR_NOODLE)
                        .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
                        .build();
        assertFalse(VALID_DESCRIPTOR_NOODLE.equals(editedNoodle));

        // different tags -> returns false
        // editedNoodle = new EditRecipeDescriptorBuilder(DESC_NOODLE).build();
        // assertFalse(DESC_NOODLE.equals(editedNoodle));
    }
}
