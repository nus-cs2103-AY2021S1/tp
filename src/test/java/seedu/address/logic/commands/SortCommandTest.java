package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.food.MenuItem;
import seedu.address.testutil.TypicalModel;

public class SortCommandTest {
    private final Model model = TypicalModel.getModelManagerWithMenu();
    private final Model expectedModel = TypicalModel.getModelManagerWithMenu();

    @Test
    public void constructor_nullString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null, null));
    }

    @Test
    public void equals() {
        SortCommand sortCommand0 = new SortCommand("n", "a");
        SortCommand sortCommand1 = new SortCommand("n", "t");

        //identity = same object should return true
        assertTrue(sortCommand0.equals(sortCommand0));

        //same values -> returns true
        SortCommand sortCommandCopy = new SortCommand("n", "a");
        assertTrue(sortCommand0.equals(sortCommandCopy));

        //different types -> return false
        assertFalse(sortCommand0.equals(0));

        //null check -> return false
        assertFalse(sortCommand0.equals(null));

        //different values -> return false
        assertFalse(sortCommand0.equals(sortCommand1));
    }

    @Test
    public void execute_validSortCommand_success() {
        SortCommand sortCommand0 = new SortCommand("n", "a");
        String expectedMessage = Messages.MESSAGE_FOOD_SORTED;
        expectedModel.updateFilteredMenuItemList(Comparator.comparing(MenuItem::getName), true);
        assertCommandSuccess(sortCommand0, model, expectedMessage, expectedModel);
        SortCommand sortCommand1 = new SortCommand("n", "d");
        expectedModel.updateFilteredMenuItemList(Comparator.comparing(MenuItem::getName).reversed(), false);
        assertCommandSuccess(sortCommand1, model, expectedMessage, expectedModel);
        SortCommand sortCommand2 = new SortCommand("p", "a");
        expectedModel.updateFilteredMenuItemList(Comparator.comparing(MenuItem::getPrice), true);
        assertCommandSuccess(sortCommand2, model, expectedMessage, expectedModel);
        SortCommand sortCommand3 = new SortCommand("p", "d");
        expectedModel.updateFilteredMenuItemList(Comparator.comparing(MenuItem::getPrice).reversed(), false);
        assertCommandSuccess(sortCommand3, model, expectedMessage, expectedModel);
    }

    @Test
    public void vendorNotSelected_throwsCommandException() {
        Model model = new ModelManager();
        SortCommand sortCommand = new SortCommand("n", "d");
        assertCommandFailure(sortCommand, model, Messages.MESSAGE_VENDOR_NOT_SELECTED);
    }
}
