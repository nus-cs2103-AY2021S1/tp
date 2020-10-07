package chopchop.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static chopchop.logic.commands.CommandTestUtil.assertCommandFailure;
import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.showPersonAtIndex;
import static chopchop.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;
import static chopchop.testutil.TypicalIndexes.INDEX_SECOND_INGREDIENT;
import static chopchop.testutil.TypicalIngredients.getTypicalIngredientBook;
import org.junit.jupiter.api.Test;
import chopchop.commons.core.Messages;
import chopchop.commons.core.index.Index;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UserPrefs;
import chopchop.model.ingredient.Ingredient;
import chopchop.model.recipe.RecipeBook;

public class DeleteIngredientCommandTest {

    private Model model = new ModelManager(new RecipeBook(), getTypicalIngredientBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Ingredient indToDelete = model.getFilteredIngredientList().get(INDEX_FIRST_INGREDIENT.getZeroBased());
        DeleteIngredientCommand deleteCommand = new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT);

        String expectedMessage = String.format(DeleteIngredientCommand.MESSAGE_DELETE_INGREDIENT_SUCCESS, indToDelete);

        ModelManager expectedModel = new ModelManager(new RecipeBook(), model.getIngredientBook(), new UserPrefs());
        expectedModel.deleteIngredient(indToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredIngredientList().size() + 1);
        DeleteIngredientCommand deleteCommand = new DeleteIngredientCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_INGREDIENT);

        Ingredient indToDelete = model.getFilteredIngredientList().get(INDEX_FIRST_INGREDIENT.getZeroBased());
        DeleteIngredientCommand deleteCommand = new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT);

        String expectedMessage = String.format(DeleteIngredientCommand.MESSAGE_DELETE_INGREDIENT_SUCCESS, indToDelete);

        Model expectedModel = new ModelManager(new RecipeBook(), model.getIngredientBook(), new UserPrefs());
        expectedModel.deleteIngredient(indToDelete);
        showNoIngredient(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_INGREDIENT);

        Index outOfBoundIndex = INDEX_SECOND_INGREDIENT;
        // ensures that outOfBoundIndex is still in bounds of ingredient book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getIngredientBook().getFoodEntryList().size());

        DeleteIngredientCommand deleteCommand = new DeleteIngredientCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteIngredientCommand deleteFirstCommand = new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT);
        DeleteIngredientCommand deleteSecondCommand = new DeleteIngredientCommand(INDEX_SECOND_INGREDIENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteIngredientCommand deleteFirstCommandCopy = new DeleteIngredientCommand(INDEX_FIRST_INGREDIENT);
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
    private void showNoIngredient(Model model) {
        model.updateFilteredIngredientList(p -> false);

        assertTrue(model.getFilteredIngredientList().isEmpty());
    }
}
