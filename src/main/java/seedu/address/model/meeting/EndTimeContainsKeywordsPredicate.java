package seedu.address.model.meeting;

import java.util.function.Predicate;

/**
 * Tests that a {@code Meeting}'s {@code EndTime} matches any of the keywords given.
 */
public class EndTimeContainsKeywordsPredicate implements Predicate<Meeting> {
    private final EndTime endTime;

    public EndTimeContainsKeywordsPredicate(EndTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean test(Meeting meeting) {
        return meeting.getEndTime().equals(endTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndTimeContainsKeywordsPredicate // instanceof handles nulls
                && endTime.equals(((EndTimeContainsKeywordsPredicate) other).endTime)); // state check
    }

}
