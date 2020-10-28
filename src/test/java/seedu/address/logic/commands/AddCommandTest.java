package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPolicies.LIFE_TIME_NAME;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.person.Person;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.PolicyList;
import seedu.address.model.policy.PolicyName;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson, null).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_inArchiveMode_throwsCommandException() {
        ModelStubInArchiveMode modelStub = new ModelStubInArchiveMode();
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson, null);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DISABLE_IN_ARCHIVE_MODE, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson, null);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePersonInArchive_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson, null);

        Person validPersonInArchive = new PersonBuilder().addToArchive().build();
        ModelStub modelStub = new ModelStubWithPerson(validPersonInArchive);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_policyNotInPolicyList_throwsCommandException() {
        Model model = new ModelManager();
        Person validPerson = new PersonBuilder().withoutPolicy().build();
        PolicyName validPolicyName = new PolicyName(LIFE_TIME_NAME);
        AddCommand addCommand = new AddCommand(validPerson, validPolicyName);
        assertThrows(CommandException.class, AddCommand.MESSAGE_POLICY_NOT_FOUND, () -> addCommand.execute(model));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice, null);
        AddCommand addBobCommand = new AddCommand(bob, null);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice, null);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
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
        public Path getClientListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientListFilePath(Path clientListFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setClientList(ReadOnlyClientList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyClientList getClientList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean getIsArchiveMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public BooleanProperty getIsArchiveModeProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIsArchiveMode(boolean isArchiveMode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPolicyListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPolicyListFilePath(Path path) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public PolicyList getPolicyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPolicy(Policy policy) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPolicy(Policy policy) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPolicy(PolicyName policyName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearPolicyList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;
        private final BooleanProperty isArchiveMode = new SimpleBooleanProperty(false);

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public boolean getIsArchiveMode() {
            return isArchiveMode.get();
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        private final BooleanProperty isArchiveMode = new SimpleBooleanProperty(false);

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyClientList getClientList() {
            return new ClientList();
        }

        @Override
        public boolean getIsArchiveMode() {
            return isArchiveMode.get();
        }

    }

    /**
     * A Model stub that is always in archive mode.
     */
    private class ModelStubInArchiveMode extends ModelStub {
        private final BooleanProperty isArchiveMode = new SimpleBooleanProperty(true);

        @Override
        public boolean getIsArchiveMode() {
            return isArchiveMode.get();
        }

    }

    /**
     * A Model stub that has no policies in policylist
     */
    private class ModelStubWithoutPolicies extends ModelStub {

        @Override
        public boolean hasPolicy(Policy policy) {
            return false;
        }
    }
}
