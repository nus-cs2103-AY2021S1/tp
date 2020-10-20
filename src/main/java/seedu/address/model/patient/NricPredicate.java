package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class NricPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public NricPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(patient.getNric().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NricPredicate// instanceof handles nulls
                && keywords.equals(((NricPredicate) other).keywords)); // state check
    }

}
