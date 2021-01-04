package seedu.homerce.logic.commands.client;

import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.testutil.client.ClientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddClientCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), getTypicalClientManager(), new ServiceManager(),
                new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
    }

    @Test
    public void execute_newClient_success() {
        Client validClient = new ClientBuilder().build();

        Model expectedModel = new ModelManager(new UserPrefs(), model.getClientManager(), new ServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.addClient(validClient);

        assertCommandSuccess(new AddClientCommand(validClient), model,
                String.format(AddClientCommand.MESSAGE_SUCCESS, validClient), expectedModel);
    }

    @Test
    public void execute_duplicateClient_throwsCommandException() {
        Client clientInList = model.getClientManager().getClientList().get(0);
        assertCommandFailure(new AddClientCommand(clientInList), model, AddClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

}
