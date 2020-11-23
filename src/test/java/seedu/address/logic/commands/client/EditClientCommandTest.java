package seedu.address.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientWithId;
import static seedu.address.testutil.TypicalIds.ID_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIds.ID_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.client.EditClientCommand.EditClientDescriptor;
import seedu.address.model.IdCounter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.testutil.ClientBuilder;
import seedu.address.testutil.EditClientDescriptorBuilder;
import seedu.address.testutil.TypicalClients;

/**
 * Contains integration tests (interaction with the Model)
 * and unit tests for EditClientCommand.
 */
public class EditClientCommandTest {

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        Client editedClient = new ClientBuilder().build(ID_FIRST_CLIENT);
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(editedClient).build();
        EditClientCommand editCommand = new EditClientCommand(ID_FIRST_CLIENT, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                editedClient);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        expectedModel.setClient(model.getClientById(ID_FIRST_CLIENT), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        ClientId indexSecondClient = ID_SECOND_CLIENT;
        Client secondClient = model.getClientById(indexSecondClient);

        ClientBuilder personInList = new ClientBuilder(secondClient);
        Client editedClient = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build(ID_FIRST_CLIENT);

        EditClientDescriptor descriptor = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditClientCommand editCommand = new EditClientCommand(indexSecondClient, descriptor);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                editedClient);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        expectedModel.setClient(secondClient, editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        EditClientCommand editCommand = new EditClientCommand(ID_FIRST_CLIENT,
                new EditClientDescriptor());
        Client editedClient = model.getClientById(ID_FIRST_CLIENT);

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                editedClient);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        showClientWithId(model, ID_FIRST_CLIENT);

        Client personInFilteredList = model.getClientById(ID_FIRST_CLIENT);;
        Client editedClient = new ClientBuilder(personInFilteredList)
                .withName(VALID_NAME_BOB).build(ID_FIRST_CLIENT);
        EditClientCommand editCommand = new EditClientCommand(ID_FIRST_CLIENT,
                new EditClientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditClientCommand.MESSAGE_EDIT_CLIENT_SUCCESS,
                editedClient);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        expectedModel.setClient(model.getClientById(ID_FIRST_CLIENT), editedClient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateClientUnfilteredList_failure() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        Client firstClient = model.getClientById(ID_FIRST_CLIENT);;
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder(firstClient).build();
        EditClientCommand editCommand = new EditClientCommand(ID_SECOND_CLIENT, descriptor);

        assertCommandFailure(editCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_duplicateClientFilteredList_failure() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        showClientWithId(model, ID_FIRST_CLIENT);

        // edit person in filtered list into a duplicate in address book
        Client personInList = model.getClientById(ID_SECOND_CLIENT);;
        EditClientCommand editCommand = new EditClientCommand(ID_FIRST_CLIENT,
                new EditClientDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditClientCommand.MESSAGE_DUPLICATE_CLIENT);
    }

    @Test
    public void execute_invalidClientIndexUnfilteredList_failure() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        ClientId outOfBoundIndex = new ClientId(String.valueOf(model
                .getFilteredClientList().size() + 1));
        EditClientDescriptor descriptor = new EditClientDescriptorBuilder()
                .withName(VALID_NAME_BOB).build();
        EditClientCommand editCommand = new EditClientCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_ID);
    }

    @Test
    public void equals() {
        final EditClientCommand standardCommand = new EditClientCommand(ID_FIRST_CLIENT, DESC_AMY);

        // same values -> returns true
        EditClientDescriptor copyDescriptor = new EditClientDescriptor(DESC_AMY);
        EditClientCommand commandWithSameValues = new EditClientCommand(ID_FIRST_CLIENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(ID_SECOND_CLIENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditClientCommand(ID_FIRST_CLIENT, DESC_BOB)));
    }

}
