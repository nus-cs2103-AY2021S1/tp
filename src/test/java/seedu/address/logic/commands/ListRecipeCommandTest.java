package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.TypicalRecipes;
import seedu.address.ui.DisplayedInventoryType;

public class ListRecipeCommandTest {

    private final ListRecipeCommand listRecipeCommand = new ListRecipeCommand();

    /**
     * Tests for correct execution and displayed message with non-empty recipe list.
     */
    @Test
    public void execute_success() {
        ModelStubWithRecipeList modelStub = new ModelStubWithRecipeList(TypicalRecipes.getTypicalRecipeList());
        CommandResult expectedCommandResult = new CommandResult(ListRecipeCommand.MESSAGE_SUCCESS,
                false, false, DisplayedInventoryType.RECIPES);
        assertCommandSuccess(listRecipeCommand, modelStub, expectedCommandResult, modelStub);
    }

    /**
     * Tests for correct execution and displayed message with empty recipe list.
     */
    @Test
    public void execute_showEmptyItemList() {
        ModelStubWithRecipeList modelStub = new ModelStubWithRecipeList(new RecipeList());
        CommandResult expectedCommandResult = new CommandResult(ListRecipeCommand.MESSAGE_NO_RECIPES,
                false, false, DisplayedInventoryType.RECIPES);
        assertCommandSuccess(listRecipeCommand, modelStub, expectedCommandResult, modelStub);
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
    }
}
