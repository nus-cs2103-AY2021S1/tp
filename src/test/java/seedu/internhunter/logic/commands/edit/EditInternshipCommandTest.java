package seedu.internhunter.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.internhunter.commons.core.Messages.MESSAGE_EDIT_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.internhunter.logic.commands.util.internship.InternshipCommandTestUtil.INVALID_INTERNSHIP_INDEX_MESSAGE;
import static seedu.internhunter.model.FilterableItemList.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.internhunter.model.util.ItemUtil.INTERNSHIP_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_THIRD;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_JOB_TITLE_FE;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_PERIOD_MAY_TO_JULY;
import static seedu.internhunter.testutil.internship.InternshipItemFieldsUtil.VALID_WAGE_3000;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.FACEBOOK_FE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.GOLDMAN_FE;
import static seedu.internhunter.testutil.internship.SampleInternshipItems.LAZADA_DS;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.Messages;
import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.ExitCommand;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.commands.util.internship.EditInternshipDescriptorBuilder;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.internship.InternshipItem;
import seedu.internhunter.model.internship.JobTitle;
import seedu.internhunter.model.internship.Period;
import seedu.internhunter.model.internship.Requirement;
import seedu.internhunter.model.internship.Wage;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.testutil.internship.InternshipItemBuilder;
import seedu.internhunter.ui.tabs.TabName;

