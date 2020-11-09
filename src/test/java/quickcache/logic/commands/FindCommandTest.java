package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.Messages;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.QuestionContainsKeywordsPredicate;
import quickcache.model.flashcard.Tag;
import quickcache.testutil.TypicalFlashcards;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(TypicalFlashcards.getTypicalQuickCache(), new UserPrefs());
    private final Model expectedModel = new ModelManager(TypicalFlashcards.getTypicalQuickCache(), new UserPrefs());

    @Test
    public void equals() {
        FlashcardPredicate firstPredicate = preparePredicate(prepareTagSet("LSM1301"), prepareKeywordList());
        FlashcardPredicate secondPredicate = preparePredicate(prepareTagSet("CS2101"), prepareKeywordList());

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different keywords -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_oneTag_multipleFlashcardsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        FlashcardPredicate predicate =
                preparePredicate(prepareTagSet("LSM1301"), prepareKeywordList());
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalFlashcards.RANDOM1, TypicalFlashcards.RANDOM2),
            model.getFilteredFlashcardList());
    }

    @Test
    public void execute_oneQuestionKeyword_multipleFlashcardsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        FlashcardPredicate predicate =
                preparePredicate(prepareTagSet(), prepareKeywordList("information"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalFlashcards.RANDOM6, TypicalFlashcards.RANDOM7),
                model.getFilteredFlashcardList());
    }

    @Test
    public void execute_oneQuestionKeywordAndOneTag_multipleFlashcardsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        FlashcardPredicate predicate =
                preparePredicate(prepareTagSet("CS2100"), prepareKeywordList("What"));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalFlashcards.RANDOM3, TypicalFlashcards.RANDOM4),
            model.getFilteredFlashcardList());
    }

    private Set<Tag> prepareTagSet(String... tags) {
        HashSet<Tag> tagSet = new HashSet<>();
        for (String tag: tags) {
            tagSet.add(new Tag(tag));
        }
        return tagSet;
    }

    private List<String> prepareKeywordList(String... keywords) {
        return Arrays.asList(keywords);
    }

    /**
     * Parses {@code Set} of {@code Tag} and {@code List} of keywords into a {@code FlashcardPredicate}.
     */
    private FlashcardPredicate preparePredicate(Set<Tag> tagsToMatch, List<String> questionKeywords) {
        ArrayList<Predicate<Flashcard>> predicates = new ArrayList<>();

        if (!tagsToMatch.isEmpty()) {
            predicates.add(new FlashcardContainsTagPredicate(tagsToMatch));
        }

        if (!questionKeywords.isEmpty()) {
            predicates.add(new QuestionContainsKeywordsPredicate(questionKeywords));
        }
        return new FlashcardPredicate(predicates);
    }
}
