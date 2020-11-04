package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.RedoCommandTest.RedoableModelStub;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;

public class RedoCommandIntegrationTest {

    @Test
    public void execute_redoCasePageCommandWithoutStateReset_success() throws CommandException {
        StateManager.setState(Index.fromOneBased(1));

        Model modelStub = new RedoableCasePageModelStub();
        CommandResult commandResult = new RedoCommand().execute(modelStub);

        Index expectedState = Index.fromOneBased(1);
        assertEquals(expectedState, StateManager.getState());
    }

    @Test
    public void execute_redoMainPageCommandWithStateReset_success() throws CommandException {
        StateManager.setState(Index.fromOneBased(2));

        Model modelStub = new RedoableMainPageModelStub();
        CommandResult commandResult = new RedoCommand().execute(modelStub);

        assertEquals(null, StateManager.getState());
    }

    private static class RedoableMainPageModelStub extends RedoableModelStub { }

    private static class RedoableCasePageModelStub extends RedoCommandTest.RedoableModelStub {

        @Override
        public boolean isMainPageCommand() {
            return false;
        }
    }
}
