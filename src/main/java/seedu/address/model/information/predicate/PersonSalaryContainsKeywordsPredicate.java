package seedu.address.model.information.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.information.Person;

/**
 * Tests that a {@code Person}'s {@code Salary} matches all of the keywords given.
 */
public class PersonSalaryContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonSalaryContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        assert keywords.stream().allMatch(keyword -> Double.parseDouble(keyword) >= 0) : "invalid salary";
        return keywords.stream()
                // Salary value matches keyword
                .allMatch(keyword -> person.getSalaryOptional()
                        .filter(salary -> salary.salary == Double.parseDouble(keyword))
                        .isPresent());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonSalaryContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonSalaryContainsKeywordsPredicate) other).keywords)); // state check
    }

}
