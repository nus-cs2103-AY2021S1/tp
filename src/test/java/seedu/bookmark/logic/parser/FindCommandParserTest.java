package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOT_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bookmark.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.bookmark.logic.commands.FindCommand;
import seedu.bookmark.model.book.predicates.BookCompletedPredicate;
import seedu.bookmark.model.book.predicates.BookNotCompletedPredicate;
import seedu.bookmark.model.book.predicates.GenreContainsKeywordsPredicate;
import seedu.bookmark.model.book.predicates.NameContainsKeywordsPredicate;
import seedu.bookmark.model.book.predicates.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validNameArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String[] keywords = {"Alice", "Bob"};
        List<String> keywordList = Arrays.asList(keywords);
        Prefix namePrefix = PREFIX_NAME;
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(keywordList), namePrefix, keywords);
        assertParseSuccess(parser, " " + PREFIX_NAME + " " + "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_NAME + "           " + " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

    @Test
    public void parse_validGenreArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String[] keywords = {"Fantasy", "Horror"};
        List<String> keywordList = Arrays.asList(keywords);
        Prefix genrePrefix = PREFIX_GENRE;
        FindCommand expectedFindCommand =
                new FindCommand(new GenreContainsKeywordsPredicate(keywordList), genrePrefix, keywords);
        assertParseSuccess(parser, " " + PREFIX_GENRE + " " + "Fantasy Horror", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_GENRE + "      " + " \n Fantasy \n \t Horror  \t", expectedFindCommand);
    }

    @Test
    public void parse_validTagArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String[] keywords = {"Happy", "Sad"};
        List<String> keywordList = Arrays.asList(keywords);
        Prefix tagPrefix = PREFIX_TAG;
        FindCommand expectedFindCommand =
                new FindCommand(new TagContainsKeywordsPredicate(keywordList), tagPrefix, keywords);
        assertParseSuccess(parser, " " + PREFIX_TAG + " " + "Happy Sad", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " " + PREFIX_TAG + "             " + " \n Happy \n \t Sad  \t", expectedFindCommand);
    }

    @Test
    public void parse_validBookCompletedArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String[] keywords = {""};
        List<String> keywordList = Arrays.asList(keywords);
        Prefix completedPrefix = PREFIX_COMPLETED;
        FindCommand expectedFindCommand =
                new FindCommand(new BookCompletedPredicate(), completedPrefix, keywords);
        assertParseSuccess(parser, " " + PREFIX_COMPLETED, expectedFindCommand);
    }

    @Test
    public void parse_validBookNotCompletedArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        String[] keywords = {""};
        List<String> keywordList = Arrays.asList(keywords);
        Prefix notCompletedPrefix = PREFIX_NOT_COMPLETED;
        FindCommand expectedFindCommand =
                new FindCommand(new BookNotCompletedPredicate(), notCompletedPrefix, keywords);
        assertParseSuccess(parser, " " + PREFIX_NOT_COMPLETED, expectedFindCommand);
    }
}
