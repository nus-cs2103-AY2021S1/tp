package seedu.pivot.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.commons.core.UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.showCaseAtIndex;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;
import static seedu.pivot.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.pivot.testutil.TypicalIndexes.SECOND_INDEX;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.pivot.commons.core.UserMessages;
import seedu.pivot.commons.core.index.Index;
import seedu.pivot.logic.commands.archivecommands.ArchiveCommand;
import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.ArchiveStatus;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code ArchiveCommand}.
 */
public class ArchiveCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUpDefaultSection() {
        StateManager.setDefaultSection();
        StateManager.resetState();
        model = new ModelManager(getTypicalPivot(), new UserPrefs());
        model.updateFilteredCaseList(Model.PREDICATE_SHOW_DEFAULT_CASES);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Case caseToArchive = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        ArchiveCommand archiveCommand = new ArchiveCommand(FIRST_INDEX);

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_CASE_SUCCESS, caseToArchive);

        Case archivedCase = new CaseBuilder(caseToArchive).withArchiveStatus(ArchiveStatus.ARCHIVED).build();

        ModelManager expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.deleteCase(caseToArchive);
        expectedModel.addCase(archivedCase);
        expectedModel.updateFilteredCaseList(Model.PREDICATE_SHOW_DEFAULT_CASES);
        expectedModel.commitPivot(expectedMessage, archiveCommand);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCaseList().size() + 1);
        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCaseAtIndex(model, FIRST_INDEX); // filter the list

        Case caseToArchive = model.getFilteredCaseList().get(FIRST_INDEX.getZeroBased());
        ArchiveCommand archiveCommand = new ArchiveCommand(FIRST_INDEX);

        String expectedMessage = String.format(ArchiveCommand.MESSAGE_ARCHIVE_CASE_SUCCESS, caseToArchive);

        Case archivedCase = new CaseBuilder(caseToArchive).withArchiveStatus(ArchiveStatus.ARCHIVED).build();

        Model expectedModel = new ModelManager(new Pivot(model.getPivot()), new UserPrefs());
        expectedModel.deleteCase(caseToArchive);
        expectedModel.addCase(archivedCase);
        expectedModel.updateFilteredCaseList(Model.PREDICATE_SHOW_DEFAULT_CASES);
        expectedModel.commitPivot(expectedMessage, archiveCommand);

        assertCommandSuccess(archiveCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCaseAtIndex(model, FIRST_INDEX);

        Index outOfBoundIndex = SECOND_INDEX;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPivot().getCaseList().size());

        ArchiveCommand archiveCommand = new ArchiveCommand(outOfBoundIndex);

        assertCommandFailure(archiveCommand, model, UserMessages.MESSAGE_INVALID_CASE_DISPLAYED_INDEX);
    }

}
