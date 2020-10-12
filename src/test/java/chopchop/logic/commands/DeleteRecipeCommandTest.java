package chopchop.logic.commands;

import chopchop.commons.core.Messages;
import chopchop.commons.core.index.Index;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UserPrefs;
import chopchop.model.ingredient.IngredientBook;
import chopchop.model.recipe.Recipe;
import org.junit.jupiter.api.Test;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.assertRecipeCommandFailure;
import static chopchop.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static chopchop.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static chopchop.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static chopchop.testutil.TypicalRecipes.getTypicalRecipeBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeleteRecipeCommandTest {

    private Model model = new ModelManager(getTypicalRecipeBook(), new IngredientBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Recipe recipeToDelete = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        DeleteRecipeCommand deleteCommand = new DeleteRecipeCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(DeleteRecipeCommand.MESSAGE_DELETE_RECIPE_SUCCESS, recipeToDelete);

        ModelManager expectedModel = new ModelManager(model.getRecipeBook(), new IngredientBook(), new UserPrefs());
        expectedModel.deleteRecipe(recipeToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        DeleteRecipeCommand deleteCommand = new DeleteRecipeCommand(outOfBoundIndex);

        assertRecipeCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Recipe recipeToDelete = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        DeleteRecipeCommand deleteCommand = new DeleteRecipeCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(DeleteRecipeCommand.MESSAGE_DELETE_RECIPE_SUCCESS, recipeToDelete);

        Model expectedModel = new ModelManager(model.getRecipeBook(), new IngredientBook(), new UserPrefs());
        expectedModel.deleteRecipe(recipeToDelete);
        showNoRecipe(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of ingredient book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRecipeBook().getFoodEntryList().size());

        DeleteRecipeCommand deleteCommand = new DeleteRecipeCommand(outOfBoundIndex);

        assertRecipeCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteRecipeCommand deleteFirstCommand = new DeleteRecipeCommand(INDEX_FIRST_RECIPE);
        DeleteRecipeCommand deleteSecondCommand = new DeleteRecipeCommand(INDEX_SECOND_RECIPE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteRecipeCommand deleteFirstCommandCopy = new DeleteRecipeCommand(INDEX_FIRST_RECIPE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoRecipe(Model model) {
        model.updateFilteredRecipeList(p -> false);

        assertTrue(model.getFilteredRecipeList().isEmpty());
    }
}
