package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalModules.CS1010S;
import static seedu.address.testutil.TypicalModules.CS1101S;
import static seedu.address.testutil.TypicalModules.CS2103;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ModuleBuilder;

public class UnassignCommandTest {

    private Model model;
    private Model expectedModel;

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

}
