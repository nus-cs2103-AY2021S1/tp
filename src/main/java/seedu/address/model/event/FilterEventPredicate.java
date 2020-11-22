package seedu.address.model.event;

import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class FilterEventPredicate implements Predicate<Person> {
    private final Set<Person> attendeeList;

    public FilterEventPredicate(Set<Person> attendeeList) {
        this.attendeeList = attendeeList;
    }

    @Override
    public boolean test(Person person) {
        return attendeeList.contains(person);
    }
}
