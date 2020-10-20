package seedu.stock.model.stock.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.testutil.StockBuilder;

public class SourceContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstSourcePredicateKeywordList = Collections.singletonList("first");
        List<String> secondSourcePredicateKeywordList = Arrays.asList("first", "second");
        List<String> thirdSourcePredicateKeywordList = Arrays.asList("first", "first");

        SourceContainsKeywordsPredicate firstSourcePredicate =
                new SourceContainsKeywordsPredicate(firstSourcePredicateKeywordList);
        SourceContainsKeywordsPredicate secondSourcePredicate =
                new SourceContainsKeywordsPredicate(secondSourcePredicateKeywordList);
        SourceContainsKeywordsPredicate thirdSourcePredicate =
                new SourceContainsKeywordsPredicate(thirdSourcePredicateKeywordList);

        // same object -> returns true
        assertTrue(firstSourcePredicate.equals(firstSourcePredicate));

        // same values -> returns true
        SourceContainsKeywordsPredicate firstPredicateCopy =
                new SourceContainsKeywordsPredicate(firstSourcePredicateKeywordList);
        assertTrue(firstSourcePredicate.equals(firstPredicateCopy));

        SourceContainsKeywordsPredicate secondPredicateCopy =
                new SourceContainsKeywordsPredicate(Arrays.asList("first", "second"));
        assertTrue(secondSourcePredicate.equals(secondPredicateCopy));

        // one same value, other copy of value -> returns false
        assertFalse(firstSourcePredicate.equals(thirdSourcePredicate));

        // different types -> returns false
        assertFalse(firstSourcePredicate.equals(1));

        // null -> returns false
        assertFalse(firstSourcePredicate.equals(null));

        // different stock -> returns false
        assertFalse(firstSourcePredicate.equals(secondSourcePredicate));
    }

    @Test
    public void test_sourceContainsKeywords_returnsTrue() {
        // One keyword that matches a word
        SourceContainsKeywordsPredicate predicate =
                new SourceContainsKeywordsPredicate(Collections.singletonList("Pigs"));
        assertTrue(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // One keyword that matches entire source exactly
        predicate = new SourceContainsKeywordsPredicate(Collections.singletonList("Pigs Farm Area-249"));
        assertTrue(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // One keyword that is substring of a word in source
        predicate = new SourceContainsKeywordsPredicate(Collections.singletonList("249"));
        assertTrue(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple keywords that match completely
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("Pigs", "Farm", "Area-249"));
        assertTrue(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple matching keywords with one non-matching keyword
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("Pigs", "Farm", "Area-249", "Donut"));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple keywords that match with only one word
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("Farm", "rm", "Far", "farm"));
        assertTrue(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple matching keywords
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("pigs", "farm", "igs", "-24"));
        assertTrue(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple matching keywords that match multiple words in source
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("pigs Farm", "farm Area-249"));
        assertTrue(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Mixed-case matching keywords
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("pIgS fARM", "fArm", "A-249"));
        assertTrue(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        //Mixed-case with multiple matching keywords and one non-matching keyword
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("pIgS fARMs", "fArm", "A-249"));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Keywords exactly matches source but do not match serial number, name, quantity and location
        predicate = new SourceContainsKeywordsPredicate(
                Arrays.asList("igs", "pigs farm", "pigs farm area-249"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").withSerialNumber("Fairprice1")
                .withSource("Pigs Farm Area-249").withQuantity("12345").withLocation("Section B").build()));
    }

    @Test
    public void test_sourceDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SourceContainsKeywordsPredicate predicate = new SourceContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // One irrelevant non-matching keyword
        predicate = new SourceContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // One non-matching keyword that contains words of source
        predicate = new SourceContainsKeywordsPredicate(Collections.singletonList("Pigs Area-249"));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple irrelevant non-matching keywords
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("Pigsy", "Farms", "2499"));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple non-matching keywords that contain words of source
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("Pig Farm", "PigsFarm", "Pigs Area-249"));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple non-matching keywords with one keyword that exactly matches source
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("Pigs Farms", "PigsFarm", "Pigs Farm Area-249"));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Keywords exactly match serial number, name, quantity and location but does not match source
        predicate = new SourceContainsKeywordsPredicate(
                Arrays.asList("345", "section b", "Pork Belly", "Fairprice1"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").withSerialNumber("Fairprice1")
                .withSource("Pigs Farm Area-249").withQuantity("12345").withLocation("Section B").build()));

    }

    @Test
    public void test_emptyStringKeywords_returnsFalse() {

        // One empty string keyword
        SourceContainsKeywordsPredicate predicate = new SourceContainsKeywordsPredicate(Collections.singletonList(""));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        // Multiple empty string keywords
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("", "", "", ""));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));

        //Mixed-case with multiple matching keywords and one empty string keyword
        predicate = new SourceContainsKeywordsPredicate(Arrays.asList("pigs farm", "FARM", ""));
        assertFalse(predicate.test(new StockBuilder().withSource("Pigs Farm Area-249").build()));
    }

    @Test
    public void testToString() {
        SourceContainsKeywordsPredicate predicate =
                new SourceContainsKeywordsPredicate(Collections.singletonList("test"));
        assertEquals(predicate.toString(), "Source: test");
    }

}
