package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.WishfulShrinking;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.consumption.ConsumptionContainsKeywordsPredicate;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.KeywordsContainIngredientPredicate;
import seedu.address.model.recipe.NameContainsKeywordsPredicate;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.EditRecipeDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    //SANDWICH
    public static final String VALID_INGREDIENT_SANDWICH = "Kaiser Rolls Or Other Bread";
    public static final String VALID_QUANTITY_SANDWICH = "2 whole";

    // NOODLE
    public static final String VALID_NAME_NOODLE = "Buttery Lemon Parsley Noodles";
    public static final String VALID_INGREDIENT_NOODLE = "Pasta fettuccine";
    public static final String VALID_QUANTITY_NOODLE = "1 pound";
    public static final Integer VALID_CALORIES_NOODLE = 180;
    public static final String VALID_INSTRUCTION_NOODLE = "Cook the noodles according to package instructions. "
            + "(If using angel hair, stop just short of the al dente stage.) Drain and set aside. "
            + "Melt butter in a large skillet over medium-high heat. "
            + "Throw in the cooked pasta and cook it around in the butter for a couple of minutes "
                + "so that a few of the noodles get a little bit of a panfried texture to them, whatever that means. "
            + "Zest the lemon. "
            + "Squeeze in the juice, then add the zest of half the lemon, salt and pepper to taste,"
                + " then toss around and serve.";
    public static final String VALID_RECIPE_IMAGE_NOODLE = "images/noodles1.jpg";
    public static final String VALID_TAG_NOODLE = "healthy";

    public static final String NAME_DESC_NOODLE = " " + PREFIX_NAME + VALID_NAME_NOODLE;
    public static final String INGREDIENT_DESC_NOODLE = " " + PREFIX_INGREDIENT + VALID_INGREDIENT_NOODLE
            + " " + PREFIX_QUANTITY + VALID_QUANTITY_NOODLE;
    public static final String CALORIES_DESC_NOODLE = " " + PREFIX_CALORIES + VALID_CALORIES_NOODLE;
    public static final String INSTRUCTION_DESC_NOODLE = " " + PREFIX_INSTRUCTION + VALID_INSTRUCTION_NOODLE;
    public static final String RECIPE_IMAGE_DESC_NOODLE = " " + PREFIX_RECIPE_IMAGE + VALID_RECIPE_IMAGE_NOODLE;
    public static final String TAG_DESC_NOODLE = " " + PREFIX_TAG + VALID_TAG_NOODLE;


    // MARGARITAS
    public static final String VALID_NAME_MARGARITAS = "Mango Margaritas";
    public static final String VALID_INGREDIENT_MARGARITAS = "Mango Chunks Drained";
    public static final String VALID_QUANTITY_MARGARITAS = "2 jars";
    public static final Integer VALID_CALORIES_MARGARITAS = 80;
    public static final String VALID_INSTRUCTION_MARGARITAS = "Zest the limes and lay the zest on a plate. "
            + "If you have the time, let the zest dry out for ten minutes or so. "
            + "Pour the coarse sugar over the zest and toss it around with your fingers to combine. "
            + "Lime sugar! "
            + "Yum. Throw the mango chunks into a blender. "
            + "Pour in the tequila, triple sec, and sugar. "
            + "Squeeze in the juice of the limes, then top off the whole thing with ice. "
            + "Blend it until it's totally smooth. "
            + "Give it a taste, then add more of what you think it needs (alcohol, sugar, lime, etc.) "
            + "To serve, cut a small wedge in one of the juiced limes and "
            + "rub the lime all over the rim of the glass to moisten. "
            + "Dip the rim of the glasses in the lime sugar to give it a pretty, crystally rim. "
            + "Pour in the margaritas and serve immediately!";
    public static final String VALID_RECIPE_IMAGE_MARGARITAS = "images/mango23.jpg";
    public static final String VALID_TAG_MARGARITAS = "healthy";

    public static final String NAME_DESC_MARGARITAS = " " + PREFIX_NAME + VALID_NAME_MARGARITAS;
    public static final String INGREDIENT_DESC_MARGARITAS = " " + PREFIX_INGREDIENT + VALID_INGREDIENT_MARGARITAS
            + " " + PREFIX_QUANTITY + VALID_QUANTITY_MARGARITAS;
    public static final String CALORIES_DESC_MARGARITAS = " " + PREFIX_CALORIES + VALID_CALORIES_MARGARITAS;
    public static final String INSTRUCTION_DESC_MARGARITAS = " " + PREFIX_INSTRUCTION + VALID_INSTRUCTION_MARGARITAS;
    public static final String RECIPE_IMAGE_DESC_MARGARITAS = " " + PREFIX_RECIPE_IMAGE + VALID_RECIPE_IMAGE_MARGARITAS;
    public static final String TAG_DESC_MARGARITAS = " " + PREFIX_TAG + VALID_TAG_MARGARITAS;

    //Invalid
    public static final String INVALID_NAME_DESC = " "
            + PREFIX_NAME + "Butter&Chicken"; // '&' not allowed in names
    public static final String INVALID_INGREDIENT_DESC = " "
            + PREFIX_INGREDIENT + " "; // not allowed in to blank the ingredients
    public static final String INVALID_CALORIES_DESC = " " + PREFIX_CALORIES + "-1"; // negative number
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "healthy*"; // '*' not allowed in tags
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

