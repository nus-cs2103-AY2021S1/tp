package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.stream.Stream;

import nustorage.logic.commands.AddCommand;
import nustorage.logic.commands.AddInventoryCommand;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.InventoryRecord;

/**
 * Parses input arguments and creates a new AddInventory object
 */
public class AddInventoryCommandParser implements Parser<AddInventoryCommand> {

    /**
     * Parses Inventory commands arguments from the user.
     * @param args User arguments.
     * @return An AddInventoryCommand.
     * @throws ParseException When user arguments results in a parsing error.
     */
    public AddInventoryCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY, PREFIX_ITEM_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUANTITY, PREFIX_ITEM_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        String itemDescription = ParserUtil.parseItemDescription(argMultimap.getValue(PREFIX_ITEM_DESCRIPTION).get());
        int quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());

        InventoryRecord record = new InventoryRecord(itemDescription, quantity);

        return new AddInventoryCommand(record);
    }

    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
