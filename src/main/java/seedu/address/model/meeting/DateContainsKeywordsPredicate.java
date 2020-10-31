package seedu.address.model.meeting;

import java.util.function.Predicate;

/**
 * Tests that a {@code Meeting}'s {@code Date} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Meeting> {
    private final Date date;

    public DateContainsKeywordsPredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Meeting meeting) {
        return meeting.getDate().equals(date);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && date.equals(((DateContainsKeywordsPredicate) other).date)); // state check
    }

}
