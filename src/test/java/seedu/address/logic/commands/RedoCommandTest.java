package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RedoCommandTest {

    private ModelStubTrackRedo modelStub;
    private RedoCommand redoCommand;

    @BeforeEach
    public void setUp() {
        modelStub = new ModelStubTrackRedo();
        redoCommand = new RedoCommand();
    }

    @Test
    public void execute_onBaseStub_failure() {
        CommandResult commandResult = redoCommand.execute(modelStub);

        assertEquals(RedoCommand.MESSAGE_FAILURE, commandResult.getFeedbackToUser());
        assertEquals(false, modelStub.canRedo);
    }

    @Test
    public void execute_afterCommand_failure() {
        modelStub.commit();
        modelStub.undo();
        assertEquals(true, modelStub.canRedo);
        modelStub.commit();

        CommandResult commandResult = redoCommand.execute(modelStub);

        assertEquals(RedoCommand.MESSAGE_FAILURE, commandResult.getFeedbackToUser());
        assertEquals(false, modelStub.canRedo);
    }

    @Test
    public void execute_afterUndo_success() {
        modelStub.commit();
        modelStub.undo();
        assertEquals(true, modelStub.canRedo);

        CommandResult commandResult = redoCommand.execute(modelStub);

        assertEquals(RedoCommand.MESSAGE_SUCCESS, commandResult.getFeedbackToUser());
        assertEquals(false, modelStub.canRedo);
    }

    @Test
    public void equals() {
        RedoCommand redoCommand = new RedoCommand();

        // same object -> true
        assertEquals(redoCommand, redoCommand);

        // null -> false
        assertNotEquals(redoCommand, null);
    }

    private class ModelStubTrackRedo extends ModelStub {
        private boolean canRedo;

        ModelStubTrackRedo() {
            canRedo = false;
        }

        public void commit() {
            canRedo = false;
        }

        public void undo() {
            canRedo = true;
        }

        @Override
        public boolean redoInventory() {
            if (!canRedo) {
                return false;
            }

            canRedo = false;
            return true;
        }
    }
}
