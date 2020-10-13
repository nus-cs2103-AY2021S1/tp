package seedu.address.model.calendar;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Meeting}'s {@code Venue} matches any of the keywords given.
 */
public class VenueContainsKeywordsPredicate implements Predicate<CalendarMeeting> {
    private final List<String> keywords;

    public VenueContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(CalendarMeeting calendarMeeting) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(calendarMeeting.getCalendarVenue().venue, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VenueContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((VenueContainsKeywordsPredicate) other).keywords)); // state check
    }

}
