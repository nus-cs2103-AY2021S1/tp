package seedu.address.model.flashcard;

public class NameContainsKeywordsPredicateTest {

    //    @Test
    //    public void equals() {
    //        List<String> firstPredicateKeywordList = Collections.singletonList("first");
    //        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");
    //
    //        QuestionContainsKeywordsPredicate firstPredicate = new
    //        QuestionContainsKeywordsPredicate(firstPredicateKeywordList);
    //        QuestionContainsKeywordsPredicate secondPredicate = new
    //        QuestionContainsKeywordsPredicate(secondPredicateKeywordList);
    //
    //        // same object -> returns true
    //        assertTrue(firstPredicate.equals(firstPredicate));
    //
    //        // same values -> returns true
    //        QuestionContainsKeywordsPredicate firstPredicateCopy = new QuestionContainsKeywordsPredicate(
    //        firstPredicateKeywordList);
    //        assertTrue(firstPredicate.equals(firstPredicateCopy));
    //
    //        // different types -> returns false
    //        assertFalse(firstPredicate.equals(1));
    //
    //        // null -> returns false
    //        assertFalse(firstPredicate.equals(null));
    //
    //        // different person -> returns false
    //        assertFalse(firstPredicate.equals(secondPredicate));
    //    }
    //
    //    @Test
    //    public void test_nameContainsKeywords_returnsTrue() {
    //        // One keyword
    //        QuestionContainsKeywordsPredicate predicate = new
    //        QuestionContainsKeywordsPredicate(Collections.singletonList("Alice"));
    //        assertTrue(predicate.test(new FlashcardBuilder().withName("Alice Bob").build()));
    //
    //        // Multiple keywords
    //        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
    //        assertTrue(predicate.test(new FlashcardBuilder().withName("Alice Bob").build()));
    //
    //        // Only one matching keyword
    //        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
    //        assertTrue(predicate.test(new FlashcardBuilder().withName("Alice Carol").build()));
    //
    //        // Mixed-case keywords
    //        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
    //        assertTrue(predicate.test(new FlashcardBuilder().withName("Alice Bob").build()));
    //    }
    //
    //    @Test
    //    public void test_nameDoesNotContainKeywords_returnsFalse() {
    //        // Zero keywords
    //        QuestionContainsKeywordsPredicate predicate =
    //            new QuestionContainsKeywordsPredicate(Collections.emptyList());
    //        assertFalse(predicate.test(new FlashcardBuilder().withName("Alice").build()));
    //
    //        // Non-matching keyword
    //        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("Carol"));
    //        assertFalse(predicate.test(new FlashcardBuilder().withName("Alice Bob").build()));
    //
    //        // Keywords match phone, email and address, but does not match name
    //        predicate = new QuestionContainsKeywordsPredicate(Arrays.asList("12345",
    //        "alice@email.com", "Main", "Street"));
    //        assertFalse(predicate.test(new FlashcardBuilder().withName("Alice").withPhone("12345")
    //                .withEmail("alice@email.com").withAddress("Main Street").build()));
    //    }
}
