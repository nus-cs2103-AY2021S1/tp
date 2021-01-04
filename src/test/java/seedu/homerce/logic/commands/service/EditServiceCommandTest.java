package seedu.homerce.logic.commands.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.DESC_LASH_LIFT;
import static seedu.homerce.logic.commands.CommandTestUtil.DESC_MANICURE;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_DURATION_LASH_LIFT;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_TITLE_MANICURE;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.logic.commands.CommandTestUtil.showServiceAtIndex;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_FIRST_SERVICE;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_SECOND_SERVICE;
import static seedu.homerce.testutil.service.TypicalServices.getTypicalServiceManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.service.Service;
import seedu.homerce.testutil.service.EditServiceDescriptorBuilder;
import seedu.homerce.testutil.service.ServiceBuilder;

public class EditServiceCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), getTypicalServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Service editedService = new ServiceBuilder().withTitle("Test Service Title").build();
        EditServiceCommand.EditServiceDescriptor descriptor = new EditServiceDescriptorBuilder(editedService).build();
        EditServiceCommand editServiceCommand = new EditServiceCommand(INDEX_FIRST_CLIENT, descriptor);

        String expectedMessage = String.format(EditServiceCommand.MESSAGE_EDIT_SERVICE_SUCCESS, editedService);

        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(),
            new ServiceManager(model.getServiceManager()), new RevenueTracker(), new ExpenseTracker(),
            new AppointmentManager());
        expectedModel.setService(model.getFilteredServiceList().get(0), editedService);

        assertCommandSuccess(editServiceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastService = Index.fromOneBased(model.getFilteredServiceList().size());
        Service lastService = model.getFilteredServiceList().get(indexLastService.getZeroBased());

        ServiceBuilder serviceInList = new ServiceBuilder(lastService);
        Service editedService = serviceInList.withTitle("Test Service Title")
            .withDuration(VALID_DURATION_LASH_LIFT)
            .build();

        EditServiceCommand.EditServiceDescriptor descriptor = new EditServiceDescriptorBuilder()
            .withTitle("Test Service Title").withDuration(VALID_DURATION_LASH_LIFT)
            .build();

        EditServiceCommand editServiceCommand = new EditServiceCommand(indexLastService, descriptor);

        String expectedMessage = String.format(EditServiceCommand.MESSAGE_EDIT_SERVICE_SUCCESS, editedService);

        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(),
            new ServiceManager(model.getServiceManager()), new RevenueTracker(),
            new ExpenseTracker(), new AppointmentManager());
        expectedModel.setService(lastService, editedService);

        assertCommandSuccess(editServiceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditServiceCommand editServiceCommand = new EditServiceCommand(INDEX_FIRST_SERVICE,
            new EditServiceCommand.EditServiceDescriptor());
        assertCommandFailure(editServiceCommand, model, Messages.MESSAGE_NOT_EDITED);
    }

    @Test
    public void execute_filteredList_success() {
        showServiceAtIndex(model, INDEX_FIRST_SERVICE);

        Service serviceInFilteredList = model.getFilteredServiceList().get(INDEX_FIRST_SERVICE.getZeroBased());
        Service editedService = new ServiceBuilder(serviceInFilteredList).withTitle("Test Title").build();
        EditServiceCommand editServiceCommand = new EditServiceCommand(INDEX_FIRST_SERVICE,
            new EditServiceDescriptorBuilder().withTitle("Test Title").build());

        String expectedMessage = String.format(EditServiceCommand.MESSAGE_EDIT_SERVICE_SUCCESS, editedService);

        assertCommandSuccess(editServiceCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidServiceIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredServiceList().size() + 1);
        EditServiceCommand.EditServiceDescriptor descriptor = new EditServiceDescriptorBuilder()
            .withTitle(VALID_TITLE_MANICURE).build();
        EditServiceCommand editServiceCommand = new EditServiceCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editServiceCommand, model, Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of homerce
     */
    @Test
    public void execute_invalidServiceIndexFilteredList_failure() {
        showServiceAtIndex(model, INDEX_FIRST_SERVICE);
        Index outOfBoundIndex = INDEX_SECOND_SERVICE;
        // ensures that outOfBoundIndex is still in bounds of homerce list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getServiceManager().getServiceList().size());

        EditServiceCommand editServiceCommand = new EditServiceCommand(outOfBoundIndex,
            new EditServiceDescriptorBuilder().withTitle(VALID_TITLE_MANICURE).build());

        assertCommandFailure(editServiceCommand, model, Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditServiceCommand standardCommand = new EditServiceCommand(INDEX_FIRST_SERVICE, DESC_MANICURE);

        // same values -> returns true
        EditServiceCommand.EditServiceDescriptor copyDescriptor =
            new EditServiceCommand.EditServiceDescriptor(DESC_MANICURE);
        EditServiceCommand commandWithSameValues = new EditServiceCommand(INDEX_FIRST_SERVICE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearServiceCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditServiceCommand(INDEX_SECOND_SERVICE, DESC_MANICURE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditServiceCommand(INDEX_FIRST_SERVICE, DESC_LASH_LIFT)));
    }

}
