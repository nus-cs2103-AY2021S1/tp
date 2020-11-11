package seedu.bookmark.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.bookmark.commons.core.Messages.MESSAGE_BOOKS_SORTED;
import static seedu.bookmark.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_READING_PROGRESS;
import static seedu.bookmark.testutil.TypicalBooks.CRIME_AND_PUNISHMENT;
import static seedu.bookmark.testutil.TypicalBooks.ENDERS_GAME;
import static seedu.bookmark.testutil.TypicalBooks.HAMLET;
import static seedu.bookmark.testutil.TypicalBooks.HARRY_POTTER;
import static seedu.bookmark.testutil.TypicalBooks.LORD_OF_THE_FLIES;
import static seedu.bookmark.testutil.TypicalBooks.ON_THE_ROAD;
import static seedu.bookmark.testutil.TypicalBooks.THE_HUNGER_GAMES;
import static seedu.bookmark.testutil.TypicalBooks.TO_KILL_A_MOCKINGBIRD;
import static seedu.bookmark.testutil.TypicalBooks.getTypicalLibrary;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.bookmark.model.Model;
import seedu.bookmark.model.ModelManager;
import seedu.bookmark.model.UserPrefs;
import seedu.bookmark.model.book.comparators.BookGenreComparator;
import seedu.bookmark.model.book.comparators.BookNameComparator;
import seedu.bookmark.model.book.comparators.BookPagesReadComparator;
import seedu.bookmark.model.book.comparators.BookReadingProgressComparator;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalLibrary(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalLibrary(), new UserPrefs());

    @Test
    public void execute_sortByName_success() {
        BookNameComparator comparator = prepareBookNameComparator();
        String expectedMessage = String.format(MESSAGE_BOOKS_SORTED + comparator.toString());
        SortCommand command = new SortCommand(comparator, PREFIX_NAME);
        expectedModel.sortFilteredBookList(comparator);
        expectedModel.setSortingPreference(PREFIX_NAME.toString());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CRIME_AND_PUNISHMENT, ENDERS_GAME, HARRY_POTTER, LORD_OF_THE_FLIES,
                ON_THE_ROAD, THE_HUNGER_GAMES, TO_KILL_A_MOCKINGBIRD), model.getFilteredBookList());
    }

    @Test
    public void execute_sortByGenre_success() {
        BookGenreComparator comparator = prepareBookGenreComparator();
        String expectedMessage = String.format(MESSAGE_BOOKS_SORTED + comparator.toString());
        SortCommand command = new SortCommand(comparator, PREFIX_GENRE);
        expectedModel.sortFilteredBookList(comparator);
        expectedModel.setSortingPreference(PREFIX_GENRE.toString());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CRIME_AND_PUNISHMENT, HARRY_POTTER, TO_KILL_A_MOCKINGBIRD,
                THE_HUNGER_GAMES, LORD_OF_THE_FLIES, ENDERS_GAME, ON_THE_ROAD), model.getFilteredBookList());
    }

    @Test
    public void execute_sortByPagesRead_success() {
        BookPagesReadComparator comparator = prepareBookPagesReadComparator();
        String expectedMessage = String.format(MESSAGE_BOOKS_SORTED + comparator.toString());
        SortCommand command = new SortCommand(comparator, PREFIX_BOOKMARK);
        expectedModel.sortFilteredBookList(comparator);
        expectedModel.setSortingPreference(PREFIX_BOOKMARK.toString());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(THE_HUNGER_GAMES, ENDERS_GAME, ON_THE_ROAD, LORD_OF_THE_FLIES,
                TO_KILL_A_MOCKINGBIRD, HARRY_POTTER, CRIME_AND_PUNISHMENT), model.getFilteredBookList());
    }

    @Test
    public void execute_sortByReadingProgress_success() {
        BookReadingProgressComparator comparator = prepareBookReadingProgressComparator();
        String expectedMessage = String.format(MESSAGE_BOOKS_SORTED + comparator.toString());
        SortCommand command = new SortCommand(comparator, PREFIX_READING_PROGRESS);
        expectedModel.sortFilteredBookList(comparator);
        expectedModel.setSortingPreference(PREFIX_READING_PROGRESS.toString());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(THE_HUNGER_GAMES, TO_KILL_A_MOCKINGBIRD, ENDERS_GAME, LORD_OF_THE_FLIES,
                ON_THE_ROAD, HARRY_POTTER, CRIME_AND_PUNISHMENT), model.getFilteredBookList());
    }

    @Test
    public void execute_sortByNameThenAddBook_success() {
        BookNameComparator comparator = prepareBookNameComparator();
        String expectedMessage = String.format(MESSAGE_BOOKS_SORTED + comparator.toString());
        SortCommand command = new SortCommand(comparator, PREFIX_NAME);
        expectedModel.sortFilteredBookList(comparator);
        expectedModel.setSortingPreference(PREFIX_NAME.toString());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        expectedModel.addBook(HAMLET);
        assertEquals(Arrays.asList(CRIME_AND_PUNISHMENT, ENDERS_GAME, HAMLET, HARRY_POTTER, LORD_OF_THE_FLIES,
                ON_THE_ROAD, THE_HUNGER_GAMES, TO_KILL_A_MOCKINGBIRD), expectedModel.getFilteredBookList());
    }

    @Test
    public void execute_sortByGenreThenAddBook_success() {
        BookGenreComparator comparator = prepareBookGenreComparator();
        String expectedMessage = String.format(MESSAGE_BOOKS_SORTED + comparator.toString());
        SortCommand command = new SortCommand(comparator, PREFIX_GENRE);
        expectedModel.sortFilteredBookList(comparator);
        expectedModel.setSortingPreference(PREFIX_GENRE.toString());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        expectedModel.addBook(HAMLET);
        assertEquals(Arrays.asList(HAMLET, CRIME_AND_PUNISHMENT, HARRY_POTTER, TO_KILL_A_MOCKINGBIRD,
                THE_HUNGER_GAMES, LORD_OF_THE_FLIES, ENDERS_GAME, ON_THE_ROAD), expectedModel.getFilteredBookList());
    }

    @Test
    public void execute_sortByPagesReadThenAddBook_success() {
        BookPagesReadComparator comparator = prepareBookPagesReadComparator();
        String expectedMessage = String.format(MESSAGE_BOOKS_SORTED + comparator.toString());
        SortCommand command = new SortCommand(comparator, PREFIX_BOOKMARK);
        expectedModel.sortFilteredBookList(comparator);
        expectedModel.setSortingPreference(PREFIX_BOOKMARK.toString());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        expectedModel.addBook(HAMLET);
        assertEquals(Arrays.asList(HAMLET, THE_HUNGER_GAMES, ENDERS_GAME, ON_THE_ROAD, LORD_OF_THE_FLIES,
                TO_KILL_A_MOCKINGBIRD, HARRY_POTTER, CRIME_AND_PUNISHMENT), expectedModel.getFilteredBookList());
    }

    @Test
    public void execute_sortByReadingProgressThenAddBook_success() {
        BookReadingProgressComparator comparator = prepareBookReadingProgressComparator();
        String expectedMessage = String.format(MESSAGE_BOOKS_SORTED + comparator.toString());
        SortCommand command = new SortCommand(comparator, PREFIX_READING_PROGRESS);
        expectedModel.sortFilteredBookList(comparator);
        expectedModel.setSortingPreference(PREFIX_READING_PROGRESS.toString());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        expectedModel.addBook(HAMLET);
        assertEquals(Arrays.asList(HAMLET, THE_HUNGER_GAMES, TO_KILL_A_MOCKINGBIRD, ENDERS_GAME, LORD_OF_THE_FLIES,
                ON_THE_ROAD, HARRY_POTTER, CRIME_AND_PUNISHMENT), expectedModel.getFilteredBookList());
    }

    /**
     * Parses {@code userInput} into a {@code BookNameComparator}.
     */
    private BookNameComparator prepareBookNameComparator() {
        return new BookNameComparator();
    }

    /**
     * Parses {@code userInput} into a {@code BookGenreComparator}.
     */
    private BookGenreComparator prepareBookGenreComparator() {
        return new BookGenreComparator();
    }

    /**
     * Parses {@code userInput} into a {@code BookPagesReadComparator}.
     */
    private BookPagesReadComparator prepareBookPagesReadComparator() {
        return new BookPagesReadComparator();
    }

    /**
     * Parses {@code userInput} into a {@code BookReadingProgressComparator}.
     */
    private BookReadingProgressComparator prepareBookReadingProgressComparator() {
        return new BookReadingProgressComparator();
    }
}
