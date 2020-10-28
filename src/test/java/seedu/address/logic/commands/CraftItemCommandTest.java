package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.BANANA;

import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.RecipeBuilder;

public class CraftItemCommandTest {

    private ModelStubWithItemAndRecipeList model;
    private ModelStubWithItemAndRecipeList sameModel; // used to test failures

    private final RecipeList recipeList = new RecipeList();
    private final ItemList itemList = new ItemList();

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new CraftItemCommand(null, new Quantity("0"), null));
    }

    @BeforeEach
    public void setUp() {
        itemList.addItem(APPLE);
        itemList.addItem(BANANA);
        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient(1, 3));
        // recipe to create 2 apples from 3 bananas
        Recipe recipe = new RecipeBuilder().withId(1).withIngredients(ingredients).withQuantity("2").build();
        recipeList.addRecipe(recipe);

        model = new ModelStubWithItemAndRecipeList(itemList, recipeList);
        sameModel = new ModelStubWithItemAndRecipeList(itemList, recipeList);
    }

    @Test
    public void execute_allFieldsPresent_success() {
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("2"), Index.fromZeroBased(0));
        String expectedMessage = String.format(CraftItemCommand.MESSAGE_SUCCESS, APPLE.getName(), 2);
        // simulate crafting in expected model manually
        // 2 more apples added
        Item editedApple = new ItemBuilder().withId(1).withName("Apple")
                .withDescription("Recovers 10 hp").withQuantity("11").build();
        // 3 bananas removed
        Item editedBanana = new ItemBuilder().withId(2).withName("Banana")
                    .withDescription("Used as bait").withQuantity("96").build();
        ItemList expectedItemList = new ItemList();
        expectedItemList.addItem(editedApple);
        expectedItemList.addItem(editedBanana);
        ModelStubWithItemAndRecipeList expectedModel = new ModelStubWithItemAndRecipeList(expectedItemList, recipeList);

        assertCommandSuccess(cic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for crafting success and filled missing recipe index with the default
     */
    @Test
    public void execute_missingRecipeId_success() {
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("2"));
        String expectedMessage = CraftItemCommand.MESSAGE_MISSING_RECIPE_INDEX
            + String.format(CraftItemCommand.MESSAGE_SUCCESS, APPLE.getName(), 2);
        // simulate crafting in expected model manually
        // 2 more apples added
        Item editedApple = new ItemBuilder().withId(1).withName("Apple")
                .withDescription("Recovers 10 hp").withQuantity("11").build();
        // 3 bananas removed
        Item editedBanana = new ItemBuilder().withId(2).withName("Banana")
                .withDescription("Used as bait").withQuantity("96").build();
        ItemList expectedItemList = new ItemList();
        expectedItemList.addItem(editedApple);
        expectedItemList.addItem(editedBanana);
        ModelStubWithItemAndRecipeList expectedModel = new ModelStubWithItemAndRecipeList(expectedItemList, recipeList);

        assertCommandSuccess(cic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for crafting success when excess crafting is done due to the recipe
     */
    @Test
    public void execute_craftExcess_success() {
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("1"), Index.fromZeroBased(0));
        String expectedMessage = String.format(CraftItemCommand.MESSAGE_SUCCESS_EXCESS, APPLE.getName(), 2, 1);
        // simulate crafting in expected model manually
        // 2 more apples added
        Item editedApple = new ItemBuilder().withId(1).withName("Apple")
                .withDescription("Recovers 10 hp").withQuantity("11").build();
        // 3 bananas removed
        Item editedBanana = new ItemBuilder().withId(2).withName("Banana")
                .withDescription("Used as bait").withQuantity("96").build();
        ItemList expectedItemList = new ItemList();
        expectedItemList.addItem(editedApple);
        expectedItemList.addItem(editedBanana);
        ModelStubWithItemAndRecipeList expectedModel = new ModelStubWithItemAndRecipeList(expectedItemList, recipeList);

        assertCommandSuccess(cic, model, expectedMessage, expectedModel);
    }

    /**
     * Tests for crafting failure when item to craft cannot be found in item list
     */
    @Test
    public void execute_invalidItem_throwsCommandException() {
        CraftItemCommand cic = new CraftItemCommand("test", new Quantity("1"), Index.fromZeroBased(0));
        String expectedMessage = String.format(Messages.MESSAGE_NO_ITEM_FOUND, "test");

        assertThrows(CommandException.class, expectedMessage, () -> cic.execute(model));
        assertEquals(sameModel, model);
    }

    /**
     * Tests for crafting failure when recipe to craft item cannot be found
     */
    @Test
    public void execute_recipeNotFound_throwsCommandException() {
        CraftItemCommand cic = new CraftItemCommand(BANANA.getName(), new Quantity("1"), Index.fromZeroBased(0));
        String expectedMessage = String.format(Messages.MESSAGE_RECIPE_NOT_FOUND, BANANA.getName());

        assertThrows(CommandException.class, expectedMessage, () -> cic.execute(model));
        assertEquals(sameModel, model);
    }

    /**
     * Tests for crafting failure when recipe index given is out of bounds
     */
    @Test
    public void execute_recipeIndexInvalid_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredRecipeList().size() + 1);
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("2"), outOfBoundsIndex);
        String expectedMessage = CraftItemCommand.MESSAGE_INDEX_OUT_OF_RANGE;

        assertThrows(CommandException.class, expectedMessage, () -> cic.execute(model));
        assertEquals(sameModel, model);
    }

    /**
     * Tests for crafting failure when product quantity is 0
     */
    @Test
    public void execute_invalidProductQuantity_throwsCommandException() {
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("0"), Index.fromZeroBased(0));
        String expectedMessage = CraftItemCommand.MESSAGE_INVALID_PRODUCT_QUANTITY;

        assertThrows(CommandException.class, expectedMessage, () -> cic.execute(model));
        assertEquals(sameModel, model);
    }

    /**
     * Tests for crafting failure when there are insufficient ingredients to craft with
     */
    @Test
    public void execute_insufficientIngredients_throwsCommandException() {
        // need 150 bananas for this crafting but only 99 present in inventory
        CraftItemCommand cic = new CraftItemCommand(APPLE.getName(), new Quantity("100"), Index.fromZeroBased(0));
        String expectedMessage = CraftItemCommand.MESSAGE_INSUFFICIENT_INGREDIENTS;

        assertThrows(CommandException.class, expectedMessage, () -> cic.execute(model));
        assertEquals(sameModel, model);
    }

    /**
     * Tests for equivalency.
     */
    @Test
    public void equals() {
        CraftItemCommand craftItemCommand = new CraftItemCommand("a", new Quantity("1"),
                Index.fromOneBased(1));

        // same object -> returns true
        assertTrue(craftItemCommand.equals(craftItemCommand));

        // same values -> returns true
        CraftItemCommand craftItemCommandCopy = new CraftItemCommand("a", new Quantity("1"),
                Index.fromOneBased(1));
        assertTrue(craftItemCommand.equals(craftItemCommandCopy));

        // different types -> returns false
        assertFalse(craftItemCommand.equals(1));

        // null -> returns false
        assertFalse(craftItemCommand.equals(null));

        CraftItemCommand craftDifferentName = new CraftItemCommand("b", new Quantity("1"),
                Index.fromOneBased(1));
        // different itemName -> returns false
        assertFalse(craftItemCommand.equals(craftDifferentName));

        CraftItemCommand craftDifferentQuantity = new CraftItemCommand("a", new Quantity("2"),
                Index.fromOneBased(1));
        // different quantity -> returns false
        assertFalse(craftItemCommand.equals(craftDifferentQuantity));

        CraftItemCommand craftDifferentIndex = new CraftItemCommand("a", new Quantity("1"),
                Index.fromOneBased(2));
        // different index -> returns false
        assertFalse(craftItemCommand.equals(craftDifferentIndex));

        // different hasDefaultIndex -> returns false
        CraftItemCommand craftNoRecipeIndex = new CraftItemCommand("a", new Quantity("1"));
        assertFalse(craftItemCommand.equals(craftNoRecipeIndex));
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

        // methods added below for test cases to run
        @Override
        public void setItem(Item target, Item editedItem) {
            requireAllNonNull(target, editedItem);

            itemList.setItem(target, editedItem);
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            requireNonNull(predicate);
            filteredItems.setPredicate(predicate);
        }
    }
}
