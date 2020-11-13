package seedu.internhunter.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_DELETED_ITEM;
import static seedu.internhunter.logic.commands.CommandTestUtil.METHOD_SHOULD_NOT_FAIL_MESSAGE;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_COMPANY_INDEX_MESSAGE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_INTERNSHIP_INDEX_MESSAGE;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.application.ApplicationItem;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteInternshipCommand}. Tests also include testing for deletion of application associated with the
 * internship.
 */
public class DeleteInternshipCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), getSampleApplicationItemList(), new ItemList<>(),
                new UserPrefs());
        expectedModel = new ModelManager(getSampleCompanyList(), getSampleApplicationItemList(),
                new ItemList<>(), new UserPrefs());
        // Set tab name to be on other tab to test if tab changes to company. Expected model tab is company by default.
        model.setTabName(TabName.PROFILE);
    }

    @Test
    public void execute_validIndexesUnfilteredList_deleteSuccess() {
        try {
            DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(INDEX_SECOND, INDEX_FIRST);
            CompanyItem companyItem = expectedModel.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());
            InternshipItem internshipItem = companyItem.getInternship(INDEX_FIRST);
            ApplicationItem applicationToDelete = new ApplicationItem(internshipItem);
            expectedModel.deleteSameApplication(applicationToDelete);

            companyItem.removeInternship(INDEX_FIRST);
            expectedModel.setCompanyViewIndex(INDEX_SECOND);

            String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, INTERNSHIP_NAME, internshipItem);

            CommandResult commandResult = new CommandResult(deleteSuccessMessage, false, false, true, true);
            assertCommandSuccess(deleteInternshipCommand, model, commandResult, expectedModel);

        } catch (CommandException e) {
            throw new AssertionError(METHOD_SHOULD_NOT_FAIL_MESSAGE, e);
        }
    }

    @Test
    public void execute_validIndexesFilteredList_success() {
        try {
            showCompanyAtIndex(model, INDEX_SECOND);
            showCompanyAtIndex(expectedModel, INDEX_SECOND);

            DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(INDEX_FIRST, INDEX_FIRST);
            CompanyItem companyItem = expectedModel.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
            InternshipItem internshipItem = companyItem.getInternship(INDEX_FIRST);
            ApplicationItem applicationToDelete = new ApplicationItem(internshipItem);
            expectedModel.deleteSameApplication(applicationToDelete);

            String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, INTERNSHIP_NAME, internshipItem);

            companyItem.removeInternship(INDEX_FIRST);
            CommandResult commandResult = new CommandResult(deleteSuccessMessage, false, false, true, true);
            assertCommandSuccess(deleteInternshipCommand, model, commandResult, expectedModel);

        } catch (CommandException e) {
            throw new AssertionError(METHOD_SHOULD_NOT_FAIL_MESSAGE, e);
        }
    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyListSize() + 1);
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(outOfBoundIndex, INDEX_FIRST);

        assertCommandFailure(deleteInternshipCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_invalidCompanyIndexFilteredList_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_SECOND);
        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of company list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredCompanyListSize());

        DeleteInternshipCommand deleteInternshipCommand =
                new DeleteInternshipCommand(outOfBoundIndex, INDEX_FIRST);

        assertCommandFailure(deleteInternshipCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_invalidInternshipIndex_throwsCommandException() {

        CompanyItem companyItem = model.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(companyItem.getNumberOfInternships() + 1);
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(INDEX_SECOND, outOfBoundIndex);

        assertCommandFailure(deleteInternshipCommand, model, INVALID_INTERNSHIP_INDEX_MESSAGE);
    }

    @Test
    public void equals_test_success() {
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(INDEX_FIRST, INDEX_SECOND);

        // same object -> returns true
        assertEquals(deleteInternshipCommand, deleteInternshipCommand);

        // same values -> returns true
        DeleteInternshipCommand deleteInternshipCommandCopy = new DeleteInternshipCommand(INDEX_FIRST, INDEX_SECOND);
        assertEquals(deleteInternshipCommandCopy, deleteInternshipCommand);

        // different type -> returns false
        assertNotEquals(deleteInternshipCommand, new DeleteApplicationCommand(INDEX_FIRST));

        // null -> returns false
        assertNotEquals(deleteInternshipCommand, null);

        // different company index -> returns false
        assertNotEquals(deleteInternshipCommand, new DeleteInternshipCommand(INDEX_THIRD, INDEX_SECOND));

        // different internship index -> returns false
        assertNotEquals(deleteInternshipCommand, new DeleteInternshipCommand(INDEX_FIRST, INDEX_FIRST));
    }

}
