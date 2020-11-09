package seedu.flashcard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashcard.commons.core.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;
import static seedu.flashcard.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_2;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_3;
import static seedu.flashcard.testutil.TypicalFlashcards.getTypicalFlashcardDeck;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.flashcard.model.Model;
import seedu.flashcard.model.ModelManager;
import seedu.flashcard.model.UserPrefs;
import seedu.flashcard.model.flashcard.AnswerContainsKeywordsPredicate;
import seedu.flashcard.model.flashcard.CategoryContainsKeywordsPredicate;
import seedu.flashcard.model.flashcard.Flashcard;
import seedu.flashcard.model.flashcard.NoteContainsKeywordsPredicate;
import seedu.flashcard.model.flashcard.QuestionContainsKeywordsPredicate;
import seedu.flashcard.model.flashcard.TagsContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {

    private Model model = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashcardDeck(), new UserPrefs());

    @Test
    public void equals() {
        List<String> firstPredicate = Collections.singletonList("first");
        List<String> secondPredicate = Collections.singletonList("second");

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

        // different flashcard -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashcardFound() {
        String expectedMessage = FindCommand.MESSAGE_NO_FLASHCARDS_MATCHING_KEYWORDS;
        List<String> keywords = prepareKeywords(" ");
        FindCommand command = new FindCommand(keywords);

        QuestionContainsKeywordsPredicate questionPredicate = new QuestionContainsKeywordsPredicate(keywords);
        AnswerContainsKeywordsPredicate answerPredicate = new AnswerContainsKeywordsPredicate(keywords);
        CategoryContainsKeywordsPredicate categoryPredicate = new CategoryContainsKeywordsPredicate(keywords);
        NoteContainsKeywordsPredicate notePredicate = new NoteContainsKeywordsPredicate(keywords);
        TagsContainsKeywordsPredicate tagPredicate = new TagsContainsKeywordsPredicate(keywords);

        List<Predicate<Flashcard>> listOfPredicates = Arrays.asList(questionPredicate, answerPredicate,
                categoryPredicate, notePredicate, tagPredicate);
        Predicate<Flashcard> allPredicates = listOfPredicates.stream().reduce(Predicate::or).orElse(x->false);

        expectedModel.updateFilteredFlashcardList(allPredicates);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashcardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashcardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        List<String> keywords = prepareKeywords("SDLC control assertions");
        FindCommand command = new FindCommand(keywords);

        QuestionContainsKeywordsPredicate questionPredicate = new QuestionContainsKeywordsPredicate(keywords);
        AnswerContainsKeywordsPredicate answerPredicate = new AnswerContainsKeywordsPredicate(keywords);
        CategoryContainsKeywordsPredicate categoryPredicate = new CategoryContainsKeywordsPredicate(keywords);
        NoteContainsKeywordsPredicate notePredicate = new NoteContainsKeywordsPredicate(keywords);
        TagsContainsKeywordsPredicate tagPredicate = new TagsContainsKeywordsPredicate(keywords);

        List<Predicate<Flashcard>> listOfPredicates = Arrays.asList(questionPredicate, answerPredicate,
                categoryPredicate, notePredicate, tagPredicate);

        Predicate<Flashcard> allPredicates = listOfPredicates.stream().reduce(Predicate::or).orElse(x->false);

        expectedModel.updateFilteredFlashcardList(allPredicates);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FLASHCARD_1, FLASHCARD_2, FLASHCARD_3), model.getFilteredFlashcardList());
    }

    /**
     * Parses {@code userInput} into a {@code List<String>}.
     */
    private List<String> prepareKeywords(String userInput) {
        return Arrays.asList(userInput.split("\\s+"));
    }
}
