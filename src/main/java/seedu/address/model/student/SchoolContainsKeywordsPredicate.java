package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code School} matches any of the keywords given.
 */
public class SchoolContainsKeywordsPredicate implements Predicate<Student> {
    public final List<String> keywords;

    public SchoolContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        if (this.keywords.size() < 1) {
            return false;
        }
        return keywords.stream()
                .map(String::toLowerCase)
                .allMatch(keyword -> StringUtil.containsIgnoreCase(student.getSchool().school, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SchoolContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((SchoolContainsKeywordsPredicate) other).keywords)); // state check
    }

}
