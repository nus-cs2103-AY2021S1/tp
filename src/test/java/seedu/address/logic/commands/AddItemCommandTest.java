package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ItemList;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemPrecursor;
import seedu.address.testutil.ItemBuilder;
import seedu.address.testutil.ItemPrecursorBuilder;

public class AddItemCommandTest {
    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItemCommand(null));
    }

    /**
     * Tests for successful adding of item.
     */
    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItemAdded modelStub = new ModelStubAcceptingItemAdded();
        ItemPrecursor validItemPrecursor = new ItemPrecursorBuilder().build();
        Item validItem = new ItemBuilder().build();

        CommandResult commandResult = new AddItemCommand(validItemPrecursor).execute(modelStub);
        assertEquals(String.format(AddItemCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
        assertEquals(Collections.singletonList(validItem).size(), modelStub.itemsAdded.size());
        assertTrue(validItem.isSameItem(modelStub.itemsAdded.get(0)));
    }

    /**
     * Tests for detecting duplicate items.
     */
    @Test
    public void execute_duplicateItem_throwsCommandException() {
        ItemPrecursor validItemPrecursor = new ItemPrecursorBuilder().build();
        Item validItem = new ItemBuilder().build();

        AddItemCommand addItemCommand = new AddItemCommand(validItemPrecursor);
        ModelStub modelStub = new ModelStubWithItem(validItem);

        assertThrows(CommandException.class,
                AddItemCommand.MESSAGE_DUPLICATE_ITEM, () -> addItemCommand.execute(modelStub));
    }

    /**
     * Tests for equivalency.
     */
    @Test
    public void equals() {
        ItemPrecursor apple = new ItemPrecursorBuilder().withName("Apple").build();
        ItemPrecursor banana = new ItemPrecursorBuilder().withName("Banana").build();
        AddItemCommand addAppleCommand = new AddItemCommand(apple);
        AddItemCommand addBananaCommand = new AddItemCommand(banana);

        // same object -> returns true
        assertTrue(addAppleCommand.equals(addAppleCommand));

        // same values -> returns true
        AddItemCommand addAppleCommandCopy = new AddItemCommand(apple);
        assertTrue(addAppleCommand.equals(addAppleCommandCopy));

        // different types -> returns false
        assertFalse(addAppleCommand.equals(1));

        // null -> returns false
        assertFalse(addAppleCommand.equals(null));

        // different item -> returns false
        assertFalse(addAppleCommand.equals(addBananaCommand));
    }

    /**
     * A Model stub that contains a single item.
     */
    private class ModelStubWithItem extends ModelStub {
        private final Item item;

        ModelStubWithItem(Item item) {
            requireNonNull(item);
            this.item = item;
        }

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return this.item.isSameItem(item);
        }
    }

    /**
     * A Model stub that always accepts the item being added.
     */
    private class ModelStubAcceptingItemAdded extends ModelStub {
        final ArrayList<Item> itemsAdded = new ArrayList<>();

        @Override
        public boolean hasItem(Item item) {
            requireNonNull(item);
            return itemsAdded.stream().anyMatch(item::isSameItem);
        }

        @Override
        public void addItem(Item item) {
            requireNonNull(item);
            itemsAdded.add(item);
        }

        @Override
        public ReadOnlyItemList getItemList() {
            return new ItemList();
        }
    }

}
