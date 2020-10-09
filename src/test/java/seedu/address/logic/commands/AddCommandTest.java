package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.log.Log;
import seedu.address.testutil.LogBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Log validLog = new LogBuilder().build();

        CommandResult commandResult = new AddCommand(validLog).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validLog), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validLog), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Log validLog = new LogBuilder().build();
        AddCommand addCommand = new AddCommand(validLog);
        ModelStub modelStub = new ModelStubWithPerson(validLog);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_LOG, () -> addCommand.execute(modelStub));
    }

    // TODO Edit function
    /*@Test
    public void equals() {
        Log alice = new LogBuilder().withName("Alice").build();
        Log bob = new LogBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different log -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }*/

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
        public void addLog(Log log) {
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
        public boolean hasLog(Log log) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteLog(Log target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setLog(Log target, Log editedLog) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Log> getFilteredLogList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredLogList(Predicate<Log> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single log.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Log log;

        ModelStubWithPerson(Log log) {
            requireNonNull(log);
            this.log = log;
        }

        @Override
        public boolean hasLog(Log log) {
            requireNonNull(log);
            return this.log.isSameLog(log);
        }
    }

    /**
     * A Model stub that always accept the log being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Log> personsAdded = new ArrayList<>();

        @Override
        public boolean hasLog(Log log) {
            requireNonNull(log);
            return personsAdded.stream().anyMatch(log::isSameLog);
        }

        @Override
        public void addLog(Log log) {
            requireNonNull(log);
            personsAdded.add(log);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
