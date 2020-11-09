package seedu.address.model.information.comparator;

import java.util.Comparator;

import seedu.address.model.information.Person;

/**
 * Compares any two {@code Person}'s {@code Experience} using the the supplied sorting criteria.
 */
public class PersonExperienceComparator extends PersonComparator implements Comparator<Person> {

    public static final String SORT_CRITERIA = "exp";

    @Override
    public int compare(Person person1, Person person2) {

        double person1Experience = person1.getExperience().experienceInYears;
        double person2Experience = person2.getExperience().experienceInYears;

        if (person1Experience < person2Experience) {
            return -1;
        } else if (person1Experience > person2Experience) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "by years of experience ";
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof PersonExperienceComparator;
    }
}