<<<<<<< HEAD
    public static final EditRecipeCommand.EditRecipeDescriptor DESC_AMY;
    public static final EditRecipeCommand.EditRecipeDescriptor DESC_BOB;
=======
    public static final EditCommand.EditRecipeDescriptor DESC_NOODLE;
    public static final EditCommand.EditRecipeDescriptor DESC_MARGARITAS;
>>>>>>> 87d382ec766cc16ee80bf67caf27dbf62638cda7

    static {
        DESC_NOODLE = new EditRecipeDescriptorBuilder().withName(VALID_NAME_NOODLE)
                .withIngredient(VALID_INGREDIENT_NOODLE, VALID_QUANTITY_NOODLE)
                .withCalories(VALID_CALORIES_NOODLE)
                .withTags(VALID_TAG_NOODLE).build();
        DESC_MARGARITAS = new EditRecipeDescriptorBuilder().withName(VALID_NAME_MARGARITAS)
                .withIngredient(VALID_INGREDIENT_MARGARITAS, VALID_QUANTITY_MARGARITAS)
                .withCalories(VALID_CALORIES_MARGARITAS)
                .withTags(VALID_TAG_NOODLE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            System.out.println(result.getFeedbackToUser());
            System.out.println(expectedCommandResult.getFeedbackToUser());
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the Wishful Shrinking, filtered recipe list and selected recipe in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        WishfulShrinking expectedWishfulShrinking = new WishfulShrinking(actualModel.getWishfulShrinking());
        List<Recipe> expectedFilteredList = new ArrayList<>(actualModel.getFilteredRecipeList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedWishfulShrinking, actualModel.getWishfulShrinking());
        assertEquals(expectedFilteredList, actualModel.getFilteredRecipeList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the recipe at the given {@code targetIndex} in the
     * {@code model}'s recipe collection.
     */
    public static void showRecipeAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredRecipeList().size());

        Recipe recipe = model.getFilteredRecipeList().get(targetIndex.getZeroBased());
        final String[] splitName = recipe.getName().fullName.split("\\s+");
        model.updateFilteredRecipeList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredRecipeList().size());
    }
    /**
     * Updates {@code model}'s filtered list to show only the ingredient at the given {@code targetIndex} in the
     * {@code model}'s fridge.
     */
    public static void showIngredientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredIngredientList().size());

        Ingredient ingredient = model.getFilteredIngredientList().get(targetIndex.getZeroBased());
        final String[] splitName = ingredient.getValue().split("\\s+");
        model.updateFilteredIngredientList(new KeywordsContainIngredientPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredIngredientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the consumption at the given {@code targetIndex} in the
     * {@code model}'s consumption list.
     */
    public static void showConsumptionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredConsumptionList().size());

        Consumption consumption = model.getFilteredConsumptionList().get(targetIndex.getZeroBased());
        final String[] splitName = consumption.getRecipe().getName().fullName.split("\\s+");
        model.updateFilteredConsumptionList(new ConsumptionContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredConsumptionList().size());
    }
}
