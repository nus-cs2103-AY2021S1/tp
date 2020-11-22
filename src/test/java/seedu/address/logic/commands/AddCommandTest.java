package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Block;
import seedu.address.model.person.Email;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Room;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, CommandUtil.MESSAGE_GENERIC_DUPLICATE_PERSON + "\n"
                + CommandUtil.MESSAGE_DUPLICATE_MATRICULATION_NUMBER + "\n"
                + CommandUtil.MESSAGE_DUPLICATE_BLOCK_ROOM + "\n"
                + CommandUtil.MESSAGE_DUPLICATE_EMAIL + "\n"
                + CommandUtil.MESSAGE_DUPLICATE_PHONE_NUMBER + "\n", () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
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

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }


    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

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
        public boolean hasBlockRoom(Block block, Room room) {
            requireAllNonNull(block, room);
            return this.person.getBlock().equals(block) && this.person.getRoom().equals(room);
        }

        @Override
        public boolean hasMatriculationNumber(MatriculationNumber matriculationNumber) {
            requireNonNull(matriculationNumber);
            return this.person.getMatriculationNumber().equals(matriculationNumber);
        }

        @Override
        public boolean hasPhone(Phone phone) {
            requireNonNull(phone);
            return this.person.getPhone().equals(phone);
        }

        @Override
        public boolean hasEmail(Email email) {
            requireNonNull(email);
            return this.person.getEmail().equals(email);
        }

    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public boolean hasBlockRoom(Block block, Room room) {
            requireAllNonNull(block, room);
            return personsAdded
                .stream()
                .anyMatch(person -> person.getBlock().equals(block) && person.getRoom().equals(room));
        }

        @Override
        public boolean hasMatriculationNumber(MatriculationNumber matriculationNumber) {
            requireNonNull(matriculationNumber);
            return personsAdded
                .stream()
                .anyMatch(person -> person.getMatriculationNumber().equals(matriculationNumber));
        }

        @Override
        public boolean hasPhone(Phone phone) {
            requireNonNull(phone);
            return personsAdded
                    .stream()
                    .anyMatch(person -> person.getPhone().equals(phone));
        }

        @Override
        public boolean hasEmail(Email email) {
            requireNonNull(email);
            return personsAdded
                    .stream()
                    .anyMatch(person -> person.getEmail().equals(email));
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
