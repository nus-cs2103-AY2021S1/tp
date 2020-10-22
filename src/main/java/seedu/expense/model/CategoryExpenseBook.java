package seedu.expense.model;

import javafx.collections.transformation.FilteredList;
import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.expense.Expense;

import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;

public class CategoryExpenseBook extends ExpenseBook {
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<CategoryBudget> filteredBudgets;

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

    public void updateFilteredExpenses(Predicate<Expense> predicate) {
        requireNonNull(predicate);
        filteredExpenses.setPredicate(predicate);
    }

    public void updateFilteredBudgets(Predicate<CategoryBudget> predicate) {
        requireNonNull(predicate);
        filteredBudgets.setPredicate(predicate);
    }
}
