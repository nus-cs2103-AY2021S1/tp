package seedu.address.logic.commands.ingredient;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showIngredientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;
import static seedu.address.testutil.TypicalIngredients.getTypicalWishfulShrinking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.ingredient.Ingredient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListIngredientCommand.
 */
public class ListIngredientsCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
        expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        ObservableList<Ingredient> ingredients = model.getFilteredIngredientList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            builder.append((i + 1) + ". " + ingredients.get(i).toString() + "\n");
        }
        assertCommandSuccess(new ListIngredientsCommand(), model,
                ListIngredientsCommand.MESSAGE_SUCCESS + builder.toString(), expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showIngredientAtIndex(model, INDEX_FIRST_INGREDIENT);
        ObservableList<Ingredient> ingredients = expectedModel.getFilteredIngredientList();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            builder.append((i + 1) + ". " + ingredients.get(i).toString() + "\n");
        }
        assertCommandSuccess(new ListIngredientsCommand(), model,
                ListIngredientsCommand.MESSAGE_SUCCESS + builder.toString(), expectedModel);
    }
}
