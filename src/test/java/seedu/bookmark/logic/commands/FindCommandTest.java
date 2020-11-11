package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.commons.core.Messages.MESSAGE_BOOKS_LISTED_OVERVIEW;
import static seedu.bookmark.commons.core.Messages.MESSAGE_WORD_NOT_UNDERSTOOD;
import static seedu.bookmark.commons.core.Messages.MESSAGE_WORD_SUGGESTION;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOT_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.testutil.TypicalBooks.CRIME_AND_PUNISHMENT;
import static seedu.bookmark.testutil.TypicalBooks.ENDERS_GAME;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;
import static seedu.bookmark.testutil.TypicalBooks.LORD_OF_THE_FLIES;
import static seedu.bookmark.testutil.TypicalBooks.ON_THE_ROAD;
import static seedu.bookmark.testutil.TypicalBooks.THE_HUNGER_GAMES;
import static seedu.bookmark.testutil.TypicalBooks.TO_KILL_A_MOCKINGBIRD;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.parser.Prefix;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.predicates.BookCompletedPredicate;
import seedu.bookmark.model.book.predicates.BookNotCompletedPredicate;
import seedu.bookmark.model.book.predicates.GenreContainsKeywordsPredicate;
import seedu.bookmark.model.book.predicates.NameContainsKeywordsPredicate;
import seedu.bookmark.model.book.predicates.TagContainsKeywordsPredicate;
import seedu.bookmark.model.wordstore.exceptions.WordStoreNotFoundException;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void namePredicateEquals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        Prefix namePrefix = PREFIX_NAME;
        String[] firstKeyword = {"first"};
        String[] secondKeyword = {"second"};

        FindCommand findFirstCommand = new FindCommand(firstPredicate, namePrefix, firstKeyword);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, namePrefix, secondKeyword);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, namePrefix, firstKeyword);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void genrePredicateEquals() {
        GenreContainsKeywordsPredicate firstPredicate =
                new GenreContainsKeywordsPredicate(Collections.singletonList("first"));
        GenreContainsKeywordsPredicate secondPredicate =
                new GenreContainsKeywordsPredicate(Collections.singletonList("second"));
        Prefix genrePrefix = PREFIX_GENRE;
        String[] firstKeyword = {"first"};
        String[] secondKeyword = {"second"};

        FindCommand findFirstCommand = new FindCommand(firstPredicate, genrePrefix, firstKeyword);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, genrePrefix, secondKeyword);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, genrePrefix, firstKeyword);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void tagPredicateEquals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));
        Prefix tagPrefix = PREFIX_TAG;
        String[] firstKeyword = {"first"};
        String[] secondKeyword = {"second"};

        FindCommand findFirstCommand = new FindCommand(firstPredicate, tagPrefix, firstKeyword);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, tagPrefix, secondKeyword);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, tagPrefix, firstKeyword);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different book -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nameZeroKeywords_noBookFound() {
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        Prefix namePrefix = PREFIX_NAME;
        String[] keywords = prepareKeywords(" ");
        String expectedMessage = String.format(MESSAGE_WORD_NOT_UNDERSTOOD, "");
        FindCommand command = new FindCommand(predicate, namePrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_nameMultipleKeywords_multipleBooksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("hunger flies enders");
        Prefix namePrefix = PREFIX_NAME;
        String[] keywords = prepareKeywords("hunger flies enders");
        FindCommand command = new FindCommand(predicate, namePrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(THE_HUNGER_GAMES, LORD_OF_THE_FLIES, ENDERS_GAME), model.getFilteredBookList());
    }

    @Test
    public void execute_genreZeroKeywords_noBookFound() {
        GenreContainsKeywordsPredicate predicate = prepareGenrePredicate(" ");
        Prefix genrePrefix = PREFIX_GENRE;
        String[] keywords = prepareKeywords(" ");
        String expectedMessage = String.format(MESSAGE_WORD_NOT_UNDERSTOOD, "");
        FindCommand command = new FindCommand(predicate, genrePrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_genreMultipleKeywords_multipleBooksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 2);
        GenreContainsKeywordsPredicate predicate = prepareGenrePredicate("thriller mystery");
        Prefix genrePrefix = PREFIX_GENRE;
        String[] keywords = prepareKeywords("thriller mystery");
        FindCommand command = new FindCommand(predicate, genrePrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LORD_OF_THE_FLIES, ENDERS_GAME), model.getFilteredBookList());
    }

    @Test
    public void execute_tagZeroKeywords_noBookFound() {
        TagContainsKeywordsPredicate predicate = prepareTagPredicate(" ");
        Prefix tagPrefix = PREFIX_TAG;
        String[] keywords = prepareKeywords(" ");
        String expectedMessage = String.format(MESSAGE_WORD_NOT_UNDERSTOOD, "");
        FindCommand command = new FindCommand(predicate, tagPrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_tagMultipleKeywords_multipleBooksFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 3);
        TagContainsKeywordsPredicate predicate = prepareTagPredicate("good bad");
        Prefix tagPrefix = PREFIX_TAG;
        String[] keywords = prepareKeywords("good bad");
        FindCommand command = new FindCommand(predicate, tagPrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HARRY_POTTER, TO_KILL_A_MOCKINGBIRD, CRIME_AND_PUNISHMENT),
                model.getFilteredBookList());
    }

    @Test
    public void execute_findCompletedBooks() { //findSuggestion will not be activated for finding CompletedBook
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 1);
        BookCompletedPredicate predicate = prepareCompletedBooksPredicate();
        Prefix completedPrefix = PREFIX_COMPLETED;
        String[] keywords = prepareKeywords("");
        FindCommand command = new FindCommand(predicate, completedPrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CRIME_AND_PUNISHMENT), model.getFilteredBookList());
    }

    @Test
    public void execute_findNotCompletedBooks() { //findSuggestion will not be activated for finding NotCompletedBook
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 6);
        BookNotCompletedPredicate predicate = prepareNotCompletedBooksPredicate();
        Prefix notCompletedPrefix = PREFIX_NOT_COMPLETED;
        String[] keywords = prepareKeywords("");
        FindCommand command = new FindCommand(predicate, notCompletedPrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(HARRY_POTTER, TO_KILL_A_MOCKINGBIRD, THE_HUNGER_GAMES, LORD_OF_THE_FLIES,
                ENDERS_GAME, ON_THE_ROAD), model.getFilteredBookList());
    }

    @Test
    public void execute_findSuggestionCorrectPrefix() {
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Hbrry");
        Prefix namePrefix = PREFIX_NAME;
        String[] keywords = prepareKeywords("Hbrry");
        String expectedMessage = String.format(MESSAGE_WORD_SUGGESTION, "Hbrry", "Harry");
        FindCommand command = new FindCommand(predicate, namePrefix, keywords);
        expectedModel.updateFilteredBookList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBookList());
    }

    @Test
    public void execute_findSuggestionInvalidPrefix() { //default on Stored NameWords
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Hbrry");
        Prefix invalidPrefix = PREFIX_BOOKMARK; //invalid prefix for findSuggestion
        String[] keywords = prepareKeywords("Hbrry");
        String expectedMessage = String.format(MESSAGE_WORD_SUGGESTION, "Hbrry", "Harry");
        FindCommand command = new FindCommand(predicate, invalidPrefix, keywords);
        assertThrows(WordStoreNotFoundException.class, () -> command.execute(model));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code GenreContainsKeywordsPredicate}.
     */
    private GenreContainsKeywordsPredicate prepareGenrePredicate(String userInput) {
        return new GenreContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code GenreContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code BookCompletedPredicate}.
     */
    private BookCompletedPredicate prepareCompletedBooksPredicate() {
        return new BookCompletedPredicate();
    }

    /**
     * Parses {@code userInput} into a {@code BookNotCompletedPredicate}.
     */
    private BookNotCompletedPredicate prepareNotCompletedBooksPredicate() {
        return new BookNotCompletedPredicate();
    }

    /**
     * Parses {@code userInput} into a {@code String[]}.
     */
    private String[] prepareKeywords(String userInput) {
        String[] arr = userInput.split("\\s+");
        return arr;
    }
}
