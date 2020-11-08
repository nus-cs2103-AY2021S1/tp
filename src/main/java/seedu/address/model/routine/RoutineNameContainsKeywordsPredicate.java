package seedu.address.model.routine;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class RoutineNameContainsKeywordsPredicate implements Predicate<Routine> {
    private final List<String> keywords;

    public RoutineNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Routine routine) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsCharIgnoreCase(routine.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoutineNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((RoutineNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
