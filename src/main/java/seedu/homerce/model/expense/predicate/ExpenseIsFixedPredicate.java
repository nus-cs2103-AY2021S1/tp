package seedu.homerce.model.expense.predicate;

import java.util.function.Predicate;

import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.expense.IsFixed;

/**
 * Tests that a {@code Expense}'s {@code IsFixed} matches the given isFixed.
 */
public class ExpenseIsFixedPredicate implements Predicate<Expense> {
    private final IsFixed isFixed;

    public ExpenseIsFixedPredicate(IsFixed isFixed) {
        this.isFixed = isFixed;
    }

    @Override
    public boolean test(Expense expense) {
        return isFixed.equals(expense.getIsFixed());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseIsFixedPredicate // instanceof handles nulls
                && isFixed.equals(((ExpenseIsFixedPredicate) other).isFixed)); // state check
    }
}
