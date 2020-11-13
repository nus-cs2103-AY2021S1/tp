package seedu.internhunter.model.company;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class CompanyNameContainsKeyWordsPredicateTest {

    private final CompanyNameContainsKeyWordsPredicate companyNameContainsKeyWordsPredicate =
        new CompanyNameContainsKeyWordsPredicate(List.of("Google"));

    @Test
    public void equals() {
        // same object -> return true
        assertTrue(companyNameContainsKeyWordsPredicate.equals(companyNameContainsKeyWordsPredicate));

        // same value -> return true
        assertTrue(companyNameContainsKeyWordsPredicate.equals(
            new CompanyNameContainsKeyWordsPredicate(List.of("Google"))));

        // null -> return false
        assertFalse(companyNameContainsKeyWordsPredicate.equals(null));

        // different types -> return false
        assertFalse(companyNameContainsKeyWordsPredicate.equals(0.5f));

        // different values -> return false
        assertFalse(companyNameContainsKeyWordsPredicate.equals(
            new CompanyNameContainsKeyWordsPredicate(List.of("Facebook"))));
    }
}
