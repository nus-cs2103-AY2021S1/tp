package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalPersonBook;

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

public class PersonBookTest {

    private final PersonBook personBook = new PersonBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), personBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyPersonBook_replacesData() {
        PersonBook newData = getTypicalPersonBook();
        personBook.resetData(newData);
        assertEquals(newData, personBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        PersonBookStub newData = new PersonBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> personBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> personBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInPersonBook_returnsFalse() {
        assertFalse(personBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInPersonBook_returnsTrue() {
        personBook.addPerson(ALICE);
        assertTrue(personBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInPersonBook_returnsTrue() {
        personBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(personBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> personBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyPersonBook whose persons list can violate interface constraints.
     */
    private static class PersonBookStub implements ReadOnlyPersonBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        PersonBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
