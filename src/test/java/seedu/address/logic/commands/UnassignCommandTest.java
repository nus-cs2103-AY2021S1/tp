package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModules.CS1010S;
import static seedu.address.testutil.TypicalModules.CS1101S;
import static seedu.address.testutil.TypicalModules.CS2103;
import static seedu.address.testutil.TypicalModules.getTypicalModules;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.PersonBuilder;

public class UnassignCommandTest {

    private Model model;
    private Model expectedModel;

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {

        Person validPerson = new PersonBuilder().build();
        ModelStubAssigning modelStub = new ModelStubAssigning(CS2103, validPerson);

        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());

        CommandResult commandResult = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes).execute(modelStub);
        assertEquals(String.format(UnassignCommand.MESSAGE_UNASSIGNMENT_SUCCESS, INDEX_FIRST_PERSON, moduleCodes), commandResult.getFeedbackToUser());

    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {

        Person validPerson = new PersonBuilder().withName("Benson").build();
        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(CS2103, validPerson);

        modelStub.updateFilteredPersonList(x -> x.getName().equals(BENSON.getName()));
        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());

        CommandResult commandResult = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes).execute(modelStub);
        assertEquals(String.format(UnassignCommand.MESSAGE_UNASSIGNMENT_SUCCESS, INDEX_FIRST_PERSON, moduleCodes), commandResult.getFeedbackToUser());
    }

    //    @Test
//    public void execute_validIndexUnfilteredList_multipleModules_success() throws CommandException {
//        Person validPerson = new PersonBuilder().build();
//        ModelStubWithModuleAndPerson modelStub = new ModelStubWithModuleAndPerson(CS2103, validPerson);
//
//        Set<ModuleCode> moduleCodes = new HashSet<>();
//        moduleCodes.add(CS2103.getModuleCode());
//        moduleCodes.add(CS1101S.getModuleCode());
//        moduleCodes.add(CS1010S.getModuleCode());
//
//        CommandResult commandResult = new UnassignCommand(INDEX_SECOND_PERSON, moduleCodes).execute(modelStub);
//        assertEquals(String.format(UnassignCommand.MESSAGE_UNASSIGNMENT_SUCCESS, INDEX_SECOND_PERSON, moduleCodes), commandResult.getFeedbackToUser());
//    }

    @BeforeEach
    public void setUp() {
        ReadOnlyAddressBook addressBook = new ModelStub().getAddressBook();
        model = new ModelManager(addressBook, new UserPrefs());
        expectedModel = new ModelManager(addressBook, new UserPrefs());
    }


    @Test
    public void execute_validIndexFilteredListInstructorDoesNotExist_failure() {
        model.updateFilteredPersonList(x -> x.getName().equals(ALICE.getName()));
        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes);

        assertCommandFailure(unassignCommand, model, Messages.MESSAGE_INSTRUCTOR_DOES_NOT_EXIST);
    }

    @Test
    public void execute_instructorDoesNotExist_failure() {
        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes);

        assertCommandFailure(unassignCommand, model, Messages.MESSAGE_INSTRUCTOR_DOES_NOT_EXIST);
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        Set<ModuleCode> moduleCodes = new HashSet<>();
        Module cs60 = new ModuleBuilder().withCode("CS60").build();
        moduleCodes.add(cs60.getModuleCode());
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes);

        assertCommandFailure(unassignCommand, model, Messages.MESSAGE_MODULE_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {

        Set<ModuleCode> firstModuleCodes = new HashSet<>();
        firstModuleCodes.add(CS2103.getModuleCode());
        firstModuleCodes.add(CS1101S.getModuleCode());
        firstModuleCodes.add(CS1010S.getModuleCode());

        Set<ModuleCode> secondModuleCodes = new HashSet<>();
        secondModuleCodes.add(CS2103.getModuleCode());
        secondModuleCodes.add(CS1101S.getModuleCode());

        UnassignCommand unassignFirstCommand = new UnassignCommand(INDEX_FIRST_PERSON, firstModuleCodes);
        UnassignCommand unassignSecondCommand = new UnassignCommand(INDEX_SECOND_PERSON, firstModuleCodes);
        UnassignCommand unassignThirdCommand = new UnassignCommand(INDEX_FIRST_PERSON, secondModuleCodes);

        // same object -> returns true
        assertTrue(unassignFirstCommand.equals(unassignFirstCommand));

        // same values -> returns true
        UnassignCommand unassignFirstCommandCopy = new UnassignCommand(INDEX_FIRST_PERSON, firstModuleCodes);
        assertTrue(unassignFirstCommand.equals(unassignFirstCommandCopy));

        // different types -> returns false
        assertFalse(unassignFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unassignFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unassignFirstCommand.equals(unassignSecondCommand));

        // different modules -> returns false
        assertFalse(unassignFirstCommand.equals(unassignThirdCommand));
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
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

        public boolean hasModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addModule(Module module) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteModule(ModuleCode moduleCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void assignInstructor(Person instructor, ModuleCode moduleCode)  {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void unassignInstructor(Person instructor, ModuleCode moduleCode) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public UniqueModuleList getModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredModuleList(Predicate<Module> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isEmptyPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isEmptyModuleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearContacts() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearMod() {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single person and a single module.
     */
    private class ModelStubWithModuleAndPerson extends UnassignCommandTest.ModelStub {
        private final Module module;
        private final Person person;
        PersonListStub personListStub = new PersonListStub();

        ModelStubWithModuleAndPerson(Module module, Person person) {
            requireAllNonNull(module, person);
            this.module = module;
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return new FilteredList<Person>(personListStub);

        }

        @Override
        public void unassignInstructor(Person person, ModuleCode moduleCode) {
            Set<Person> instructors = this.module.getInstructors();
            instructors.add(this.person);
            this.module.getInstructors().remove(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    /**
     * A Model stub that contains a single person and a single module.
     */
    private class ModelStubAssigning extends UnassignCommandTest.ModelStub {
        private final Module module;
        private final Person person;
        PersonListStub personListStub = new PersonListStub();


        ModelStubAssigning(Module module, Person person) {
            requireAllNonNull(module, person);
            this.module = module;
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.isSameModule(module);
        }

        @Override
        public void assignInstructor(Person person, ModuleCode moduleCode) {
            this.module.assignInstructor(person);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return (ObservableList<Person>) personListStub;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

    private class PersonListStub extends UniquePersonList {

        private ArrayList<Person> personList = new ArrayList<>();
        public void add(Person ... persons) {
            Collections.addAll(personList, persons);
        }

        @Override
        public void remove(Person toRemove) {
            requireNonNull(toRemove);
            personList.remove(toRemove);
        }

        public boolean contains(Person toCheck) {
            requireNonNull(toCheck);
            return personList.stream().anyMatch(toCheck::isSamePerson);
        }
    }

}
