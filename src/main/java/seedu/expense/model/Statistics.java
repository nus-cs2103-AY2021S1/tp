package seedu.expense.model;

import java.util.function.Predicate;

import seedu.expense.model.budget.CategoryBudget;
import seedu.expense.model.expense.Expense;

/**
 * Statistical calculation of an expense book
 */
public interface Statistics {

    /**
     * Returns the tallied total expenses amount
     *
     * @return tallied amount
     */
    double tallyExpenses();

    /**
     * Returns the tallied expenses amount that matches {@code predicate}
     *
     * @return tallied filtered amount
     */
    double tallyExpenses(Predicate<Expense> predicate);

    /**
     * Returns the tallied filtered budgets amount
     *
     * @return tallied amount
     */
    double tallyBudgets();

    /**
     * Returns the tallied budgets amount thats matches {@code predicate}
     *
     * @return tallied filtered amount
     */
    double tallyBudgets(Predicate<CategoryBudget> predicate);

    /**
     * Tallies the balance of filtered budgets and filtered expenses in the expense book.
     *
     * @return tallied balance of the expense book
     */
    double tallyBalance();

    /**
     * Tallies the balance of expenses and budgets in the expense book that matches
     * {@code expensePredicate} and {@code categoryBudgetPredicate} respectively.
     *
     * @return tallied balance of filtered expenses and budgets in the expense book
     */
    double tallyBalance(Predicate<Expense> expensePredicate,
                               Predicate<CategoryBudget> categoryBudgetPredicate);
}
