package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Meeting}'s {@code Venue} matches any of the keywords given.
 */
public class VenueContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public VenueContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(meeting.getVenue().venue, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VenueContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((VenueContainsKeywordsPredicate) other).keywords)); // state check
    }

}
