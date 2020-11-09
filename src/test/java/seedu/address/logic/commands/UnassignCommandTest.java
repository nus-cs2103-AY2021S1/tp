package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModules.CS1010S;
import static seedu.address.testutil.TypicalModules.CS1101S;
import static seedu.address.testutil.TypicalModules.CS2103;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.builders.ModuleBuilder;
import seedu.address.testutil.builders.PersonBuilder;

public class UnassignCommandTest {

    private Model model;
    private Model expectedModel;


    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        Person validPerson = new PersonBuilder().build();
        ModelStubAssigning modelStub = new ModelStubAssigning(validPerson, CS2103);

        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());

        CommandResult commandResult = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes).execute(modelStub);

        assertEquals(String.format(UnassignCommand.MESSAGE_UNASSIGNMENT_SUCCESS,
                validPerson.getName(), CS2103.getModuleCode()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validIndexNoModulesUnfilteredList_success() throws CommandException {
        Person validPerson = new PersonBuilder().build();
        ModelStubAssigning modelStub = new ModelStubAssigning(validPerson, CS2103);

        CommandResult commandResult = new UnassignCommand(INDEX_FIRST_PERSON).execute(modelStub);

        assertEquals(String.format(UnassignCommand.MESSAGE_UNASSIGNMENT_SUCCESS,
                validPerson.getName(), "all modules"),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {

        Person validPerson = new PersonBuilder().withName("Benson").build();
        ModelStubAssigning modelStub = new ModelStubAssigning(validPerson, CS2103);
        modelStub.updateFilteredPersonList(x -> x.getName().equals(BENSON.getName()));

        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());

        CommandResult commandResult = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes).execute(modelStub);

        assertEquals(String.format(UnassignCommand.MESSAGE_UNASSIGNMENT_SUCCESS,
                validPerson.getName(), CS2103.getModuleCode()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_validIndexNoModulesFilteredList_success() throws CommandException {
        Person validPerson = new PersonBuilder().withName("Benson").build();
        ModelStubAssigning modelStub = new ModelStubAssigning(validPerson, CS2103);
        modelStub.updateFilteredPersonList(x -> x.getName().equals(BENSON.getName()));

        CommandResult commandResult = new UnassignCommand(INDEX_FIRST_PERSON).execute(modelStub);

        assertEquals(String.format(UnassignCommand.MESSAGE_UNASSIGNMENT_SUCCESS,
                validPerson.getName(), "all modules"),
                commandResult.getFeedbackToUser());
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexFilteredListInstructorDoesNotExist_failure() {
        model.updateFilteredPersonList(x -> x.getName().equals(ALICE.getName()));
        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes);

        List<Person> lastShownList = model.getFilteredPersonList();
        Person instructor = lastShownList.get(INDEX_FIRST_PERSON.getZeroBased());

        assertCommandFailure(unassignCommand, model,
            String.format(Messages.MESSAGE_INSTRUCTOR_IS_NOT_ASSIGNED,
                    instructor.getName(), VALID_MODULE_CODE_CS2103));
    }

    @Test
    public void execute_instructorDoesNotExist_failure() {
        Set<ModuleCode> moduleCodes = new HashSet<>();
        moduleCodes.add(CS2103.getModuleCode());
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes);

        List<Person> lastShownList = model.getFilteredPersonList();
        Person instructor = lastShownList.get(INDEX_FIRST_PERSON.getZeroBased());

        assertCommandFailure(unassignCommand, model,
            String.format(Messages.MESSAGE_INSTRUCTOR_IS_NOT_ASSIGNED,
                    instructor.getName(), VALID_MODULE_CODE_CS2103));
    }

    @Test
    public void execute_moduleDoesNotExist_failure() {
        Set<ModuleCode> moduleCodes = new HashSet<>();
        Module cs60 = new ModuleBuilder().withCode("CS60").build();
        moduleCodes.add(cs60.getModuleCode());
        UnassignCommand unassignCommand = new UnassignCommand(INDEX_FIRST_PERSON, moduleCodes);

        assertCommandFailure(unassignCommand, model, String.format(
                Messages.MESSAGE_MODULE_DOES_NOT_EXIST, cs60.getModuleCode()));
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
     * A Model stub that contains a single person and a single module.
     * The person is assigned as an instructor of the module.
     */
    private class ModelStubAssigning extends ModelStub {
        private final Person person;
        private final Module module;

        ModelStubAssigning(Person person, Module module) {
            requireAllNonNull(person, module);
            this.person = person;
            this.module = module;
            this.module.assignInstructor(this.person);
        }

        @Override
        public boolean hasModuleCode(ModuleCode moduleCode) {
            requireNonNull(moduleCode);
            return this.module.getModuleCode().equals(moduleCode);
        }

        @Override
        public void assignInstructor(Person person, ModuleCode moduleCode) {
            this.module.assignInstructor(this.person);
        }

        @Override
        public void unassignInstructor(Person person, ModuleCode moduleCode) {
            this.module.unassignInstructor(this.person);
        }

        @Override
        public void unassignInstructorFromAll(Person instructor) {
            this.module.unassignInstructor(this.person);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            ObservableList<Person> filteredPersonList = FXCollections.observableArrayList();
            filteredPersonList.addAll(this.person);
            return filteredPersonList;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            ObservableList<Person> filteredPersonList = FXCollections.observableArrayList();
            filteredPersonList.addAll(this.person);
        }

        @Override
        public boolean moduleCodeHasInstructor(ModuleCode moduleCode, Person instructor) {
            return true;
        }

    }
}
