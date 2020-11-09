package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.testutil.TypicalBinItems.getTypicalResiReg;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_BIN_ITEM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_BIN_ITEM;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.bin.Binnable;
import seedu.resireg.storage.Storage;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code RestoreCommand}.
 */
public class RestoreCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());
    private Storage storage = null;

    @Test
    public void executeStudent_validIndexUnfilteredList_success() {
        BinItem itemToRestore = model.getFilteredBinItemList().get(INDEX_FIRST_BIN_ITEM.getZeroBased());
        Binnable item = itemToRestore.getBinnedItem();
        RestoreCommand restoreCommand = new RestoreCommand(INDEX_FIRST_BIN_ITEM);

        String expectedMessage = RestoreCommand.MESSAGE_RESTORE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.restoreItem(item);
        expectedModel.deleteBinItem(itemToRestore);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(restoreCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void executeRoom_validIndexUnfilteredList_success() {
        BinItem itemToRestore = model.getFilteredBinItemList().get(INDEX_SECOND_BIN_ITEM.getZeroBased());
        Binnable item = itemToRestore.getBinnedItem();
        RestoreCommand restoreCommand = new RestoreCommand(INDEX_SECOND_BIN_ITEM);

        String expectedMessage = RestoreCommand.MESSAGE_RESTORE_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.restoreItem(item);
        expectedModel.deleteBinItem(itemToRestore);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(restoreCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBinItemList().size() + 1);
        RestoreCommand restoreCommand = new RestoreCommand(outOfBoundIndex);

        assertCommandFailure(restoreCommand, model, history, Messages.MESSAGE_INVALID_BIN_ITEM_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        BinItem toRestore = model.getFilteredBinItemList().get(INDEX_FIRST_BIN_ITEM.getZeroBased());
        RestoreCommand restoreCommand = new RestoreCommand(INDEX_FIRST_BIN_ITEM);
        Model expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.restoreItem(toRestore.getBinnedItem());
        expectedModel.deleteBinItem(toRestore);
        expectedModel.saveStateResiReg();

        // restore -> first bin item restored to student list
        restoreCommand.execute(model, storage, history);

        // undo -> reverts resireg back to previous state
        expectedModel.undoResiReg();
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first student deleted again
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBinItemList().size() + 1);
        RestoreCommand restoreCommand = new RestoreCommand(outOfBoundIndex);

        // execution failed -> state not added to model
        assertCommandFailure(restoreCommand, model, history, Messages.MESSAGE_INVALID_BIN_ITEM_DISPLAYED_INDEX);

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
    public void equals() {
        RestoreCommand restoreFirstCommand = new RestoreCommand(INDEX_FIRST_BIN_ITEM);
        RestoreCommand restoreSecondCommand = new RestoreCommand(INDEX_SECOND_BIN_ITEM);

        // same object -> returns true
        assertTrue(restoreFirstCommand.equals(restoreFirstCommand));

        // same values -> returns true
        RestoreCommand deleteFirstCommandCopy = new RestoreCommand(INDEX_FIRST_BIN_ITEM);
        assertTrue(restoreFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(restoreFirstCommand.equals(1));

        // null -> returns false
        assertFalse(restoreFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(restoreFirstCommand.equals(restoreSecondCommand));
    }
}
