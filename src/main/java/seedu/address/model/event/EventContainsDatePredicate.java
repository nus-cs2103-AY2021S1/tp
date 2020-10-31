package seedu.address.model.event;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

public class EventContainsDatePredicate implements Predicate<Event> {
    private final List<String> dates;

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
        return other == this // short circuit if same object
                || (other instanceof EventContainsDatePredicate // instanceof handles nulls
                && dates.equals(((EventContainsDatePredicate) other).dates)); // state check
    }
}
