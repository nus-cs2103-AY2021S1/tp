package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INSTRUCTOR_ALREADY_ASSIGNED;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModuleCodes.CS1010S_CODE;
import static seedu.address.testutil.TypicalModuleCodes.CS1101S_CODE;
import static seedu.address.testutil.TypicalModuleCodes.CS2030_CODE;
import static seedu.address.testutil.TypicalModuleCodes.CS2100_CODE;
import static seedu.address.testutil.TypicalModuleCodes.CS2103_CODE;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.FIONA;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.UniqueModuleList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.builders.ModuleBuilder;

class AssignCommandTest {

    private static ModuleCode[] codeArray = {CS1010S_CODE, CS2030_CODE, CS2103_CODE};
    private static ModuleCode[] inexistentCodeArray = {CS2100_CODE, CS1101S_CODE};
    private static Set<ModuleCode> moduleCodes = new HashSet<>(Arrays.asList(codeArray));
    private static Set<ModuleCode> inexistentModuleCodes = new HashSet<>(
            Arrays.asList(inexistentCodeArray));


    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(null, moduleCodes));
    }


    @Test
    public void constructor_nullModuleCodes_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AssignCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AssignCommand assignCommand = new AssignCommand(Index.fromZeroBased(0), moduleCodes);
        assertThrows(NullPointerException.class, () -> assignCommand.execute(null));
    }

    @Test
    void execute_success() {
        Model model = new ModelStubAcceptingPersonAndModule();
        ObservableList<Person> personList = model.getFilteredPersonList();
        Person personToAssign = personList.get(INDEX_FIRST_PERSON.getZeroBased());
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, moduleCodes);

        StringBuilder moduleString = new StringBuilder();
        for (ModuleCode moduleCode: moduleCodes) {
            moduleString.append(moduleCode).append(", ");
        }
        moduleString.delete(moduleString.length() - 2, moduleString.length());

        String expectedMessage = String.format(AssignCommand.MESSAGE_ADD_ASSIGNMENT_SUCCESS,
                personToAssign.getName(), moduleString);

        ModelStubAcceptingPersonAndModule expectedModel = new ModelStubAcceptingPersonAndModule();
        expectedModel.assignInstructor(personToAssign, CS1010S_CODE);
        expectedModel.assignInstructor(personToAssign, CS2030_CODE);
        expectedModel.assignInstructor(personToAssign, CS2103_CODE);

        assertCommandSuccess(assignCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_inexistentModuleCode_failure() {
        Model model = new ModelStubAcceptingPersonAndModule();
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, inexistentModuleCodes);
        ModuleCode inexistentModuleCode = inexistentCodeArray[0];
        assertCommandFailure(assignCommand, model, String.format(Messages.MESSAGE_MODULE_DOES_NOT_EXIST,
                inexistentModuleCode));
    }

    @Test
    public void execute_alreadyAssigned_failure() {
        Model model = new ModelStubAcceptingPersonAndModule();
        ObservableList<Person> personList = model.getFilteredPersonList();
        Person personToAssign = personList.get(INDEX_FIRST_PERSON.getZeroBased());
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, moduleCodes);

        model.assignInstructor(personToAssign, CS1010S_CODE);

        assertCommandFailure(assignCommand, model,
                String.format(MESSAGE_INSTRUCTOR_ALREADY_ASSIGNED,
                        personToAssign.getName(), CS1010S_CODE));
    }

    @Test
    public void equals() {
        AssignCommand assignCommand = new AssignCommand(INDEX_FIRST_PERSON, moduleCodes);
        AssignCommand otherCommand = new AssignCommand(INDEX_FIRST_PERSON, moduleCodes);
        AssignCommand differentIndexCommand = new AssignCommand(INDEX_SECOND_PERSON, moduleCodes);
        AssignCommand differentCodesCommand = new AssignCommand(INDEX_SECOND_PERSON, inexistentModuleCodes);

        // same object -> returns true
        assertEquals(assignCommand, assignCommand);

        // same values -> returns true
        assertEquals(assignCommand, otherCommand);

        // different types -> returns false
        assertNotEquals(assignCommand, new Object());

        // null -> returns false
        assertNotEquals(assignCommand, null);

        // different index -> returns false
        assertNotEquals(assignCommand, differentIndexCommand);

        // different module codes -> returns false
        assertNotEquals(assignCommand, differentCodesCommand);
    }

    /**
     * A Model stub that accepts persons and modules
     */
    private class ModelStubAcceptingPersonAndModule extends ModelStub {

        final PersonListStub personList;
        final ModuleListStub moduleList;

        public ModelStubAcceptingPersonAndModule() {
            personList = new PersonListStub();
            moduleList = new ModuleListStub();
            personList.add(ALICE, BOB);
            Module cs1010s = new ModuleBuilder()
                    .withCode("CS1010S").withName("Programming Methodology")
                    .withInstructors(BENSON, FIONA).build();
            Module cs2030 = new ModuleBuilder()
                    .withCode("CS2030").withName("Programming Methodology 2")
                    .withInstructors(CARL).build();
            Module cs2103 = new ModuleBuilder()
                    .withCode("CS2103").withName("Software Engineering")
                    .withInstructors(BENSON, CARL, DANIEL).build();
            moduleList.add(cs1010s, cs2030, cs2103);
        }

        public void addModules(Module ...modules) {
            requireNonNull(modules);
            moduleList.add(modules);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return personList.asUnmodifiableObservableList();
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            return moduleList.asUnmodifiableObservableList();
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public boolean hasModuleCode(ModuleCode moduleCode) {
            return moduleList.containsModuleCode(moduleCode);
        }

        @Override
        public void assignInstructor(Person instructor, ModuleCode moduleCode) {
            moduleList.assignInstructor(instructor, moduleCode);
        }

        @Override
        public boolean moduleCodeHasInstructor(ModuleCode moduleCode, Person instructor) {
            requireAllNonNull(instructor, moduleCode);
            return moduleList.moduleCodeHasInstructor(moduleCode, instructor);
        }

        @Override
        public boolean equals(Object obj) {
            // short circuit if same object
            if (obj == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(obj instanceof ModelStubAcceptingPersonAndModule)) {
                return false;
            }

            // state check
            ModelStubAcceptingPersonAndModule other = (ModelStubAcceptingPersonAndModule) obj;
            return personList.equals(other.personList)
                    && moduleList.equals(other.moduleList);
        }
    }

    private class PersonListStub extends UniquePersonList {
        private ObservableList<Person> persons;

        public PersonListStub() {
            persons = FXCollections.observableArrayList();
        }

        public void add(Person ...persons) {
            Collections.addAll(this.persons, persons);
        }

        @Override
        public ObservableList<Person> asUnmodifiableObservableList() {
            return FXCollections.unmodifiableObservableList(persons);
        }
    }

    private class ModuleListStub extends UniqueModuleList {
        private ObservableList<Module> modules;

        public ModuleListStub() {
            modules = FXCollections.observableArrayList();
        }

        public void add(Module ...modules) {
            Collections.addAll(this.modules, modules);
        }

        @Override
        public boolean containsModuleCode(ModuleCode moduleCodeToCheck) {
            requireNonNull(moduleCodeToCheck);
            return modules.stream().anyMatch(module -> module.hasModuleCode(moduleCodeToCheck));
        }

        @Override
        public void assignInstructor(Person instructor, ModuleCode moduleCodeToAssign) {
            requireAllNonNull(instructor, moduleCodeToAssign);
            int indexOfModuleToAssign = 0;
            while (!modules.get(indexOfModuleToAssign).hasModuleCode(moduleCodeToAssign)
                    && indexOfModuleToAssign < modules.size()) {
                indexOfModuleToAssign++;
            }
            Module moduleToAssign = modules.get(indexOfModuleToAssign);
            moduleToAssign.assignInstructor(instructor);
            modules.set((indexOfModuleToAssign), moduleToAssign);
        }

        @Override
        public boolean moduleCodeHasInstructor(ModuleCode moduleCode, Person instructor) {
            requireAllNonNull(instructor, moduleCode);

            for (Module moduleToCheck : modules) {
                if (moduleToCheck.hasModuleCode(moduleCode)
                        && !moduleToCheck.hasInstructor(instructor)) {
                    return false;
                }
            }

            return true;
        }
    }
}
