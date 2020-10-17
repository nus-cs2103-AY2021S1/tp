package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditItemCommandTest.ModelStubWithItemList;
import seedu.address.model.ItemList;
import seedu.address.model.item.Item;
import seedu.address.testutil.ItemBuilder;

public class AddQuantityToItemCommandTest {

    private ModelStubWithItemList modelStub;
    private ModelStubWithItemList expectedModelStub;
    private Item apple = new ItemBuilder(APPLE).withQuantity("50").build();
    private ItemList itemList = new ItemList();
    private ItemList expectedItemList = new ItemList();

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new AddQuantityToItemCommand(null, 0));
    }

    /**
     * Tests for successful increase of an item's quantity found in the item list.
     */
    @Test
    public void execute_increaseQuantity_success() {
        itemList.addItem(apple);
        modelStub = new ModelStubWithItemList(itemList);

        AddQuantityToItemCommand aic = new AddQuantityToItemCommand("Apple", 10);
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, apple);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE).withQuantity("60").build();
        expectedItemList.addItem(editedApple);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        assertCommandSuccess(aic, modelStub, expectedMessage, expectedModelStub);
    }

    /**
     * Tests for successful decrease of an item's quantity found in the item list.
     */
    @Test
    public void execute_decreaseQuantity_success() {
        itemList.addItem(apple);
        modelStub = new ModelStubWithItemList(itemList);

        AddQuantityToItemCommand aic = new AddQuantityToItemCommand("Apple", -10);
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, apple);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE).withQuantity("40").build();
        expectedItemList.addItem(editedApple);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        assertCommandSuccess(aic, modelStub, expectedMessage, expectedModelStub);
    }

    @Test
    public void equals() {
        AddQuantityToItemCommand aic1 = new AddQuantityToItemCommand("Apple", 10);
        AddQuantityToItemCommand aic2 = new AddQuantityToItemCommand("Apple", 10);

        // same values -> returns true
        assertEquals(aic1, aic2);

        // same object -> returns true
        assertEquals(aic1, aic1);

        // null -> returns false
        assertNotEquals(null, aic1);

        // different types -> returns false
        assertNotEquals(aic1, new ListItemCommand());

        // different quantity -> returns false
        AddQuantityToItemCommand aic3 = new AddQuantityToItemCommand("Apple", 20);
        assertNotEquals(aic1, aic3);

        // different name -> returns false
        AddQuantityToItemCommand aic4 = new AddQuantityToItemCommand("Banana", 10);
        assertNotEquals(aic1, aic4);
    }
}
