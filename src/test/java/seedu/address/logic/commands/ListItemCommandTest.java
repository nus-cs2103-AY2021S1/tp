package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.model.ItemList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalItems;
import seedu.address.ui.DisplayedInventoryType;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalLocations.getTypicalLocationsList;

public class ListItemCommandTest {

    private Model model;
    private ListItemCommand listItemCommand = new ListItemCommand();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new ItemList(), getTypicalLocationsList(),
                new RecipeList(), new UserPrefs());
    }

    /**
     * Tests for correct execution and displayed message with non-empty item list.
     */
    @Test
    public void execute_success() {
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
        CommandResult expectedCommandResult = new CommandResult(ListItemCommand.MESSAGE_NO_ITEMS,
                false, false, DisplayedInventoryType.ITEMS);
        assertCommandSuccess(listItemCommand, model, expectedCommandResult, model);
    }
}