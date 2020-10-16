package seedu.address.logic.commands;

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
import seedu.address.model.consumption.Consumption;
import seedu.address.model.recipe.Recipe;

public class EatRecipeCommandTest {
    private Model model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Recipe recipeToEat = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        EatRecipeCommand eatRecipeCommand = new EatRecipeCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(EatRecipeCommand.MESSAGE_EAT_RECIPE_SUCCESS, extractString(recipeToEat));

        ModelManager expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        expectedModel.addConsumption(new Consumption(recipeToEat));

        assertCommandSuccess(eatRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        EatRecipeCommand eatRecipeCommand = new EatRecipeCommand(outOfBoundIndex);

        assertCommandFailure(eatRecipeCommand, model, Messages.MESSAGE_INVALID_CONSUMPTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Recipe recipeToEat = model.getFilteredRecipeList().get(INDEX_FIRST_RECIPE.getZeroBased());
        EatRecipeCommand eatRecipeCommand = new EatRecipeCommand(INDEX_FIRST_RECIPE);

        String expectedMessage = String.format(EatRecipeCommand.MESSAGE_EAT_RECIPE_SUCCESS, extractString(recipeToEat));

        Model expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        expectedModel.addConsumption(new Consumption(recipeToEat));
        showRecipeAtIndex(expectedModel, INDEX_FIRST_RECIPE);

        assertCommandSuccess(eatRecipeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);

        Index outOfBoundIndex = INDEX_SECOND_RECIPE;
        // ensures that outOfBoundIndex is still in bounds of recipe collection list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getWishfulShrinking().getRecipeList().size());

        EatRecipeCommand eatRecipeCommand = new EatRecipeCommand(outOfBoundIndex);

        assertCommandFailure(eatRecipeCommand, model, Messages.MESSAGE_INVALID_CONSUMPTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EatRecipeCommand eatFirstCommand = new EatRecipeCommand(INDEX_FIRST_RECIPE);
        EatRecipeCommand eatSecondCommand = new EatRecipeCommand(INDEX_SECOND_RECIPE);

        // same object -> returns true
        assertTrue(eatFirstCommand.equals(eatFirstCommand));

        // same values -> returns true
        EatRecipeCommand eatFirstCommandCopy = new EatRecipeCommand(INDEX_FIRST_RECIPE);
        assertTrue(eatFirstCommand.equals(eatFirstCommandCopy));

        // different types -> returns false
        assertFalse(eatFirstCommand.equals(1));

        // null -> returns false
        assertFalse(eatFirstCommand.equals(null));

        // different consumption -> returns false
        assertFalse(eatFirstCommand.equals(eatSecondCommand));
    }

    /**
     * Extract the importat information from the Recipe
     */
    private String extractString(Recipe recipe) {
        final StringBuilder builder = new StringBuilder();
        builder.append(recipe.getName())
                .append(" Calories: ")
                .append(recipe.getCalories() + " cal");
        return builder.toString();
    }
}
