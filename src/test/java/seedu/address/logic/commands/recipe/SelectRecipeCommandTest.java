package seedu.address.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_RECIPE;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class SelectRecipeCommandTest {

    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Recipe recipeToSelect = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        SelectRecipeCommand selectRecipeCommand = new SelectRecipeCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(SelectRecipeCommand.MESSAGE_SELECT_RECIPE_SUCCESS,
                selectRecipeCommand.separateRecipeString(recipeToSelect));

        ModelManager expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());

        assertCommandSuccess(selectRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        SelectRecipeCommand selectRecipeCommand = new SelectRecipeCommand(outOfBoundIndex);

        assertCommandFailure(selectRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of recipe collection list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWishfulShrinking().getRecipeList().size());

        SelectRecipeCommand selectRecipeCommand = new SelectRecipeCommand(outOfBoundIndex);

        assertCommandFailure(selectRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectRecipeCommand selectFirstCommand = new SelectRecipeCommand(INDEX_FIRST_RECIPE);
        SelectRecipeCommand selectSecondCommand = new SelectRecipeCommand(INDEX_SECOND_RECIPE);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectRecipeCommand selectFirstCommandCopy = new SelectRecipeCommand(INDEX_FIRST_RECIPE);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different recipe -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoRecipe(Model model) {
        model.updateFilteredRecipeList(p -> false);

        assertTrue(model.getFilteredRecipeList().isEmpty());
    }
}
