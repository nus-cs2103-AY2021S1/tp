package seedu.address.model.information.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonExpectedSalaryComparatorTest {

    private final PersonExpectedSalaryComparator expectedSalaryComparator = new PersonExpectedSalaryComparator();

    @Test
    public void equals() {
        PersonExpectedSalaryComparator firstComparator = new PersonExpectedSalaryComparator();
        PersonExpectedSalaryComparator secondComparator = new PersonExpectedSalaryComparator();

        // same comparator -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // different comparators -> returns true
        assertTrue(firstComparator.equals(secondComparator));

        // different types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));
    }

    @Test
    public void testEqual() {
        Person firstPerson = new PersonBuilder().build();
        Person secondPerson = new PersonBuilder().build();
        int result = expectedSalaryComparator.compare(firstPerson, secondPerson);
        assertEquals(result, 0);
    }

    @Test
    public void testGreaterThan() {
        Person firstPerson = new PersonBuilder().withSalary("1000").build();
        Person secondPerson = new PersonBuilder().withSalary("500").build();
        int result = expectedSalaryComparator.compare(firstPerson, secondPerson);
        assertEquals(result, 1);
    }

    @Test
    public void testLessThan() {
        Person firstPerson = new PersonBuilder().withSalary("500").build();
        Person secondPerson = new PersonBuilder().withSalary("1000").build();
        int result = expectedSalaryComparator.compare(firstPerson, secondPerson);
        assertEquals(result, -1);
    }
}
