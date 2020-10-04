package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLIER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ItemContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUPPLIER, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SUPPLIER, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        Prefix type = null;
        String trimmedArgs = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            trimmedArgs = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName;
            type = PREFIX_NAME;
        } else if (argMultimap.getValue(PREFIX_SUPPLIER).isPresent()) {
            trimmedArgs = ParserUtil.parseSupplier(argMultimap.getValue(PREFIX_SUPPLIER).get()).value;
            type = PREFIX_SUPPLIER;
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            trimmedArgs = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()).tagName;
            type = PREFIX_TAG;
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindCommand(new ItemContainsKeywordsPredicate(Arrays.asList(nameKeywords), type));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
