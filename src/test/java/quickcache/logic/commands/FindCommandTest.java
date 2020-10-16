package quickcache.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import quickcache.commons.core.Messages;
import quickcache.model.Model;
import quickcache.model.ModelManager;
import quickcache.model.UserPrefs;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;
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
        FlashcardContainsTagPredicate firstPredicate =
            new FlashcardContainsTagPredicate(Collections.singletonList(new Tag("LSM1301")));
        FlashcardContainsTagPredicate secondPredicate =
            new FlashcardContainsTagPredicate(Collections.singletonList(new Tag("CS2101")));

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
    public void execute_oneKeyword_multipleFlashcardsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        FlashcardContainsTagPredicate predicate = preparePredicate("LSM1301");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalFlashcards.RANDOM1, TypicalFlashcards.RANDOM2),
            model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 2);
        FlashcardContainsTagPredicate predicate = preparePredicate("CS2100 CS");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashcardList(predicate);
        CommandTestUtil.assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalFlashcards.RANDOM3, TypicalFlashcards.RANDOM4),
            model.getFilteredFlashcardList());
    }

    /**
     * Parses {@code userInput} into a {@code FlashcardContainsTagPredicate}.
     */
    private FlashcardContainsTagPredicate preparePredicate(String userInput) {
        String trimmedArgs = userInput.trim();
        List<Tag> tagsToMatch = Arrays.stream(trimmedArgs.split("\\s+"))
                .map(Tag::new).collect(Collectors.toCollection(ArrayList::new));

        return new FlashcardContainsTagPredicate(tagsToMatch);
    }
}
