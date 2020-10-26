package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.student.Student;
import seedu.resireg.storage.Storage;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());
    private Storage storage = null;

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
            studentToDelete.getNameAsString());

        ModelManager expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        expectedModel.addBinItem(new BinItem(studentToDelete));
        expectedModel.saveStateResiReg();

        assertCommandSuccess(deleteCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, history, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Student studentToDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS,
            studentToDelete.getNameAsString());

        Model expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        expectedModel.addBinItem(new BinItem(studentToDelete));
        expectedModel.saveStateResiReg();
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showStudentAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of students list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getStudentList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, history, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Student toDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        Model expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.deleteStudent(toDelete);
        expectedModel.addBinItem(new BinItem(toDelete));
        expectedModel.saveStateResiReg();

        // delete -> first student deleted
        deleteCommand.execute(model, storage, history);

        // undo -> reverts resireg back to previous state
        expectedModel.undoResiReg();
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first student deleted again
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        // execution failed -> state not added to model
        assertCommandFailure(deleteCommand, model, history, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        // single resireg state in model -> undo and redo failures
        assertCommandFailure(new UndoCommand(), model, history, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, history, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Student} from a filtered list.
     * 2. Undo deletion.
     * 3. The list should have the same filtering as before.
     * 4. Remove list filtering. Verify the index of the deleted student has changed.
     * 5. Redo deletion, ensuring {@code RedoCommand} deletes the student
     * regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameStudentDeleted() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        Model expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());

        showStudentAtIndex(model, INDEX_SECOND_PERSON);
        Student toDelete = model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased());
        expectedModel.deleteStudent(toDelete);
        expectedModel.addBinItem(new BinItem(toDelete));
        expectedModel.saveStateResiReg();

        // delete -> deletes second student in unfiltered student list, first student in filtered student list
        deleteCommand.execute(model, storage, history);

        // undo -> reverts resireg back to previous state, keeps filtering
        expectedModel.undoResiReg();
        showStudentAtIndex(expectedModel, INDEX_SECOND_PERSON);
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // remove filtering
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_PERSONS);
        expectedModel.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_PERSONS);

        assertNotEquals(toDelete, model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased()));
        // redo -> delete same second student in unfiltered student list
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
