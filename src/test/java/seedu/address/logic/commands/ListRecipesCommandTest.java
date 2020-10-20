package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListRecipesCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        ObservableList<Recipe> recipes = model.getFilteredRecipeList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            builder.append((i + 1) + ". " + recipes.get(i).getName() + "\n");
        }
        assertCommandSuccess(new ListRecipesCommand(), model,
                ListRecipesCommand.MESSAGE_SUCCESS + builder.toString(), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);
        ObservableList<Recipe> recipes = expectedModel.getFilteredRecipeList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < recipes.size(); i++) {
            builder.append((i + 1) + ". " + recipes.get(i).getName() + "\n");
        }
        assertCommandSuccess(new ListRecipesCommand(), model,
                ListRecipesCommand.MESSAGE_SUCCESS + builder.toString(), expectedModel);
    }
}
