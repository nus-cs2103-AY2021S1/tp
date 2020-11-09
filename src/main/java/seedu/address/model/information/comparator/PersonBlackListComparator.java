package seedu.address.model.information.comparator;

import java.util.Comparator;

import seedu.address.model.information.Person;

/**
 * Compares any two {@code Person's} {@code BlackListStatus} using the supplied sorting criteria.
 */
public class PersonBlackListComparator extends PersonComparator implements Comparator<Person> {

    public static final String SORT_CRITERIA = "bl";

    @Override
    public int compare(Person person1, Person person2) {
        Boolean person1BlackListStatus = person1.getBlacklistStatus().isBlacklisted;
        Boolean person2BlackListStatus = person2.getBlacklistStatus().isBlacklisted;
        return Boolean.compare(person1BlackListStatus, person2BlackListStatus);
    }

    @Override
    public String toString() {
        return "by blacklist status ";
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof PersonBlackListComparator;
    }
}
