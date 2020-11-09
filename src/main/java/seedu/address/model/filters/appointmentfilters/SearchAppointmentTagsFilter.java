package seedu.address.model.filters.appointmentfilters;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Appointment}'s {@code Patient}'s {@code Name} matches any of the keywords given.
 */
public class SearchAppointmentTagsFilter implements Predicate<Appointment> {
    private final List<String> tags;

    public SearchAppointmentTagsFilter(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean test(Appointment appointment) {
        return tags.stream()
                .anyMatch(tag -> {
                    Tag currentTag = new Tag(tag);
                    return appointment.getTags().contains(currentTag);
                });
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SearchAppointmentTagsFilter // instanceof handles nulls
                && tags.equals(((SearchAppointmentTagsFilter) other).tags)); // state check
    }
}
