package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddQuantityToItemCommand.MESSAGE_ITEM_NOT_PROVIDED;
import static seedu.address.logic.commands.AddQuantityToItemCommand.MESSAGE_QUANTITY_NOT_PROVIDED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;

import seedu.address.logic.commands.AddQuantityToItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddQuantityToItemCommand object
 */
public class AddQuantityToItemCommandParser implements Parser<AddQuantityToItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddQuantityToItemCommand
     * and returns an AddQuantityToItemCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public AddQuantityToItemCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY);

        String itemName;
        int quantity;

        if (argMultimap.getValue(PREFIX_ITEM_NAME).isPresent()) {
            itemName = ItemParserUtil.parseName(argMultimap.getValue(PREFIX_ITEM_NAME).get());
        } else {
            throw new ParseException(MESSAGE_ITEM_NOT_PROVIDED);
        }
        if (argMultimap.getValue(PREFIX_ITEM_QUANTITY).isPresent()) {
            quantity = ItemParserUtil.parseInt(argMultimap.getValue(PREFIX_ITEM_QUANTITY).get());
        } else {
            throw new ParseException(MESSAGE_QUANTITY_NOT_PROVIDED);
        }

        return new AddQuantityToItemCommand(itemName, quantity);
    }

}
