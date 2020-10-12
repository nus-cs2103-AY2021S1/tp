package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalItems;
import seedu.address.ui.DisplayedInventoryType;

public class ListItemCommandIntegrationTest {

    private final ListItemCommand listItemCommand = new ListItemCommand();

    /**
     * Tests for correct execution and displayed message with non-empty item list.
     */
    @Test
    public void execute_success() {
        Model model = new ModelManager(TypicalItems.getTypicalItemList(), new LocationList(),
                new RecipeList(), new UserPrefs());
        model.setItemList(TypicalItems.getTypicalItemList());
        CommandResult expectedCommandResult = new CommandResult(ListItemCommand.MESSAGE_SUCCESS,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(listItemCommand, model, expectedCommandResult, model);
    }

    /**
     * Tests for correct execution and displayed message with empty item list.
     */
    @Test
    public void execute_showEmptyItemList() {
        Model model = new ModelManager(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
        CommandResult expectedCommandResult = new CommandResult(ListItemCommand.MESSAGE_NO_ITEMS,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(listItemCommand, model, expectedCommandResult, model);
    }
}
