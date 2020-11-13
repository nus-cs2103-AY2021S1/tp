package seedu.address.model.person.util;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;

import seedu.address.model.person.person.Name;
import seedu.address.model.person.person.Person;

/**
 * Represents a Comparator for Person which sorts by Name.
 */
public class PersonComparator implements Comparator<Person> {
    /**
     * Compares Persons by Name in alphabetical order.
     */
    public int compare(Person a, Person b) {
        requireAllNonNull(a, b);
        Name aName = a.getName();
        Name bName = b.getName();

        return aName.compareTo(bName);
    }
}
