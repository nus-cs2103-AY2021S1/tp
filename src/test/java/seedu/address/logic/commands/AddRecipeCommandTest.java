package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.recipe.RecipePrecursor;
import seedu.address.testutil.RecipePrecursorBuilder;

public class AddRecipeCommandTest {
    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRecipeCommand(null));
    }

    /**
     * Tests for detecting item not found in recipe.
     */
    @Test
    public void execute_itemNotFoundRecipe_throwsItemNotFoundException() {
        RecipePrecursor validRecipePrecursor = new RecipePrecursorBuilder().build();

        AddRecipeCommand addRecipeCommand = new AddRecipeCommand(validRecipePrecursor);
        ModelStub modelStub = new ModelStub();

        assertThrows(CommandException.class,
                AddRecipeCommand.MESSAGE_ITEM_NOT_FOUND, () -> addRecipeCommand.execute(modelStub));
    }

    /**
     * Tests for equivalency.
     */
    @Test
    public void equals() {
        RecipePrecursor applePie = new RecipePrecursorBuilder().withDescription("Apple Pie").build();
        RecipePrecursor bananaPie = new RecipePrecursorBuilder().withDescription("Banana Pie").build();
        AddRecipeCommand addApplePieCommand = new AddRecipeCommand(applePie);
        AddRecipeCommand addBananaPieCommand = new AddRecipeCommand(bananaPie);

        // same object -> returns true
        assertTrue(addApplePieCommand.equals(addApplePieCommand));

        // same values -> returns true
        AddRecipeCommand addAppleCommandCopy = new AddRecipeCommand(applePie);
        assertTrue(addApplePieCommand.equals(addAppleCommandCopy));

        // different types -> returns false
        assertFalse(addApplePieCommand.equals(1));

        // null -> returns false
        assertFalse(addApplePieCommand.equals(null));

        // different item -> returns false
        assertFalse(addApplePieCommand.equals(addBananaPieCommand));
    }

}
