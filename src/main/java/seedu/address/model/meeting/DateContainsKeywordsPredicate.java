package seedu.address.model.meeting;

import java.util.function.Predicate;

/**
 * Tests that a {@code Meeting}'s {@code MeetingDate} matches any of the keywords given.
 */
public class DateContainsKeywordsPredicate implements Predicate<Meeting> {
    private final MeetingDate meetingDate;

    public DateContainsKeywordsPredicate(MeetingDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    @Override
    public boolean test(Meeting meeting) {
        return meeting.getMeetingDate().equals(meetingDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateContainsKeywordsPredicate // instanceof handles nulls
                && meetingDate.equals(((DateContainsKeywordsPredicate) other).meetingDate)); // state check
    }

}
