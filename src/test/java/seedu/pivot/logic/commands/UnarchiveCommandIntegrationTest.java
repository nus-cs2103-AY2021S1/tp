package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ARCHIVED_CASES;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.pivot.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.archivecommands.UnarchiveCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.ArchiveStatus;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code UnarchiveCommand}.
 */
public class UnarchiveCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUpArchivedSection() {
        StateManager.setArchivedSection();
        StateManager.resetState();
        model = new ModelManager(getTypicalPivot(), new UserPrefs());
        model.updateFilteredCaseList(PREDICATE_SHOW_ARCHIVED_CASES);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Case caseToUnarchive = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(FIRST_INDEX);

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_CASE_SUCCESS, caseToUnarchive);

        Case unarchivedCase = new CaseBuilder(caseToUnarchive).withArchiveStatus(ArchiveStatus.DEFAULT).build();

        ModelManager expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.deleteCase(caseToUnarchive);
        expectedModel.addCase(unarchivedCase);
        expectedModel.updateFilteredCaseList(PREDICATE_SHOW_ARCHIVED_CASES);
        expectedModel.commitPivot(expectedMessage, unarchiveCommand);

        assertCommandSuccess(unarchiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCaseList().size() + 1);
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(unarchiveCommand, model, MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCaseAtIndex(model, FIRST_INDEX); // filter the list

        Case caseToUnarchive = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(FIRST_INDEX);

        String expectedMessage = String.format(UnarchiveCommand.MESSAGE_UNARCHIVE_CASE_SUCCESS, caseToUnarchive);

        Case unarchivedCase = new CaseBuilder(caseToUnarchive).withArchiveStatus(ArchiveStatus.DEFAULT).build();

        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.deleteCase(caseToUnarchive);
        expectedModel.addCase(unarchivedCase);
        expectedModel.updateFilteredCaseList(PREDICATE_SHOW_ARCHIVED_CASES);
        expectedModel.commitPivot(expectedMessage, unarchiveCommand);

        assertCommandSuccess(unarchiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCaseAtIndex(model, FIRST_INDEX);

        Index outOfBoundIndex = SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPivot().getCaseList().size());

        UnarchiveCommand unarchiveCommand = new UnarchiveCommand(outOfBoundIndex);

        assertCommandFailure(unarchiveCommand, model, MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

}
