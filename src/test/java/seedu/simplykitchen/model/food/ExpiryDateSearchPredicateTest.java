package seedu.simplykitchen.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.testutil.FoodBuilder;

public class ExpiryDateSearchPredicateTest {

    @Test
    public void equals() {
        String firstExpiryDatePredicate = "31-01-2022";
        String secondExpiryDatePredicate = "31-12-2021";

        ExpiryDateSearchPredicate firstPredicate =
                new ExpiryDateSearchPredicate(firstExpiryDatePredicate);
        new ExpiryDateSearchPredicate(secondExpiryDatePredicate);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ExpiryDateSearchPredicate firstPredicateCopy =
                new ExpiryDateSearchPredicate(firstExpiryDatePredicate);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different food -> returns false
        assertFalse(firstPredicate.equals(secondExpiryDatePredicate));
    }

    @Test
    public void test_expiryDateSearch_returnsTrue() {
        // Search for specific expiry date
        ExpiryDateSearchPredicate predicate =
                new ExpiryDateSearchPredicate("31-12-2021");
        assertTrue(predicate.test(new FoodBuilder().withExpiryDate("31-12-2021").build()));
    }

    @Test
    public void test_noFoodItemWithMatchingExpiryDate_returnsFalse() {
        // Non-matching expiry date
        ExpiryDateSearchPredicate predicate = predicate = new ExpiryDateSearchPredicate("31-12-2021");
        assertFalse(predicate.test(new FoodBuilder().withExpiryDate("31-01-2022").build()));

        // Expiry date search query matches tag, but does not match expiry date
        predicate = new ExpiryDateSearchPredicate("31-12-2021");
        assertFalse(predicate.test(new FoodBuilder().withTags("31-12-2021").build()));
    }
}

