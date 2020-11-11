package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_GENRE;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_NOT_COMPLETED;
import static seedu.bookmark.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.function.Predicate;

import seedu.bookmark.logic.commands.FindCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;
import seedu.bookmark.model.book.Book;
import seedu.bookmark.model.book.predicates.BookCompletedPredicate;
import seedu.bookmark.model.book.predicates.BookNotCompletedPredicate;
import seedu.bookmark.model.book.predicates.GenreContainsKeywordsPredicate;
import seedu.bookmark.model.book.predicates.NameContainsKeywordsPredicate;
import seedu.bookmark.model.book.predicates.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    private Prefix inputPrefix;

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        Predicate<Book> predicate;
        int prefixCount = 0;

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_NAME, PREFIX_GENRE, PREFIX_COMPLETED, PREFIX_NOT_COMPLETED, PREFIX_TAG);

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_NAME;
        }
        if (argMultimap.getValue(PREFIX_GENRE).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_GENRE;
        }
        if (argMultimap.getValue(PREFIX_COMPLETED).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_COMPLETED;
        }
        if (argMultimap.getValue(PREFIX_NOT_COMPLETED).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_NOT_COMPLETED;
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            prefixCount += 1;
            inputPrefix = PREFIX_TAG;
        }
        if (prefixCount != 1) { //if more than/ less than 1 input prefix, we throw an error.
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String trimmedArgs = argMultimap.getValue(inputPrefix).get().trim();
        if (trimmedArgs.isEmpty() && inputPrefix != PREFIX_COMPLETED && inputPrefix != PREFIX_NOT_COMPLETED) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.isEmpty() && (inputPrefix == PREFIX_COMPLETED || inputPrefix == PREFIX_NOT_COMPLETED)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        predicate = predicateGenerator(inputPrefix, keywords);

        return new FindCommand(predicate, inputPrefix, keywords);
    }

    /**
     * Returns a predicate based on the input prefix
     * @return Predicate based on input prefix
     */
    public Predicate<Book> predicateGenerator(Prefix inputPrefix, String[] keywords) {
        Predicate<Book> predicate = null;
        if (inputPrefix == PREFIX_NAME) {
            predicate = new NameContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (inputPrefix == PREFIX_GENRE) {
            predicate = new GenreContainsKeywordsPredicate(Arrays.asList(keywords));
        } else if (inputPrefix == PREFIX_COMPLETED) {
            predicate = new BookCompletedPredicate();
        } else if (inputPrefix == PREFIX_NOT_COMPLETED) {
            predicate = new BookNotCompletedPredicate();
        } else if (inputPrefix == PREFIX_TAG) {
            predicate = new TagContainsKeywordsPredicate(Arrays.asList(keywords));
        }
        return predicate;
    }
}
