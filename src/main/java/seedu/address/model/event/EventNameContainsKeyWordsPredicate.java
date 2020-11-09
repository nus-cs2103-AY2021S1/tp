package seedu.address.model.event;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class EventNameContainsKeyWordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public EventNameContainsKeyWordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().getName(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof EventNameContainsKeyWordsPredicate) {
            return keywords.equals(((EventNameContainsKeyWordsPredicate) other).keywords);
        } else {
            return false;
        }
    }

}
