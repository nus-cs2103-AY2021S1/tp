package seedu.internhunter.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhunter.commons.core.Messages.MESSAGE_EDIT_SUCCESS;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.internhunter.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.internhunter.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.internhunter.logic.commands.util.company.CompanyCommandTestUtil.INVALID_COMPANY_INDEX_MESSAGE;
import static seedu.internhunter.model.util.ItemUtil.COMPANY_NAME;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_EMAIL_AMAZON;
import static seedu.internhunter.testutil.company.CompanyItemFieldsUtil.VALID_PHONE_EIGHT_NUMBERS;
import static seedu.internhunter.testutil.company.SampleCompanyItems.AMAZON;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GARENA;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GOLDMAN;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.internhunter.commons.core.index.Index;
import seedu.internhunter.logic.commands.CommandResult;
import seedu.internhunter.logic.commands.ExitCommand;
import seedu.internhunter.logic.commands.exceptions.CommandException;
import seedu.internhunter.logic.commands.util.company.EditCompanyDescriptorBuilder;
import seedu.internhunter.model.Model;
import seedu.internhunter.model.ModelManager;
import seedu.internhunter.model.UserPrefs;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.testutil.company.CompanyItemBuilder;
import seedu.internhunter.ui.tabs.TabName;

public class EditCompanyCommandTest {
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
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        CompanyItem editedCompany = new CompanyItemBuilder(AMAZON).build();
        EditCompanyCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder(editedCompany).build();
        EditCompanyCommand editCommand = new EditCompanyCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, COMPANY_NAME, editedCompany);

        CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);
        expectedModel.setCompany(model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased()),
                editedCompany);
        expectedModel.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased()).updateAllInternshipsCompanyName();
        expectedModel.setTabName(TabName.COMPANY);
        expectedModel.setCompanyViewIndex(INDEX_FIRST);

        assertCommandSuccess(editCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_oneFieldSpecifiedUnfilteredList_success() {
        CompanyItem companyToEdit = model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
        CompanyItem editedCompany = new CompanyItemBuilder(companyToEdit)
                .withPhone(VALID_PHONE_EIGHT_NUMBERS)
                .build();
        EditCompanyCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder(editedCompany).build();
        EditCompanyCommand editCommand = new EditCompanyCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, COMPANY_NAME, editedCompany);

        CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);
        expectedModel.setCompany(model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased()),
                editedCompany);
        expectedModel.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased()).updateAllInternshipsCompanyName();
        expectedModel.setTabName(TabName.COMPANY);
        expectedModel.setCompanyViewIndex(INDEX_FIRST);

        assertCommandSuccess(editCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCompanyCommand editCommand = new EditCompanyCommand(INDEX_SECOND,
                new EditCompanyCommand.EditCompanyDescriptor());
        CompanyItem editedCompany = model.getCompanyItemFromFilteredList(INDEX_SECOND.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, COMPANY_NAME, editedCompany);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);
        expectedModel.setTabName(TabName.COMPANY);
        expectedModel.setCompanyViewIndex(INDEX_SECOND);

        assertCommandSuccess(editCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCompanyAtIndex(model, INDEX_SECOND);
        showCompanyAtIndex(expectedModel, INDEX_SECOND);
        CompanyItem companyInFilteredList =
                model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased());
        CompanyItem editedCompany =
                new CompanyItemBuilder(companyInFilteredList).withPhone(VALID_PHONE_EIGHT_NUMBERS).build();
        EditCompanyCommand editCommand = new EditCompanyCommand(INDEX_FIRST,
                new EditCompanyDescriptorBuilder().withPhone(VALID_PHONE_EIGHT_NUMBERS).build());

        String expectedMessage = String.format(MESSAGE_EDIT_SUCCESS, COMPANY_NAME, editedCompany);
        expectedModel.setCompany(model.getCompanyItemFromFilteredList(INDEX_FIRST.getZeroBased()), editedCompany);
        expectedModel.setTabName(TabName.COMPANY);
        expectedModel.setCompanyViewIndex(INDEX_SECOND);
        CommandResult commandResult = new CommandResult(expectedMessage, false, false, false, true);

        assertCommandSuccess(editCommand, model, commandResult, expectedModel);
    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyListSize() + 1);
        EditCompanyCommand.EditCompanyDescriptor descriptor =
                new EditCompanyDescriptorBuilder().withEmail(VALID_EMAIL_AMAZON).build();
        EditCompanyCommand editCommand = new EditCompanyCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of company list
     */
    @Test
    public void execute_invalidCompanyIndexFilteredList_failure() {
        showCompanyAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of company list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUnFilteredCompanyListSize());

        EditCompanyCommand editCommand = new EditCompanyCommand(outOfBoundIndex,
                new EditCompanyDescriptorBuilder().withEmail(VALID_EMAIL_AMAZON).build());

        assertCommandFailure(editCommand, model, INVALID_COMPANY_INDEX_MESSAGE);
    }

    @Test
    public void execute_editAnItemToBeTheSameAsAnItemWhichAlreadyExist_throwsCommandException() {
        EditCompanyCommand.EditCompanyDescriptor descriptor =
            new EditCompanyDescriptorBuilder(GOLDMAN).build();
        EditCompanyCommand editCommand = new EditCompanyCommand(INDEX_FIRST, descriptor);
        assertThrows(CommandException.class, () -> editCommand.execute(model));
    }

    @Test
    public void equals_test_success() {
        EditCompanyCommand.EditCompanyDescriptor amazon = new EditCompanyDescriptorBuilder(AMAZON).build();
        EditCompanyCommand.EditCompanyDescriptor garena = new EditCompanyDescriptorBuilder(GARENA).build();
        final EditCompanyCommand standardCommand = new EditCompanyCommand(INDEX_SECOND, amazon);

        // same values -> returns true
        EditCompanyCommand.EditCompanyDescriptor copyDescriptor = new EditCompanyCommand.EditCompanyDescriptor(amazon);
        EditCompanyCommand commandWithSameValues = new EditCompanyCommand(INDEX_SECOND, copyDescriptor);
        assertEquals(commandWithSameValues, standardCommand);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(standardCommand, null);

        // different types -> returns false
        assertNotEquals(new ExitCommand(), standardCommand);

        // different index -> returns false
        assertNotEquals(new EditCompanyCommand(INDEX_FIRST, amazon), standardCommand);

        // different descriptor -> returns false
        assertNotEquals(new EditCompanyCommand(INDEX_SECOND, garena), standardCommand);
    }
}
