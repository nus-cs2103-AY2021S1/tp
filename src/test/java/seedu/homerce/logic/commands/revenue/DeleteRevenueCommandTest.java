package seedu.homerce.logic.commands.revenue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_FIRST_REVENUE;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_SECOND_REVENUE;
import static seedu.homerce.testutil.revenue.TypicalRevenues.getTypicalRevenueTracker;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.service.Duration;
import seedu.homerce.model.service.Service;
import seedu.homerce.model.util.attributes.Amount;
import seedu.homerce.model.util.attributes.Title;
import seedu.homerce.model.util.nonuniquelist.exceptions.ItemNotFoundException;
import seedu.homerce.testutil.revenue.RevenueBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteRevenueCommand}.
 */
public class DeleteRevenueCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
        getTypicalRevenueTracker(), new ExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_deleteRevenueThatExistInList_success() {
        Revenue revenueToDelete = model.getFilteredRevenueList().get(INDEX_FIRST_REVENUE.getZeroBased());

        DeleteRevenueCommand deleteRevenueCommand = new DeleteRevenueCommand(revenueToDelete);

        String expectedMessage = String.format(DeleteRevenueCommand.MESSAGE_DELETE_REVENUE_SUCCESS, revenueToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            model.getRevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.deleteRevenue(revenueToDelete);

        assertCommandSuccess(deleteRevenueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteRevenueThatNotInList_noDeletion() {
        Revenue revenueToDelete = new RevenueBuilder()
            .withService(new Service(new Title("Cut Hair"), new Duration(8.0), new Amount(8.00)), "SC995")
            .withDate("28-10-2020").build();

        ModelManager expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            model.getRevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        Exception exception = assertThrows(ItemNotFoundException.class, () -> {
            expectedModel.deleteRevenue(revenueToDelete);
        });

        assertEquals(new ItemNotFoundException().toString(), exception.toString());
    }

    @Test
    public void equals() {
        Revenue revenueToDelete = model.getFilteredRevenueList().get(INDEX_FIRST_REVENUE.getZeroBased());
        Revenue revenueToDeleteSecond = model.getFilteredRevenueList().get(INDEX_SECOND_REVENUE.getZeroBased());
        DeleteRevenueCommand deleteFirstCommand = new DeleteRevenueCommand(revenueToDelete);
        DeleteRevenueCommand deleteSecondCommand = new DeleteRevenueCommand(revenueToDeleteSecond);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteRevenueCommand deleteFirstCommandCopy = new DeleteRevenueCommand(revenueToDelete);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different revenue -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
