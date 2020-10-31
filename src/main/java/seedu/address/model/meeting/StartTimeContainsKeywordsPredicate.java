package seedu.address.model.meeting;

import java.util.function.Predicate;

/**
 * Tests that a {@code Meeting}'s {@code StartTime} matches any of the keywords given.
 */
public class StartTimeContainsKeywordsPredicate implements Predicate<Meeting> {
    private final StartTime startTime;

    public StartTimeContainsKeywordsPredicate(StartTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public boolean test(Meeting meeting) {
        return meeting.getStartTime().equals(startTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTimeContainsKeywordsPredicate // instanceof handles nulls
                && startTime.equals(((StartTimeContainsKeywordsPredicate) other).startTime)); // state check
    }

}
