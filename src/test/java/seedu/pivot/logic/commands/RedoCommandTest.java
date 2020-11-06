package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;

public class RedoCommandTest {
    @Test
    public void execute_cannotRedo_throwsCommandException() {
        Model model = new ModelManager(getTypicalPivot(), new UserPrefs());
        RedoCommand redoCommand = new RedoCommand();

        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_REDO_FAILURE);
    }

    @Test
    public void execute_canRedo_success() {
        Model model = new ModelManager(getTypicalPivot(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPivot(), new UserPrefs());
        expectedModel.setPivot(new Pivot());

    }

    @Test
    public void execute_redoFromMainPage_success() throws CommandException {
        Model modelStub = new RedoableModelStub();
        CommandResult commandResult = new RedoCommand().execute(modelStub);

        String expectedMessage = "Returned message";
        assertEquals(RedoCommand.MESSAGE_REDO_SUCCESS + expectedMessage,
                commandResult.getFeedbackToUser());
    }

    protected static class RedoableModelStub extends ModelStub {

        @Override
        public ObservableList<Case> getFilteredCaseList() {
            ObservableList<Case> filteredCaseListStub = FXCollections.observableArrayList();
            filteredCaseListStub.add(new CaseBuilder().build());
            return filteredCaseListStub;
        }

        @Override
        public boolean canRedoPivot() {
            return true;
        }

        @Override
        public void redoPivot() { }

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
