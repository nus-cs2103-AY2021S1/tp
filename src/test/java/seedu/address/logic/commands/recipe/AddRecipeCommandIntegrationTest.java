package seedu.address.logic.commands.recipe;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.RecipeBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddRecipeCommand}.
 */
public class AddRecipeCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWishfulShrinking(), new UserPrefs());
    }

    @Test
    public void execute_newRecipe_success() {
        Recipe validRecipe = new RecipeBuilder().buildOtherRecipe();

        Model expectedModel = new ModelManager(model.getWishfulShrinking(), new UserPrefs());
        expectedModel.addRecipe(validRecipe);

        assertCommandSuccess(new AddRecipeCommand(validRecipe), model,
                String.format(AddRecipeCommand.MESSAGE_SUCCESS, validRecipe), expectedModel);
    }

    @Test
    public void execute_newDuplicateRecipe_failure() {
        Recipe validRecipe = new RecipeBuilder().build();

        assertCommandFailure(new AddRecipeCommand(validRecipe), model, AddRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        Recipe recipeInList = model.getWishfulShrinking().getRecipeList().get(0);
        assertCommandFailure(new AddRecipeCommand(recipeInList), model, AddRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

}
