package seedu.homerce.model.appointment.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.homerce.commons.util.StringUtil;
import seedu.homerce.model.appointment.Appointment;

public class AppointmentNamePredicate implements Predicate<Appointment> {
    private final List<String> keywords;

    public AppointmentNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return keywords.stream().anyMatch(keyword ->
            StringUtil.containsWordIgnoreCase(appointment.getClient().getName().fullName, keyword)
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentNamePredicate// instanceof handles nulls
            && keywords.equals(((AppointmentNamePredicate) other).keywords)); // state check
    }
}
