package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.item.Item;
import seedu.address.model.item.NameMatchesKeywordsPredicate;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.RecipeBuilder;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalItems;

import java.util.Collections;
import java.util.HashSet;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.*;

public class CraftItemCommandTest {

    private ModelStubWithItemAndRecipeList model;
    private ModelStubWithItemAndRecipeList expectedModel;
    private RecipeList recipeList;

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new CraftItemCommand(null, new Quantity("0"), null));
    }

    @BeforeEach
    public void setUp() {
        ItemList itemlist = new ItemList();
        itemlist.addItem(APPLE);
        itemlist.addItem(BANANA);

        IngredientList ingredients = new IngredientList();
        ingredients.add(new Ingredient(1,3));
        // recipe to create 2 apples from 3 bananas
        Recipe recipe = new RecipeBuilder().withId(1).withIngredients(ingredients).withQuantity("2").build();
        recipeList = new RecipeList();
        recipeList.addRecipe(recipe);

        model = new ModelStubWithItemAndRecipeList(itemlist, recipeList);
    }

    @Test
    public void execute_craft_success() {
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
        expectedModel = new ModelStubWithItemAndRecipeList(expectedItemList, recipeList);

        assertCommandSuccess(cic, model, expectedMessage, expectedModel);
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