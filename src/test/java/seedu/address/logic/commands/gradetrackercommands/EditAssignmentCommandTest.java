package seedu.address.logic.commands.gradetrackercommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_NAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ASSIGNMENT_PERCENTAGE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_CS2030;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULENAME_ES2660;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.CS2030_WITH_ASSIGNMENT;
import static seedu.address.testutil.TypicalModules.getTypicalModulesWithAssignmentList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.modulelistcommands.UndoCommand;
import seedu.address.model.ContactList;
import seedu.address.model.EventList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleList;
import seedu.address.model.TodoList;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.model.module.grade.Assignment;
import seedu.address.testutil.ModuleBuilder;
import seedu.address.testutil.ModuleListBuilder;
import seedu.address.testutil.gradetracker.AssignmentBuilder;
import seedu.address.testutil.gradetracker.EditAssignmentDescriptorBuilder;

public class EditAssignmentCommandTest {
    private static final Index FIRST_INDEX = Index.fromOneBased(1);

    private Model model = new ModelManager(getTypicalModulesWithAssignmentList(), new ModuleList(),
            new ContactList(), new TodoList(), new EventList(), new UserPrefs());
    @Test
    public void execute_allFieldsSpecified_success() {
        Module editedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2030).build();
        Assignment editedAssignment = new AssignmentBuilder().build();
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(editedAssignment).build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(FIRST_INDEX,
                editedModule.getName(), descriptor);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment);
        ModuleList updatedModuleList = new ModuleListBuilder(getTypicalModulesWithAssignmentList()).build();
        Model expectedModel = new ModelManager(new ModuleList(updatedModuleList), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        updatedModuleList.setModule(CS2030_WITH_ASSIGNMENT, editedModule);
        assertCommandSuccess(editAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Module editedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2030).build();
        Assignment editedAssignment = new AssignmentBuilder().withAssignmentName(VALID_ASSIGNMENT_NAME_2).build();
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(editedAssignment)
                .withAssignmentName(VALID_ASSIGNMENT_NAME_2).build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(FIRST_INDEX,
                editedModule.getName(), descriptor);

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment);
        ModuleList updatedModuleList = new ModuleListBuilder(getTypicalModulesWithAssignmentList()).build();
        Model expectedModel = new ModelManager(new ModuleList(updatedModuleList), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        updatedModuleList.setModule(CS2030_WITH_ASSIGNMENT, editedModule);
        assertCommandSuccess(editAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        Module editedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2030).build();
        Assignment editedAssignment = new AssignmentBuilder().build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(FIRST_INDEX,
                editedModule.getName(), new EditAssignmentDescriptor());

        String expectedMessage = String.format(EditAssignmentCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment);
        ModuleList updatedModuleList = new ModuleListBuilder(getTypicalModulesWithAssignmentList()).build();
        Model expectedModel = new ModelManager(new ModuleList(updatedModuleList), new ModuleList(),
                new ContactList(), new TodoList(), new EventList(), new UserPrefs());
        updatedModuleList.setModule(CS2030_WITH_ASSIGNMENT, editedModule);
        assertCommandSuccess(editAssignmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidModule_failure() {
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder().build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(FIRST_INDEX,
                new ModuleName(VALID_MODULENAME_ES2660), descriptor);
        assertCommandFailure(editAssignmentCommand, model, EditAssignmentCommand.MESSAGE_MODULE_INVALID);
    }

    @Test
    public void execute_invalidIndex_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(10);
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder().build();
        EditAssignmentCommand editAssignmentCommand = new EditAssignmentCommand(outOfBoundsIndex,
                new ModuleName(VALID_MODULENAME_CS2030), descriptor);
        assertCommandFailure(editAssignmentCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Module editedModule = new ModuleBuilder().withName(VALID_MODULENAME_CS2030).build();
        Assignment editedAssignment = new AssignmentBuilder().build();
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(editedAssignment).build();
        final EditAssignmentCommand standardCommand = new EditAssignmentCommand(FIRST_INDEX, editedModule.getName(),
                descriptor);

        // same values -> returns true
        EditAssignmentDescriptor copyDescriptor = new EditAssignmentDescriptor(descriptor);
        EditAssignmentCommand commandWithSameValues = new EditAssignmentCommand(FIRST_INDEX, editedModule.getName(),
                copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new UndoCommand());

        // different module name -> returns false
        assertNotEquals(standardCommand, new EditAssignmentCommand(FIRST_INDEX,
                new ModuleName(VALID_MODULENAME_ES2660), descriptor));

        // different descriptor -> returns false
        EditAssignmentDescriptor differentDescriptor = new EditAssignmentDescriptorBuilder(editedAssignment)
                .withAssignmentName(VALID_ASSIGNMENT_NAME_2)
                .withAssignmentPercentage(VALID_ASSIGNMENT_PERCENTAGE_1).build();
        assertNotEquals(standardCommand, new EditAssignmentCommand(FIRST_INDEX, editedModule.getName(),
                differentDescriptor));
    }

}
