package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.property.PropertyBuilder;

public class PropertyIsRentalPredicateTest {

    @Test
    public void equals() {

        IsRental yes = new IsRental("yes");
        IsRental no = new IsRental("no");

        PropertyIsRentalPredicate firstPredicate =
                new PropertyIsRentalPredicate(yes);
        PropertyIsRentalPredicate secondPredicate =
                new PropertyIsRentalPredicate(no);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropertyIsRentalPredicate firstPredicateCopy =
                new PropertyIsRentalPredicate(yes);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_isClosedDealContainsKeywords_returnsTrue() {

        IsRental yes = new IsRental("yes");
        IsRental no = new IsRental("no");

        PropertyIsRentalPredicate predicate =
                new PropertyIsRentalPredicate(yes);
        assertTrue(predicate.test(new PropertyBuilder().withIsRental("yes").build()));

        predicate =
                new PropertyIsRentalPredicate(no);
        assertTrue(predicate.test(new PropertyBuilder().withIsRental("no").build()));
    }

    @Test
    public void test_isClosedDealDoesNotContainKeywords_returnsFalse() {
        IsRental yes = new IsRental("yes");
        IsRental no = new IsRental("no");

        PropertyIsRentalPredicate predicate =
                new PropertyIsRentalPredicate(no);
        assertFalse(predicate.test(new PropertyBuilder().withIsRental("yes").build()));

        predicate =
                new PropertyIsRentalPredicate(yes);
        assertFalse(predicate.test(new PropertyBuilder().withIsRental("no").build()));
    }
}
