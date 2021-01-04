package seedu.homerce.logic.commands.revenue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.commons.core.Messages.MESSAGE_REVENUE_LISTED_OVERVIEW;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.revenue.TypicalRevenues.getTypicalRevenueTracker;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.revenue.predicate.RevenueDatePredicate;
import seedu.homerce.model.util.attributes.Date;

/**
 * Contains integration tests (interaction with the Model) for {@code FindRevenueCommand}.
 */
public class FindRevenueCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
        getTypicalRevenueTracker(), new ExpenseTracker(), new AppointmentManager());
    private Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
        getTypicalRevenueTracker(), new ExpenseTracker(), new AppointmentManager());

    @Test
    public void equals() {
        RevenueDatePredicate firstPredicate =
            new RevenueDatePredicate(new Date("25-10-2020"));
        RevenueDatePredicate secondPredicate =
            new RevenueDatePredicate(new Date("26-10-2020"));

        FindRevenueCommand findFirstCommand = new FindRevenueCommand(firstPredicate);
        FindRevenueCommand findSecondCommand = new FindRevenueCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindRevenueCommand findFirstCommandCopy = new FindRevenueCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_dateWithNoRevenue_noRevenueFound() {
        String expectedMessage = String.format(MESSAGE_REVENUE_LISTED_OVERVIEW, 0);
        RevenueDatePredicate predicate = new RevenueDatePredicate(new Date("25-10-1000"));
        FindRevenueCommand command = new FindRevenueCommand(predicate);
        expectedModel.updateFilteredRevenueList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredRevenueList());
    }
}
