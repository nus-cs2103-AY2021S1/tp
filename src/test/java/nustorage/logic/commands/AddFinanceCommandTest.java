package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.AddressBook;
import nustorage.model.ReadOnlyAddressBook;
import nustorage.model.person.Person;
import nustorage.model.record.FinanceRecord;
import nustorage.testutil.FinanceRecordBuilder;
import nustorage.testutil.PersonBuilder;
import nustorage.testutil.stub.ModelStub;

public class AddFinanceCommandTest {

    @Test
    public void constructor_nullFinanceRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddFinanceCommand(null));
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

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        FinanceRecord record1 = new FinanceRecordBuilder().withAmount(200).build();
        FinanceRecord record2 = new FinanceRecordBuilder().withAmount(10000).build();
        AddFinanceCommand addFinanceCommand1 = new AddFinanceCommand(record1);
        AddFinanceCommand addFinanceCommand2 = new AddFinanceCommand(record2);

        // same object -> returns true
        assertTrue(addFinanceCommand1.equals(addFinanceCommand1));

        // same values -> returns true
        AddFinanceCommand addFinanceCommand1Copy = new AddFinanceCommand(record1);
        assertTrue(addFinanceCommand1.equals(addFinanceCommand1Copy));

        // different types -> returns false
        assertFalse(addFinanceCommand1.equals(1));

        // null -> returns false
        assertFalse(addFinanceCommand1.equals(null));

        // different person -> returns false
        assertFalse(addFinanceCommand1.equals(addFinanceCommand2));
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
