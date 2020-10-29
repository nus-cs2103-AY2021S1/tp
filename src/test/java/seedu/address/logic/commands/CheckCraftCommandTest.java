package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CheckCraftCommand.MESSAGE_SUCCESS_CRAFTABLE;
import static seedu.address.logic.commands.CheckCraftCommand.MESSAGE_SUCCESS_UNCRAFTABLE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.BANANA;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ItemList;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.RecipeBuilder;

public class CheckCraftCommandTest {

    private ModelStubWithItemAndRecipeList model;
    private ModelStubWithItemAndRecipeList expectedModel;

    // used to test display of recipe when crafting is possible
    private final String recipeString = "test: Banana[6] -> Apple[4]\n";

    @BeforeEach
    public void setUp() {
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient(2, 3));
        // recipe to create 2 apples from 3 bananas
        Recipe recipe = new RecipeBuilder().withId(1).withIngredients(ingredients).withQuantity("2")
                .withDescription("test").build();
        RecipeList recipeList = new RecipeList();
        recipeList.addRecipe(recipe);
        model = new ModelStubWithItemAndRecipeList(getTypicalItemList(), recipeList);
        expectedModel = new ModelStubWithItemAndRecipeList(getTypicalItemList(), recipeList);
    }

    /**
     * Tests for success when item can be crafted with 1 recipe.
     */
    @Test
    public void execute_craftable_success() {
        CheckCraftCommand ccc = new CheckCraftCommand(APPLE.getName(), new Quantity("3"));
        String expectedMessage = String.format(MESSAGE_SUCCESS_CRAFTABLE, APPLE.getName(), 3)
                + "\n" + recipeString;
        assertCommandSuccess(ccc, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for success when item cannot be crafted with any recipe.
     */
    @Test
    public void execute_uncraftable_success() {
        CheckCraftCommand ccc = new CheckCraftCommand(APPLE.getName(), new Quantity("200"));
        String expectedMessage = String.format(MESSAGE_SUCCESS_UNCRAFTABLE, APPLE.getName(), 200);
        assertCommandSuccess(ccc, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for failure when quantity is zero.
     */
    @Test
    public void execute_invalidQuantity_throwsCommandException() {
        CheckCraftCommand ccc = new CheckCraftCommand(APPLE.getName(), new Quantity("0"));
        String expectedMessage = CheckCraftCommand.MESSAGE_INVALID_QUANTITY;

        assertThrows(CommandException.class, expectedMessage, () -> ccc.execute(model));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests for failure when item cannot be found in item list.
     */
    @Test
    public void execute_invalidItem_throwsCommandException() {
        CheckCraftCommand ccc = new CheckCraftCommand("apple2", new Quantity("1"));
        String expectedMessage = String.format(Messages.MESSAGE_NO_ITEM_FOUND, "apple2");

        assertThrows(CommandException.class, expectedMessage, () -> ccc.execute(model));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests for failure when no recipe to craft the item can be found
     */
    @Test
    public void execute_recipeNotFound_throwsCommandException() {
        CheckCraftCommand ccc = new CheckCraftCommand(BANANA.getName(), new Quantity("1"));
        String expectedMessage = String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, BANANA.getName());

        assertThrows(CommandException.class, expectedMessage, () -> ccc.execute(model));
        assertEquals(expectedModel, model);
    }

    /**
     * Tests for equivalency.
     */
    @Test
    public void equals() {
        CheckCraftCommand checkCraftCommand = new CheckCraftCommand("a", new Quantity("1"));

        // same object -> returns true
        assertTrue(checkCraftCommand.equals(checkCraftCommand));

        // same values -> returns true
        CheckCraftCommand checkCraftCommandCopy = new CheckCraftCommand("a", new Quantity("1"));
        assertTrue(checkCraftCommand.equals(checkCraftCommandCopy));

        // different types -> returns false
        assertFalse(checkCraftCommand.equals(1));

        // null -> returns false
        assertFalse(checkCraftCommand.equals(null));

        CheckCraftCommand checkCraftDiffName = new CheckCraftCommand("b", new Quantity("1"));
        // different itemName -> returns false
        assertFalse(checkCraftCommand.equals(checkCraftDiffName));

        CheckCraftCommand checkCraftDiffQuantity = new CheckCraftCommand("a", new Quantity("2"));
        // different quantity -> returns false
        assertFalse(checkCraftCommand.equals(checkCraftDiffQuantity));
    }

    /**
     * A Model stub which contains a item and a recipe list.
     */
    private class ModelStubWithItemAndRecipeList extends ModelStub {
        private final ItemList itemList;
        private final FilteredList<Item> filteredItems;
        private final RecipeList recipeList;
        private final FilteredList<Recipe> filteredRecipes;

        public ModelStubWithItemAndRecipeList(ReadOnlyItemList itemList, ReadOnlyRecipeList recipeList) {
            this.recipeList = new RecipeList(recipeList);
            filteredRecipes = new FilteredList<>(this.recipeList.getRecipeList());
            this.itemList = new ItemList(itemList);
            filteredItems = new FilteredList<>(this.itemList.getItemList());
        }

        @Override
        public ReadOnlyRecipeList getRecipeList() {
            return recipeList;
        }

        @Override
        public ObservableList<Recipe> getFilteredRecipeList() {
            return filteredRecipes;
        }

        @Override
        public ReadOnlyItemList getItemList() {
            return itemList;
        }

        @Override
        public ObservableList<Item> getFilteredItemList() {
            return filteredItems;
        }
    }
}
