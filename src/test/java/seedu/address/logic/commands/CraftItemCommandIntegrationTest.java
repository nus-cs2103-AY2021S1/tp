package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertInventoryCommandFailure;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.BANANA;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.RecipeBuilder;

public class CraftItemCommandIntegrationTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient(2, 3));
        // recipe to create 2 apples from 3 bananas
        Recipe recipe = new RecipeBuilder().withId(1).withIngredients(ingredients).withQuantity("2").build();
        model.addRecipe(recipe);
        expectedModel = new ModelManager(model.getItemList(), model.getLocationList(),
                model.getRecipeList(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsPresent_success() throws CommandException {
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("2"), Index.fromZeroBased(0));
        String expectedMessage = String.format(CraftItemCommand.MESSAGE_SUCCESS, APPLE.getName(), 2);
        // simulate crafting in expected model manually
        AddQuantityToItemCommand use3Bananas = new AddQuantityToItemCommand("Banana", -3);
        AddQuantityToItemCommand craft1Apple = new AddQuantityToItemCommand("Apple", 2);
        use3Bananas.execute(expectedModel);
        craft1Apple.execute(expectedModel);

        assertCommandSuccess(cic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for crafting success and filled missing recipe index with the default
     */
    @Test
    public void execute_missingRecipeId_success() throws CommandException {
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("2"));
        String expectedMessage = CraftItemCommand.MESSAGE_MISSING_RECIPE_INDEX
                + String.format(CraftItemCommand.MESSAGE_SUCCESS, APPLE.getName(), 2);
        // simulate crafting in expected model manually
        AddQuantityToItemCommand use3Bananas = new AddQuantityToItemCommand("Banana", -3);
        AddQuantityToItemCommand craft1Apple = new AddQuantityToItemCommand("Apple", 2);
        use3Bananas.execute(expectedModel);
        craft1Apple.execute(expectedModel);

        assertCommandSuccess(cic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for crafting success when excess crafting is done due to the recipe
     */
    @Test
    public void execute_craftExcess_success() throws CommandException {
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("1"), Index.fromZeroBased(0));
        String expectedMessage = String.format(CraftItemCommand.MESSAGE_SUCCESS_EXCESS, APPLE.getName(), 2, 1);
        // simulate crafting in expected model manually
        AddQuantityToItemCommand use3Bananas = new AddQuantityToItemCommand("Banana", -3);
        AddQuantityToItemCommand craft1Apple = new AddQuantityToItemCommand("Apple", 2);
        use3Bananas.execute(expectedModel);
        craft1Apple.execute(expectedModel);

        assertCommandSuccess(cic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for crafting failure when item to craft cannot be found in item list
     */
    @Test
    public void execute_invalidItem_throwsCommandException() {
        CraftItemCommand cic = new CraftItemCommand("test", new Quantity("1"), Index.fromZeroBased(0));
        String expectedMessage = String.format(Messages.MESSAGE_NO_ITEM_FOUND, "test");
        assertInventoryCommandFailure(cic, model, expectedMessage);
    }

    /**
     * Tests for crafting failure when recipe to craft item cannot be found
     */
    @Test
    public void execute_recipeNotFound_throwsCommandException() {
        CraftItemCommand cic = new CraftItemCommand(BANANA.getName(), new Quantity("1"), Index.fromZeroBased(0));
        String expectedMessage = String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, BANANA.getName());
        assertInventoryCommandFailure(cic, model, expectedMessage);
    }

    /**
     * Tests for crafting failure when recipe index given is out of bounds
     */
    @Test
    public void execute_recipeIndexInvalid_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("2"), outOfBoundsIndex);
        String expectedMessage = CraftItemCommand.MESSAGE_INDEX_OUT_OF_RANGE;
        assertInventoryCommandFailure(cic, model, expectedMessage);
    }

    /**
     * Tests for crafting failure when product quantity is 0.
     */
    @Test
    public void execute_invalidProductQuantity_throwsCommandException() {
        // trying to craft 1 apple when only 2 can be crafted at a time
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("0"), Index.fromZeroBased(0));
        String expectedMessage = CraftItemCommand.MESSAGE_INVALID_PRODUCT_QUANTITY;
        assertInventoryCommandFailure(cic, model, expectedMessage);
    }

    /**
     * Tests for crafting failure when there are insufficient ingredients to craft with
     */
    @Test
    public void execute_insufficientIngredients_throwsCommandException() {
        // need 150 bananas for this crafting but only 99 present in inventory
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("100"), Index.fromZeroBased(0));
        String expectedMessage = CraftItemCommand.MESSAGE_INSUFFICIENT_INGREDIENTS;
        assertInventoryCommandFailure(cic, model, expectedMessage);
    }
}
