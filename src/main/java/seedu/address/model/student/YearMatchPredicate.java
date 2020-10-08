package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Student}'s {@code Year} matches the given year.
 */
public class YearMatchPredicate implements Predicate<Student> {

    // Attributes
    public final List<String> keywords;

    // Constructor
    public YearMatchPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Student student) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(student.getYear().year, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof YearMatchPredicate // instanceof handles nulls
                && this.keywords.equals(((YearMatchPredicate) other).keywords)); // state check
    }


}
