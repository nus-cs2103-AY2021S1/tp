package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertInventoryCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.ItemBuilder;

public class DeleteItemCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalItemList(), getTypicalLocationsList(),
                getTypicalRecipeList(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Item itemToDelete = new ItemBuilder(APPLE).build();
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(itemToDelete.getName());
        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_SUCCESS, itemToDelete);
        ModelManager expectedModel = new ModelManager(model.getItemList(), model.getLocationList(),
                model.getRecipeList(), new UserPrefs());

        // Perform a manual deletion of all matching recipes
        expectedModel.deleteItem(itemToDelete);
        List<Recipe> recipeList = new ArrayList<>(expectedModel.getFilteredRecipeList());
        recipeList.removeIf(y -> !y.getProductName().equals(itemToDelete.getName())
                && y.getIngredients()
                .asUnmodifiableObservableList()
                .stream()
                .noneMatch(z -> z.isItem(itemToDelete.getId())));

        for (Recipe r : recipeList) {
            for (Item i : model.getFilteredItemList()) {
                if (i.getRecipeIds().contains(r.getId())) {
                    i.removeRecipeId(r.getId());
                }
            }
        }
        recipeList.forEach(expectedModel::deleteRecipe);

        assertCommandSuccess(deleteItemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        String itemName = "Someone's Toenail";
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(itemName);
        String expectedMessage = String.format(Messages.MESSAGE_NO_ITEM_FOUND, itemName);

        assertInventoryCommandFailure(deleteItemCommand, model, expectedMessage);
    }

    @Test
    public void execute_deleteAnDeletedItem_throwsCommandException() throws Exception {
        Item itemToDelete = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteItemCommand dc = new DeleteItemCommand(itemToDelete.getName());
        dc.execute(model); // Expected to succeed
        DeleteItemCommand d2 = new DeleteItemCommand(itemToDelete.getName());
        String expectedMessage = String.format(Messages.MESSAGE_NO_ITEM_FOUND, itemToDelete.getName());

        // Delete an item a second time.
        assertThrows(CommandException.class, expectedMessage, () -> d2.execute(model));
    }
}
