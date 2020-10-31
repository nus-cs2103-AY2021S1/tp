package seedu.address.model.event;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.NameContainsKeywordsPredicate;

import java.util.List;
import java.util.function.Predicate;

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
        return other == this // short circuit if same object
                || (other instanceof EventNameContainsKeyWordsPredicate // instanceof handles nulls
                && keywords.equals(((EventNameContainsKeyWordsPredicate) other).keywords)); // state check
    }

}
