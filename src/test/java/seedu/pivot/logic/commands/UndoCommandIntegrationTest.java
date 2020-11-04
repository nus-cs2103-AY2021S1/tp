package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.UndoCommandTest.UndoableModelStub;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;

public class UndoCommandIntegrationTest {

    @Test
    public void execute_undoCasePageCommandWithoutStateReset_success() throws CommandException {
        StateManager.setState(Index.fromOneBased(1));

        Model modelStub = new UndoableCasePageModelStub();
        CommandResult commandResult = new UndoCommand().execute(modelStub);

        Index expectedState = Index.fromOneBased(1);
        assertEquals(expectedState, StateManager.getState());
    }

    @Test
    public void execute_undoMainPageCommandWithStateReset_success() throws CommandException {
        StateManager.setState(Index.fromOneBased(2));

        Model modelStub = new UndoableMainPageModelStub();
        CommandResult commandResult = new UndoCommand().execute(modelStub);

        assertEquals(null, StateManager.getState());
    }

    private static class UndoableMainPageModelStub extends UndoableModelStub { }

    private static class UndoableCasePageModelStub extends UndoableModelStub {

        @Override
        public boolean isMainPageCommand() {
            return false;
        }
    }
}
