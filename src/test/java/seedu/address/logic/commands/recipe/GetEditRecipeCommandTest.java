package seedu.address.logic.commands.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEditRecipeCommandSuccess;
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

public class GetEditRecipeCommandTest {
    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Recipe recipeToEdit =
                model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        GetEditRecipeCommand getEditRecipeCommand = new GetEditRecipeCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(GetEditRecipeCommand.MESSAGE_GET_EDIT_RECIPE_SUCCESS,
                recipeToEdit.toString());

        ModelManager expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());

        showEditRecipeCommandSuccess(getEditRecipeCommand, model, expectedMessage, recipeToEdit,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        GetEditRecipeCommand getEditRecipeCommand = new GetEditRecipeCommand(outOfBoundIndex);

        assertCommandFailure(getEditRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        Recipe recipeToEdit =
                model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        GetEditRecipeCommand getEditRecipeCommand = new GetEditRecipeCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(GetEditRecipeCommand.MESSAGE_GET_EDIT_RECIPE_SUCCESS,
                recipeToEdit);

        Model expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());

        assertCommandSuccess(getEditRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of fridge's Recipe list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWishfulShrinking().getRecipeList().size());

        GetEditRecipeCommand getEditRecipeCommand = new GetEditRecipeCommand(outOfBoundIndex);

        assertCommandFailure(getEditRecipeCommand, model, Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GetEditRecipeCommand getEditRecipeFirstCommand =
                new GetEditRecipeCommand(INDEX_FIRST_RECIPE);
        GetEditRecipeCommand getEditRecipeSecondCommand =
                new GetEditRecipeCommand(INDEX_SECOND_RECIPE);

        // same object -> returns true
        assertTrue(getEditRecipeFirstCommand.equals(getEditRecipeFirstCommand));

        // same values -> returns true
        GetEditRecipeCommand getEditRecipeFirstCommandCopy =
                new GetEditRecipeCommand(INDEX_FIRST_RECIPE);
        assertTrue(getEditRecipeFirstCommand.equals(getEditRecipeFirstCommandCopy));

        // different types -> returns false
        assertFalse(getEditRecipeFirstCommand.equals(1));

        // null -> returns false
        assertFalse(getEditRecipeFirstCommand.equals(null));

        // different x -> returns false
        assertFalse(getEditRecipeFirstCommand.equals(getEditRecipeSecondCommand));
    }
}
