package seedu.homerce.model.expense.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.homerce.commons.util.StringUtil;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.util.attributes.Description;

/**
 * Tests that a {@code Expense}'s {@code Description} matches the given description.
 */
public class ExpenseDescriptionPredicate implements Predicate<Expense> {
    private final Description description;

    public ExpenseDescriptionPredicate(Description description) {
        this.description = description;
    }

    @Override
    public boolean test(Expense expense) {
        List<String> keywords = Arrays.asList(description.value.toLowerCase().split(" "));
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(expense.getDescription().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseDescriptionPredicate // instanceof handles nulls
                && description.equals(((ExpenseDescriptionPredicate) other).description)); // state check
    }
}
