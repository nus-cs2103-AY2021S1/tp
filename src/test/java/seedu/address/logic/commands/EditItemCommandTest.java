package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_DESCRIPTION_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_QUANTITY_BANANA;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalTags.getTypicalTagSet;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ItemList;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.testutil.ItemBuilder;

public class EditItemCommandTest {

    private ModelStubWithItemList modelStub;
    private ModelStubWithItemList expectedModelStub;
    private Item apple = new ItemBuilder(APPLE).build();
    private ItemList itemList = new ItemList();
    private ItemList expectedItemList = new ItemList();

    @Test
    public void constructor_throwsNullException() {
        assertThrows(NullPointerException.class, () -> new EditItemCommand(null, null));
    }

    /**
     * Tests for successful edit of an item's quantity found in the item list.
     */
    @Test
    public void execute_singleField_success() {
        itemList.addItem(apple);
        modelStub = new ModelStubWithItemList(itemList);

        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_BANANA));
        EditItemCommand eic = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, apple);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_ITEM_QUANTITY_BANANA).build();
        expectedItemList.addItem(editedApple);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        assertCommandSuccess(eic, modelStub, expectedMessage, expectedModelStub);
    }

    /**
     * Tests for successful edit of an item's quantity and description found in the item list.
     */
    @Test
    public void execute_multipleFields_success() {
        itemList.addItem(apple);
        modelStub = new ModelStubWithItemList(itemList);

        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_BANANA));
        descriptor.setDescription(VALID_ITEM_DESCRIPTION_BANANA);
        descriptor.setTags(getTypicalTagSet());
        EditItemCommand eic = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, apple);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE)
                .withQuantity(VALID_ITEM_QUANTITY_BANANA)
                .withDescription(VALID_ITEM_DESCRIPTION_BANANA)
                .withTags(getTypicalTagSet())
                .build();

        expectedItemList.addItem(editedApple);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        assertCommandSuccess(eic, modelStub, expectedMessage, expectedModelStub);
    }

    /**
     * Tests for failure when no field is specified.
     */
    @Test
    public void execute_noFieldSpecified_failure() {
        itemList.addItem(apple);
        modelStub = new ModelStubWithItemList(itemList);

        expectedItemList.addItem(apple);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        // edit command has empty descriptor with no fields specified
        EditItemCommand eic = new EditItemCommand(VALID_ITEM_NAME_APPLE, new EditItemCommand.EditItemDescriptor());

        assertThrows(CommandException.class, () -> eic.execute(modelStub),
                EditItemCommand.MESSAGE_NOT_EDITED);
        assertEquals(expectedModelStub, modelStub);
    }

    /**
     * Tests for failure when the edited item is a duplicate of an existing item.
     */
    @Test
    public void execute_duplicateItem_failure() {
        itemList.addItem(apple);
        Item banana = new ItemBuilder(APPLE).withName(VALID_ITEM_NAME_BANANA).build();
        itemList.addItem(banana);
        modelStub = new ModelStubWithItemList(itemList); // model now has apple and banana

        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setName(banana.getName());
        // This command tries to modify apple to be named banana, which is already in the model
        EditItemCommand eic = new EditItemCommand(VALID_ITEM_NAME_APPLE, descriptor);

        // expected model should be the same as the model
        expectedItemList.addItem(apple);
        expectedItemList.addItem(banana);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        assertThrows(CommandException.class, () -> eic.execute(modelStub),
                EditItemCommand.MESSAGE_DUPLICATE_ITEM);
        assertEquals(expectedModelStub, modelStub);
    }

    @Test
    public void equals() {
        EditItemCommand.EditItemDescriptor descName = new EditItemCommand.EditItemDescriptor();
        descName.setName("x");
        EditItemCommand editName = new EditItemCommand(VALID_ITEM_NAME_APPLE, descName);

        // same values -> returns true
        EditItemCommand.EditItemDescriptor descName2 = new EditItemCommand.EditItemDescriptor();
        descName2.setName("x");
        EditItemCommand editName2 = new EditItemCommand(VALID_ITEM_NAME_APPLE, descName2);
        assertTrue(editName.equals(editName2));

        // same object -> returns true
        assertTrue(editName.equals(editName));

        // null -> returns false
        assertFalse(editName.equals(null));

        // different types -> returns false
        assertFalse(editName.equals(new ListItemCommand()));

        // different name -> returns false
        assertFalse(editName.equals(new EditItemCommand(VALID_ITEM_NAME_BANANA, descName)));

        // different descriptor -> returns false
        EditItemCommand.EditItemDescriptor descQuant = new EditItemCommand.EditItemDescriptor();
        descQuant.setQuantity(new Quantity("1"));
        assertFalse(editName.equals(new EditItemCommand(VALID_ITEM_NAME_APPLE, descQuant)));
    }

    /**
     * A Model stub which contains an item list.
     */
    public static class ModelStubWithItemList extends ModelStub {

        private final ItemList itemList;
        private final FilteredList<Item> filteredItems;

        public ModelStubWithItemList(ReadOnlyItemList itemList) {
            this.itemList = new ItemList(itemList);
            filteredItems = new FilteredList<>(this.itemList.getItemList());
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
        public void setItem(Item target, Item editedItem) {
            requireAllNonNull(target, editedItem);

            itemList.setItem(target, editedItem);
        }

        @Override
        public void updateFilteredItemList(Predicate<Item> predicate) {
            requireNonNull(predicate);
            filteredItems.setPredicate(predicate);
        }

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return itemList.hasItem(item);
        }
    }
}
