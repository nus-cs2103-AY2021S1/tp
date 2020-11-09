package seedu.address.model.information.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonBlackListComparatorTest {

    private final PersonBlackListComparator blackListComparator = new PersonBlackListComparator();

    @Test
    public void equals() {
        PersonBlackListComparator firstComparator = new PersonBlackListComparator();
        PersonBlackListComparator secondComparator = new PersonBlackListComparator();

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
        int result = blackListComparator.compare(firstPerson, secondPerson);
        assertEquals(result, 0);
    }

    @Test
    public void testGreaterThan() {
        Person firstPerson = new PersonBuilder().withBlacklistStatus("true").build();
        Person secondPerson = new PersonBuilder().withBlacklistStatus("false").build();
        int result = blackListComparator.compare(firstPerson, secondPerson);
        assertEquals(result, 1);
    }

    @Test
    public void testLessThan() {
        Person firstPerson = new PersonBuilder().withBlacklistStatus("false").build();
        Person secondPerson = new PersonBuilder().withBlacklistStatus("true").build();
        int result = blackListComparator.compare(firstPerson, secondPerson);
        assertEquals(result, -1);
    }
}
