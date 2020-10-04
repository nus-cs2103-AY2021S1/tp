package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAssignmentAtIndex;
import static seedu.address.testutil.TypicalAssignments.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ASSIGNMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ASSIGNMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditAssignmentDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Assignment;
import seedu.address.testutil.AssignmentBuilder;
import seedu.address.testutil.EditAssignmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Assignment editedAssignment = new AssignmentBuilder().build();
        EditCommand.EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(editedAssignment).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ASSIGNMENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), editedAssignment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAssignment = Index.fromOneBased(model.getFilteredAssignmentList().size());
        Assignment lastAssignment = model.getFilteredAssignmentList().get(indexLastAssignment.getZeroBased());

        AssignmentBuilder assignmentInList = new AssignmentBuilder(lastAssignment);
        Assignment editedAssignment = assignmentInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditCommand.EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastAssignment, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAssignment(lastAssignment, editedAssignment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ASSIGNMENT, new EditAssignmentDescriptor());
        Assignment editedAssignment = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        Assignment assignmentInFilteredList = model.getFilteredAssignmentList()
                .get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        Assignment editedAssignment = new AssignmentBuilder(assignmentInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ASSIGNMENT,
                new EditAssignmentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_ASSIGNMENT_SUCCESS, editedAssignment);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setAssignment(model.getFilteredAssignmentList().get(0), editedAssignment);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateAssignmentUnfilteredList_failure() {
        Assignment firstAssignment = model.getFilteredAssignmentList().get(INDEX_FIRST_ASSIGNMENT.getZeroBased());
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder(firstAssignment).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_ASSIGNMENT, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }

    @Test
    public void execute_duplicateAssignmentFilteredList_failure() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);

        // edit assignment in filtered list into a duplicate in address book
        Assignment assignmentInList = model.getAddressBook().getAssignmentList()
                .get(INDEX_SECOND_ASSIGNMENT.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ASSIGNMENT,
                new EditAssignmentDescriptorBuilder(assignmentInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_ASSIGNMENT);
    }

    @Test
    public void execute_invalidAssignmentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAssignmentList().size() + 1);
        EditAssignmentDescriptor descriptor = new EditAssignmentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidAssignmentIndexFilteredList_failure() {
        showAssignmentAtIndex(model, INDEX_FIRST_ASSIGNMENT);
        Index outOfBoundIndex = INDEX_SECOND_ASSIGNMENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getAssignmentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditAssignmentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_ASSIGNMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_ASSIGNMENT, DESC_AMY);

        // same values -> returns true
        EditCommand.EditAssignmentDescriptor copyDescriptor = new EditCommand.EditAssignmentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_ASSIGNMENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_ASSIGNMENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_ASSIGNMENT, DESC_BOB)));
    }

}
