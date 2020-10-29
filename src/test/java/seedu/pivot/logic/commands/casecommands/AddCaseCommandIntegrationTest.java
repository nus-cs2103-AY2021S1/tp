package seedu.pivot.logic.commands.casecommands;

import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandFailure;
import static seedu.pivot.logic.commands.testutil.CommandTestUtil.assertCommandSuccess;
import static seedu.pivot.model.Model.PREDICATE_SHOW_ARCHIVED_CASES;
import static seedu.pivot.model.Model.PREDICATE_SHOW_DEFAULT_CASES;
import static seedu.pivot.testutil.TypicalCases.getTypicalPivot;

import org.junit.jupiter.api.Test;

import seedu.pivot.logic.state.StateManager;
import seedu.pivot.model.Model;
import seedu.pivot.model.ModelManager;
import seedu.pivot.model.UserPrefs;
import seedu.pivot.model.investigationcase.ArchiveStatus;
import seedu.pivot.model.investigationcase.Case;
import seedu.pivot.testutil.CaseBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCaseCommand}.
 */
public class AddCaseCommandIntegrationTest {

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
    public void executeDefaultSection_newCase_success() {
        setUpDefaultSection();
        Case validCase = new CaseBuilder().build();

        Model expectedModel = new ModelManager(model.getPivot(), new UserPrefs());
        expectedModel.addCase(validCase);
        expectedModel.updateFilteredCaseList(PREDICATE_SHOW_DEFAULT_CASES);

        assertCommandSuccess(new AddCaseCommand(validCase), model,
                String.format(AddCaseCommand.MESSAGE_ADD_CASE_SUCCESS, validCase), expectedModel);
    }

    @Test
    public void executeArchivedSection_newCase_success() {
        setUpArchivedSection();
        Case validCase = new CaseBuilder().withArchiveStatus(ArchiveStatus.ARCHIVED).build();

        Model expectedModel = new ModelManager(model.getPivot(), new UserPrefs());
        expectedModel.addCase(validCase);
        expectedModel.updateFilteredCaseList(PREDICATE_SHOW_ARCHIVED_CASES);

        assertCommandSuccess(new AddCaseCommand(validCase), model,
                String.format(AddCaseCommand.MESSAGE_ADD_CASE_SUCCESS, validCase), expectedModel);
    }

    @Test
    public void executeDefaultSection_duplicateCase_throwsCommandException() {
        setUpDefaultSection();
        Case caseInList = model.getPivot().getCaseList().get(0);
        assertCommandFailure(new AddCaseCommand(caseInList), model, AddCaseCommand.MESSAGE_DUPLICATE_CASE);
    }

    @Test
    public void executeArchiveSection_duplicateCase_throwsCommandException() {
        setUpArchivedSection();
        Case caseInList = model.getPivot().getCaseList().get(0);
        assertCommandFailure(new AddCaseCommand(caseInList), model, AddCaseCommand.MESSAGE_DUPLICATE_CASE);
    }

}
