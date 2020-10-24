package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TYPICAL_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TYPICAL_REVENUE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_ROSES;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.getTypicalCommonCents;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.address.model.CommonCents;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.account.Account;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
import seedu.address.testutil.EditEntryDescriptorBuilder;
import seedu.address.testutil.ExpenseBuilder;
import seedu.address.testutil.RevenueBuilder;

public class EditCommandTest {
    private final Model model = new ModelManager(getTypicalCommonCents(), new UserPrefs());
    private final Account account = model.getFilteredAccountList().get(0);
    private final ExpenseBuilder expenseBuilder = new ExpenseBuilder();
    private final RevenueBuilder revenueBuilder = new RevenueBuilder();

    @Test
    public void execute_allExpenseFieldsSpecifiedList_success() {
        Expense editedExpense = expenseBuilder.build();
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(editedExpense).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new CommonCents(model.getCommonCents()), new UserPrefs());
        Account expectedAccount = expectedModel.getFilteredAccountList().get(0);
        expectedAccount.setExpense(account.getExpenseList().get(0), editedExpense);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allRevenueFieldsSpecifiedList_success() {
        Revenue editedRevenue = revenueBuilder.build();
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(editedRevenue).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedRevenue);

        Model expectedModel = new ModelManager(new CommonCents(model.getCommonCents()), new UserPrefs());
        Account expectedAccount = expectedModel.getFilteredAccountList().get(0);
        expectedAccount.setRevenue(account.getRevenueList().get(0), editedRevenue);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_someFieldsSpecifiedList_success() {
        Index indexLastExpense = Index.fromOneBased(account.getExpenseList().size());
        Entry lastEntry = account.getExpenseList().get(indexLastExpense.getZeroBased());

        assert(lastEntry instanceof Expense);
        ExpenseBuilder expenseInList = new ExpenseBuilder((Expense) lastEntry);
        Expense editedExpense = expenseInList.withDescription(VALID_DESCRIPTION_EXPENSE)
                .withTags(VALID_TAG_ROSES).build();

        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withCategory("expense")
                .withDescription(VALID_DESCRIPTION_EXPENSE).withTags(VALID_TAG_ROSES).build();
        EditCommand editCommand = new EditCommand(indexLastExpense, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new CommonCents(model.getCommonCents()), new UserPrefs());
        Account expectedAccount = expectedModel.getFilteredAccountList().get(0);
        expectedAccount.setExpense(account.getExpenseList().get(0), editedExpense);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedList_success() {
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withCategory("expense").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        Expense editedExpense = account.getExpenseList().get(INDEX_FIRST_ENTRY.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new CommonCents(model.getCommonCents()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidExpenseIndexList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(account.getExpenseList().size() + 1);
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withCategory("expense").build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidRevenueIndexList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(account.getRevenueList().size() + 1);
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder().withCategory("revenue").build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommandExpense = new EditCommand(INDEX_FIRST_ENTRY,
                DESC_TYPICAL_EXPENSE);
        final EditCommand standardCommandRevenue = new EditCommand(INDEX_FIRST_ENTRY,
                DESC_TYPICAL_REVENUE);

        // same values -> returns true
        EditEntryDescriptor copyDescriptorExpense = new EditEntryDescriptor(DESC_TYPICAL_EXPENSE);
        EditCommand commandWithSameValuesExpense = new EditCommand(INDEX_FIRST_ENTRY, copyDescriptorExpense);
        assertTrue(standardCommandExpense.equals(commandWithSameValuesExpense));

        EditEntryDescriptor copyDescriptorRevenue = new EditEntryDescriptor(DESC_TYPICAL_REVENUE);
        EditCommand commandWithSameValuesRevenue = new EditCommand(INDEX_FIRST_ENTRY, copyDescriptorRevenue);
        assertTrue(standardCommandRevenue.equals(commandWithSameValuesRevenue));

        // same object -> returns true
        assertTrue(standardCommandExpense.equals(standardCommandExpense));
        assertTrue(standardCommandRevenue.equals(standardCommandRevenue));

        // null -> returns false
        assertFalse(standardCommandExpense.equals(null));
        assertFalse(standardCommandRevenue.equals(null));

        // different types -> returns false
        assertFalse(standardCommandExpense.equals(new ExitCommand()));
        assertFalse(standardCommandRevenue.equals(new ExitCommand()));

        // different index -> returns false
        assertFalse(standardCommandExpense.equals(new EditCommand(INDEX_SECOND_ENTRY,
                DESC_TYPICAL_EXPENSE)));
        assertFalse(standardCommandRevenue.equals(new EditCommand(INDEX_SECOND_ENTRY,
                DESC_TYPICAL_REVENUE)));

        // different descriptor -> returns false
        assertFalse(standardCommandExpense.equals(new EditCommand(INDEX_FIRST_ENTRY,
                DESC_TYPICAL_REVENUE)));
    }

}
