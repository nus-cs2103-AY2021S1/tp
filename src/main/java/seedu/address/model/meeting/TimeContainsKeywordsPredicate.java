package seedu.address.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Meeting}'s {@code Time} matches any of the keywords given.
 */
public class TimeContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public TimeContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword ->
                        StringUtil.containsWordIgnoreCase(meeting.getTime().time, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TimeContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TimeContainsKeywordsPredicate) other).keywords)); // state check
    }

}
