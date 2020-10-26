package chopchop.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static chopchop.logic.commands.CommandTestUtil.assertCommandFailure;
import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static chopchop.testutil.TypicalReferences.INDEXED_FIRST;
import static chopchop.testutil.TypicalReferences.INDEXED_SECOND;
import static chopchop.testutil.TypicalRecipes.getTypicalRecipeBook;

import org.junit.jupiter.api.Test;

import chopchop.logic.parser.ItemReference;
import chopchop.model.EntryBook;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UsageList;
import chopchop.model.UserPrefs;
import chopchop.model.usage.IngredientUsage;
import chopchop.model.usage.RecipeUsage;

public class DeleteRecipeCommandTest {

    private Model model = new ModelManager(getTypicalRecipeBook(), new EntryBook<>(), new UsageList<RecipeUsage>(),
        new UsageList<IngredientUsage>(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        var recToDelete = model.getFilteredRecipeList().get(INDEXED_FIRST.getZeroIndex());
        var deleteCommand = new DeleteRecipeCommand(INDEXED_FIRST);

        var expectedModel = new ModelManager(model.getRecipeBook(), new EntryBook<>(), new UsageList<RecipeUsage>(),
                new UsageList<IngredientUsage>(), new UserPrefs());
        expectedModel.deleteRecipe(recToDelete);

        assertCommandSuccess(deleteCommand, model, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        var outOfBoundIndex = ItemReference.ofOneIndex(model.getFilteredRecipeList().size() + 1);
        var deleteCommand = new DeleteRecipeCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showRecipeAtIndex(model, INDEXED_FIRST);

        var recToDelete = model.getFilteredRecipeList().get(INDEXED_FIRST.getZeroIndex());
        var deleteCommand = new DeleteRecipeCommand(INDEXED_FIRST);

        var expectedModel = new ModelManager(model.getRecipeBook(), new EntryBook<>(), new UsageList<RecipeUsage>(),
                new UsageList<IngredientUsage>(), new UserPrefs());
        expectedModel.deleteRecipe(recToDelete);
        showNoRecipe(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRecipeAtIndex(model, INDEXED_FIRST);

        var outOfBoundIndex = INDEXED_SECOND;
        // ensures that outOfBoundIndex is still in bounds of recipe book list
        assertTrue(outOfBoundIndex.getZeroIndex() < model.getRecipeBook().getEntryList().size());

        var deleteCommand = new DeleteRecipeCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model);
    }

    @Test
    public void equals() {
        var deleteFirstCommand = new DeleteRecipeCommand(INDEXED_FIRST);
        var deleteSecondCommand = new DeleteRecipeCommand(INDEXED_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        var deleteFirstCommandCopy = new DeleteRecipeCommand(INDEXED_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different values -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no recipe.
     * @param model
     */
    private void showNoRecipe(Model model) {
        model.updateFilteredRecipeList(p -> false);

        assertTrue(model.getFilteredRecipeList().isEmpty());
    }
}
