package seedu.homerce.logic.commands.service;

import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.service.TypicalServices.getTypicalServiceManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.service.Service;
import seedu.homerce.testutil.service.ServiceBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddServiceCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new ClientManager(), getTypicalServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
    }

    @Test
    public void execute_newService_success() {
        // Service code is SC003 as the typicalServiceManager has 3 existing services. New service will have code SC003
        Service validService = new ServiceBuilder().withServiceCode("SC003").withTitle("Test Title").build();

        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), model.getServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.addService(validService);

        assertCommandSuccess(new AddServiceCommand(validService), model,
            String.format(AddServiceCommand.MESSAGE_SUCCESS, validService), expectedModel);
    }
}
