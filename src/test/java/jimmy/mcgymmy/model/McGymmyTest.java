package jimmy.mcgymmy.model;

import static jimmy.mcgymmy.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class McGymmyTest {

    private final McGymmy mcGymmy = new McGymmy();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), mcGymmy.getFoodList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> mcGymmy.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        McGymmy newData = TypicalPersons.getTypicalAddressBook();
        mcGymmy.resetData(newData);
        assertEquals(newData, mcGymmy);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress("valid address").build();
        List<Person> newPersons = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        McGymmyStub newData = new McGymmyStub(newPersons);

        Assert.assertThrows(DuplicatePersonException.class, () -> mcGymmy.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> mcGymmy.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(mcGymmy.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        mcGymmy.addPerson(TypicalPersons.ALICE);
        assertTrue(mcGymmy.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        mcGymmy.addPerson(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress("valid address2").build();
        assertTrue(mcGymmy.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> mcGymmy.getFoodList().remove(0));
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
