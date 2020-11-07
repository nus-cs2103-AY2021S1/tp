package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.contact.FindContactCriteriaBuilder;


public class FindContactCriteriaTest {

    @Test
    public void addPredicate_nullPredicate_throwsNullPointerException() {
        FindContactCriteria findContactCriteria = new FindContactCriteria();
        assertThrows(NullPointerException.class, () -> findContactCriteria.addPredicate(null));
    }

    @Test
    public void getFindContactPredicate_singlePredicate_success() {
        ContactNameContainsKeywordsPredicate predicate =
                new ContactNameContainsKeywordsPredicate(Arrays.asList("first"));
        FindContactCriteria findContactCriteria = new FindContactCriteriaBuilder()
                .withNamePredicate(predicate).build();
        assertTrue(findContactCriteria.getFindContactPredicate().equals(predicate));
    }

    @Test
    public void equals() {

        ContactNameContainsKeywordsPredicate predicate =
                new ContactNameContainsKeywordsPredicate(Arrays.asList("first"));

        FindContactCriteria firstFindContactCriteria = new FindContactCriteriaBuilder()
                .withNamePredicate(predicate).build();

        FindContactCriteria secondFindContactCriteria = new FindContactCriteria();

        // same object -> returns true
        assertTrue(firstFindContactCriteria.equals(firstFindContactCriteria));

        // same value -> returns true
        FindContactCriteria firstFindContactCriteriaCopy = new FindContactCriteriaBuilder()
                .withNamePredicate(predicate).build();
        assertTrue(firstFindContactCriteria.equals(firstFindContactCriteriaCopy));

        // null -> returns false
        assertFalse(firstFindContactCriteria.equals(null));

        // different type -> returns false
        assertFalse(firstFindContactCriteria.equals(10));

        // different value -> returns false
        assertFalse(firstFindContactCriteria.equals(secondFindContactCriteria));

    }
}
