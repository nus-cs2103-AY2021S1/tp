package seedu.address.model.event;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class EventContainsDatePredicate implements Predicate<Event> {
    private final List<String> dates;

    /**
     * Represents the Predicate for dates.
     * @param dates date to search for.
     */
    public EventContainsDatePredicate(List<String> dates) {
        this.dates = dates;
    }

    @Override
    public boolean test(Event event) {
        return dates.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getTime().getStart().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof EventContainsDatePredicate) {
            return this.dates.equals(((EventContainsDatePredicate) other).dates);
        } else {
            return false;
        }
    }
}
