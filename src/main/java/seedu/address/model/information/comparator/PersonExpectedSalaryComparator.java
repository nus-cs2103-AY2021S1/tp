package seedu.address.model.information.comparator;

import java.util.Comparator;
import java.util.Optional;

import seedu.address.model.information.Person;
import seedu.address.model.information.Salary;

/**
 * Compares any two {@code Person's} {@code Salary} using the supplied sorting criteria
 */
public class PersonExpectedSalaryComparator extends PersonComparator implements Comparator<Person> {

    public static final String SORT_CRITERIA = "sal";

    @Override
    public int compare(Person person1, Person person2) {
        Optional<Salary> person1Salary = person1.getSalaryOptional();
        Optional<Salary> person2Salary = person2.getSalaryOptional();
        if (person1Salary.isPresent() && person2Salary.isPresent()) {
            return Double.compare(person1Salary.get().salary, person2Salary.get().salary);
        } else if (person1Salary.isPresent()) {
            return 1;
        } else if (person2Salary.isPresent()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "by expected salary ";
    }

    @Override
    public boolean equals(Object other) {
        return this == other || other instanceof PersonExpectedSalaryComparator;
    }
}
