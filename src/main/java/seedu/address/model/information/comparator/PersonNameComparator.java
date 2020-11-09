package seedu.address.model.information.comparator;

import java.util.Comparator;

import seedu.address.model.information.Person;

/**
 * Compares any two {@code Person}'s {@code Name} using the the supplied sorting criteria.
 */
public class PersonNameComparator extends PersonComparator implements Comparator<Person> {

    public static final String SORT_CRITERIA = "n";

    @Override
    public int compare(Person person1, Person person2) {

        String name1 = person1.getName().fullName.toLowerCase();
        String name2 = person2.getName().fullName.toLowerCase();

        return name1.compareTo(name2);
    }

    @Override
    public String toString() {
        return "by name ";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PersonNameComparator); // instanceof handles nulls
    }
}
