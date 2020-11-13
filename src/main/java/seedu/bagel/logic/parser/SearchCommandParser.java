package seedu.bagel.logic.parser;

import static seedu.bagel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_KEYWORD;

import java.util.stream.Stream;

import seedu.bagel.logic.commands.SearchCommand;
import seedu.bagel.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SearchCommand object
 */
public class SearchCommandParser implements Parser<SearchCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SearchCommand
     * and returns a SearchCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_KEYWORD);

        if (!arePrefixesPresent(argMultimap, PREFIX_KEYWORD)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        String keyword = argMultimap.getValue(PREFIX_KEYWORD).get();
        if (keyword.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchCommand.MESSAGE_USAGE));
        }

        return new SearchCommand(keyword);
    }
}
