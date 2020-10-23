package seedu.expense.model;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.tag.Tag;

/**
 * Wraps all data at the category-expense-book level
 * Duplicates are not allowed (by .isSameExpense comparison)
 * Contains only items with matched category Tag in {@code filteredExpenses}
 * and {@code filteredBudgets}
 */
public class CategoryExpenseBook extends ExpenseBook {
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<CategoryBudget> filteredBudgets;

    /**
     * Creates a CategoryExpenseBook using the Expenses and Budgets in the {@code toBeCopied}
     */
    CategoryExpenseBook(ExpenseBook expenseBook) {
        super(expenseBook);
        filteredExpenses = new FilteredList<>(expenseBook.getExpenseList());
        filteredBudgets = new FilteredList<>(expenseBook.getBudgetList());
    }

    /**
     * Returns the tallied filtered expenses amount
     *
     * @return tallied amount
     */
    @Override
    public double tallyExpenses() {
        return filteredExpenses.stream()
            .map(expense -> expense.getAmount().asDouble())
            .reduce(0.0, (partialSum, amount) -> partialSum + amount);
    }

    /**
     * Returns the tallied filtered budgets amount
     *
     * @return tallied amount
     */
    @Override
    public double tallyBudgets() {
        return filteredBudgets.stream()
            .map(budget -> budget.getAmount().asDouble())
            .reduce(0.0, (partialSum, amount) -> partialSum + amount);
    }

    /**
     * Tallies the balance of filtered budgets and filtered expenses in the expense book.
     *
     * @return tallied balance of the expense book
     */
    @Override
    public double tallyBalance() {
        return tallyBudgets() - tallyExpenses();
    }

    /**
     * Updates the filter of the filtered expense list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredExpenses(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(predicate);
    }

    /**
     * Updates the filter of the filtered budget list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    public void updateFilteredBudgets(Predicate<CategoryBudget> predicate) {
        requireNonNull(predicate);
        filteredBudgets.setPredicate(predicate);
    }

    public ObservableList<Expense> getFilteredExpenses() {
        return this.filteredExpenses;
    }

    public ObservableList<CategoryBudget> getFilteredBudgets() {
        return this.filteredBudgets;
    }

    /**
     * Checks if the given Tag is present in any of the category budget.
     */
    public boolean containsCategory(Tag toCheck) {
        return budgets.contains(new CategoryBudget(toCheck));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CategoryExpenseBook // instanceof handles nulls
            && expenses.equals(((CategoryExpenseBook) other).expenses))
            && budgets.equals(((CategoryExpenseBook) other).budgets)
            && filteredExpenses.equals(((CategoryExpenseBook) other).filteredExpenses)
            && filteredBudgets.equals(((CategoryExpenseBook) other).filteredBudgets);
    }
}
