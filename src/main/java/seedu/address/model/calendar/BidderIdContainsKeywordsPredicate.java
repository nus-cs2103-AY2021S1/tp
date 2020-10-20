package seedu.address.model.calendar;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Meeting}'s {@code BidderId} matches any of the keywords given.
 */
public class BidderIdContainsKeywordsPredicate implements Predicate<CalendarMeeting> {
    private final List<String> keywords;

    public BidderIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(CalendarMeeting calendarMeeting) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(calendarMeeting.getCalendarBidderId().bidderId, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BidderIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((BidderIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
