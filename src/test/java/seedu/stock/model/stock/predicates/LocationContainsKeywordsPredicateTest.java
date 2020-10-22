package seedu.stock.model.stock.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.testutil.StockBuilder;

public class LocationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstLocationPredicateKeywordList = Collections.singletonList("first");
        List<String> secondLocationPredicateKeywordList = Arrays.asList("first", "second");
        List<String> thirdLocationPredicateKeywordList = Arrays.asList("first", "first");

        LocationContainsKeywordsPredicate firstLocationPredicate =
                new LocationContainsKeywordsPredicate(firstLocationPredicateKeywordList);
        LocationContainsKeywordsPredicate secondLocationPredicate =
                new LocationContainsKeywordsPredicate(secondLocationPredicateKeywordList);
        LocationContainsKeywordsPredicate thirdLocationPredicate =
                new LocationContainsKeywordsPredicate(thirdLocationPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstLocationPredicate.equals(firstLocationPredicate));

        // same values -> returns true
        LocationContainsKeywordsPredicate firstPredicateCopy =
                new LocationContainsKeywordsPredicate(firstLocationPredicateKeywordList);
        assertTrue(firstLocationPredicate.equals(firstPredicateCopy));

        LocationContainsKeywordsPredicate secondPredicateCopy =
                new LocationContainsKeywordsPredicate(Arrays.asList("first", "second"));
        assertTrue(secondLocationPredicate.equals(secondPredicateCopy));

        // one same value, other copy of value -> returns false
        assertFalse(firstLocationPredicate.equals(thirdLocationPredicate));

        // different types -> returns false
        assertFalse(firstLocationPredicate.equals(1));

        // null -> returns false
        assertFalse(firstLocationPredicate.equals(null));

        // different stock -> returns false
        assertFalse(firstLocationPredicate.equals(secondLocationPredicate));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // One keyword that matches a word
        LocationContainsKeywordsPredicate predicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList("Poultry"));
        assertTrue(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // One keyword that matches entire Location exactly
        predicate = new LocationContainsKeywordsPredicate(Collections.singletonList("Poultry Section B1"));
        assertTrue(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // One keyword that is substring of a word in Location
        predicate = new LocationContainsKeywordsPredicate(Collections.singletonList("tion"));
        assertTrue(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple keywords that match completely
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Poultry", "Section", "B1"));
        assertTrue(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple matching keywords with one non-matching keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Poultry", "Section", "B1", "Donut"));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple keywords that match with only one word
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Sec", "tion", "section", "tion"));
        assertTrue(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple matching keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("poultry", "section", "b1", "poul"));
        assertTrue(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple matching keywords that match multiple words in Location
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("poultry Section", "section B1"));
        assertTrue(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Mixed-case matching keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("pOULtrY sEcTIOn", "sectION", "b1"));
        assertTrue(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        //Mixed-case with multiple matching keywords and one non-matching keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("pOutry section", "section", "B1"));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Keywords exactly matches Location but do not match serial number, name, quantity and source
        predicate = new LocationContainsKeywordsPredicate(
                Arrays.asList("oultry", "poultry section", "Poultry Section B1"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").withSerialNumber("Fairprice1")
                .withLocation("Poultry Section B1").withQuantity("12345").withSource("Fairprice").build()));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LocationContainsKeywordsPredicate predicate = new LocationContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // One irrelevant non-matching keyword
        predicate = new LocationContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // One non-matching keyword that contains words of Location
        predicate = new LocationContainsKeywordsPredicate(Collections.singletonList("Poultry B1"));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple irrelevant non-matching keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("One", "Two", "12345"));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple non-matching keywords that contain words of Location
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Poultry Sections",
                "PoultrySection", "Poultry B1"));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple non-matching keywords with one keyword that exactly matches Location
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("Poultry Sections",
                "PoultrySection", "Poultry Section B1"));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Keywords exactly match serial number, name, quantity and source but does not match Location
        predicate = new LocationContainsKeywordsPredicate(
                Arrays.asList("12345", "Fairprice", "Pork Belly"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").withSerialNumber("Fairprice1")
                .withLocation("Poultry Section B1").withQuantity("12345").withSource("Fairprice").build()));

    }

    @Test
    public void test_emptyStringKeywords_returnsFalse() {

        // One empty string keyword
        LocationContainsKeywordsPredicate predicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList(""));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        // Multiple empty string keywords
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("", "", "", ""));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));

        //Mixed-case with multiple matching keywords and one empty string keyword
        predicate = new LocationContainsKeywordsPredicate(Arrays.asList("pOultry section", "b1", ""));
        assertFalse(predicate.test(new StockBuilder().withLocation("Poultry Section B1").build()));
    }

    @Test
    public void testToString() {
        LocationContainsKeywordsPredicate predicate =
                new LocationContainsKeywordsPredicate(Collections.singletonList("test"));
        assertEquals(predicate.toString(), "Location: test");
    }

}
