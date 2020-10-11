package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.model.ItemList;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.item.Item;
import seedu.address.testutil.TypicalItems;
import seedu.address.ui.DisplayedInventoryType;

public class ListItemCommandTest {

    private final ListItemCommand listItemCommand = new ListItemCommand();

    /**
     * Tests for correct execution and displayed message with non-empty item list.
     */
    @Test
    public void execute_success() {
        ModelStubWithItemList modelStub = new ModelStubWithItemList(TypicalItems.getTypicalItemList());
        CommandResult expectedCommandResult = new CommandResult(ListItemCommand.MESSAGE_SUCCESS,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(listItemCommand, modelStub, expectedCommandResult, modelStub);
    }

    /**
     * Tests for correct execution and displayed message with empty item list.
     */
    @Test
    public void execute_showEmptyItemList() {
        ModelStubWithItemList modelStub = new ModelStubWithItemList(new ItemList());
        CommandResult expectedCommandResult = new CommandResult(ListItemCommand.MESSAGE_NO_ITEMS,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(listItemCommand, modelStub, expectedCommandResult, modelStub);
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
    }
}
