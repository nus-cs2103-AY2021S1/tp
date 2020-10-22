package chopchop.logic.commands;

import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UserPrefs;
import chopchop.model.recipe.Recipe;
import chopchop.model.ingredient.IngredientBook;
import chopchop.testutil.RecipeBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.assertRecipeCommandFailure;
import static chopchop.testutil.TypicalRecipes.getTypicalRecipeBook;

public class AddRecipeCommandIntTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRecipeBook(), new IngredientBook(), new UserPrefs());
    }

    @Test
    public void execute_newRecipe_success() {
        Recipe validRecipe = new RecipeBuilder().build();

        Model expectedModel = new ModelManager(model.getRecipeBook(), new IngredientBook(), new UserPrefs());
        expectedModel.addRecipe(validRecipe);

        assertCommandSuccess(new AddRecipeCommand(validRecipe), model,
            String.format(AddRecipeCommand.MESSAGE_SUCCESS, validRecipe), expectedModel);
    }

    @Test
    public void execute_duplicateRecipe_throwsCommandException() {
        Recipe recipeInList = model.getRecipeBook().getFoodEntryList().get(0);
        assertRecipeCommandFailure(new AddRecipeCommand(recipeInList), model,
            AddRecipeCommand.MESSAGE_DUPLICATE_RECIPE);
    }

}
