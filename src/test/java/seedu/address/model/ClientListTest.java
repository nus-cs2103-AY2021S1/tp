package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLIENTSOURCE_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalClientList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.policy.PolicyList;
import seedu.address.testutil.PersonBuilder;

public class ClientListTest {

    private final ClientList clientList = new ClientList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), clientList.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clientList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyClientList_replacesData() {
        ClientList newData = getTypicalClientList();
        clientList.resetData(newData);
        assertEquals(newData, clientList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ClientListStub newData = new ClientListStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> clientList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> clientList.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInClientList_returnsFalse() {
        assertFalse(clientList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInClientList_returnsTrue() {
        clientList.addPerson(ALICE);
        assertTrue(clientList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInClientList_returnsTrue() {
        clientList.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB)
                .withClientSources(VALID_CLIENTSOURCE_HUSBAND)
                .build();
        assertTrue(clientList.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> clientList.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyClientList whose persons list can violate interface constraints.
     */
    private static class ClientListStub implements ReadOnlyClientList {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        ClientListStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public void updateClientListWithPolicyList(PolicyList policyList) {

        }
    }

}
