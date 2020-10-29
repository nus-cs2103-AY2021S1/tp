package seedu.pivot.logic.commands.casecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.CommandResult;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.commands.exceptions.CommandException;
import seedu.pivot.logic.commands.testutil.ModelStub;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.investigationcase.ArchiveStatus;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;


/**
 * Unit Testing for DeleteCaseCommand
 */
public class DeleteCaseCommandTest {

    public void setUpMainPageDefaultSection() {
        StateManager.resetState();
        StateManager.setDefaultSection();
    }

    public void setUpMainPageArchivedSection() {
        StateManager.resetState();
        StateManager.setArchivedSection();
    }

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

        // different case -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void executeDefaultSection_caseDeletedByModel_deleteSuccessful() throws CommandException {
        setUpMainPageDefaultSection();

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
    public void executeArchivedSection_caseDeletedByModel_deleteSuccessful() throws CommandException {
        setUpMainPageArchivedSection();

        Case testCase = new CaseBuilder().withTitle("Alice").withArchiveStatus(ArchiveStatus.ARCHIVED).build();
        List<Case> caseList = new ArrayList<>();
        caseList.add(testCase);

        Index index = Index.fromZeroBased(0);
        ModelStubWithCaseList modelStub = new ModelStubWithCaseList(caseList);
        DeleteCommand deleteCommand = new DeleteCaseCommand(index);
        CommandResult result = deleteCommand.execute(modelStub);
        assertEquals(String.format(DeleteCaseCommand.MESSAGE_DELETE_CASE_SUCCESS, testCase),
                result.getFeedbackToUser());
        assertEquals(Arrays.asList(), modelStub.caseList);
    }

    @Test
    public void executeDefaultSection_invalidIndex_throwsCommandException() {
        setUpMainPageDefaultSection();

        List<Case> caseList = new ArrayList<>();
        Index index = Index.fromZeroBased(0);
        ModelStub modelStub = new ModelStubWithCaseList(caseList);
        DeleteCommand deleteCommand = new DeleteCaseCommand(index);
        assertThrows(CommandException.class,
                UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX, () -> deleteCommand.execute(modelStub));
    }

    @Test
    public void executeArchivedSection_invalidIndex_throwsCommandException() {
        setUpMainPageArchivedSection();

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
        final List<Case> caseList;

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
        public void updateFilteredCaseList(Predicate<Case> predicate) {
            caseList.stream().filter(predicate);
        }

        @Override
        public void commitPivot(String command, boolean isMainPageCommand) {}
    }
}
