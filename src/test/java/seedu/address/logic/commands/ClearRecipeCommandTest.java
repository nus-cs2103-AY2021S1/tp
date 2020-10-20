package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;


public class ClearRecipeCommandTest {

    @Test
    public void execute_emptyRecipeList_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearRecipeCommand(), model, ClearRecipeCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyRecipeList_success() {
        Model model = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
                getTypicalRecipeList(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
                getTypicalRecipeList(), new UserPrefs());
        expectedModel.setRecipeList(new RecipeList());
        assertCommandSuccess(new ClearRecipeCommand(), model, ClearRecipeCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
