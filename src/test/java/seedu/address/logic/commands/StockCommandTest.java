package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BOOKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalBooks.BOOK5;
import static seedu.address.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.book.Book;
import seedu.address.model.book.NameMatchesKeywordPredicate;
import seedu.address.model.book.NumberContainsKeywordPredicate;
import seedu.address.ui.Mode;

/**
 * Contains integration tests (interaction with the Model) and unit tests for StockCommand.
 */
class StockCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    void execute_twoKeywords_oneBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 1);
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate =
                new NameMatchesKeywordPredicate(Arrays.asList("Elle"));
        NumberContainsKeywordPredicate numberContainsKeywordsPredicate =
                new NumberContainsKeywordPredicate(Arrays.asList("9482224"));

        Predicate<Book> predicate = (book -> nameMatchesKeywordsPredicate.test(book)
                || numberContainsKeywordsPredicate.test(book));

        expectedModel.updateFilteredBookList(predicate, Mode.NORMAL);

        StockCommand stockCommand = new StockCommand(Arrays.asList("Elle"), Arrays.asList("9482224"));
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOOK5), model.getFilteredBookList());
    }

    @Test
    void execute_twoKeywords_noBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 0);
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate =
                new NameMatchesKeywordPredicate(Arrays.asList("Elle"));
        NumberContainsKeywordPredicate numberContainsKeywordsPredicate =
                new NumberContainsKeywordPredicate(Arrays.asList("94351253"));

        Predicate<Book> predicate = (book -> nameMatchesKeywordsPredicate.test(book)
                && numberContainsKeywordsPredicate.test(book));

        expectedModel.updateFilteredBookList(predicate, Mode.NORMAL);

        StockCommand stockCommand = new StockCommand(Arrays.asList("Elle"), Arrays.asList("94351253"));
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void execute_oneKeyword_oneBookFound() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 1);
        NameMatchesKeywordPredicate nameMatchesKeywordsPredicate =
                new NameMatchesKeywordPredicate(Arrays.asList("Elle"));
        expectedModel.updateFilteredBookList(nameMatchesKeywordsPredicate, Mode.NORMAL);
        StockCommand stockCommand = new StockCommand(Arrays.asList("Elle"), null);
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOOK5), model.getFilteredBookList());

        NumberContainsKeywordPredicate numberContainsKeywordsPredicate =
                new NumberContainsKeywordPredicate(Arrays.asList("9482224"));
        expectedModel.updateFilteredBookList(numberContainsKeywordsPredicate, Mode.NORMAL);
        stockCommand = new StockCommand(null, Arrays.asList("9482224"));
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BOOK5), model.getFilteredBookList());
    }

    @Test
    void execute_noKeyWord_allBookShown() {
        String expectedMessage = String.format(MESSAGE_BOOKS_LISTED_OVERVIEW, 7);

        StockCommand stockCommand = new StockCommand(null, null);
        assertCommandSuccess(stockCommand, model, expectedMessage, expectedModel);
    }

    @Test
    void equals() {
        StockCommand originalCommand = new StockCommand(Arrays.asList("Elle"), Arrays.asList("9482224"));

        StockCommand newCommand = new StockCommand(Arrays.asList("Elle"), Arrays.asList("9482224"));

        newCommand.setPredicate(originalCommand.getPredicate());

        // same object -> returns true
        assertTrue(originalCommand.equals(originalCommand));

        // same value -> returns true
        assertTrue(originalCommand.equals(newCommand));

        // null -> returns false
        assertFalse(originalCommand.equals(null));

        // different types -> returns false
        assertFalse(originalCommand.equals(new ClearCommand()));

        // different name keyword -> returns false
        assertFalse(originalCommand.equals(new StockCommand(Arrays.asList("Elle", "Smith"),
                Arrays.asList("9482224"))));

        // different ISBN number -> returns false
        assertFalse(originalCommand.equals(new StockCommand(Arrays.asList("Elle"),
                Arrays.asList("9482227"))));
    }
}
