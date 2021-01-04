package seedu.homerce.model.revenue.predicate;

import java.util.function.Predicate;

import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.model.util.attributes.Date;

/**
 * Tests that a {@code Revenue}'s {@code Date} matches the given date.
 */
public class RevenueDatePredicate implements Predicate<Revenue> {

    private final Date date;

    public RevenueDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Revenue revenue) {
        return date.equals(revenue.getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RevenueDatePredicate // instanceof handles nulls
            && date.equals(((RevenueDatePredicate) other).date)); // state check
    }
}
