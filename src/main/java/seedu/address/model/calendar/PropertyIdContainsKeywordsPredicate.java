package seedu.address.model.calendar;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Meeting}'s {@code PropertyId} matches any of the keywords given.
 */
public class PropertyIdContainsKeywordsPredicate implements Predicate<CalendarMeeting> {
    private final List<String> keywords;

    public PropertyIdContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(CalendarMeeting calendarMeeting) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(calendarMeeting.getCalendarPropertyId().propertyId, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyIdContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PropertyIdContainsKeywordsPredicate) other).keywords)); // state check
    }

}
