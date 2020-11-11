package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOTE_TEXT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOTE_TITLE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TOTAL_PAGES;
import static seedu.bookmark.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.exceptions.CommandException;
import seedu.bookmark.model.Library;
import seedu.bookmark.model.Model;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.predicates.NameContainsKeywordsPredicate;
import seedu.bookmark.testutil.EditBookDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_1984 = "1984";
    public static final String VALID_NAME_JANE_EYRE = "Jane Eyre";
    public static final String VALID_GENRE_1984 = "Science Fiction";
    public static final String VALID_GENRE_JANE_EYRE = "Novel";
    public static final String VALID_TAG_GOOD = "Good";
    public static final String VALID_TAG_BAD = "Bad";
    public static final String VALID_TOTAL_PAGES_1984 = "684";
    public static final String VALID_TOTAL_PAGES_JANE_EYRE = "532";
    public static final String VALID_TOTAL_PAGES = "1";
    public static final String VALID_BOOKMARK_1984 = "239";
    public static final String VALID_BOOKMARK_JANE_EYRE = "123";
    public static final String VALID_NOTE_TITLE = "- Chapter 1 -";
    public static final String VALID_NOTE_TEXT = "Awesome read!";

    public static final String NAME_DESC_1984 = " " + PREFIX_NAME + VALID_NAME_1984;
    public static final String NAME_DESC_JANE_EYRE = " " + PREFIX_NAME + VALID_NAME_JANE_EYRE;
    public static final String GENRE_DESC_1984 = " " + PREFIX_GENRE + VALID_GENRE_1984;
    public static final String GENRE_DESC_JANE_EYRE = " " + PREFIX_GENRE + VALID_GENRE_JANE_EYRE;
    public static final String TAG_DESC_GOOD = " " + PREFIX_TAG + VALID_TAG_GOOD;
    public static final String TAG_DESC_BAD = " " + PREFIX_TAG + VALID_TAG_BAD;
    public static final String TOTAL_PAGES_DESC_1984 = " " + PREFIX_TOTAL_PAGES + VALID_TOTAL_PAGES_1984;
    public static final String TOTAL_PAGES_DESC_JANE_EYRE = " " + PREFIX_TOTAL_PAGES + VALID_TOTAL_PAGES_JANE_EYRE;
    public static final String BOOKMARK_DESC_1984 = " " + PREFIX_BOOKMARK + VALID_BOOKMARK_1984;
    public static final String BOOKMARK_DESC_JANE_EYRE = " " + PREFIX_BOOKMARK + VALID_BOOKMARK_JANE_EYRE;
    public static final String NOTE_TITLE = " " + PREFIX_NOTE_TITLE + "My thoughts...";
    public static final String NOTE_TEXT = " " + PREFIX_NOTE_TEXT + "Awesome read!";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME
            + "a".repeat(Name.MAX_NAME_LENGTH + 1); //char limit120
    public static final String INVALID_GENRE_DESC = " " + PREFIX_GENRE + "@@@@"; // '@' not allowed in genres
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TOTAL_PAGES_DESC = " " + PREFIX_TOTAL_PAGES + "cnw"; // "alphabets not allowed"
    public static final String INVALID_BOOKMARK_DESC = " " + PREFIX_BOOKMARK + "-12"; // "negative numbers not allowed"
    public static final String INVALID_NOTE_TITLE = " " + PREFIX_NOTE_TITLE + " "; // ' ' not allowed at start of text
    public static final String INVALID_NOTE_TEXT = " " + PREFIX_NOTE_TEXT + " "; // ' ' not allowed at start of text

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditBookDescriptor DESC_1984;
    public static final EditCommand.EditBookDescriptor DESC_JANE_EYRE;

    static {
        DESC_1984 = new EditBookDescriptorBuilder().withName(VALID_NAME_1984)
                .withGenre(VALID_GENRE_1984)
                .withTags(VALID_TAG_BAD)
                .withTotalPages(VALID_TOTAL_PAGES_1984)
                .withBookmark(VALID_BOOKMARK_1984).build();
        DESC_JANE_EYRE = new EditBookDescriptorBuilder().withName(VALID_NAME_JANE_EYRE)
                .withGenre(VALID_GENRE_JANE_EYRE)
                .withTags(VALID_TAG_GOOD, VALID_TAG_BAD)
                .withTotalPages(VALID_TOTAL_PAGES_JANE_EYRE)
                .withBookmark(VALID_BOOKMARK_JANE_EYRE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the library, filtered book list and selected book in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        Library expectedLibrary = new Library(actualModel.getLibrary());
        List<Book> expectedFilteredList = new ArrayList<>(actualModel.getFilteredBookList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedLibrary, actualModel.getLibrary());
        assertEquals(expectedFilteredList, actualModel.getFilteredBookList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the book at the given {@code targetIndex} in the
     * {@code model}'s library.
     */
    public static void showBookAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBookList().size());

        Book book = model.getFilteredBookList().get(targetIndex.getZeroBased());
        final String[] splitName = book.getName().fullName.split("\\s+");
        model.updateFilteredBookList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredBookList().size());
    }

}
