package seedu.homerce.model.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;
import static seedu.homerce.testutil.service.TypicalServices.MANICURE;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.service.predicate.ServiceTitlePredicate;
import seedu.homerce.model.util.attributes.Title;

public class ServiceTitlePredicateTest {
    @Test
    public void equals() {
        Title firstTitle = new Title("Lash Lift");
        Title secondTitle = new Title("Manicure");

        ServiceTitlePredicate firstPredicate = new ServiceTitlePredicate(firstTitle);
        ServiceTitlePredicate secondPredicate = new ServiceTitlePredicate(secondTitle);


        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ServiceTitlePredicate firstPredicateCopy = new ServiceTitlePredicate(firstTitle);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different titles -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameDoesNotContainTitle_returnsFalse() {
        ServiceTitlePredicate predicate = new ServiceTitlePredicate(new Title("Lash Lift"));
        assertFalse(predicate.test(MANICURE));

    }

    @Test
    public void test_nameContainsTitle_returnsTrue() {
        ServiceTitlePredicate predicate = new ServiceTitlePredicate(new Title("Lash Lift"));
        assertTrue(predicate.test(LASH_LIFT));

    }
}
