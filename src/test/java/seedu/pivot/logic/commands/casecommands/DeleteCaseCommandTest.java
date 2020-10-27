package seedu.pivot.logic.commands.casecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;


/**
 * Unit Testing for DeleteCaseCommand
 */
public class DeleteCaseCommandTest {

    @Test
    public void constructor_nullCase_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteCaseCommand(null));
    }

    @Test
    public void equals() {
        Index indexZero = Index.fromZeroBased(0);
        Index indexOne = Index.fromOneBased(1000);
        DeleteCommand deleteFirstCommand = new DeleteCaseCommand(indexZero);
        DeleteCommand deleteSecondCommand = new DeleteCaseCommand(indexOne);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCaseCommand(indexZero);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void execute_caseDeletedByModel_deleteSuccessful() throws CommandException {
        Case testCase = new CaseBuilder().withTitle("Alice").build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        Index index = Index.fromZeroBased(0);
        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        DeleteCommand deleteCommand = new DeleteCaseCommand(index);
        CommandResult result = deleteCommand.execute(modelStub);
        assertEquals(String.format(DeleteCaseCommand.MESSAGE_DELETE_CASE_SUCCESS, testCase),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        List<Case> caseList = new ArrayList<>();
        Index index = Index.fromZeroBased(0);
        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        DeleteCommand deleteCommand = new DeleteCaseCommand(index);
        assertThrows(CommandException.class,
                UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX, () -> deleteCommand.execute(modelStub));
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
        public void deleteCase(Case target) {
            caseList.remove(target);
        }

        @Override
        public void commitPivot(String command) {}
    }
}
