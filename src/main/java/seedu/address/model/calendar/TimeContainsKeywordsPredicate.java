package seedu.address.model.calendar;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Meeting}'s {@code Time} matches any of the keywords given.
 */
public class TimeContainsKeywordsPredicate implements Predicate<CalendarMeeting> {
    private final List<String> keywords;

    public TimeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(CalendarMeeting calendarMeeting) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(calendarMeeting.getCalendarTime().time, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TimeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
