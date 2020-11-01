package seedu.address.logic.commands.ingredient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showIngredientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_INGREDIENT;
import static seedu.address.testutil.TypicalIngredients.getTypicalWishfulShrinking;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.Ingredient;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteIngredientCommandTest {

    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Ingredient ingredientToDelete = model.getFilteredIngredientList().get(INDEX_FIRST_INGREDIENT.getZeroBased());
        DeleteIngredientCommand deleteIngredientCommand = new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT);

        String expectedMessage = String.format(DeleteIngredientCommand.MESSAGE_DELETE_INGREDIENT_SUCCESS,
                ingredientToDelete);

        ModelManager expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        expectedModel.deleteIngredient(ingredientToDelete);

        assertCommandSuccess(deleteIngredientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIngredientList().size() + 1);
        DeleteIngredientCommand deleteIngredientCommand = new DeleteIngredientCommand(outOfBoundIndex);

        assertCommandFailure(deleteIngredientCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showIngredientAtIndex(model, INDEX_FIRST_INGREDIENT);

        Ingredient ingredientToDelete =
                model.getFilteredIngredientList().get(INDEX_FIRST_INGREDIENT.getZeroBased());
        DeleteIngredientCommand deleteIngredientCommand = new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT);

        String expectedMessage = String.format(DeleteIngredientCommand.MESSAGE_DELETE_INGREDIENT_SUCCESS,
                ingredientToDelete);

        Model expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        expectedModel.deleteIngredient(ingredientToDelete);
        showNoIngredient(expectedModel);

        assertCommandSuccess(deleteIngredientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showIngredientAtIndex(model, INDEX_FIRST_INGREDIENT);

        Index outOfBoundIndex = INDEX_SECOND_INGREDIENT;
        // ensures that outOfBoundIndex is still in bounds of fridge's ingredient list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWishfulShrinking().getIngredientList().size());

        DeleteIngredientCommand deleteIngredientCommand = new DeleteIngredientCommand(outOfBoundIndex);

        assertCommandFailure(deleteIngredientCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteIngredientCommand deleteIngredientFirstCommand = new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT);
        DeleteIngredientCommand deleteIngredientSecondCommand = new DeleteIngredientCommand(INDEX_SECOND_INGREDIENT);

        // same object -> returns true
        assertTrue(deleteIngredientFirstCommand.equals(deleteIngredientFirstCommand));

        // same values -> returns true
        DeleteIngredientCommand deleteIngredientFirstCommandCopy = new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT);
        assertTrue(deleteIngredientFirstCommand.equals(deleteIngredientFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteIngredientFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteIngredientFirstCommand.equals(null));

        // different x -> returns false
        assertFalse(deleteIngredientFirstCommand.equals(deleteIngredientSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoIngredient(Model model) {
        model.updateFilteredIngredientList(p -> false);

        assertTrue(model.getFilteredIngredientList().isEmpty());
    }
}
