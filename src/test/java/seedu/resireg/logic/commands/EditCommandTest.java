package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.resireg.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.resireg.testutil.TypicalStudents.getTypicalResiReg;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;
import seedu.resireg.storage.StorageManager;
import seedu.resireg.testutil.EditStudentDescriptorBuilder;
import seedu.resireg.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());
    private Storage storage = new StorageManager(null, null);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            editedStudent.getNameAsString());

        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), model.getUserPrefs());
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(editCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastStudent = Index.fromOneBased(model.getFilteredStudentList().size());
        Student lastStudent = model.getFilteredStudentList().get(indexLastStudent.getZeroBased());

        StudentBuilder studentInList = new StudentBuilder(lastStudent);
        Student editedStudent = studentInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTags(VALID_TAG_HUSBAND).build();

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastStudent, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            editedStudent.getNameAsString());

        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());
        expectedModel.setStudent(lastStudent, editedStudent);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(editCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, new EditStudentDescriptor());
        Student editedStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            editedStudent.getNameAsString());

        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());
        expectedModel.saveStateResiReg();

        assertCommandSuccess(editCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Student studentInFilteredList = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        Student editedStudent = new StudentBuilder(studentInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
            editedStudent.getNameAsString());

        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());
        showStudentAtIndex(expectedModel, INDEX_FIRST_PERSON);
        expectedModel.setStudent(model.getFilteredStudentList().get(0), editedStudent);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(editCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateStudentUnfilteredList_failure() {
        Student firstStudent = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(firstStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, history, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_duplicateStudentFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        // edit student in filtered list into a duplicate in ResiReg
        Student studentInList = model.getResiReg().getStudentList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON,
            new EditStudentDescriptorBuilder(studentInList).build());

        assertCommandFailure(editCommand, model, history, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_invalidStudentIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, history, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of ResiReg
     */
    @Test
    public void execute_invalidStudentIndexFilteredList_failure() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of ResiReg's student list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getStudentList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
            new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, history, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Student editedStudent = new StudentBuilder().build();
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditStudentDescriptor desc = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, desc);
        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());
        expectedModel.setStudent(studentToEdit, editedStudent);
        expectedModel.saveStateResiReg();

        // edit -> first student edited
        editCommand.execute(model, storage, history);

        // undo -> reverts resireg back to prev state and filtered student list to show all students
        expectedModel.undoResiReg();
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first student edited again
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outofBounds = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        EditStudentDescriptor desc = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outofBounds, desc);

        // execution failed -> resireg state not added into model
        assertCommandFailure(editCommand, model, history, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // single resireg state in mode -> undo and redo fails
        assertCommandFailure(new UndoCommand(), model, history, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, history, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code Student} from a filtered list.
     * 2. Undo the edit.
     * 3. The list should have the same filtering as before.
     * 4. Remove list filtering. Verify the index of the edited student has changed.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the student object
     * regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameStudentEdited() throws Exception {
        Student editedStudent = new StudentBuilder().build();
        EditStudentDescriptor desc = new EditStudentDescriptorBuilder(editedStudent).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PERSON, desc);
        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());

        showStudentAtIndex(model, INDEX_SECOND_PERSON);
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.setStudent(studentToEdit, editedStudent);
        expectedModel.saveStateResiReg();

        // edit -> edits second student in unfiltered list / first student in filtered student list
        editCommand.execute(model, storage, history);

        // undo -> reverts resireg back to prev state, keeps filtering
        expectedModel.undoResiReg();
        showStudentAtIndex(expectedModel, INDEX_SECOND_PERSON);
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // remove filtering
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_PERSONS);

        assertNotEquals(model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased()), studentToEdit);
        // redo -> edits same second student in unfiltered student list
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditStudentDescriptor copyDescriptor = new EditStudentDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }

}
