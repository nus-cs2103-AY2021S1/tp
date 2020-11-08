package seedu.pivot.logic.commands.casecommands.descriptioncommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.casecommands.descriptioncommands.DeleteDescriptionCommand.MESSAGE_DELETE_DESCRIPTION_SUCCESS;
import static seedu.pivot.logic.commands.casecommands.descriptioncommands.DeleteDescriptionCommand.MESSAGE_NO_DESCRIPTION_TO_DELETE;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.Undoable;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;

public class DeleteDescriptionCommandTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteDescriptionCommand(null));
    }

    @Test
    public void equals() {
        Index indexOne = Index.fromZeroBased(0);

        Index indexTwo = Index.fromZeroBased(1000);

        // same object -> returns true
        DeleteCommand testCommand = new DeleteDescriptionCommand(indexOne);
        assertTrue(testCommand.equals(testCommand));

        // same values -> returns true
        assertTrue(testCommand.equals(new DeleteDescriptionCommand(indexOne)));

        // different types -> returns false
        assertFalse(testCommand.equals(1));

        // null -> returns false
        assertFalse(testCommand.equals(null));

        // different index -> returns false
        assertFalse(testCommand.equals(new DeleteDescriptionCommand(indexTwo)));
    }

    @Test
    public void execute_caseWithDescription_success() throws CommandException {

        // Setup
        Index index = Index.fromZeroBased(0);
        StateManager.setState(index);

        // Generate Case with description
        Case testCase = new CaseBuilder().withDescription("To be deleted!").build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        // Delete a description from a Case
        ModelStub modelStub = new DeleteDescriptionCommandTest.ModelStubWithCaseList(caseList);
        DeleteCommand command = new DeleteDescriptionCommand(index);
        assertEquals(String.format(MESSAGE_DELETE_DESCRIPTION_SUCCESS, testCase.getDescription().toString()),
                command.execute(modelStub).getFeedbackToUser());
        StateManager.resetState();
    }

    @Test
    public void execute_caseWithoutDescription_throwsCommandException() {

        // Setup
        Index index = Index.fromZeroBased(0);
        StateManager.setState(index);

        // Generate Case with no description
        Case testCase = new CaseBuilder().build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        // Delete a description to a Case
        ModelStub modelStub = new DeleteDescriptionCommandTest.ModelStubWithCaseList(caseList);
        DeleteCommand command = new DeleteDescriptionCommand(index);
        assertThrows(CommandException.class, MESSAGE_NO_DESCRIPTION_TO_DELETE, () -> command.execute(modelStub));
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
        public void updateFilteredCaseList(Predicate<Case> predicate) {
            return;
        }

        @Override
        public void commitPivot(String commandMessage, Undoable command) {}
    }
}
