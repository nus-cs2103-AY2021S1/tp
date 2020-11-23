package seedu.stock.model.stock.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.testutil.StockBuilder;

public class NameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstNamePredicateKeywordList = Collections.singletonList("first");
        List<String> secondNamePredicateKeywordList = Arrays.asList("first", "second");
        List<String> thirdNamePredicateKeywordList = Arrays.asList("first", "first");

        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(firstNamePredicateKeywordList);
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(secondNamePredicateKeywordList);
        NameContainsKeywordsPredicate thirdNamePredicate =
                new NameContainsKeywordsPredicate(thirdNamePredicateKeywordList);

        // same object -> returns true
        assertTrue(firstNamePredicate.equals(firstNamePredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy =
                new NameContainsKeywordsPredicate(firstNamePredicateKeywordList);
        assertTrue(firstNamePredicate.equals(firstPredicateCopy));

        NameContainsKeywordsPredicate secondPredicateCopy =
                new NameContainsKeywordsPredicate(Arrays.asList("first", "second"));
        assertTrue(secondNamePredicate.equals(secondPredicateCopy));

        // one same value, other copy of value -> returns false
        assertFalse(firstNamePredicate.equals(thirdNamePredicate));

        // different types -> returns false
        assertFalse(firstNamePredicate.equals(1));

        // null -> returns false
        assertFalse(firstNamePredicate.equals(null));

        // different stock -> returns false
        assertFalse(firstNamePredicate.equals(secondNamePredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword that matches a word
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Pork"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // One keyword that matches entire name exactly
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Pork Belly 100g"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // One keyword that is substring of a word in name
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("100"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple keywords that match completely
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Pork", "Belly", "100g"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple matching keywords with one non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Pork", "Belly", "100g", "Donut"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple keywords that match with only one word
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Belly", "ly", "bel", "belly"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple matching keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("pork", "belly", "ork", "00g"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple matching keywords that match multiple words in name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("pork Belly", "belly 100"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Mixed-case matching keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("pOrK bEllY", "bElly", "100G"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        //Mixed-case with multiple matching keywords and one non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("pOrK bEllYs", "bElly", "100G"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Keywords exactly matches name but do not match serial number, source, quantity and location
        predicate = new NameContainsKeywordsPredicate(
                Arrays.asList("ork", "pork belly", "pork belly 100g"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").withSerialNumber("Fairprice1")
                .withSource("Fairprice").withQuantity("12345").withLocation("Section B").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // One irrelevant non-matching keyword
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // One non-matching keyword that contains words of name
        predicate = new NameContainsKeywordsPredicate(Collections.singletonList("Pork 100g"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple irrelevant non-matching keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Porky", "Bully", "200"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple non-matching keywords that contain words of name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Pork Bellys", "PorkBelly", "Pork 100g"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple non-matching keywords with one keyword that exactly matches name
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("Pork Bellys", "PorkBelly", "Pork Belly 100g"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Keywords exactly match serial number, source, quantity and location but does not match name
        predicate = new NameContainsKeywordsPredicate(
                Arrays.asList("345", "section b", "Fairprice"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").withSerialNumber("Fairprice1")
                .withSource("Fairprice").withQuantity("12345").withLocation("Section B").build()));

    }

    @Test
    public void test_emptyStringKeywords_returnsFalse() {

        // One empty string keyword
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Collections.singletonList(""));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        // Multiple empty string keywords
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("", "", "", ""));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));

        //Mixed-case with multiple matching keywords and one empty string keyword
        predicate = new NameContainsKeywordsPredicate(Arrays.asList("pOrK bEllY", "bElly", ""));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").build()));
    }

    @Test
    public void testToString() {
        NameContainsKeywordsPredicate predicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("test"));
        assertEquals(predicate.toString(), "Name: test");
    }

}
