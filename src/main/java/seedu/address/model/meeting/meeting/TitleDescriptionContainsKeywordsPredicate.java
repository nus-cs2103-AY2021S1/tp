package seedu.address.model.meeting.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TitleDescriptionContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public TitleDescriptionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        meeting.getTitle().value.replaceAll("\\W", " "), keyword)
                        || StringUtil.containsWordIgnoreCase(
                                meeting.getDescription().value.orElse("").replaceAll("\\W", " "), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleDescriptionContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleDescriptionContainsKeywordsPredicate) other).keywords)); // state check
    }
}
