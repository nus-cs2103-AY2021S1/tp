package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.getTypicalCommonCents;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditEntryDescriptor;
import seedu.address.model.CommonCents;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.account.Account;
import seedu.address.model.account.entry.Expense;
import seedu.address.testutil.EditEntryDescriptorBuilder;
import seedu.address.testutil.ExpenseBuilder;

public class EditCommandTest {
    private final Model model = new ModelManager(getTypicalCommonCents(), new UserPrefs());
    private final Account account = model.getFilteredAccountList().get(0);
    private final ExpenseBuilder expenseBuilder = new ExpenseBuilder();

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Expense editedExpense = expenseBuilder.build();
        EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(editedExpense).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new CommonCents(model.getCommonCents()), new UserPrefs());
        Account expectedAccount = expectedModel.getFilteredAccountList().get(0);
        expectedAccount.setExpense(account.getExpenseList().get(0), editedExpense);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

    }
}
