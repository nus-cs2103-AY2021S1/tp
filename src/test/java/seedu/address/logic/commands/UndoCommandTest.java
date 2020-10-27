package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UndoCommandTest {

    private ModelStubTrackUndo modelStub;
    private UndoCommand undoCommand;

    @BeforeEach
    public void setUp() {
        modelStub = new ModelStubTrackUndo();
        undoCommand = new UndoCommand();
    }

    @Test
    public void execute_onBaseStub_failure() {
        CommandResult commandResult = undoCommand.execute(modelStub);

        assertEquals(UndoCommand.MESSAGE_FAILURE, commandResult.getFeedbackToUser());
        assertEquals(false, modelStub.canUndo);
    }

    @Test
    public void execute_afterCommand_success() {
        modelStub.commit();
        assertEquals(true, modelStub.canUndo);

        CommandResult commandResult = undoCommand.execute(modelStub);

        assertEquals(UndoCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(false, modelStub.canUndo);
    }

    @Test
    public void equals() {
        UndoCommand undoCommand = new UndoCommand();

        // same object -> true
        assertEquals(undoCommand, undoCommand);

        // null -> false
        assertNotEquals(undoCommand, null);
    }

    private class ModelStubTrackUndo extends ModelStub {
        private boolean canUndo;

        ModelStubTrackUndo() {
            canUndo = false;
        }

        public void commit() {
            canUndo = true;
        }

        @Override
        public boolean undoInventory() {
            if (!canUndo) {
                return false;
            }

            canUndo = false;
            return true;
        }
    }
}
