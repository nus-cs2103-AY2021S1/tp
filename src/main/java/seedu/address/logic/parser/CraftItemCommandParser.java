package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_ID;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CraftItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Quantity;

/**
 * Parses input arguments and creates a new CraftItemCommand object
 */
public class CraftItemCommandParser implements Parser<CraftItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CraftItemCommand
     * and returns a CraftItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public CraftItemCommand parse(String args) throws ParseException {
        String itemName;
        Quantity quantity;
        Index index;
        //TODO make index optional
        try {
            ArgumentMultimap argMultimap =
                    ArgumentTokenizer.tokenize(args, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY, PREFIX_RECIPE_ID);
            // only item name and quantity need to be present, allow for missing recipe id
            if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        CraftItemCommand.MESSAGE_USAGE));
            }
            itemName = ItemParserUtil.parseName(argMultimap.getValue(PREFIX_ITEM_NAME).get());
            quantity = ItemParserUtil.parseQuantity(argMultimap.getValue(PREFIX_ITEM_QUANTITY).get());

            if (arePrefixesPresent(argMultimap, PREFIX_RECIPE_ID)) {
                index = ItemParserUtil.parseIndex(argMultimap.getValue(PREFIX_RECIPE_ID).get());
                return new CraftItemCommand(itemName, quantity, index);
            } else {
                // craft item command will add in the default index
                return new CraftItemCommand(itemName, quantity);
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CraftItemCommand.MESSAGE_USAGE), pe);
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
