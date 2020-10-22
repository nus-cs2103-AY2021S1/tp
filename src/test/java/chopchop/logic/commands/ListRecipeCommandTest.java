package chopchop.logic.commands;

import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UserPrefs;
import chopchop.model.ingredient.IngredientBook;
import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static chopchop.testutil.TypicalRecipes.getTypicalRecipeBook;
import static chopchop.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListRecipeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRecipeBook(), new IngredientBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getRecipeBook(), new IngredientBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListRecipeCommand(), model, ListRecipeCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showRecipeAtIndex(model, INDEX_FIRST_RECIPE);
        assertCommandSuccess(new ListRecipeCommand(), model, ListRecipeCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
