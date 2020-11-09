package seedu.address.model.filters.patientfilters;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.patient.Patient;

/**
 * Tests that a {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class SearchNameFilter implements Predicate<Patient> {
    private final List<String> keywords;

    public SearchNameFilter(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchNameFilter // instanceof handles nulls
                && keywords.equals(((SearchNameFilter) other).keywords)); // state check
    }
}
