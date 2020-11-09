package seedu.address.model.person;

import java.util.Comparator;

/**
 * Compares any two {@code Person's} {@code PersonName} to sort in alphabetical order.
 */
public class PersonNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().fullName.compareToIgnoreCase(p2.getName().fullName);
    }
}
