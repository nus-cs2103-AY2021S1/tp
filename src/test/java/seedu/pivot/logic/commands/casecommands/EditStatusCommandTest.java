package seedu.pivot.logic.commands.casecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.pivot.logic.commands.casecommands.EditStatusCommand.MESSAGE_EDIT_STATUS_SUCCESS;
import static seedu.pivot.model.investigationcase.Status.ACTIVE;
import static seedu.pivot.model.investigationcase.Status.CLOSED;
import static seedu.pivot.model.investigationcase.Status.COLD;
import static seedu.pivot.testutil.Assert.assertThrows;
import static seedu.pivot.testutil.TypicalCases.getTypicalCases;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;

import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.logic.commands.Undoable;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Case;

public class EditStatusCommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditStatusCommand(null, ACTIVE));
        assertThrows(NullPointerException.class, () -> new EditStatusCommand(FIRST_INDEX, null));
    }

    @Test
    public void execute_validStatus_success() throws CommandException {
        //set up case page
        StateManager.setState(FIRST_INDEX);

        //set up model with case list
        ModelStub modelStub = new ModelStubWithCaseList(getTypicalCases());

        //initialise valid commands
        EditStatusCommand commandActive = new EditStatusCommand(FIRST_INDEX, ACTIVE);
        EditStatusCommand commandCold = new EditStatusCommand(FIRST_INDEX, COLD);
        EditStatusCommand commandClosed = new EditStatusCommand(FIRST_INDEX, CLOSED);

        assertEquals(String.format(MESSAGE_EDIT_STATUS_SUCCESS, ACTIVE),
                commandActive.execute(modelStub).getFeedbackToUser());
        assertEquals(String.format(MESSAGE_EDIT_STATUS_SUCCESS, COLD),
                commandCold.execute(modelStub).getFeedbackToUser());
        assertEquals(String.format(MESSAGE_EDIT_STATUS_SUCCESS, CLOSED),
                commandClosed.execute(modelStub).getFeedbackToUser());

        StateManager.resetState();
    }

    /**
     * A Model stub that holds a caseList.
     */
    private class ModelStubWithCaseList extends ModelStub {
        private final List<Case> caseList;

        private ModelStubWithCaseList(List<Case> caseList) {
            this.caseList = caseList;
        }

        @Override
        public ObservableList<Case> getFilteredCaseList() {
            return FXCollections.observableList(caseList);
        }

        @Override
        public void setCase(Case target, Case editedCase) {
            this.caseList.set(caseList.indexOf(target), editedCase);
        }

        @Override
        public void commitPivot(String commandMessage, Undoable command) {}

    }
}
