package seedu.address.model.delivery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.delivery.predicate.DeliveryContainsKeywordsPredicate;
import seedu.address.testutil.DeliveryBuilder;

public class DeliveryContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DeliveryContainsKeywordsPredicate firstPredicate = new DeliveryContainsKeywordsPredicate(
                firstPredicateKeywordList, PREFIX_NAME);
        DeliveryContainsKeywordsPredicate secondPredicate = new DeliveryContainsKeywordsPredicate(
                secondPredicateKeywordList, PREFIX_NAME);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DeliveryContainsKeywordsPredicate firstPredicateCopy = new DeliveryContainsKeywordsPredicate(
                firstPredicateKeywordList, PREFIX_NAME);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different Item -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        DeliveryContainsKeywordsPredicate predicate =
                new DeliveryContainsKeywordsPredicate(Collections.singletonList("Damith"), PREFIX_NAME);
        assertTrue(predicate.test(new DeliveryBuilder().withName("Damith Aaron").build()));

        // Multiple keywords
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Damith", "Aaron"), PREFIX_NAME);
        assertTrue(predicate.test(new DeliveryBuilder().withName("Chicken Aaron").build()));

        // Only one matching keyword
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Damith", "Aaron"), PREFIX_NAME);
        assertTrue(predicate.test(new DeliveryBuilder().withName("Aaron Lim").build()));

        // Mixed-case keywords
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Aaron", "Damith"), PREFIX_NAME);
        assertTrue(predicate.test(new DeliveryBuilder().withName("Aaron Damith").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DeliveryContainsKeywordsPredicate predicate = new DeliveryContainsKeywordsPredicate(
                Collections.emptyList(), PREFIX_NAME);
        assertFalse(predicate.test(new DeliveryBuilder().withName("Damith").build()));

        // Non-matching keyword
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Wilson"), PREFIX_NAME);
        assertFalse(predicate.test(new DeliveryBuilder().withName("Damith Sanjaya").build()));
    }

    @Test
    public void test_addressContainsKeywords_returnsTrue() {
        // One keyword
        DeliveryContainsKeywordsPredicate predicate =
                new DeliveryContainsKeywordsPredicate(Collections.singletonList("Jln"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new DeliveryBuilder().withAddress("Jln").build()));

        // Multiple keywords
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Jln buroh", "Jln kecil"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new DeliveryBuilder().withAddress("Jln buroh kecil").build()));

        // Only one matching keyword
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Jln buroh", "Jln kecil"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new DeliveryBuilder().withAddress("Jln buroh").build()));

        // Mixed-case keywords
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Aaron", "Damith"), PREFIX_ADDRESS);
        assertTrue(predicate.test(new DeliveryBuilder().withAddress("Aaron Damith").build()));
    }

    @Test
    public void test_addressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DeliveryContainsKeywordsPredicate predicate = new DeliveryContainsKeywordsPredicate(
                Collections.emptyList(), PREFIX_ADDRESS);
        assertFalse(predicate.test(new DeliveryBuilder().withAddress("Jln besar").build()));

        // Non-matching keyword
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Jln besar"), PREFIX_ADDRESS);
        assertFalse(predicate.test(new DeliveryBuilder().withAddress("Jln kecil").build()));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        DeliveryContainsKeywordsPredicate predicate =
                new DeliveryContainsKeywordsPredicate(Collections.singletonList("91231231"), PREFIX_PHONE);
        assertTrue(predicate.test(new DeliveryBuilder().withPhone("91231231").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DeliveryContainsKeywordsPredicate predicate = new DeliveryContainsKeywordsPredicate(
                Collections.emptyList(), PREFIX_PHONE);
        assertFalse(predicate.test(new DeliveryBuilder().withPhone("91231231").build()));

        // Non-matching keyword
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Damith"), PREFIX_PHONE);
        assertFalse(predicate.test(new DeliveryBuilder().withPhone("91234122").build()));

        // Keywords match name and tag, but does not match supplier
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Damith", "Loh"), PREFIX_PHONE);
        assertFalse(predicate.test(new DeliveryBuilder().withName("Damith").withPhone("91278322")
                .build()));
    }

    @Test
    public void test_ordersContainsKeywords_returnsTrue() {
        // One keyword
        DeliveryContainsKeywordsPredicate predicate =
                new DeliveryContainsKeywordsPredicate(Collections.singletonList("nasi"), PREFIX_ORDER);
        assertTrue(predicate.test(new DeliveryBuilder().withOrder("nasi ayam").build()));

        // Mixed-case keywords
        predicate = new DeliveryContainsKeywordsPredicate(Collections.singletonList("nasi lemak"), PREFIX_ORDER);
        assertTrue(predicate.test(new DeliveryBuilder().withOrder("nasi lemak").build()));
    }

    @Test
    public void test_ordersDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DeliveryContainsKeywordsPredicate predicate = new DeliveryContainsKeywordsPredicate(
                Collections.emptyList(), PREFIX_ORDER);
        assertFalse(predicate.test(new DeliveryBuilder().withOrder("meat").build()));

        // Non-matching keyword
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("fish"), PREFIX_PHONE);
        assertFalse(predicate.test(new DeliveryBuilder().withOrder("meat").build()));

        // Keywords match name and phone, but not order
        predicate = new DeliveryContainsKeywordsPredicate(Arrays.asList("Kelvin", "fish"), PREFIX_ORDER);
        assertFalse(predicate.test(new DeliveryBuilder().withName("Kelvin").withOrder("meat")
                .build()));
    }
}
