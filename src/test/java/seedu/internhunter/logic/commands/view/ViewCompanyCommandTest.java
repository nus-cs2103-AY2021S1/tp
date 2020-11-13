package seedu.internhunter.logic.commands.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_VIEW_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_COMPANY_INDEX_MESSAGE;
import static seedu.internhunter.logic.commands.view.ViewCommand.MESSAGE_ALREADY_VIEWING;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code ViewCompanyCommand}.
 */
public class ViewCompanyCommandTest {

    private Model model;
    private Model expectedModel;

    private final String messageAlreadyViewing = String.format(MESSAGE_ALREADY_VIEWING, COMPANY_NAME, INDEX_SECOND);

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), new ItemList<>(), new ItemList<>(), new UserPrefs());
        expectedModel = new ModelManager(getSampleCompanyList(), new ItemList<>(), new ItemList<>(),
                new UserPrefs());
        model.setTabName(TabName.COMPANY);
    }

    @Test
    public void execute_validAndNotCurrentIndexDifferentTabUnfilteredList_success() {
        ViewCompanyCommand viewCommand = new ViewCompanyCommand(INDEX_SECOND);

        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, COMPANY_NAME, INDEX_SECOND);
        final CommandResult commandResultSuccess = new CommandResult(messageViewSuccess, false, false, false, true);

        expectedModel.setCompanyViewIndex(INDEX_SECOND);
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyListSize() + 1);
        ViewCompanyCommand viewCommand = new ViewCompanyCommand(outOfBoundIndex);
        assertCommandFailure(viewCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        // Start from any tab besides the Company tab
        model.setTabName(TabName.PROFILE);
        expectedModel.setTabName(TabName.PROFILE);

        showCompanyAtIndex(model, INDEX_FIRST);
        ViewCompanyCommand viewCommand = new ViewCompanyCommand(INDEX_FIRST);

        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, COMPANY_NAME, INDEX_FIRST);

        CommandResult commandResultSuccess = new CommandResult(messageViewSuccess, false, false, true, true);

        expectedModel.setCompanyViewIndex(INDEX_FIRST);
        expectedModel.setTabName(TabName.COMPANY);
        showCompanyAtIndex(expectedModel, INDEX_FIRST);
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_SECOND);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of company list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredCompanyListSize());

        ViewCompanyCommand viewCommand = new ViewCompanyCommand(outOfBoundIndex);

        assertCommandFailure(viewCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_validAndCurrentIndexAndCurrentTab_successAlreadyViewing() {
        model.setCompanyViewIndex(INDEX_SECOND);
        ViewCompanyCommand viewCommand = new ViewCompanyCommand(INDEX_SECOND);

        final CommandResult commandResultViewing = new CommandResult(messageAlreadyViewing, false, false, false, false);

        expectedModel.setCompanyViewIndex(INDEX_SECOND);
        assertCommandSuccess(viewCommand, model, commandResultViewing, expectedModel);
    }

    @Test
    public void execute_currentTabAndDifferentIndex_success() {
        ViewCompanyCommand viewCommand = new ViewCompanyCommand(INDEX_SECOND);
        final String messageViewSuccess = String.format(MESSAGE_VIEW_SUCCESS, COMPANY_NAME, INDEX_SECOND);
        final CommandResult commandResultSuccess = new CommandResult(messageViewSuccess);

        expectedModel.setCompanyViewIndex(INDEX_SECOND);
        assertCommandSuccess(viewCommand, model, commandResultSuccess, expectedModel);
    }

    @Test
    public void equals_test_success() {
        ViewCompanyCommand viewFirstCommand = new ViewCompanyCommand(INDEX_FIRST);
        ViewCompanyCommand viewSecondCommand = new ViewCompanyCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(viewFirstCommand, viewFirstCommand);

        // same values -> returns true
        ViewCompanyCommand viewFirstCommandCopy = new ViewCompanyCommand(INDEX_FIRST);
        assertEquals(viewFirstCommandCopy, viewFirstCommand);

        // different types -> returns false
        assertNotEquals(viewFirstCommand, new ViewCompanyCommand(INDEX_SECOND));

        // null -> returns false
        assertNotEquals(viewFirstCommand, null);

        // different index -> returns false
        assertNotEquals(viewSecondCommand, viewFirstCommand);
    }
}
