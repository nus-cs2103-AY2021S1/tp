package seedu.pivot.logic.commands.casecommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ARCHIVED_CASES;
import static seedu.pivot.model.Model.PREDICATE_SHOW_DEFAULT_CASES;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.pivot.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.DeleteCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.Case;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCaseCommand}.
 */
public class DeleteCaseCommandIntegrationTest {

    private Model model;

    public void setUpDefaultSection() {
        StateManager.setDefaultSection();
        model = new ModelManager(getTypicalPivot(), new UserPrefs());
        model.updateFilteredCaseList(PREDICATE_SHOW_DEFAULT_CASES);
    }

    public void setUpArchivedSection() {
        StateManager.setArchivedSection();
        model = new ModelManager(getTypicalPivot(), new UserPrefs());
        model.updateFilteredCaseList(PREDICATE_SHOW_ARCHIVED_CASES);
    }

    @Test
    public void executeDefaultSection_validIndexUnfilteredList_success() {
        setUpDefaultSection();

        Case caseToDelete = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCaseCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCaseCommand.MESSAGE_DELETE_CASE_SUCCESS, caseToDelete);

        ModelManager expectedModel = new ModelManager(model.getPivot(), new UserPrefs());
        expectedModel.deleteCase(caseToDelete);
        expectedModel.updateFilteredCaseList(PREDICATE_SHOW_DEFAULT_CASES);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeArchivedSection_validIndexUnfilteredList_success() {
        setUpArchivedSection();

        Case caseToDelete = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCaseCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCaseCommand.MESSAGE_DELETE_CASE_SUCCESS, caseToDelete);

        ModelManager expectedModel = new ModelManager(model.getPivot(), new UserPrefs());
        expectedModel.deleteCase(caseToDelete);
        expectedModel.updateFilteredCaseList(PREDICATE_SHOW_ARCHIVED_CASES);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeDefaultSection_invalidIndexUnfilteredList_throwsCommandException() {
        setUpDefaultSection();

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCaseList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCaseCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

    @Test
    public void executeArchivedSection_invalidIndexUnfilteredList_throwsCommandException() {
        setUpArchivedSection();

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCaseList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCaseCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

    @Test
    public void executeDefaultSection_validIndexFilteredList_success() {
        setUpDefaultSection();

        showCaseAtIndex(model, INDEX_FIRST_PERSON); // filter the list

        Case caseToDelete = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCaseCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCaseCommand.MESSAGE_DELETE_CASE_SUCCESS, caseToDelete);

        Model expectedModel = new ModelManager(model.getPivot(), new UserPrefs());
        expectedModel.deleteCase(caseToDelete);
        expectedModel.updateFilteredCaseList(PREDICATE_SHOW_DEFAULT_CASES);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeArchivedSection_validIndexFilteredList_success() {
        setUpArchivedSection();

        showCaseAtIndex(model, INDEX_FIRST_PERSON); // filter the list

        Case caseToDelete = model.getFilteredCaseList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCaseCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCaseCommand.MESSAGE_DELETE_CASE_SUCCESS, caseToDelete);

        Model expectedModel = new ModelManager(model.getPivot(), new UserPrefs());
        expectedModel.deleteCase(caseToDelete);
        expectedModel.updateFilteredCaseList(PREDICATE_SHOW_ARCHIVED_CASES);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeDefaultSection_invalidIndexFilteredList_throwsCommandException() {
        setUpDefaultSection();

        showCaseAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPivot().getCaseList().size());

        DeleteCommand deleteCommand = new DeleteCaseCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

    @Test
    public void executeArchivedSection_invalidIndexFilteredList_throwsCommandException() {
        setUpArchivedSection();

        showCaseAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPivot().getCaseList().size());

        DeleteCommand deleteCommand = new DeleteCaseCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCaseCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCaseCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCaseCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredCaseList(p -> false);

        assertTrue(model.getFilteredCaseList().isEmpty());
    }
}
