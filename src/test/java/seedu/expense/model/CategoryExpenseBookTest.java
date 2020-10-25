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
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.exceptions.DuplicateExpenseException;
import seedu.expense.testutil.ExpenseBuilder;

class CategoryExpenseBookTest {

    private final ExpenseBook expenseBook = new ExpenseBook();
    private final CategoryExpenseBook categoryExpenseBook = new CategoryExpenseBook(expenseBook);

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), categoryExpenseBook.getExpenseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> categoryExpenseBook.resetData(null));
    }

    @Test
    public void resetData_withValidExpenseBook_replacesData() {
        ExpenseBook newData = getTypicalExpenseBook();
        categoryExpenseBook.resetData(newData);
        assertEquals(newData, categoryExpenseBook);
    }

    @Test
    public void resetData_withDuplicateExpenses_throwsDuplicateExpenseException() {
        // Two expenses with the same identity fields
        Expense edited = new ExpenseBuilder(FEL_BDAY).withTags(VALID_TAG_TRANSPORT)
            .build();
        List<Expense> newExpenses = Arrays.asList(FEL_BDAY, edited);
        CategoryExpenseBookTest.CategoryExpenseBookStub newData =
            new CategoryExpenseBookTest.CategoryExpenseBookStub(newExpenses);

        assertThrows(DuplicateExpenseException.class, () -> categoryExpenseBook.resetData(newData));
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> categoryExpenseBook.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInCategoryExpenseBook_returnsFalse() {
        assertFalse(categoryExpenseBook.hasExpense(FEL_BDAY));
    }

    @Test
    public void hasExpense_expenseInCategoryExpenseBook_returnsTrue() {
        categoryExpenseBook.addExpense(FEL_BDAY);
        assertTrue(categoryExpenseBook.hasExpense(FEL_BDAY));
    }

    @Test
    public void hasExpense_expenseWithSameIdentityFieldsInCategoryExpenseBook_returnsTrue() {
        categoryExpenseBook.addExpense(FEL_BDAY);
        Expense edited = new ExpenseBuilder(FEL_BDAY).withTags(VALID_TAG_TRANSPORT)
            .build();
        assertTrue(categoryExpenseBook.hasExpense(edited));
    }

    @Test
    public void getExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> categoryExpenseBook.getExpenseList().remove(0));
    }

    @Test
    public void getBudgetList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> categoryExpenseBook.getBudgetList().remove(0));
    }

    @Test
    public void getFilteredExpenses_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> categoryExpenseBook.getFilteredExpenses().remove(0));
    }

    @Test
    public void getFilteredBudgets_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> categoryExpenseBook.getFilteredBudgets().remove(0));
    }

    /**
     * A stub ReadOnlyExpenseBook whose expenses list can violate interface constraints.
     */
    private static class CategoryExpenseBookStub extends ExpenseBook {

        private final ObservableList<Expense> expenses = FXCollections.observableArrayList();
        private final UniqueCategoryBudgetList budgets = new UniqueCategoryBudgetList();

        CategoryExpenseBookStub(Collection<Expense> expenses) {
            this.expenses.setAll(expenses);
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
        public double tallyExpenses() {
            return -1; // should not be called
        }

        @Override
        public double tallyBudgets() {
            return -1; // should not be called
        }

        @Override
        public double tallyBalance() {
            return -1; // should not be called
        }
    }
}
