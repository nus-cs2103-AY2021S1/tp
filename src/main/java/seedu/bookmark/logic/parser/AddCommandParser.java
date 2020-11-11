package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.commons.core.Messages.MESSAGE_TOO_MANY_TAGS;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_BOOKMARK;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TOTAL_PAGES;

import java.util.Set;
import java.util.stream.Stream;

import seedu.bookmark.logic.commands.AddCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.Bookmark;
import seedu.bookmark.model.book.Genre;
import seedu.bookmark.model.book.Name;
import seedu.bookmark.model.book.TotalPages;
import seedu.bookmark.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format or bookmark > totalpages
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_GENRE, PREFIX_TAG,
                        PREFIX_TOTAL_PAGES, PREFIX_BOOKMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_GENRE, PREFIX_TOTAL_PAGES)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Genre genre = ParserUtil.parseGenre(argMultimap.getValue(PREFIX_GENRE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        TotalPages totalPages = ParserUtil.parseTotalPages(argMultimap.getValue(PREFIX_TOTAL_PAGES).get());
        Bookmark bookmark = ParserUtil.parseBookmark(argMultimap.getValue(PREFIX_BOOKMARK));

        if (!Bookmark.isValidBookmark(bookmark, totalPages)) {
            throw new ParseException(Bookmark.MESSAGE_CONSTRAINTS);
        }

        if (!Book.isValidNumTags(tagList)) {
            throw new ParseException(String.format(MESSAGE_TOO_MANY_TAGS, Book.MAX_TAG_COUNT));
        }

        Book book = new Book(name, genre, tagList, totalPages, bookmark);

        return new AddCommand(book);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
