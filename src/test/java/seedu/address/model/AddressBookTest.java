package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_CS50;
import static seedu.address.logic.commands.CommandTestUtil.VALID_OFFICE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS1101S;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.Module;
import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.builders.ModuleBuilder;
import seedu.address.testutil.builders.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withDepartment(VALID_DEPARTMENT_BOB)
                .withOffice(VALID_OFFICE_BOB).withTags(VALID_TAG_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void clearContacts_personInAddressBook_returnsTrue() {
        addressBook.clearContacts();
        assertEquals(addressBook.getPersonList(), new UniquePersonList().asUnmodifiableObservableList());
    }

    @Test
    public void clearContacts_checkInstructorInModuleList_returnsTrue() {
        addressBook.clearContacts();
        AddressBook temp = getTypicalAddressBook();
        temp.clearContacts();
        assertEquals(addressBook.getSemOneModuleList(), temp.getSemOneModuleList() );
        assertEquals(addressBook.getSemTwoModuleList(), temp.getSemTwoModuleList() );
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withDepartment(VALID_DEPARTMENT_BOB)
                .withOffice(VALID_OFFICE_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void resetData_withDuplicateModules_throwsDuplicateModuleException() {
        // Two modules with the same identity fields
        Module editedCs1101s = new ModuleBuilder(CS1101S).withName(VALID_MODULE_NAME_CS50).build();
        List<Module> newModules = Arrays.asList(CS1101S, editedCs1101s);
        List<Person> persons = Collections.singletonList(ALICE);
        AddressBookStub newData = new AddressBookStub(persons, newModules);

        assertThrows(DuplicateModuleException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasModule_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasModule(null));
    }

    @Test
    public void hasModule_moduleNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasModule(CS1101S));
    }

    @Test
    public void hasModule_moduleInAddressBook_returnsTrue() {
        addressBook.addModule(CS1101S);
        assertTrue(addressBook.hasModule(CS1101S));
    }

    @Test
    public void hasModule_moduleWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addModule(CS1101S);
        Module editedCs1101s = new ModuleBuilder(CS1101S).withName(VALID_MODULE_NAME_CS50).build();
        assertTrue(addressBook.hasModule(editedCs1101s));
    }

    @Test
    public void hasModuleCode_nullModuleCode_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasModule(null));
    }

    @Test
    public void hasModuleCode_moduleCodeNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasModuleCode(CS1101S.getModuleCode()));
    }

    @Test
    public void hasModuleCode_moduleCodeInAddressBook_returnsTrue() {
        addressBook.addModule(CS1101S);
        assertTrue(addressBook.hasModuleCode(CS1101S.getModuleCode()));
    }

    @Test
    public void hasModuleCode_moduleCodeWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addModule(CS1101S);
        Module editedCs1101s = new ModuleBuilder(CS1101S).withName(VALID_MODULE_NAME_CS50).build();
        assertTrue(addressBook.hasModuleCode(editedCs1101s.getModuleCode()));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Module> modules = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons, Collection<Module> modules) {
            this.persons.setAll(persons);
            this.modules.setAll(modules);
        }

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Module> getModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Module> getSemOneModuleList() {
            return modules;
        }

        @Override
        public ObservableList<Module> getSemTwoModuleList() {
            return modules;
        }
    }

}
