package seedu.address.model.lesson;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Lesson}'s {@code Name} matches any of the keywords given.
 */
public class LessonNameContainsKeywordsPredicate implements Predicate<Lesson> {
    private final List<String> keywords;

    /**
     * Constructor method that checks if the keywords are found in a lesson.
     */
    public LessonNameContainsKeywordsPredicate(List<String> keywords) {
        assert(keywords != null);
        this.keywords = keywords;
    }

    @Override
    public boolean test(Lesson lesson) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsCharIgnoreCase(lesson.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LessonNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((LessonNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
