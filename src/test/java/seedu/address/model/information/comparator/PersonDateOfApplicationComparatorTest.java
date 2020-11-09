package seedu.address.model.information.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.information.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonDateOfApplicationComparatorTest {

    private final PersonDateOfApplicationComparator dateOfApplicationComparator =
            new PersonDateOfApplicationComparator();

    @Test
    public void equals() {
        PersonDateOfApplicationComparator firstComparator = new PersonDateOfApplicationComparator();
        PersonDateOfApplicationComparator secondComparator = new PersonDateOfApplicationComparator();

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
        int result = dateOfApplicationComparator.compare(firstPerson, secondPerson);
        assertEquals(result, 0);
    }

    @Test
    public void testGreaterThan() {
        Person firstPerson = new PersonBuilder().withDateOfApplication("25-11-20").build();
        Person secondPerson = new PersonBuilder().withDateOfApplication("24-11-20").build();
        int result = dateOfApplicationComparator.compare(firstPerson, secondPerson);
        assertEquals(result, 1);
    }

    @Test
    public void testLessThan() {
        Person firstPerson = new PersonBuilder().withDateOfApplication("24-11-20").build();
        Person secondPerson = new PersonBuilder().withDateOfApplication("25-11-20").build();
        int result = dateOfApplicationComparator.compare(firstPerson, secondPerson);
        assertEquals(result, -1);
    }
}
