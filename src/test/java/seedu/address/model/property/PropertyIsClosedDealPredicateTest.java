package seedu.address.model.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.property.PropertyBuilder;

public class PropertyIsClosedDealPredicateTest {

    @Test
    public void equals() {

        IsClosedDeal active = new IsClosedDeal("active");
        IsClosedDeal close = new IsClosedDeal("close");

        PropertyIsClosedDealPredicate firstPredicate =
                new PropertyIsClosedDealPredicate(active);
        PropertyIsClosedDealPredicate secondPredicate =
                new PropertyIsClosedDealPredicate(close);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PropertyIsClosedDealPredicate firstPredicateCopy =
                new PropertyIsClosedDealPredicate(active);
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

        IsClosedDeal active = new IsClosedDeal("active");
        IsClosedDeal close = new IsClosedDeal("close");

        PropertyIsClosedDealPredicate predicate =
                new PropertyIsClosedDealPredicate(active);
        assertTrue(predicate.test(new PropertyBuilder().withIsClosedDeal("active").build()));

         predicate =
                new PropertyIsClosedDealPredicate(close);
        assertTrue(predicate.test(new PropertyBuilder().withIsClosedDeal("close").build()));
    }

    @Test
    public void test_isClosedDealDoesNotContainKeywords_returnsFalse() {
        IsClosedDeal active = new IsClosedDeal("active");
        IsClosedDeal close = new IsClosedDeal("close");

        PropertyIsClosedDealPredicate predicate =
                new PropertyIsClosedDealPredicate(close);
        assertFalse(predicate.test(new PropertyBuilder().withIsClosedDeal("active").build()));

        predicate =
                new PropertyIsClosedDealPredicate(active);
        assertFalse(predicate.test(new PropertyBuilder().withIsClosedDeal("close").build()));
    }
}
