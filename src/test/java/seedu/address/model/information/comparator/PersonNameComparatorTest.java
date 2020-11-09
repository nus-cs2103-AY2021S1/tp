package seedu.address.model.information.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonNameComparatorTest {

    private final PersonNameComparator nameComparator = new PersonNameComparator();

    @Test
    public void equals() {
        PersonNameComparator firstComparator = new PersonNameComparator();
        PersonNameComparator secondComparator = new PersonNameComparator();

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
        Person firstPerson = new PersonBuilder().withName("A").build();
        Person secondPerson = new PersonBuilder().withName("a").build();
        int result = nameComparator.compare(firstPerson, secondPerson);
        assertEquals(result, 0);
    }

    @Test
    public void testGreaterThan() {
        Person firstPerson = new PersonBuilder().withName("b").build();
        Person secondPerson = new PersonBuilder().withName("A").build();
        int result = nameComparator.compare(firstPerson, secondPerson);
        assertEquals(result, 1);
    }

    @Test
    public void testLessThan() {
        Person firstPerson = new PersonBuilder().withName("Fa").build();
        Person secondPerson = new PersonBuilder().withName("Fb").build();
        int result = nameComparator.compare(firstPerson, secondPerson);
        assertEquals(result, -1);
    }
}
