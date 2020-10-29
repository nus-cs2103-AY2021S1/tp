package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;

import java.util.stream.Stream;

import seedu.address.logic.commands.CheckCraftCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Quantity;

/**
 * Parses input arguments and creates a new CheckCraftCommand object
 */
public class CheckCraftCommandParser implements Parser<CheckCraftCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CheckCraftCommand
     * and returns a CheckCraftCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public CheckCraftCommand parse(String args) throws ParseException {
        String itemName;
        Quantity quantity;
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY);
            if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        CheckCraftCommand.MESSAGE_USAGE));
            }
            itemName = ItemParserUtil.parseName(argMultimap.getValue(PREFIX_ITEM_NAME).get());
            quantity = ItemParserUtil.parseQuantity(argMultimap.getValue(PREFIX_ITEM_QUANTITY).get());
            return new CheckCraftCommand(itemName, quantity);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckCraftCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
