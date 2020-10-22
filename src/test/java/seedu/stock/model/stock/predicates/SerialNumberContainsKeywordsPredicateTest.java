package seedu.stock.model.stock.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.stock.testutil.StockBuilder;

public class SerialNumberContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstSerialNumberPredicateKeywordList = Collections.singletonList("first");
        List<String> secondSerialNumberPredicateKeywordList = Arrays.asList("first", "second");
        List<String> thirdSerialNumberPredicateKeywordList = Arrays.asList("first", "first");

        SerialNumberContainsKeywordsPredicate firstSerialNumberPredicate =
                new SerialNumberContainsKeywordsPredicate(firstSerialNumberPredicateKeywordList);
        SerialNumberContainsKeywordsPredicate secondSerialNumberPredicate =
                new SerialNumberContainsKeywordsPredicate(secondSerialNumberPredicateKeywordList);
        SerialNumberContainsKeywordsPredicate thirdSerialNumberPredicate =
                new SerialNumberContainsKeywordsPredicate(thirdSerialNumberPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstSerialNumberPredicate.equals(firstSerialNumberPredicate));

        // same values -> returns true
        SerialNumberContainsKeywordsPredicate firstPredicateCopy =
                new SerialNumberContainsKeywordsPredicate(firstSerialNumberPredicateKeywordList);
        assertTrue(firstSerialNumberPredicate.equals(firstPredicateCopy));

        SerialNumberContainsKeywordsPredicate secondPredicateCopy =
                new SerialNumberContainsKeywordsPredicate(Arrays.asList("first", "second"));
        assertTrue(secondSerialNumberPredicate.equals(secondPredicateCopy));

        // one same value, other copy of value -> returns false
        assertFalse(firstSerialNumberPredicate.equals(thirdSerialNumberPredicate));

        // different types -> returns false
        assertFalse(firstSerialNumberPredicate.equals(1));

        // null -> returns false
        assertFalse(firstSerialNumberPredicate.equals(null));

        // different stock -> returns false
        assertFalse(firstSerialNumberPredicate.equals(secondSerialNumberPredicate));
    }

    @Test
    public void test_serialNumberContainsKeywords_returnsTrue() {
        // One keyword that matches a word
        SerialNumberContainsKeywordsPredicate predicate =
                new SerialNumberContainsKeywordsPredicate(Collections.singletonList("ABC"));
        assertTrue(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // One keyword that matches entire Serial Number exactly
        predicate = new SerialNumberContainsKeywordsPredicate(Collections.singletonList("ABC-DEF12345"));
        assertTrue(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // One keyword that is substring of a word in Serial Number
        predicate = new SerialNumberContainsKeywordsPredicate(Collections.singletonList("F123"));
        assertTrue(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Multiple keywords that match substring
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("ABC", "-DE", "12345"));
        assertTrue(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Multiple matching keywords with one non-matching keyword
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("ABC", "DEF", "12345", "Donut"));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Multiple keywords that match with only one word
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("ABC", "abc", "bc", "Ab"));
        assertTrue(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Multiple matching keywords
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("ABC-DEF", "-DEF123"));
        assertTrue(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Mixed-case matching keywords
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("abC-dEF", "eF12", "-deF123"));
        assertTrue(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        //Mixed-case with multiple matching keywords and one non-matching keyword
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("abC-dEF", "eF12", "ABC-12345"));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Keywords exactly matches Serial Number but do not match source, name, quantity and location
        predicate = new SerialNumberContainsKeywordsPredicate(
                Arrays.asList("ABC", "bc-def", "ABC-DEF12345"));
        assertTrue(predicate.test(new StockBuilder().withName("Pork Belly 100g").withSource("Fairprice")
                .withSerialNumber("ABC-DEF12345").withQuantity("12345").withLocation("Section B").build()));
    }

    @Test
    public void test_serialNumberDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SerialNumberContainsKeywordsPredicate predicate =
                new SerialNumberContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // One irrelevant non-matching keyword
        predicate = new SerialNumberContainsKeywordsPredicate(Collections.singletonList("Apple"));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // One non-matching keyword that contains words of Serial Number
        predicate = new SerialNumberContainsKeywordsPredicate(Collections.singletonList("ABC-12345"));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Multiple irrelevant non-matching keywords
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("ABCD", "CDEF", "123456"));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Multiple non-matching keywords that contain words of Serial Number
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("ABCDEF", "ABC12345", "ABC -DEF12345"));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Multiple non-matching keywords with one keyword that exactly matches Serial Number
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("Pigs Farms", "PigsFarm", "ABC-DEF12345"));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Keywords exactly match source, name, quantity and location but does not match Serial Number
        predicate = new SerialNumberContainsKeywordsPredicate(
                Arrays.asList("12345", "section b", "Pork Belly", "Fairprice"));
        assertFalse(predicate.test(new StockBuilder().withName("Pork Belly 100g").withSource("Fairprice")
                .withSerialNumber("ABC-DEF12345").withQuantity("12345").withLocation("Section B").build()));

    }

    @Test
    public void test_emptyStringKeywords_returnsFalse() {

        // One empty string keyword
        SerialNumberContainsKeywordsPredicate predicate =
                new SerialNumberContainsKeywordsPredicate(Collections.singletonList(""));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        // Multiple empty string keywords
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("", "", "", ""));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));

        //Mixed-case with multiple matching keywords and one empty string keyword
        predicate = new SerialNumberContainsKeywordsPredicate(Arrays.asList("ABC-DEF", "DEF12345", ""));
        assertFalse(predicate.test(new StockBuilder().withSerialNumber("ABC-DEF12345").build()));
    }

    @Test
    public void testToString() {
        SerialNumberContainsKeywordsPredicate predicate =
                new SerialNumberContainsKeywordsPredicate(Collections.singletonList("test"));
        assertEquals(predicate.toString(), "Serial Number: test");
    }

}
