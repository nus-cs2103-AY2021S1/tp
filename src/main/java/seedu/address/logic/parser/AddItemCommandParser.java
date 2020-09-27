package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemPrecursor;
import seedu.address.model.item.Quantity;


/**
 * Parses input arguments and creates a new AddItemCommand object
 */
public class AddItemCommandParser implements Parser<AddItemCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddItemCommand parse(String args) throws ParseException, IOException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY,
                        PREFIX_ITEM_DESCRIPTION, PREFIX_ITEM_LOCATION);

        if (!arePrefixesPresent(argMultimap, PREFIX_ITEM_NAME, PREFIX_ITEM_QUANTITY,
                PREFIX_ITEM_DESCRIPTION, PREFIX_ITEM_LOCATION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
        }
        String name = ItemParserUtil.parseName(argMultimap.getValue(PREFIX_ITEM_NAME).get());
        Quantity quantity = ItemParserUtil.parseQuantity(argMultimap.getValue(PREFIX_ITEM_QUANTITY).get());
        String description = ItemParserUtil.parseDescription(argMultimap.getValue(PREFIX_ITEM_DESCRIPTION).get());
        Set<String> locationList = ItemParserUtil.parseLocations(argMultimap.getAllValues(PREFIX_ITEM_LOCATION));

        ItemPrecursor itemPrecursor = new ItemPrecursor(Item.getIdCounter() + 1, name, quantity, description,
                locationList, new HashSet<>());

        return new AddItemCommand(itemPrecursor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
