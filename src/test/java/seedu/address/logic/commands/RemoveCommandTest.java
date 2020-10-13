package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ITEM;
import static seedu.address.testutil.TypicalItems.getTypicalInventoryBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.UserPrefs;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.inventorymodel.InventoryModelManager;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.testutil.ItemBuilder;


public class RemoveCommandTest {

    private InventoryModel inventoryModel = new InventoryModelManager(getTypicalInventoryBook(), new UserPrefs());

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        Item currentItem = new ItemBuilder().withName("Chicken").withQuantity("12").build();
        assertThrows(NullPointerException.class, () -> new RemoveCommand(null, null));
        assertThrows(NullPointerException.class, () -> new RemoveCommand(null, currentItem.getQuantity()));
        assertThrows(NullPointerException.class, () -> new RemoveCommand(INDEX_FIRST_ITEM, null));
    }

    @Test
    public void execute_itemAcceptedByModel_removeSuccessful() throws CommandException {
        Item afterRemoveItem = new ItemBuilder().withName("Chicken")
                .withSupplier("Giant")
                .withQuantity("2")
                .withTags("meat")
                .build();
        Quantity quantity = new ItemBuilder().withQuantity("10").build().getQuantity();

        CommandResult commandResult = new RemoveCommand(INDEX_FIRST_ITEM, quantity).execute(inventoryModel);

        assertEquals(String.format(RemoveCommand.MESSAGE_EDIT_ITEM_SUCCESS, afterRemoveItem),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        RemoveCommand removeOne = new RemoveCommand(INDEX_FIRST_ITEM, new Quantity("10"));
        RemoveCommand removeTwo = new RemoveCommand(INDEX_SECOND_ITEM, new Quantity("10"));
        RemoveCommand removeThree = new RemoveCommand(INDEX_SECOND_ITEM, new Quantity("20"));

        assertTrue(removeOne.equals(removeOne));

        assertFalse(removeOne.equals(removeTwo));

        assertFalse(removeTwo.equals(removeThree));
    }

}
