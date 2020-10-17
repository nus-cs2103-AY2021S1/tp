//package quickcache.model.flashcard;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import quickcache.testutil.FlashcardBuilder;
//
//class FlashcardContainsTagPredicateTest {
//
//    @Test
//    public void equals() {
//        List<Tag> firstPredicateKeywordList = Collections.singletonList(new Tag("first"));
//        List<Tag> secondPredicateKeywordList = Arrays.asList(new Tag("first"), new Tag("second"));
//
//        FlashcardContainsTagPredicate firstPredicate = new FlashcardContainsTagPredicate(firstPredicateKeywordList);
//        FlashcardContainsTagPredicate secondPredicate = new FlashcardContainsTagPredicate(secondPredicateKeywordList);
//
//        // same object -> returns true
//        assertTrue(firstPredicate.equals(firstPredicate));
//
//        // same values -> returns true
//        FlashcardContainsTagPredicate firstPredicateCopy = new FlashcardContainsTagPredicate(firstPredicateKeywordList);
//        assertTrue(firstPredicate.equals(firstPredicateCopy));
//
//        // different types -> returns false
//        assertFalse(firstPredicate.equals(1));
//
//        // null -> returns false
//        assertFalse(firstPredicate.equals(null));
//
//        // different predicate -> returns false
//        assertFalse(firstPredicate.equals(secondPredicate));
//    }
//
//    @Test
//    public void test_nameContainsKeywords_returnsTrue() {
//        // One keyword
//        FlashcardContainsTagPredicate predicate =
//            new FlashcardContainsTagPredicate(Collections.singletonList(new Tag("Programming")));
//        assertTrue(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));
//
//        // Multiple keywords
//        predicate = new FlashcardContainsTagPredicate(
//                Arrays.asList(new Tag("Programming"), new Tag("English")));
//        assertTrue(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));
//
//        // Only one matching keyword
//        predicate = new FlashcardContainsTagPredicate(Arrays.asList(
//                new Tag("Programming"), new Tag("Carol")));
//        assertTrue(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));
//    }
//
//    @Test
//    public void test_nameDoesNotContainKeywords_returnsFalse() {
//        // Zero keywords
//        FlashcardContainsTagPredicate predicate = new FlashcardContainsTagPredicate(Collections.emptyList());
//        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming").build()));
//
//        // Non-matching keyword
//        predicate = new FlashcardContainsTagPredicate(Collections.singletonList(new Tag("Carol")));
//        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));
//
//        // Mixed-case keywords
//        predicate = new FlashcardContainsTagPredicate(Arrays.asList(new Tag(
//                "pRogramming"), new Tag("EnGlish")));
//        assertFalse(predicate.test(new FlashcardBuilder().withTags("Programming", "English").build()));
//    }
//}
