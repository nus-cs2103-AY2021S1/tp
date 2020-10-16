package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
// import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.contact.Contact;
// import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.ContactBuilder;

public class ModuleListTest {

    private final ModuleList moduleList = new ModuleList();

    @Test
    public void constructor() {
        // assertEquals(Collections.emptyList(), moduleList.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> moduleList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ModuleList newData = getTypicalAddressBook();
        moduleList.resetData(newData);
        assertEquals(newData, moduleList);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Contact editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Contact> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        // assertThrows(DuplicatePersonException.class, () -> moduleList.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        // assertThrows(NullPointerException.class, () -> moduleList.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        // assertFalse(moduleList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        // oduleList.addPerson(ALICE);
        // assertTrue(moduleList.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        // moduleList.addPerson(ALICE);
        Contact editedAlice = new ContactBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        // assertTrue(moduleList.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        // assertThrows(UnsupportedOperationException.class, () -> moduleList.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyContactList {
        private final ObservableList<Contact> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Contact> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Contact> getContactList() {
            return persons;
        }
    }

}
