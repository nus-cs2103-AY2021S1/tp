package seedu.homerce.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.homerce.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.homerce.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.homerce.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.testutil.client.ClientBuilder;
import seedu.homerce.testutil.client.EditClientDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests
 * for EditClientCommand.
 */
public class EditClientCommandTest {

    private Model model = new ModelManager(new UserPrefs(), getTypicalClientManager(), new ServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Client editedClient = new ClientBuilder().build();
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(model.getClientManager()),
            new ServiceManager(), new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.setClient(model.getFilteredClientList().get(0), editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastClient = Index.fromOneBased(model.getFilteredClientList().size());
        Client lastClient = model.getFilteredClientList().get(indexLastClient.getZeroBased());

        ClientBuilder clientInList = new ClientBuilder(lastClient);
        Client editedClient = clientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND).build();

        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditClientCommand editClientCommand = new EditClientCommand(indexLastClient, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(model.getClientManager()),
            new ServiceManager(), new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.setClient(lastClient, editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT,
                new EditClientCommand.EditClientDescriptor());
        assertCommandFailure(editClientCommand, model, MESSAGE_NOT_EDITED);
    }

    @Test
    public void execute_filteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientInFilteredList = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        Client editedClient = new ClientBuilder(clientInFilteredList).withName(VALID_NAME_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT,
            new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS, editedClient);

        assertCommandSuccess(editClientCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        Client firstClient = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_SECOND_CLIENT, descriptor);

        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        // edit client in filtered list into a duplicate in homerce
        Client clientInList = model.getClientManager().getClientList().get(INDEX_SECOND_CLIENT.getZeroBased());
        EditClientCommand editClientCommand = new EditClientCommand(INDEX_FIRST_CLIENT,
            new EditClientDescriptorBuilder(clientInList).build());

        assertCommandFailure(editClientCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        EditClientCommand.EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
            .withName(VALID_NAME_BOB).build();
        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of homerce
     */
    @Test
    public void execute_invalidClientIndexFilteredList_failure() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);
        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of homerce list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClientManager().getClientList().size());

        EditClientCommand editClientCommand = new EditClientCommand(outOfBoundIndex,
            new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditClientCommand standardCommand = new EditClientCommand(INDEX_FIRST_CLIENT, DESC_AMY);

        // same values -> returns true
        EditClientCommand.EditClientDescriptor copyDescriptor = new EditClientCommand.EditClientDescriptor(DESC_AMY);
        EditClientCommand commandWithSameValues = new EditClientCommand(INDEX_FIRST_CLIENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearClientCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_SECOND_CLIENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(INDEX_FIRST_CLIENT, DESC_BOB)));
    }

}
