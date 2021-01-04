package seedu.homerce.logic.commands.revenue;

import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.revenue.TypicalRevenues.getTypicalRevenueTracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.ServiceManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListRevenueCommand.
 */
public class ListRevenueCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            getTypicalRevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(), model
            .getRevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.getRevenueTracker().sortDefaultRevenueList();
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListRevenueCommand(), model, ListRevenueCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
