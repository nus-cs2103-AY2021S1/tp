package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LIST_TYPE;

import seedu.stock.logic.commands.ListAllCommand;
import seedu.stock.logic.commands.ListBookmarkCommand;
import seedu.stock.logic.commands.ListCommand;
import seedu.stock.logic.commands.ListLowStocksCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    private static final Prefix[] allPossiblePrefixes = CliSyntax.getAllPossiblePrefixesAsArray();
    private static final Prefix[] validPrefixesForList = { PREFIX_LIST_TYPE };

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedListType = args.trim();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, allPossiblePrefixes);

        // Checks if the args start with "lt/"
        if (!trimmedListType.startsWith("lt/")
                || ParserUtil.isInvalidPrefixPresent(argMultimap, validPrefixesForList)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        String listType = trimmedListType.substring(3);

        switch (listType) {

        case ListAllCommand.LIST_WORD:
            return new ListAllCommand();

        case ListBookmarkCommand.LIST_WORD:
            return new ListBookmarkCommand();

        case ListLowStocksCommand.LIST_WORD:
            return new ListLowStocksCommand();

        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

    }

    public boolean hasNoArguments(String args) {
        return args.length() == 0;
    }
}
