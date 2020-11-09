package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalItineraryAttractions.JURONG_BIRD_PARK;
import static seedu.address.testutil.TypicalItineraryAttractions.SINGAPORE_ZOO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItineraryBuilder;

class ItineraryContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ItineraryContainsKeywordsPredicate firstPredicate =
                new ItineraryContainsKeywordsPredicate(firstPredicateKeywordList);
        ItineraryContainsKeywordsPredicate secondPredicate =
                new ItineraryContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ItineraryContainsKeywordsPredicate firstPredicateCopy =
                new ItineraryContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ItineraryContainsKeywordsPredicate predicate =
                new ItineraryContainsKeywordsPredicate(Collections.singletonList("Paris"));
        assertTrue(predicate.test(new ItineraryBuilder().withName("Paris trip").build()));

        // Multiple keywords
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("paris", "trip"));
        assertTrue(predicate.test(new ItineraryBuilder().withName("Paris trip").build()));

        // Only one matching keyword
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("Paris", "Japan"));
        assertTrue(predicate.test(new ItineraryBuilder().withName("Paris trip").build()));

        // Mixed-case keywords
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("pARiS", "triP"));
        assertTrue(predicate.test(new ItineraryBuilder().withName("Paris trip").build()));

        // Keywords match start date, end date and description but does not match name
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("Maldives", "12-01-2020", "14-01-2020",
                "diving"));
        assertTrue(predicate.test(new ItineraryBuilder().withName("Malaysia").withStartDate("12-01-2020")
                .withEndDate("14-01-2020").withDescription("Weekend diving").build()));
    }

    @Test
    public void test_descriptionContainsKeywords_returnsTrue() {
        // One keyword
        ItineraryContainsKeywordsPredicate predicate =
                new ItineraryContainsKeywordsPredicate(Collections.singletonList("springs"));
        assertTrue(predicate.test(new ItineraryBuilder().withDescription("Hot springs hotel").build()));

        // Multiple keywords
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("mountain", "hiking"));
        assertTrue(predicate.test(new ItineraryBuilder().withDescription("mountain hiking").build()));

        // Only one matching keyword
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("mountain", "hiking"));
        assertTrue(predicate.test(new ItineraryBuilder().withDescription("mountain viewing").build()));

        // Mixed-case keywords
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("shOPPIng", "maLL"));
        assertTrue(predicate.test(new ItineraryBuilder().withDescription("Shopping mall").build()));
    }

    @Test
    public void test_startDateContainsKeywords_returnsTrue() {
        // One keyword
        ItineraryContainsKeywordsPredicate predicate =
                new ItineraryContainsKeywordsPredicate(Collections.singletonList("11-12-2020"));
        assertTrue(predicate.test(new ItineraryBuilder().withStartDate("11-12-2020").build()));

        // Only one matching keyword
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("11-12-2020", "12-12-2020"));
        assertTrue(predicate.test(new ItineraryBuilder().withStartDate("11-12-2020").build()));
    }

    @Test
    public void test_endDateContainsKeywords_returnsTrue() {
        // One keyword
        ItineraryContainsKeywordsPredicate predicate =
                new ItineraryContainsKeywordsPredicate(Collections.singletonList("16-12-2020"));
        assertTrue(predicate.test(new ItineraryBuilder().withEndDate("16-12-2020").build()));

        // Only one matching keyword
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("11-12-2020", "16-12-2020"));
        assertTrue(predicate.test(new ItineraryBuilder().withEndDate("16-12-2020").build()));
    }

    @Test
    public void test_budgetContainsKeywords_returnsTrue() {
        // One keyword
        ItineraryContainsKeywordsPredicate predicate =
                new ItineraryContainsKeywordsPredicate(Collections.singletonList("200"));
        assertTrue(predicate.test(new ItineraryBuilder().withBudget("200").build()));

        // Only one matching keyword
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("200", "300"));
        assertTrue(predicate.test(new ItineraryBuilder().withBudget("200").build()));

        // With $ sign
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("$200"));
        assertTrue(predicate.test(new ItineraryBuilder().withBudget("200").build()));
    }

    @Test
    public void test_attractionContainsKeywords_returnsTrue() {
        // One keyword
        ItineraryContainsKeywordsPredicate predicate =
                new ItineraryContainsKeywordsPredicate(Collections.singletonList("Jurong"));
        assertTrue(predicate.test(new ItineraryBuilder()
                .withItineraryAttraction(JURONG_BIRD_PARK, INDEX_FIRST).build()));

        // Multiple keywords
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("Jurong", "zoo"));
        assertTrue(predicate.test(new ItineraryBuilder()
                .withItineraryAttraction(JURONG_BIRD_PARK, INDEX_FIRST)
                .withItineraryAttraction(SINGAPORE_ZOO, INDEX_FIRST).build()));

        // Only one matching keyword
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("jurong", "sexy"));
        assertTrue(predicate.test(new ItineraryBuilder()
                .withItineraryAttraction(JURONG_BIRD_PARK, INDEX_FIRST).build()));

        // Mixed-case keywords
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("jURoNG", "ZoO"));
        assertTrue(predicate.test(new ItineraryBuilder()
                .withItineraryAttraction(JURONG_BIRD_PARK, INDEX_FIRST)
                .withItineraryAttraction(SINGAPORE_ZOO, INDEX_FIRST).build()));
    }


    @Test
    public void test_fieldDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ItineraryContainsKeywordsPredicate predicate =
                new ItineraryContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ItineraryBuilder().withName("grand canyon").build()));

        predicate = new ItineraryContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ItineraryBuilder().withDescription("racing").build()));

        // Non-matching keyword
        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("ski"));
        assertFalse(predicate.test(new ItineraryBuilder().withName("Paris trip").build()));

        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("This", "is", "The"));
        assertFalse(predicate.test(new ItineraryBuilder().withDescription("Big theme park").build()));

        predicate = new ItineraryContainsKeywordsPredicate(Arrays.asList("Antarctica"));
        assertFalse(predicate.test(new ItineraryBuilder()
                .withItineraryAttraction(JURONG_BIRD_PARK, INDEX_FIRST)
                .withItineraryAttraction(SINGAPORE_ZOO, INDEX_FIRST).build()));

    }
}
