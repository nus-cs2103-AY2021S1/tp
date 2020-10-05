package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class McGymmyTest {

    private final McGymmy mcGymmy = new McGymmy();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mcGymmy.getFoodList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
<<<<<<< Updated upstream:src/test/java/seedu/address/model/AddressBookTest.java
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
=======
        Assert.assertThrows(NullPointerException.class, () -> mcGymmy.resetData(null));
>>>>>>> Stashed changes:src/test/java/seedu/address/model/McGymmyTest.java
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
<<<<<<< Updated upstream:src/test/java/seedu/address/model/AddressBookTest.java
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
=======
        McGymmy newData = TypicalPersons.getTypicalAddressBook();
        mcGymmy.resetData(newData);
        assertEquals(newData, mcGymmy);
>>>>>>> Stashed changes:src/test/java/seedu/address/model/McGymmyTest.java
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
<<<<<<< Updated upstream:src/test/java/seedu/address/model/AddressBookTest.java
        Person editedAlice = new PersonBuilder(ALICE).withAddress("valid address").build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
=======
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress("valid address").build();
        List<Person> newPersons = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        McGymmyStub newData = new McGymmyStub(newPersons);

        Assert.assertThrows(DuplicatePersonException.class, () -> mcGymmy.resetData(newData));
>>>>>>> Stashed changes:src/test/java/seedu/address/model/McGymmyTest.java
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
<<<<<<< Updated upstream:src/test/java/seedu/address/model/AddressBookTest.java
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
=======
        Assert.assertThrows(NullPointerException.class, () -> mcGymmy.hasPerson(null));
>>>>>>> Stashed changes:src/test/java/seedu/address/model/McGymmyTest.java
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
<<<<<<< Updated upstream:src/test/java/seedu/address/model/AddressBookTest.java
        assertFalse(addressBook.hasPerson(ALICE));
=======
        assertFalse(mcGymmy.hasPerson(TypicalPersons.ALICE));
>>>>>>> Stashed changes:src/test/java/seedu/address/model/McGymmyTest.java
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
<<<<<<< Updated upstream:src/test/java/seedu/address/model/AddressBookTest.java
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
=======
        mcGymmy.addPerson(TypicalPersons.ALICE);
        assertTrue(mcGymmy.hasPerson(TypicalPersons.ALICE));
>>>>>>> Stashed changes:src/test/java/seedu/address/model/McGymmyTest.java
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
<<<<<<< Updated upstream:src/test/java/seedu/address/model/AddressBookTest.java
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress("valid address2").build();
        assertTrue(addressBook.hasPerson(editedAlice));
=======
        mcGymmy.addPerson(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress("valid address2").build();
        assertTrue(mcGymmy.hasPerson(editedAlice));
>>>>>>> Stashed changes:src/test/java/seedu/address/model/McGymmyTest.java
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
<<<<<<< Updated upstream:src/test/java/seedu/address/model/AddressBookTest.java
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
=======
        Assert.assertThrows(UnsupportedOperationException.class, () -> mcGymmy.getFoodList().remove(0));
>>>>>>> Stashed changes:src/test/java/seedu/address/model/McGymmyTest.java
    }

    /**
     * A stub ReadOnlyMcGymmy whose persons list can violate interface constraints.
     */
    private static class McGymmyStub implements ReadOnlyMcGymmy {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        McGymmyStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getFoodList() {
            return persons;
        }
    }

}
