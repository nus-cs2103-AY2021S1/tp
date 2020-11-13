package com.eva.model;

import static com.eva.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static com.eva.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static com.eva.testutil.Assert.assertThrows;
import static com.eva.testutil.TypicalPersons.ALICE;
import static com.eva.testutil.TypicalPersons.getTypicalPersonDatabase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.eva.model.person.Person;
import com.eva.model.person.exceptions.DuplicatePersonException;
import com.eva.testutil.PersonBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EvaDatabaseTest {

    private final EvaDatabase<Person> evaDatabase = new EvaDatabase<>();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), evaDatabase.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> evaDatabase.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEvaDatabase_replacesData() {
        EvaDatabase<Person> newData = getTypicalPersonDatabase();
        evaDatabase.resetData(newData);
        assertEquals(newData, evaDatabase);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> evaDatabase.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> evaDatabase.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(evaDatabase.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        evaDatabase.addPerson(ALICE);
        assertTrue(evaDatabase.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        evaDatabase.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(evaDatabase.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> evaDatabase.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyEvaDatabase whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyEvaDatabase {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}
