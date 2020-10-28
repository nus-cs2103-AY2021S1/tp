package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_COST;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.Optional;
import java.util.stream.Stream;

import nustorage.logic.commands.AddInventoryRecordCommand;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

/**
 * Parses input arguments and creates a new AddInventory object
 */
public class AddInventoryRecordCommandParser implements Parser<AddInventoryRecordCommand> {

    /**
     * Parses Inventory commands arguments from the user.
     * @param args User arguments.
     * @return An AddInventoryCommand.
     * @throws ParseException When user arguments results in a parsing error.
     */
    public AddInventoryRecordCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY, PREFIX_ITEM_DESCRIPTION, PREFIX_ITEM_COST);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUANTITY, PREFIX_ITEM_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddInventoryRecordCommand.MESSAGE_USAGE));
        }

        String itemDescription = ParserUtil.parseItemDescription(argMultimap.getValue(PREFIX_ITEM_DESCRIPTION).get());
        int quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());

        InventoryRecord inventoryRecord = new InventoryRecord(itemDescription, quantity);

        if (argMultimap.getValue(PREFIX_ITEM_COST).isPresent()) {
            double cost = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_ITEM_COST).get());
            FinanceRecord financeRecord = new FinanceRecord(cost * quantity, true);
            return new AddInventoryRecordCommand(inventoryRecord, Optional.of(financeRecord));
        }

        return new AddInventoryRecordCommand(inventoryRecord, Optional.empty());
    }

    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
