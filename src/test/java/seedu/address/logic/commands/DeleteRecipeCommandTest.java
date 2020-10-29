package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.BANANA_PIE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.TypicalRecipes;

public class DeleteRecipeCommandTest {

    private ModelStubWithRecipeList modelStub;
    private ModelStubWithRecipeList expectedModelStub;

    @BeforeEach
    public void setUp() {
        modelStub = new ModelStubWithRecipeList(TypicalRecipes.getTypicalRecipeList());
        expectedModelStub = new ModelStubWithRecipeList(TypicalRecipes.getTypicalRecipeList());
    }

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new DeleteRecipeCommand(null, null));
    }

    /**
     * Tests for successful deletion of a recipe found in the recipe list.
     */
    @Test
    public void execute_success() throws CommandException {
        DeleteRecipeCommand drc = new DeleteRecipeCommand("Apple", Index.fromOneBased(1));
        expectedModelStub.deleteRecipe(BANANA_PIE);
        String expectedMessage = String.format(DeleteRecipeCommand.MESSAGE_SUCCESS, BANANA_PIE);
        CommandResult commandResult = drc.execute(modelStub);
        assertEquals(new CommandResult(expectedMessage), commandResult);
        assertEquals(expectedModelStub, modelStub);
    }

    /**
     * Tests for failure of deletion of recipe when the product name cannot be found in the recipe list.
     */
    @Test
    public void execute_recipeNotFound() {
        DeleteRecipeCommand drc = new DeleteRecipeCommand("Cake", Index.fromOneBased(1));
        // Cake does not exist in recipe list
        String expectedMessage = String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, "Cake");

        assertThrows(CommandException.class, expectedMessage, () -> drc.execute(modelStub));
        assertEquals(expectedModelStub, modelStub);
    }

    /**
     * Tests for failure of deletion of recipe when product name can be found but index is out of range.
     */
    @Test
    public void execute_indexOutOfRange() throws CommandException {
        DeleteRecipeCommand drc = new DeleteRecipeCommand("Apple", Index.fromOneBased(2));
        // index out of range since only 1 recipe for apple
        drc.execute(modelStub);
        assertThrows(CommandException.class, DeleteRecipeCommand.MESSAGE_INDEX_NOT_FOUND, (
        ) -> drc.execute(modelStub));
        assertEquals(expectedModelStub, modelStub);
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

    /**
     * A Model stub which contains a recipe list.
     */
    private class ModelStubWithRecipeList extends ModelStub {

        private final RecipeList recipeList;
        private final FilteredList<Recipe> filteredRecipes;

        public ModelStubWithRecipeList(ReadOnlyRecipeList recipeList) {
            this.recipeList = new RecipeList(recipeList);
            filteredRecipes = new FilteredList<>(this.recipeList.getRecipeList());
        }

        @Override
        public ReadOnlyRecipeList getRecipeList() {
            return recipeList;
        }

        @Override
        public ObservableList<Recipe> getFilteredRecipeList() {
            return filteredRecipes;
        }

        @Override
        public void deleteRecipe(Recipe target) {
            recipeList.deleteRecipe(target);
        }
    }
}
