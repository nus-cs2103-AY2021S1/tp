package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Client;
import seedu.address.model.person.Order;
import seedu.address.testutil.PersonBuilder;

/**
 * Tests both addition of clients and orders
 */
public class AddCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Client validClient = new PersonBuilder().build();

        CommandResult commandResult = new ClientCommand(validClient).execute(modelStub, commandHistory);

        assertEquals(String.format(ClientCommand.MESSAGE_SUCCESS, validClient), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validClient), modelStub.personsAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Client validClient = new PersonBuilder().build();
        ClientCommand clientCommand = new ClientCommand(validClient);
        ModelStub modelStub = new ModelStubWithPerson(validClient);

        assertThrows(CommandException.class, ClientCommand.MESSAGE_DUPLICATE_PERSON, ()
            -> clientCommand.execute(modelStub, commandHistory));
    }

    @Test
    public void equals() {
        Client alice = new PersonBuilder().withName("Alice").build();
        Client bob = new PersonBuilder().withName("Bob").build();
        ClientCommand clientAliceCommand = new ClientCommand(alice);
        ClientCommand clientBobCommand = new ClientCommand(bob);

        // same object -> returns true
        assertTrue(clientAliceCommand.equals(clientAliceCommand));

        // same values -> returns true
        ClientCommand clientAliceCommandCopy = new ClientCommand(alice);
        assertTrue(clientAliceCommand.equals(clientAliceCommandCopy));

        // different types -> returns false
        assertFalse(clientAliceCommand.equals(1));

        // null -> returns false
        assertFalse(clientAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(clientAliceCommand.equals(clientBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Client target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Client target, Client editedClient) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Client> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Client> getUnfilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(Order target, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getUnfilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Client client;

        ModelStubWithPerson(Client client) {
            requireNonNull(client);
            this.client = client;
        }

        @Override
        public boolean hasPerson(Client client) {
            requireNonNull(client);
            return this.client.isSamePerson(client);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Client> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Client client) {
            requireNonNull(client);
            return personsAdded.stream().anyMatch(client::isSamePerson);
        }

        @Override
        public void addPerson(Client client) {
            requireNonNull(client);
            personsAdded.add(client);
        }

        @Override
        public void commitAddressBook() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
