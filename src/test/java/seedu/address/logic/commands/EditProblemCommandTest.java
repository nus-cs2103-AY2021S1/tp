package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPORT1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_REPORT2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_P2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SEVERITY_P2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showReportAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_REPORT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_REPORT;
import static seedu.address.testutil.TypicalProblems.getTypicalReportLibrary;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Library;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.problem.Problem;
import seedu.address.testutil.EditProblemDescriptorBuilder;
import seedu.address.testutil.ProblemBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditProblemCommandTest {

    private Model model = new ModelManager(getTypicalReportLibrary(), new UserPrefs());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastReport = Index.fromOneBased(model.getFilteredProblemList().size());
        Problem lastProblem = model.getFilteredProblemList().get(indexLastReport.getZeroBased());

        ProblemBuilder problemInList = new ProblemBuilder(lastProblem);
        Problem editedProblem = problemInList.withDescription(VALID_DESCRIPTION_P2).withSeverity(VALID_SEVERITY_P2)
               .build();

        EditProblemCommand.EditProblemDescriptor descriptor = new EditProblemDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_P2).withSeverity(VALID_SEVERITY_P2).build();
        EditProblemCommand editProblemCommand = new EditProblemCommand(indexLastReport, descriptor);

        String expectedMessage = String.format(EditProblemCommand.MESSAGE_EDIT_REPORT_SUCCESS, editedProblem);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setProblem(lastProblem, editedProblem);

        assertCommandSuccess(editProblemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditProblemCommand editProblemCommand = new EditProblemCommand(INDEX_FIRST_REPORT,
                new EditProblemCommand.EditProblemDescriptor());

        Problem editedReport = model.getFilteredProblemList().get(INDEX_FIRST_REPORT.getZeroBased());

        String expectedMessage = String.format(EditProblemCommand.MESSAGE_EDIT_REPORT_SUCCESS, editedReport);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());

        assertCommandSuccess(editProblemCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showReportAtIndex(model, INDEX_FIRST_REPORT);

        Problem problemInFilteredList = model.getFilteredProblemList().get(INDEX_FIRST_REPORT.getZeroBased());
        Problem editedReport = new ProblemBuilder(problemInFilteredList).withSeverity(VALID_SEVERITY_P2).build();
        EditProblemCommand editCommand = new EditProblemCommand(INDEX_FIRST_REPORT,
                new EditProblemDescriptorBuilder().withSeverity(VALID_SEVERITY_P2).build());

        String expectedMessage = String.format(EditProblemCommand.MESSAGE_EDIT_REPORT_SUCCESS, editedReport);

        Model expectedModel = new ModelManager(new Library(model.getLibrary()), new UserPrefs());
        expectedModel.setProblem(model.getFilteredProblemList().get(0), editedReport);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateBookUnfilteredList_failure() {
        Problem firstReport = model.getFilteredProblemList().get(INDEX_FIRST_REPORT.getZeroBased());
        EditProblemCommand.EditProblemDescriptor descriptor = new EditProblemDescriptorBuilder(firstReport).build();
        EditProblemCommand editProblemCommand = new EditProblemCommand(INDEX_SECOND_REPORT, descriptor);

        assertCommandFailure(editProblemCommand, model, EditProblemCommand.MESSAGE_DUPLICATE_REPORT);
    }

    @Test
    public void equals() {
        final EditProblemCommand standardCommand = new EditProblemCommand(INDEX_FIRST_REPORT, DESC_REPORT1);

        // same values -> returns true
        EditProblemCommand.EditProblemDescriptor copyDescriptor =
                new EditProblemCommand.EditProblemDescriptor(DESC_REPORT1);
        EditProblemCommand commandWithSameValues = new EditProblemCommand(INDEX_FIRST_REPORT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditProblemCommand(INDEX_SECOND_REPORT, DESC_REPORT1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditProblemCommand(INDEX_FIRST_REPORT, DESC_REPORT2)));
    }

}
