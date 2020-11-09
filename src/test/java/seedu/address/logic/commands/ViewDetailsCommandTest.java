package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.APPLE_PIE_ITEM;
import static seedu.address.testutil.TypicalRecipes.BANANA_PIE;
import static seedu.address.testutil.TypicalRecipes.FOUND_APPLE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.Messages;
import seedu.address.model.ItemList;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;
import seedu.address.model.item.Item;
import seedu.address.model.item.NameIsExactlyPredicate;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.TypicalRecipes;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Contains unit tests for {@code ViewDetailsCommand}.
 */
public class ViewDetailsCommandTest {
    private ModelStubWithItemAndRecipeList modelStub;
    private ItemList itemList;

    @BeforeEach
    public void setUp() {
        itemList = new ItemList();
        itemList.addItem(APPLE);
        itemList.addItem(APPLE_PIE_ITEM);
        modelStub = new ModelStubWithItemAndRecipeList(itemList, TypicalRecipes.getTypicalRecipeList());
    }

    /**
     * Tests for successful extraction of item and recipes which craft it.
     */
    @Test
    public void execute_success() {
        String expectedMessage = ViewDetailsCommand.MESSAGE_SUCCESS;
        NameIsExactlyPredicate predicate = new NameIsExactlyPredicate(Collections.singletonList(APPLE.getName()));
        ViewDetailsCommand vdc = new ViewDetailsCommand(predicate);

        assertEquals(vdc.execute(modelStub), new CommandResult(expectedMessage, false,
                false, DisplayedInventoryType.DETAILED_ITEM));

        // check the matching item is extracted
        List<Item> expectedItemList = Collections.singletonList(APPLE);
        ObservableList<Item> exObservableItemList = FXCollections.observableList(expectedItemList);
        FilteredList<Item> exFilteredItemList = exObservableItemList.filtered(predicate);
        assertEquals(exFilteredItemList, modelStub.getFilteredItemList());

        // check the relevant recipes are extracted
        List<Recipe> expectedRecipeList = new ArrayList<>();
        expectedRecipeList.add(BANANA_PIE);
        expectedRecipeList.add(FOUND_APPLE);
        ObservableList<Recipe> exObservableRecipeList = FXCollections.observableList(expectedRecipeList);
        FilteredList<Recipe> exFilteredRecipeList = exObservableRecipeList.filtered(PREDICATE_SHOW_ALL_RECIPES);
        assertEquals(exFilteredRecipeList, modelStub.getFilteredRecipeList());
    }

    /**
     * Tests for success when the item name specified in predicate cannot be found.
     */
    @Test
    public void execute_noItemFound() {
        NameIsExactlyPredicate predicate = new NameIsExactlyPredicate(Collections.singletonList("test"));
        ViewDetailsCommand vdc = new ViewDetailsCommand(predicate);

        assertEquals(vdc.execute(modelStub), new CommandResult(String.format(Messages.MESSAGE_NO_ITEM_FOUND,
                predicate.getKeyword()), false, false, DisplayedInventoryType.UNCHANGED));
        assertTrue(modelStub.getFilteredItemList().isEmpty());
    }

    @Test
    public void equals() {
        NameIsExactlyPredicate firstPredicate =
                new NameIsExactlyPredicate(Collections.singletonList("first"));
        NameIsExactlyPredicate secondPredicate =
                new NameIsExactlyPredicate(Collections.singletonList("second"));

        ViewDetailsCommand findFirstCommand = new ViewDetailsCommand(firstPredicate);
        ViewDetailsCommand findSecondCommand = new ViewDetailsCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        ViewDetailsCommand findFirstCommandCopy = new ViewDetailsCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * A Model stub which contains an item and a recipe list.
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
        public void updateFilteredItemList(Predicate<Item> predicate) {
            requireNonNull(predicate);
            filteredItems.setPredicate(predicate);
        }

        @Override
        public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
            requireNonNull(predicate);
            filteredRecipes.setPredicate(predicate);
        }
    }
}
