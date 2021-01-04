package seedu.homerce.logic.commands.client;

import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListClientCommand.
 */
public class ListClientCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), getTypicalClientManager(), new ServiceManager(), new RevenueTracker(),
            new ExpenseTracker(), new AppointmentManager());
        expectedModel = new ModelManager(new UserPrefs(), model.getClientManager(),
                new ServiceManager(), new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListClientCommand(), model, ListClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        assertCommandSuccess(new ListClientCommand(), model, ListClientCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
