package seedu.expense.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_TRANSPORT;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalExpenses.FEL_BDAY;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.budget.UniqueCategoryBudgetList;
import seedu.expense.model.expense.Amount;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.exceptions.DuplicateExpenseException;
import seedu.expense.model.tag.Tag;
import seedu.expense.testutil.ExpenseBuilder;

public class ExpenseBookTest {

    private final ExpenseBook expenseBook = new ExpenseBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), expenseBook.getExpenseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyExpenseBook_replacesData() {
        ExpenseBook newData = getTypicalExpenseBook();
        expenseBook.resetData(newData);
        assertEquals(newData, expenseBook);
    }

    @Test
    public void resetData_withDuplicateExpenses_throwsDuplicateExpenseException() {
        // Two expenses with the same identity fields
        Expense editedAlice = new ExpenseBuilder(FEL_BDAY).withTag(VALID_TAG_TRANSPORT)
                .build();
        List<Expense> newExpenses = Arrays.asList(FEL_BDAY, editedAlice);
        ExpenseBookStub newData = new ExpenseBookStub(newExpenses);

        assertThrows(DuplicateExpenseException.class, () -> expenseBook.resetData(newData));
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> expenseBook.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInExpenseBook_returnsFalse() {
        assertFalse(expenseBook.hasExpense(FEL_BDAY));
    }

    @Test
    public void hasExpense_expenseInExpenseBook_returnsTrue() {
        expenseBook.addCategory(FEL_BDAY.getTag());
        expenseBook.addExpense(FEL_BDAY);
        assertTrue(expenseBook.hasExpense(FEL_BDAY));
    }

    @Test
    public void hasExpense_expenseWithSameIdentityFieldsInExpenseBook_returnsTrue() {
        expenseBook.addCategory(FEL_BDAY.getTag());
        expenseBook.addExpense(FEL_BDAY);
        expenseBook.addCategory(new Tag(VALID_TAG_TRANSPORT));
        Expense editedAlice = new ExpenseBuilder(FEL_BDAY).withTag(VALID_TAG_TRANSPORT)
                .build();
        assertTrue(expenseBook.hasExpense(editedAlice));
    }

    @Test
    public void getExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> expenseBook.getExpenseList().remove(0));
    }

    @Test
    public void getBudgetList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> expenseBook.getBudgetList().remove(0));
    }

    /**
     * A stub ReadOnlyExpenseBook whose expenses list can violate interface constraints.
     */
    private static class ExpenseBookStub implements ReadOnlyExpenseBook, Statistics {

        private final ObservableList<Tag> categories = FXCollections.observableArrayList();
        private final ObservableList<Expense> expenses = FXCollections.observableArrayList();
        private final UniqueCategoryBudgetList budgets = new UniqueCategoryBudgetList();

        ExpenseBookStub(Collection<Expense> expenses) {
            this.expenses.setAll(expenses);
        }

        @Override
        public ObservableList<Tag> getTags() {
            return categories;
        }

        @Override
        public ObservableList<Expense> getExpenseList() {
            return expenses;
        }

        @Override
        public ObservableList<CategoryBudget> getBudgetList() {
            return budgets.asUnmodifiableObservableList();
        }

        @Override
        public UniqueCategoryBudgetList getBudgets() {
            return budgets;
        }

        @Override
        public Amount tallyExpenses() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Amount tallyBudgets() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Amount tallyBalance() {
            throw new AssertionError("This method should not be called.");
        }
    }

}
