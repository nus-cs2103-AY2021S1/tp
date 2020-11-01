package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECREASED_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCREASED_QUANTITY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_ORIGINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_DECREMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_INCREMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_INT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_INT_ALT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditItemCommandTest.ModelStubWithItemList;
import seedu.address.model.ItemList;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.testutil.ItemBuilder;

public class AddQuantityToItemCommandTest {

    private ModelStubWithItemList modelStub;
    private ModelStubWithItemList expectedModelStub;

    private Item apple;
    private ItemList itemList;
    private ItemList expectedItemList;
    private Quantity incrementedQuantity = new Quantity(VALID_QUANTITY_INCREMENT + "");
    private Quantity decrementedQuantity = new Quantity(VALID_QUANTITY_DECREMENT + "", true);
    private Quantity validQuantity = new Quantity(VALID_QUANTITY_INT + "");
    private Quantity validQuantityAlt = new Quantity(VALID_QUANTITY_INT_ALT + "");

    @BeforeEach
    public void setUp() {
        apple = new ItemBuilder(APPLE).withQuantity(VALID_ITEM_QUANTITY_ORIGINAL).build();
        itemList = new ItemList();
        expectedItemList = new ItemList();
    }

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () ->
                new AddQuantityToItemCommand(null, new Quantity(0 + "")));
    }

    /**
     * Tests for successful increase of an item's quantity found in the item list.
     */
    @Test
    public void execute_increaseQuantity_success() {
        itemList.addItem(apple);
        modelStub = new ModelStubWithItemList(itemList);

        AddQuantityToItemCommand aic = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE, incrementedQuantity);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(apple).withQuantity(VALID_INCREASED_QUANTITY).build();

        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedApple);
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

        AddQuantityToItemCommand aic = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE, decrementedQuantity);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_DECREASED_QUANTITY).build();

        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, editedApple);

        expectedItemList.addItem(editedApple);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        assertCommandSuccess(aic, modelStub, expectedMessage, expectedModelStub);
    }

    @Test
    public void equals() {
        AddQuantityToItemCommand aic1 = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE, validQuantity);
        AddQuantityToItemCommand aic2 = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE, validQuantity);

        // same values -> returns true
        assertEquals(aic1, aic2);

        // same object -> returns true
        assertEquals(aic1, aic1);

        // null -> returns false
        assertNotEquals(null, aic1);

        // different types -> returns false
        assertNotEquals(aic1, new ListItemCommand());

        // different quantity -> returns false
        AddQuantityToItemCommand aic3 = new AddQuantityToItemCommand(VALID_ITEM_NAME_APPLE, validQuantityAlt);
        assertNotEquals(aic1, aic3);

        // different name -> returns false
        AddQuantityToItemCommand aic4 = new AddQuantityToItemCommand(VALID_ITEM_NAME_BANANA, validQuantity);
        assertNotEquals(aic1, aic4);
    }
}