public class EditInternshipCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getSampleCompanyList(), new ItemList<>(), new ItemList<>(),
                new UserPrefs());
        expectedModel = new ModelManager(getSampleCompanyList(), new ItemList<>(), new ItemList<>(),
                new UserPrefs());
        model.setTabName(TabName.COMPANY);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredCompanyList_success() {
        InternshipItem editedInternship = new InternshipItemBuilder(GOLDMAN_FE).build();
        EditInternshipCommand.EditInternshipDescriptor descriptor =
                new EditInternshipDescriptorBuilder(editedInternship).build();
        EditInternshipCommand editCommand = new EditInternshipCommand(INDEX_SECOND, INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, INTERNSHIP_NAME, editedInternship);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);

        CompanyItem companyOfInternshipToEdit =
                expectedModel.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());
        try {
            InternshipItem internshipToEdit = companyOfInternshipToEdit.getInternship(INDEX_FIRST);

            JobTitle initialJobTitle = internshipToEdit.getJobTitle();
            Period initialPeriod = internshipToEdit.getPeriod();
            Wage initialWage = internshipToEdit.getWage();
            Set<Requirement> initialRequirements = internshipToEdit.getRequirements();

            internshipToEdit.setJobTitle(editedInternship.getJobTitle());
            internshipToEdit.setPeriod(editedInternship.getPeriod());
            internshipToEdit.setWage(editedInternship.getWage());
            internshipToEdit.setRequirements(editedInternship.getRequirements());

            expectedModel.setCompanyViewIndex(INDEX_SECOND);
            assertCommandSuccess(editCommand, model, commandResult, expectedModel);

            // Restore internship back to its previous state
            internshipToEdit.setJobTitle(initialJobTitle);
            internshipToEdit.setPeriod(initialPeriod);
            internshipToEdit.setWage(initialWage);
            internshipToEdit.setRequirements(initialRequirements);
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    public void execute_oneFieldSpecifiedUnfilteredCompanyList_success() {
        CompanyItem companyOfTestInternshipToEdit = model.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());
        try {
            InternshipItem testInternshipToEdit = companyOfTestInternshipToEdit.getInternship(INDEX_FIRST);
            InternshipItem editedInternship = new InternshipItemBuilder(testInternshipToEdit)
                    .withJobTitle(VALID_JOB_TITLE_FE)
                    .build();
            EditInternshipCommand.EditInternshipDescriptor descriptor =
                    new EditInternshipDescriptorBuilder(editedInternship).build();
            EditInternshipCommand editCommand = new EditInternshipCommand(INDEX_SECOND, INDEX_FIRST, descriptor);

            String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, INTERNSHIP_NAME, editedInternship);
            CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);

            CompanyItem companyOfEditedInternship =
                    expectedModel.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());
            InternshipItem internshipToEdit = companyOfEditedInternship.getInternship(INDEX_FIRST);

            JobTitle initialJobTitle = internshipToEdit.getJobTitle();
            Period initialPeriod = internshipToEdit.getPeriod();
            Wage initialWage = internshipToEdit.getWage();
            Set<Requirement> initialRequirements = internshipToEdit.getRequirements();

            internshipToEdit.setJobTitle(editedInternship.getJobTitle());
            internshipToEdit.setPeriod(editedInternship.getPeriod());
            internshipToEdit.setWage(editedInternship.getWage());
            internshipToEdit.setRequirements(editedInternship.getRequirements());

            expectedModel.setCompanyViewIndex(INDEX_SECOND);
            assertCommandSuccess(editCommand, model, commandResult, expectedModel);

            // Restore internship back to its previous state
            internshipToEdit.setJobTitle(initialJobTitle);
            internshipToEdit.setPeriod(initialPeriod);
            internshipToEdit.setWage(initialWage);
            internshipToEdit.setRequirements(initialRequirements);
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredCompanyList_success() {
        EditInternshipCommand editCommand = new EditInternshipCommand(INDEX_SECOND, INDEX_FIRST,
                new EditInternshipCommand.EditInternshipDescriptor());
        CompanyItem companyOfEditedInternship = model.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());
        try {
            InternshipItem editedInternship = companyOfEditedInternship.getInternship(INDEX_FIRST);

            String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, INTERNSHIP_NAME, editedInternship);
            CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);

            expectedModel.setCompanyViewIndex(INDEX_SECOND);
            assertCommandSuccess(editCommand, model, commandResult, expectedModel);
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    public void execute_filteredCompanyList_success() {
        showCompanyAtIndex(model, INDEX_SECOND);
        showCompanyAtIndex(expectedModel, INDEX_SECOND);
        CompanyItem companyInFilteredList =
                model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
        try {
            InternshipItem testInternshipToEdit = companyInFilteredList.getInternship(INDEX_FIRST);
            InternshipItem editedInternship =
                    new InternshipItemBuilder(testInternshipToEdit).withPeriod(VALID_PERIOD_MAY_TO_JULY).build();
            EditInternshipCommand editCommand = new EditInternshipCommand(INDEX_FIRST, INDEX_FIRST,
                    new EditInternshipDescriptorBuilder().withPeriod(VALID_PERIOD_MAY_TO_JULY).build());

            String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, INTERNSHIP_NAME, editedInternship);
            CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);

            CompanyItem companyOfEditedInternship =
                    expectedModel.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
            InternshipItem internshipToEdit = companyOfEditedInternship.getInternship(INDEX_FIRST);

            JobTitle initialJobTitle = internshipToEdit.getJobTitle();
            Period initialPeriod = internshipToEdit.getPeriod();
            Wage initialWage = internshipToEdit.getWage();
            Set<Requirement> initialRequirements = internshipToEdit.getRequirements();

            internshipToEdit.setJobTitle(editedInternship.getJobTitle());
            internshipToEdit.setPeriod(editedInternship.getPeriod());
            internshipToEdit.setWage(editedInternship.getWage());
            internshipToEdit.setRequirements(editedInternship.getRequirements());

            expectedModel.updateFilteredCompanyList(PREDICATE_SHOW_ALL_ITEMS);
            expectedModel.setCompanyViewIndex(INDEX_SECOND);
            assertCommandSuccess(editCommand, model, commandResult, expectedModel);

            // Restore internship back to its previous state
            internshipToEdit.setJobTitle(initialJobTitle);
            internshipToEdit.setPeriod(initialPeriod);
            internshipToEdit.setWage(initialWage);
            internshipToEdit.setRequirements(initialRequirements);
        } catch (CommandException e) {
            fail(e);
        }
    }

    @Test
    public void execute_invalidInternshipIndexUnfilteredCompanyList_failure() {
        CompanyItem companyOfInternshipToEdit = model.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());
        Index outOfBoundInternshipIndex = Index.fromOneBased(companyOfInternshipToEdit.getNumberOfInternships() + 1);
        EditInternshipCommand.EditInternshipDescriptor descriptor =
                new EditInternshipDescriptorBuilder().withWage(VALID_WAGE_3000).build();
        EditInternshipCommand editCommand = new EditInternshipCommand(INDEX_SECOND, outOfBoundInternshipIndex,
                descriptor);

        assertCommandFailure(editCommand, model, INVALID_INTERNSHIP_INDEX_MESSAGE);
    }

    @Test
    public void execute_invalidInternshipIndexFilteredCompanyList_failure() {
        showCompanyAtIndex(model, INDEX_SECOND);
        CompanyItem companyOfInternshipToEdit = model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
        Index outOfBoundInternshipIndex = Index.fromOneBased(companyOfInternshipToEdit.getNumberOfInternships() + 1);

        EditInternshipCommand editCommand = new EditInternshipCommand(INDEX_FIRST, outOfBoundInternshipIndex,
                new EditInternshipDescriptorBuilder().withWage(VALID_WAGE_3000).build());

        assertCommandFailure(editCommand, model, INVALID_INTERNSHIP_INDEX_MESSAGE);
    }

    @Test
    public void execute_editIntoDuplicateInternship_failure() {
        InternshipItem editedInternship = new InternshipItemBuilder(FACEBOOK_FE).build();
        EditInternshipCommand.EditInternshipDescriptor descriptor =
                new EditInternshipDescriptorBuilder(editedInternship).build();
        EditInternshipCommand editCommand = new EditInternshipCommand(INDEX_THIRD, INDEX_FIRST, descriptor);

        String expectedMessage = String.format(Messages.MESSAGE_DUPLICATE_ITEM, INTERNSHIP_NAME);

        assertCommandFailure(editCommand, model, expectedMessage);
    }

    @Test
    public void equals_test_success() {
        EditInternshipCommand.EditInternshipDescriptor facebookFe =
                new EditInternshipDescriptorBuilder(FACEBOOK_FE).build();
        EditInternshipCommand.EditInternshipDescriptor lazadaDs =
                new EditInternshipDescriptorBuilder(LAZADA_DS).build();
        final EditInternshipCommand standardCommand = new EditInternshipCommand(INDEX_THIRD, INDEX_FIRST, facebookFe);

        // same values -> returns true
        EditInternshipCommand.EditInternshipDescriptor copyDescriptor = new EditInternshipCommand
                .EditInternshipDescriptor(facebookFe);
        EditInternshipCommand commandWithSameValues = new EditInternshipCommand(INDEX_THIRD, INDEX_FIRST,
                copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(standardCommand, new ExitCommand());

        // different internship index -> returns false
        assertNotEquals(new EditInternshipCommand(INDEX_THIRD, INDEX_SECOND, facebookFe), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditInternshipCommand(INDEX_THIRD, INDEX_FIRST, lazadaDs), standardCommand);

        // different company index -> returns false
        assertNotEquals(new EditInternshipCommand(INDEX_SECOND, INDEX_FIRST, lazadaDs), standardCommand);
    }
}
