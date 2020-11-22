package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showExpenseAtIndex;
import static seedu.address.testutil.TypicalExpenses.getTypicalExpenseBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.address.model.ExpenseBook;
import seedu.address.model.ExpenseModelManager;
import seedu.address.model.Model;
import seedu.address.model.UserPrefs;
import seedu.address.model.expense.Expense;
import seedu.address.testutil.EditExpenseDescriptorBuilder;
import seedu.address.testutil.ExpenseBuilder;


public class EditCommandTest {
    private Model model = new ExpenseModelManager(getTypicalExpenseBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Expense editedExpense = new ExpenseBuilder().build();
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder(editedExpense).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXPENSE, descriptor);

        String expectedMessage = String.format(EditCommand.SUCCESSFUL_MESSAGE, editedExpense);

        Model expectedModel = new ExpenseModelManager(new ExpenseBook(model.getExpenseBook()), new UserPrefs());
        expectedModel.setExpense(model.getFilteredExpenseList().get(0), editedExpense);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastExpense = Index.fromOneBased(model.getFilteredExpenseList().size());
        Expense lastExpense = model.getFilteredExpenseList().get(indexLastExpense.getZeroBased());

        ExpenseBuilder expenseInList = new ExpenseBuilder(lastExpense);
        Expense editedExpense = expenseInList.withAmount(VALID_AMOUNT_BOOKS).withCategory(VALID_CATEGORY_BOOKS).build();

        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_BOOKS)
                .withCategory(VALID_CATEGORY_BOOKS).build();
        EditCommand editCommand = new EditCommand(indexLastExpense, descriptor);

        String expectedMessage = String.format(EditCommand.SUCCESSFUL_MESSAGE, editedExpense);

        Model expectedModel = new ExpenseModelManager(new ExpenseBook(model.getExpenseBook()), new UserPrefs());
        expectedModel.setExpense(lastExpense, editedExpense);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXPENSE, new EditExpenseDescriptor());
        Expense editedExpense = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());

        String expectedMessage = String.format(EditCommand.SUCCESSFUL_MESSAGE, editedExpense);

        Model expectedModel = new ExpenseModelManager(new ExpenseBook(model.getExpenseBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        Expense personInFilteredList = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        Expense editedPerson = new ExpenseBuilder(personInFilteredList).withAmount(VALID_AMOUNT_BOOKS).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXPENSE,
                new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_BOOKS).build());

        String expectedMessage = String.format(EditCommand.SUCCESSFUL_MESSAGE, editedPerson);

        Model expectedModel = new ExpenseModelManager(new ExpenseBook(model.getExpenseBook()), new UserPrefs());
        expectedModel.setExpense(model.getFilteredExpenseList().get(0), editedPerson);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Expense firstPerson = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder(firstPerson).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_EXPENSE, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EXPENSE);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        // edit Expense filtered list into a duplicate in Expense book
        Expense personInList = model.getExpenseBook().getExpenseList().get(INDEX_SECOND_EXPENSE.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_EXPENSE,
                new EditExpenseDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_EXPENSE);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenseList().size() + 1);
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_BOOKS).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);
        Index outOfBoundIndex = INDEX_SECOND_EXPENSE;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpenseBook().getExpenseList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditExpenseDescriptorBuilder().withAmount(VALID_AMOUNT_BOOKS).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }


}
