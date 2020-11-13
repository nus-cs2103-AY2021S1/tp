package seedu.internhunter.model.profile;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class ProfileItemContainsKeywordPredicateTest {

    private final ProfileItemContainsKeywordPredicate profileItemContainsKeywordPredicate =
        new ProfileItemContainsKeywordPredicate(List.of("React"));

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(profileItemContainsKeywordPredicate.equals(profileItemContainsKeywordPredicate));

        // same value -> return true
        assertTrue(profileItemContainsKeywordPredicate.equals(
            new ProfileItemContainsKeywordPredicate(List.of("React"))));

        // null -> return false
        assertFalse(profileItemContainsKeywordPredicate.equals(null));

        // different types -> return false
        assertFalse(profileItemContainsKeywordPredicate.equals(0.5f));

        // different values -> return false
        assertFalse(profileItemContainsKeywordPredicate.equals(
            new ProfileItemContainsKeywordPredicate(List.of("Java"))));
    }
}
