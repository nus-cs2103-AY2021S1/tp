package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.testutil.ItemBuilder;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.testutil.TypicalIndexes.*;
import static seedu.address.testutil.TypicalItems.APPLE;

public class EditItemCommandTest {

    private ModelStubWithItemList modelStub;
    private ModelStubWithItemList expectedModelStub;
    private Item apple;
    private ItemList itemList;
    private ItemList expectedItemList;

    @BeforeEach
    public void setUp() {
        apple = new ItemBuilder(APPLE).build();
        itemList = new ItemList();
        expectedItemList = new ItemList();
    }

    /**
     * Tests for successful edit of an item found in the item list.
     */
    @Test
    public void execute_success() {
        itemList.addItem(apple);
        modelStub = new ModelStubWithItemList(itemList);

        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_BANANA));
        EditItemCommand eic = new EditItemCommand(Index.fromZeroBased(0), descriptor);
        String expectedMessage = String.format(EditItemCommand.MESSAGE_EDIT_ITEM_SUCCESS, apple);

        // expected model should contain the edited apple
        Item editedApple = new ItemBuilder(APPLE).withQuantity(VALID_ITEM_QUANTITY_BANANA).build();
        expectedItemList.addItem(editedApple);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        assertCommandSuccess(eic, modelStub, expectedMessage, expectedModelStub);
    }

    /**
     * Tests for failure when index is out of range of the number of items.
     */
    @Test
    public void execute_invalidIndex_failure() {
        itemList.addItem(apple);
        modelStub = new ModelStubWithItemList(itemList);

        Index outOfBoundIndex = Index.fromOneBased(modelStub.getFilteredItemList().size() + 1);
        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setQuantity(new Quantity(VALID_ITEM_QUANTITY_BANANA));
        EditItemCommand eic = new EditItemCommand(outOfBoundIndex, descriptor);

        // expected model should be the same as the model
        expectedItemList.addItem(apple);
        expectedModelStub = new ModelStubWithItemList(expectedItemList);

        assertThrows(CommandException.class, () -> eic.execute(modelStub),
                EditItemCommand.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        assertEquals(expectedModelStub, modelStub);
    }

    /**
     * Tests for failure when the edited item is a duplicate of an existing item.
     */
    @Test
    public void execute_duplicateItem_failure() {
        itemList.addItem(apple);
        Item banana = new ItemBuilder(APPLE).withName("Banana").build();
        itemList.addItem(banana);
        modelStub = new ModelStubWithItemList(itemList); // model now has apple and banana

        EditItemCommand.EditItemDescriptor descriptor = new EditItemCommand.EditItemDescriptor();
        descriptor.setName(banana.getName());
        // This command tries to modify apple to be named banana, which is already in the model
        EditItemCommand eic = new EditItemCommand(Index.fromZeroBased(0), descriptor);

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
        EditItemCommand editName = new EditItemCommand(INDEX_FIRST_ITEM, descName);

        // same values -> returns true
        EditItemCommand.EditItemDescriptor descName2 = new EditItemCommand.EditItemDescriptor();
        descName2.setName("x");
        EditItemCommand editName2 = new EditItemCommand(INDEX_FIRST_ITEM, descName2);
        assertTrue(editName.equals(editName2));

        // same object -> returns true
        assertTrue(editName.equals(editName));

        // null -> returns false
        assertFalse(editName.equals(null));

        // different types -> returns false
        assertFalse(editName.equals(new ListCommand()));

        // different index -> returns false
        assertFalse(editName.equals(new EditItemCommand(INDEX_SECOND_ITEM, descName)));

        // different descriptor -> returns false
        EditItemCommand.EditItemDescriptor descQuant = new EditItemCommand.EditItemDescriptor();
        descQuant.setQuantity(new Quantity("1"));
        assertFalse(editName.equals(new EditItemCommand(INDEX_FIRST_ITEM, descQuant)));
    }

    /**
     * A Model stub which contains an item list.
     */
    private class ModelStubWithItemList extends ModelStub {

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