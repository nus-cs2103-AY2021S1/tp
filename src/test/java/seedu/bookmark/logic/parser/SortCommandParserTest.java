package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_READING_PROGRESS;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.commands.SortCommand;
import seedu.bookmark.model.book.comparators.BookGenreComparator;
import seedu.bookmark.model.book.comparators.BookNameComparator;
import seedu.bookmark.model.book.comparators.BookPagesReadComparator;
import seedu.bookmark.model.book.comparators.BookReadingProgressComparator;

public class SortCommandParserTest {

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNamePrefixArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(new BookNameComparator(), PREFIX_NAME);
        assertParseSuccess(parser, " " + PREFIX_NAME, expectedSortCommand);

        // multiple whitespaces in command
        assertParseSuccess(parser, " " + PREFIX_NAME + "           ", expectedSortCommand);
    }

    @Test
    public void parse_validGenrePrefixArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(new BookGenreComparator(), PREFIX_GENRE);
        assertParseSuccess(parser, " " + PREFIX_GENRE, expectedSortCommand);

        // multiple whitespaces in command
        assertParseSuccess(parser, " " + PREFIX_GENRE + "      ", expectedSortCommand);
    }

    @Test
    public void parse_validBookmarkPrefixArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(new BookPagesReadComparator(), PREFIX_BOOKMARK);
        assertParseSuccess(parser, " " + PREFIX_BOOKMARK, expectedSortCommand);

        // multiple whitespaces in command
        assertParseSuccess(parser, " " + PREFIX_BOOKMARK + "             ", expectedSortCommand);
    }

    @Test
    public void parse_validReadingProgressPrefixArgs_returnsSortCommand() {
        // no leading and trailing whitespaces
        SortCommand expectedSortCommand =
                new SortCommand(new BookReadingProgressComparator(), PREFIX_READING_PROGRESS);
        assertParseSuccess(parser, " " + PREFIX_READING_PROGRESS, expectedSortCommand);

        // multiple whitespaces in command
        assertParseSuccess(parser, " " + PREFIX_READING_PROGRESS + "             ", expectedSortCommand);
    }
}
