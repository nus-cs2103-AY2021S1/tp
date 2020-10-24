package seedu.fma.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.fma.logic.commands.CommandTestUtil.EDIT_LOG_DESCRIPTOR_A;
import static seedu.fma.logic.commands.CommandTestUtil.EDIT_LOG_DESCRIPTOR_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_COMMENT_A_STR;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_A;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_EXERCISE_B;
import static seedu.fma.logic.commands.CommandTestUtil.VALID_REP_A_STR;
import static seedu.fma.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.fma.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.fma.logic.commands.CommandTestUtil.showLogAtIndex;
import static seedu.fma.testutil.TypicalIndexes.INDEX_FIRST_LOG;
import static seedu.fma.testutil.TypicalIndexes.INDEX_SECOND_LOG;
import static seedu.fma.testutil.TypicalLogs.getTypicalLogBook;

import org.junit.jupiter.api.Test;

import seedu.fma.commons.core.Messages;
import seedu.fma.commons.core.index.Index;
import seedu.fma.logic.commands.EditCommand.EditLogDescriptor;
import seedu.fma.model.LogBook;
import seedu.fma.model.Model;
import seedu.fma.model.ModelManager;
import seedu.fma.model.UserPrefs;
import seedu.fma.model.log.Log;
import seedu.fma.testutil.EditLogDescriptorBuilder;
import seedu.fma.testutil.LogBuilder;


/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */

public class EditCommandTest {

    private Model model = new ModelManager(getTypicalLogBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        //specify all fields
        Log editedLog = new LogBuilder().withExercise(VALID_EXERCISE_B).withComment("This is boring")
                .withReps("200").build();
        EditLogDescriptor descriptor = new EditLogDescriptorBuilder(editedLog).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_LOG, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LOG_SUCCESS, editedLog);

        Model expectedModel = new ModelManager(new LogBook(model.getLogBook()), new UserPrefs());
        expectedModel.setLog(model.getFilteredLogList().get(0), editedLog);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastLog = Index.fromOneBased(model.getFilteredLogList().size());
        Log lastLog = model.getFilteredLogList().get(indexLastLog.getZeroBased());

        LogBuilder logInList = new LogBuilder(lastLog);
        Log editedLog = logInList.withExercise(VALID_EXERCISE_A).withComment(VALID_COMMENT_A_STR)
                .withReps(VALID_REP_A_STR).build();

        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A)
                .withComment(VALID_COMMENT_A_STR).withReps(VALID_REP_A_STR).build();
        EditCommand editCommand = new EditCommand(indexLastLog, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LOG_SUCCESS, editedLog);

        Model expectedModel = new ModelManager(new LogBook(model.getLogBook()), new UserPrefs());
        expectedModel.setLog(lastLog, editedLog);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_LOG, new EditLogDescriptor());
        Log editedLog = model.getFilteredLogList().get(INDEX_FIRST_LOG.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LOG_SUCCESS, editedLog);

        Model expectedModel = new ModelManager(new LogBook(model.getLogBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showLogAtIndex(model, INDEX_FIRST_LOG);

        Log logInFilteredList = model.getFilteredLogList().get(INDEX_FIRST_LOG.getZeroBased());
        Log editedLog = new LogBuilder(logInFilteredList).withExercise(VALID_EXERCISE_B).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_LOG,
                new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_B).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_LOG_SUCCESS, editedLog);

        Model expectedModel = new ModelManager(new LogBook(model.getLogBook()), new UserPrefs());
        expectedModel.setLog(model.getFilteredLogList().get(0), editedLog);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidLogIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredLogList().size() + 1);
        EditLogDescriptor descriptor = new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_LOG_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidLogIndexFilteredList_failure() {
        showLogAtIndex(model, INDEX_FIRST_LOG);
        Index outOfBoundIndex = INDEX_SECOND_LOG;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getLogBook().getLogList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditLogDescriptorBuilder().withExercise(VALID_EXERCISE_A).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_LOG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_LOG, EDIT_LOG_DESCRIPTOR_A);

        // same values -> returns true
        EditCommand.EditLogDescriptor copyDescriptor = EDIT_LOG_DESCRIPTOR_A;
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_LOG, copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different index -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_SECOND_LOG, EDIT_LOG_DESCRIPTOR_A));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditCommand(INDEX_FIRST_LOG, EDIT_LOG_DESCRIPTOR_B));
    }

}
