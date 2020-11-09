package seedu.address.model.filters.appointmentfilters;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.appointment.Appointment;

/**
 * Tests that a {@code Appointment}'s {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class SearchPatientFilter implements Predicate<Appointment> {
    private final List<String> keywords;

    public SearchPatientFilter(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                        .containsWordIgnoreCase(appointment.getPatientString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.filters.appointmentfilters
                                        .SearchPatientFilter // instanceof handles nulls
                && keywords.equals(((seedu.address.model
                                            .filters.appointmentfilters
                                            .SearchPatientFilter) other).keywords)); // state check
    }
}
