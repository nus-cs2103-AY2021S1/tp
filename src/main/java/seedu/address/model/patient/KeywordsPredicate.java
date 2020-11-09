package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class KeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public KeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(patient.getNric().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof KeywordsPredicate // instanceof handles nulls
                && keywords.equals(((KeywordsPredicate) other).keywords)); // state check
    }

}
