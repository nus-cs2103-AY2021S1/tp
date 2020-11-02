package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;

public class UndoCommandTest {
    @BeforeEach
    public void setUp() {
        StateManager.resetState();
    }

    @AfterAll
    public static void tearDown() {
        StateManager.resetState();
    }

    @Test
    public void execute_cannotUndo_throwsCommandException() {
        Model model = new ModelManager(getTypicalPivot(), new UserPrefs());
        UndoCommand undoCommand = new UndoCommand();

        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_UNDO_FAILURE);
    }

    @Test
    public void execute_undoFromMainPage_success() throws CommandException {
        Model modelStub = new UndoableModelStub();
        CommandResult commandResult = new UndoCommand().execute(modelStub);

        String expectedMessage = "Returned message";
        assertEquals(UndoCommand.MESSAGE_UNDO_SUCCESS + expectedMessage,
                commandResult.getFeedbackToUser());
    }

    protected static class UndoableModelStub extends ModelStub {

        @Override
        public ObservableList<Case> getFilteredCaseList() {
            ObservableList<Case> filteredCaseListStub = FXCollections.observableArrayList();
            filteredCaseListStub.add(new CaseBuilder().build());
            return filteredCaseListStub;
        }

        @Override
        public boolean canUndoPivot() {
            return true;
        }

        @Override
        public void undoPivot() { }

        @Override
        public String getCommandMessage() {
            return "Returned message";
        }

        @Override
        public boolean isMainPageCommand() {
            return true;
        }
    }
}
