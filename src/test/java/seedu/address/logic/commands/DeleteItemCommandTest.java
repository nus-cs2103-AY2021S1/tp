package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.item.Item;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

public class DeleteItemCommandTest {
    private Model model = new ModelManager(getTypicalItemList(), getTypicalLocationsList()
            , getTypicalRecipeList(), new UserPrefs());
    
    @Test
    public void execute_validIndexUnfilteredList_success() {
        Item itemToDelete = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(itemToDelete.getName());
        
        String expectedMessage = String.format(DeleteItemCommand.MESSAGE_SUCCESS, itemToDelete);
        
        ModelManager expectedModel = new ModelManager(model.getItemList(), model.getLocationList(),
                model.getRecipeList(), new UserPrefs());
        expectedModel.deleteItem(itemToDelete);
        
        assertCommandSuccess(deleteItemCommand, model, expectedMessage, expectedModel);
    }
    
    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        String itemName = "Someone's Toenail";
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(itemName);
        
        assertCommandFailure(deleteItemCommand, model, DeleteItemCommand.MESSAGE_ITEM_NOT_FOUND);
    }
    
    @Test
    public void execute_deleteAnDeletedItem_throwsCommandException() throws Exception{
        Item itemToDelete = model.getFilteredItemList().get(INDEX_FIRST_ITEM.getZeroBased());
        DeleteItemCommand deleteItemCommand = new DeleteItemCommand(itemToDelete.getName());
        deleteItemCommand.execute(model);
        assertCommandFailure(deleteItemCommand, model, DeleteItemCommand.MESSAGE_ITEM_ALREADY_DELETED);
    }
}
