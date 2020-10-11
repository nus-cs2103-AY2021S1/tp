package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertInventoryCommandFailure;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;
import static seedu.address.testutil.TypicalRecipes.APPLE_PIE;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteRecipeCommandTest {

    private final Model model = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
            getTypicalRecipeList(), new UserPrefs());

    /**
     * Tests for successful deletion of a recipe found in the recipe list.
     */
    @Test
    public void execute_success() {
        DeleteRecipeCommand drc = new DeleteRecipeCommand("Apple", Index.fromOneBased(1));
        Model expectedModel = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
                getTypicalRecipeList(), new UserPrefs());
        expectedModel.deleteRecipe(APPLE_PIE);
        String expectedMessage = String.format(DeleteRecipeCommand.MESSAGE_SUCCESS, APPLE_PIE);
        assertCommandSuccess(drc, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for failure of deletion of recipe when the product name cannot be found in the recipe list.
     */
    @Test
    public void execute_recipeNotFound() {
        DeleteRecipeCommand drc = new DeleteRecipeCommand("Cake", Index.fromOneBased(1));
        // Cake does not exist in recipe list
        assertInventoryCommandFailure(drc, model, DeleteRecipeCommand.MESSAGE_RECIPE_NOT_FOUND);
    }

    /**
     * Tests for failure of deletion of recipe when produce name can be found but index is out of range.
     */
    @Test
    public void execute_indexOutOfRange() throws CommandException {
        DeleteRecipeCommand drc = new DeleteRecipeCommand("Apple", Index.fromOneBased(2));
        // index out of range since only 1 recipe for apple
        drc.execute(model);
        assertInventoryCommandFailure(drc, model, DeleteRecipeCommand.MESSAGE_INDEX_NOT_FOUND);
    }

    /**
     * Tests for equivalency.
     */
    @Test
    public void equals() {
        DeleteRecipeCommand deleteAppleCommand = new DeleteRecipeCommand("Apple", Index.fromOneBased(1));
        DeleteRecipeCommand deleteApple2Command = new DeleteRecipeCommand("Apple", Index.fromOneBased(2));
        DeleteRecipeCommand deleteBananaCommand = new DeleteRecipeCommand("Banana", Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(deleteAppleCommand.equals(deleteAppleCommand));

        // same values -> returns true
        DeleteRecipeCommand deleteApple1Command = new DeleteRecipeCommand("Apple", Index.fromOneBased(1));
        assertTrue(deleteAppleCommand.equals(deleteApple1Command));

        // different types -> returns false
        assertFalse(deleteAppleCommand.equals(1));

        // null -> returns false
        assertFalse(deleteAppleCommand.equals(null));

        // different product name -> returns false
        assertFalse(deleteAppleCommand.equals(deleteBananaCommand));

        // different index -> returns false
        assertFalse(deleteAppleCommand.equals(deleteApple2Command));
    }
}
