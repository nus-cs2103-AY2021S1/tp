package seedu.address.logic.parser.itemparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLIER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.itemcommand.ItemFindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.ItemContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ItemFindCommand object
 */
public class ItemFindCommandParser implements Parser<ItemFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ItemFindCommand
     * and returns a ItemFindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ItemFindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SUPPLIER, PREFIX_TAG);

        if (!onlyOnePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_SUPPLIER, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ItemFindCommand.MESSAGE_USAGE));
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

        return new ItemFindCommand(new ItemContainsKeywordsPredicate(Arrays.asList(nameKeywords), type));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean onlyOnePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        int i = 0;
        for (Prefix p: prefixes) {
            if (argumentMultimap.getValue(p).isPresent()) {
                i++;
            }
        }
        return i == 1 && Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
