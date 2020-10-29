package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.BANANA;
import static seedu.address.testutil.TypicalItems.PEAR;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

import java.util.ArrayList;
import java.util.List;

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
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.ItemBuilder;


public class DeleteItemCommandTest {
    private ModelStubWithItemAndRecipeList modelStub;
    private ModelStubWithItemAndRecipeList expectedModelStub;

    @BeforeEach
    public void setUp() {
        modelStub = new ModelStubWithItemAndRecipeList(getTypicalItemList(), getTypicalRecipeList());
        expectedModelStub = new ModelStubWithItemAndRecipeList(getTypicalItemList(), getTypicalRecipeList());
    }

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new DeleteItemCommand(null));
    }

    /**
     * Tests for successful deletion of a item found in the item list
     * with no connecting recipes
     */
    @Test
    public void execute_isolatedItemDeletion_success() {
        DeleteItemCommand dic = new DeleteItemCommand(PEAR.getName());
        expectedModelStub.deleteItem(PEAR);
        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_SUCCESS, PEAR);
        assertCommandSuccess(dic, modelStub, expectedMessage, expectedModelStub);
    }

    @Test
    public void execute_itemNotFound_throwsCommandException() {
        DeleteItemCommand dic = new DeleteItemCommand("Bob's Toe nail");
        String expectedMessage = String.format(Messages.MESSAGE_NO_ITEM_FOUND, "Bob's Toe nail");

        assertThrows(CommandException.class, expectedMessage, () -> dic.execute(modelStub));
        assertEquals(modelStub, expectedModelStub);
    }

    /**
     * Tests for successful deletion of a item found in the item list with recipes
     */
    @Test
    public void execute_itemDeletion_success() {
        Item itemToDelete = new ItemBuilder(APPLE).build();
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(itemToDelete.getName());
        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_SUCCESS, itemToDelete);

        // Perform a manual deletion of all matching recipes
        expectedModelStub.deleteItem(itemToDelete);
        List<Recipe> recipeList = new ArrayList<>(expectedModelStub.getFilteredRecipeList());
        recipeList.removeIf(y -> !y.getProductName().equals(itemToDelete.getName())
                && y.getIngredients()
                .asUnmodifiableObservableList()
                .stream()
                .noneMatch(z -> z.isItem(itemToDelete.getId())));

        recipeList.forEach(expectedModelStub::deleteRecipe);

        assertCommandSuccess(deleteItemCommand, modelStub, expectedMessage, expectedModelStub);
    }

    @Test
    public void execute_itemDeleted() throws Exception {
        DeleteItemCommand dic = new DeleteItemCommand(PEAR.getName());
        dic.execute(modelStub);

        //Assert that the item not contained within the model
        assertFalse(modelStub.getItemList().getItemList().contains(PEAR));
    }

    @Test
    public void equals() {
        DeleteItemCommand deleteAppleCommand = new DeleteItemCommand(APPLE.getName());
        DeleteItemCommand deleteApple2Command = new DeleteItemCommand(APPLE.getName());
        DeleteItemCommand deleteBananaCommand = new DeleteItemCommand(BANANA.getName());

        // same object -> returns true
        assertTrue(deleteAppleCommand.equals(deleteAppleCommand));

        // same values -> returns true
        assertTrue(deleteAppleCommand.equals(deleteApple2Command));

        // different types -> returns false
        assertFalse(deleteAppleCommand.equals(1));

        // null -> returns false
        assertFalse(deleteAppleCommand.equals(null));

        // different product name -> returns false
        assertFalse(deleteAppleCommand.equals(deleteBananaCommand));
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
        public void deleteRecipe(Recipe target) {
            recipeList.deleteRecipe(target);
        }

        @Override
        public ReadOnlyItemList getItemList() {
            return itemList;
        }

        @Override
        public ObservableList<Item> getFilteredItemList() {
            return filteredItems;
        }

        @Override
        public void deleteItem(Item item) {
            itemList.deleteItem(item);
        }
    }
}
