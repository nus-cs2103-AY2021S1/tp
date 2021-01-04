package seedu.homerce.model.expense;

import java.util.function.Predicate;

import seedu.homerce.model.util.attributes.Tag;

/**
 * Tests that a {@code Expense}'s {@code Tag} matches the given tag.
 */
public class TagPredicate implements Predicate<Expense> {
    private final Tag tag;

    public TagPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Expense expense) {
        return tag.equals(expense.getTag());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagPredicate // instanceof handles nulls
                && tag.equals(((TagPredicate) other).tag)); // state check
    }
}
