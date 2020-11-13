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
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
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

public class DeleteCompanyCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), getSampleApplicationItemList(), new ItemList<>(),
                new UserPrefs());
        expectedModel = new ModelManager(getSampleCompanyList(), getSampleApplicationItemList(),
                new ItemList<>(), new UserPrefs());
        model.setTabName(TabName.COMPANY);
    }

    @Test
    public void execute_validIndexesUnfilteredList_deleteSuccess() {
        try {
            DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_SECOND);

            // Delete all applications for internships to the company to be deleted
            CompanyItem companyToDelete = expectedModel.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());
            InternshipItem internshipToDelete = companyToDelete.getInternship(INDEX_FIRST);
            ApplicationItem applicationToDelete = new ApplicationItem(internshipToDelete);
            expectedModel.deleteSameApplication(applicationToDelete);

            // Delete all internships from the company
            companyToDelete.removeInternship(INDEX_FIRST);

            // Delete the company
            expectedModel.deleteCompany(companyToDelete);
            expectedModel.setCompanyViewIndex(INDEX_FIRST);

            String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, COMPANY_NAME, companyToDelete);

            CommandResult commandResult = new CommandResult(deleteSuccessMessage, false, false, false, true);
            assertCommandSuccess(deleteCompanyCommand, model, commandResult, expectedModel);

        } catch (CommandException e) {
            throw new AssertionError(METHOD_SHOULD_NOT_FAIL_MESSAGE, e);
        }
    }

    @Test
    public void execute_validIndexesFilteredList_success() {
        try {
            showCompanyAtIndex(model, INDEX_SECOND);
            showCompanyAtIndex(expectedModel, INDEX_SECOND);

            DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_FIRST);

            // Delete all applications for internships to the company to be deleted
            CompanyItem companyToDelete = expectedModel.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
            InternshipItem internshipToDelete = companyToDelete.getInternship(INDEX_FIRST);
            ApplicationItem applicationToDelete = new ApplicationItem(internshipToDelete);
            expectedModel.deleteSameApplication(applicationToDelete);

            // Delete all internships from the company
            companyToDelete.removeInternship(INDEX_FIRST);

            // Delete the company
            expectedModel.deleteCompany(companyToDelete);

            String deleteSuccessMessage = String.format(MESSAGE_DELETED_ITEM, COMPANY_NAME, companyToDelete);

            CommandResult commandResult = new CommandResult(deleteSuccessMessage, false, false, false, true);
            assertCommandSuccess(deleteCompanyCommand, model, commandResult, expectedModel);

        } catch (CommandException e) {
            throw new AssertionError(METHOD_SHOULD_NOT_FAIL_MESSAGE, e);
        }
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyListSize() + 1);
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(outOfBoundIndex);

        assertCommandFailure(deleteCompanyCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_invalidCompanyIndexFilteredList_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_SECOND);
        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of company list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredCompanyListSize());

        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(outOfBoundIndex);

        assertCommandFailure(deleteCompanyCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void equals_test_success() {
        DeleteCompanyCommand deleteCompanyCommand = new DeleteCompanyCommand(INDEX_FIRST);

        // same object -> returns true
        assertEquals(deleteCompanyCommand, deleteCompanyCommand);

        // same values -> returns true
        DeleteCompanyCommand deleteCompanyCommandCopy = new DeleteCompanyCommand(INDEX_FIRST);
        assertEquals(deleteCompanyCommandCopy, deleteCompanyCommand);

        // different type -> returns false
        assertNotEquals(deleteCompanyCommand, new DeleteApplicationCommand(INDEX_FIRST));

        // null -> returns false
        assertNotEquals(deleteCompanyCommand, null);

        // different index -> returns false
        assertNotEquals(deleteCompanyCommand, new DeleteCompanyCommand(INDEX_THIRD));
    }

}
