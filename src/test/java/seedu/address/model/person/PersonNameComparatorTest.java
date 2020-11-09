package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class PersonNameComparatorTest {

    @Test
    public void equals() {
        PersonNameComparator firstComparator = new PersonNameComparator();
        PersonNameComparator secondComparator = new PersonNameComparator();

        // same comparator -> returns true
        assertTrue(firstComparator.equals(firstComparator));

        // different data types -> returns false
        assertFalse(firstComparator.equals(1));

        // null -> returns false
        assertFalse(firstComparator.equals(null));

        // different comparators -> returns false
        assertFalse(firstComparator.equals(secondComparator));
    }

    @Test
    public void test_personNameEqual_returns0() {
        PersonNameComparator comparator = new PersonNameComparator();
        Person p1 = new PersonBuilder().build();
        Person p2 = new PersonBuilder().build();
        assertEquals(comparator.compare(p1, p2), 0);
    }

    @Test
    public void test_personNameGreaterThan_returnsNegativeValues() {
        PersonNameComparator comparator = new PersonNameComparator();
        Person p1 = new PersonBuilder().withName("Alice").build();
        Person p2 = new PersonBuilder().withName("Carl").build();
        assertTrue(comparator.compare(p1, p2) < 0);
    }

    @Test
    public void test_personNameSmallerThan_returnsPositiveValues() {
        PersonNameComparator comparator = new PersonNameComparator();
        Person p1 = new PersonBuilder().withName("Diana").build();
        Person p2 = new PersonBuilder().withName("Bob").build();
        assertTrue(comparator.compare(p1, p2) > 0);
    }
}
