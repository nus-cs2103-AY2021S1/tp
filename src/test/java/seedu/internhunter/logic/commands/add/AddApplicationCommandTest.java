package seedu.internhunter.logic.commands.add;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_ADD_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.METHOD_SHOULD_NOT_FAIL_MESSAGE;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.internhunter.logic.commands.util.application.ApplicationCommandTestUtil.DUPLICATE_APPLICATION_MESSAGE;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_COMPANY_INDEX_MESSAGE;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_INTERNSHIP_INDEX_MESSAGE;
import static seedu.internhunter.model.util.ItemUtil.APPLICATION_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_DATE_JUNE_2021;
import static seedu.internhunter.testutil.application.ApplicationItemFieldsUtil.VALID_STATUS_DATE_JUNE_2022;
import static seedu.internhunter.testutil.application.SampleApplicationItems.getSampleApplicationItemList;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
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
import seedu.internhunter.model.application.Status;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.ui.tabs.TabName;

/**
 * Contains integration tests (interaction with the Model).
 */
public class AddApplicationCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), getSampleApplicationItemList(), new ItemList<>(),
                new UserPrefs());
        expectedModel = new ModelManager(model.getUnfilteredCompanyList(), model.getUnfilteredApplicationList(),
                new ItemList<>(), new UserPrefs());
    }

    @Test
    public void execute_validIndexesUnfilteredList_addSuccess() {
        try {
            AddApplicationCommand addApplicationCommand = new AddApplicationCommand(INDEX_THIRD, INDEX_SECOND,
                    Status.APPLIED, VALID_STATUS_DATE_JUNE_2021);
            CompanyItem companyItem = model.getCompanyItemFromFilteredList(INDEX_THIRD.getZeroBased());
            InternshipItem internshipItem = companyItem.getInternship(INDEX_SECOND);
            ApplicationItem applicationToAdd = new ApplicationItem(internshipItem, Status.APPLIED,
                    VALID_STATUS_DATE_JUNE_2021);

            String addSuccessMessage = String.format(MESSAGE_ADD_SUCCESS, APPLICATION_NAME, applicationToAdd);

            expectedModel.addApplication(applicationToAdd);
            setApplicationViewIndex(expectedModel);
            expectedModel.setTabName(TabName.APPLICATION);

            CommandResult commandResult = new CommandResult(addSuccessMessage, false, false, true, true);
            assertCommandSuccess(addApplicationCommand, model, commandResult, expectedModel);

        } catch (CommandException e) {
            throw new AssertionError(METHOD_SHOULD_NOT_FAIL_MESSAGE, e);
        }
    }

    @Test
    public void execute_validIndexesFilteredList_success() {
        try {
            showCompanyAtIndex(model, INDEX_THIRD);
            showCompanyAtIndex(expectedModel, INDEX_THIRD);
            AddApplicationCommand addApplicationCommand = new AddApplicationCommand(INDEX_FIRST, INDEX_SECOND,
                    Status.APPLIED, VALID_STATUS_DATE_JUNE_2021);
            CompanyItem companyItem = model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
            InternshipItem internshipItem = companyItem.getInternship(INDEX_SECOND);
            ApplicationItem applicationToAdd = new ApplicationItem(internshipItem, Status.APPLIED,
                    VALID_STATUS_DATE_JUNE_2021);

            String addSuccessMessage = String.format(MESSAGE_ADD_SUCCESS, APPLICATION_NAME, applicationToAdd);

            expectedModel.addApplication(applicationToAdd);
            setApplicationViewIndex(expectedModel);
            expectedModel.setTabName(TabName.APPLICATION);

            CommandResult commandResult = new CommandResult(addSuccessMessage, false, false, true, true);
            assertCommandSuccess(addApplicationCommand, model, commandResult, expectedModel);

        } catch (CommandException e) {
            throw new AssertionError(METHOD_SHOULD_NOT_FAIL_MESSAGE, e);
        }
    }

    @Test
    public void execute_validIndexesDuplicateApplication_throwsCommandException() {
        try {
            AddApplicationCommand addApplicationCommand = new AddApplicationCommand(INDEX_THIRD, INDEX_SECOND,
                    Status.APPLIED, VALID_STATUS_DATE_JUNE_2021);
            CompanyItem companyItem = model.getCompanyItemFromFilteredList(INDEX_THIRD.getZeroBased());
            InternshipItem internshipItem = companyItem.getInternship(INDEX_SECOND);
            ApplicationItem applicationToAdd = new ApplicationItem(internshipItem, Status.APPLIED,
                    VALID_STATUS_DATE_JUNE_2021);

            model.addApplication(applicationToAdd);
            assertCommandFailure(addApplicationCommand, model, DUPLICATE_APPLICATION_MESSAGE);

        } catch (CommandException e) {
            throw new AssertionError(METHOD_SHOULD_NOT_FAIL_MESSAGE, e);
        }
    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyListSize() + 1);
        AddApplicationCommand addApplicationCommand = new AddApplicationCommand(outOfBoundIndex, INDEX_FIRST,
                Status.APPLIED, VALID_STATUS_DATE_JUNE_2021);

        assertCommandFailure(addApplicationCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_invalidCompanyIndexFilteredList_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_SECOND);
        Index outOfBoundIndex = INDEX_SECOND;

        // ensures that outOfBoundIndex is still in bounds of company list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredCompanyListSize());

        AddApplicationCommand addApplicationCommand =
                new AddApplicationCommand(outOfBoundIndex, INDEX_FIRST, Status.APPLIED, VALID_STATUS_DATE_JUNE_2021);

        assertCommandFailure(addApplicationCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_invalidInternshipIndex_throwsCommandException() {

        CompanyItem companyItem = model.getCompanyItemFromFilteredList(INDEX_THIRD.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(companyItem.getNumberOfInternships() + 1);
        AddApplicationCommand addApplicationCommand = new AddApplicationCommand(INDEX_THIRD, outOfBoundIndex,
                Status.APPLIED, VALID_STATUS_DATE_JUNE_2021);

        assertCommandFailure(addApplicationCommand, model, INVALID_INTERNSHIP_INDEX_MESSAGE);
    }

    @Test
    public void equals_test_success() {
        AddApplicationCommand addApplicationCommand = new AddApplicationCommand(INDEX_FIRST, INDEX_SECOND,
                Status.APPLIED, VALID_STATUS_DATE_JUNE_2021);

        // same object -> returns true
        assertEquals(addApplicationCommand, addApplicationCommand);

        // same values -> returns true
        AddApplicationCommand addCommandCopy = new AddApplicationCommand(INDEX_FIRST, INDEX_SECOND,
                Status.APPLIED, VALID_STATUS_DATE_JUNE_2021);
        assertEquals(addCommandCopy, addApplicationCommand);

        // different type -> returns false
        assertNotEquals(addApplicationCommand, new AddCompanyCommand(GOLDMAN));

        // null -> returns false
        assertNotEquals(addApplicationCommand, null);

        // different company index -> returns false
        assertNotEquals(addApplicationCommand, new AddApplicationCommand(INDEX_THIRD, INDEX_SECOND,
                Status.APPLIED, VALID_STATUS_DATE_JUNE_2021));

        // different internship index -> returns false
        assertNotEquals(addApplicationCommand, new AddApplicationCommand(INDEX_FIRST, INDEX_FIRST,
                Status.APPLIED, VALID_STATUS_DATE_JUNE_2021));

        // different status -> returns false
        assertNotEquals(addApplicationCommand, new AddApplicationCommand(INDEX_FIRST, INDEX_SECOND,
                Status.ACCEPTED, VALID_STATUS_DATE_JUNE_2021));

        // different status date -> returns false
        assertNotEquals(addApplicationCommand, new AddApplicationCommand(INDEX_FIRST, INDEX_SECOND,
                Status.APPLIED, VALID_STATUS_DATE_JUNE_2022));
    }

    /**
     * Sets the application view index to the newly added application.
     *
     * @param model {@code Model} which the command should operate on.
     */
    private void setApplicationViewIndex(Model model) {
        int size = model.getFilteredApplicationListSize();
        model.setApplicationViewIndex(Index.fromOneBased(size));
    }

}
