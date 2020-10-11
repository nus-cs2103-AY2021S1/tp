package chopchop.logic.commands;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.showPersonAtIndex;
import static chopchop.testutil.TypicalIndexes.INDEX_FIRST_INGREDIENT;
import static chopchop.testutil.TypicalIngredients.getTypicalIngredientBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UserPrefs;
import chopchop.model.recipe.RecipeBook;

public class ListIngredientCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new RecipeBook(), getTypicalIngredientBook(), new UserPrefs());
        expectedModel = new ModelManager(new RecipeBook(), model.getIngredientBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListIngredientCommand(), model, ListIngredientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_INGREDIENT);
        assertCommandSuccess(new ListIngredientCommand(), model, ListIngredientCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
