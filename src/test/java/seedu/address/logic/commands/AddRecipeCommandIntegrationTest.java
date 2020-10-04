package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertItemCommandFailure;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipePrecursor;
import seedu.address.testutil.RecipePrecursorBuilder;

public class AddRecipeCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
                new RecipeList(), new UserPrefs());
    }

    // TODO these tests cause other tests to fail...?
    /*
    @Test
    public void execute_newRecipe_success() {
        RecipePrecursor validRecipePrecursor = new RecipePrecursorBuilder().build();
        Recipe validRecipe = model.processPrecursor(validRecipePrecursor);

        Model expectedModel = new ModelManager(model.getItemList(), model.getLocationList(),
                model.getRecipeList(), new UserPrefs());
        expectedModel.addRecipe(validRecipe);

        assertCommandSuccess(new AddRecipeCommand(validRecipePrecursor), model,
                String.format(AddRecipeCommand.MESSAGE_SUCCESS, validRecipe), expectedModel);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        RecipePrecursor validRecipePrecursor = new RecipePrecursorBuilder().build();
        Recipe validRecipe = model.processPrecursor(validRecipePrecursor);

        Model modelWithRecipe = new ModelManager(model.getItemList(), model.getLocationList(),
                model.getRecipeList(), new UserPrefs());
        modelWithRecipe.addRecipe(validRecipe);

        assertItemCommandFailure(new AddRecipeCommand(validRecipePrecursor), modelWithRecipe,
                AddRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }
     */
}
