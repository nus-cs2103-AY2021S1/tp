package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CliSyntax.PREFIX_DATETIME;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_COST;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import nustorage.logic.commands.CreateInventoryRecordCommand;
import nustorage.logic.parser.exceptions.ParseException;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

/**
 * Parses input arguments and creates a new AddInventory object
 */
public class CreateInventoryRecordCommandParser implements Parser<CreateInventoryRecordCommand> {

    /**
     * Parses InventoryWindow commands arguments from the user.
     * @param args User arguments.
     * @return An AddInventoryCommand.
     * @throws ParseException When user arguments results in a parsing error.
     */
    public CreateInventoryRecordCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY, PREFIX_ITEM_DESCRIPTION,
                        PREFIX_ITEM_COST, PREFIX_DATETIME);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUANTITY, PREFIX_ITEM_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CreateInventoryRecordCommand.MESSAGE_USAGE));
        }

        String itemDescription = ParserUtil.parseItemDescription(argMultimap.getValue(PREFIX_ITEM_DESCRIPTION).get());
        int quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());

        double unitCost;
        if (argMultimap.getValue(PREFIX_ITEM_COST).isPresent()) {
            unitCost = ParserUtil.parseItemCost(argMultimap.getValue(PREFIX_ITEM_COST).get());
        } else {
            unitCost = 0;
        }
        LocalDateTime dateTime;
        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            dateTime = ParserUtil.parseDatetime(argMultimap.getValue(PREFIX_DATETIME).get());
        } else {
            dateTime = LocalDateTime.now();
        }
        FinanceRecord financeRecord = new FinanceRecord(unitCost * quantity, true);
        InventoryRecord inventoryRecord = new InventoryRecord(itemDescription, quantity, unitCost, dateTime);

        return new CreateInventoryRecordCommand(inventoryRecord, financeRecord);
    }

    private boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
