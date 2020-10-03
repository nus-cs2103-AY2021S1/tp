package seedu.stock.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIALNUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import seedu.stock.logic.commands.UpdateCommand;
import seedu.stock.logic.commands.UpdateCommand.UpdateStockDescriptor;
import seedu.stock.logic.parser.exceptions.ParseException;

public class UpdateCommandParser implements Parser<UpdateCommand> {

    @Override
    public UpdateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_SERIALNUMBER, PREFIX_QUANTITY, PREFIX_NEW_QUANTITY,
                        PREFIX_NAME, PREFIX_SOURCE, PREFIX_LOCATION
                );

        // If serial number is not provided
        if (!argMultimap.getValue(PREFIX_SERIALNUMBER).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateCommand.MESSAGE_USAGE));
        }

        // TODO: wait for find command, implement logic to obtain the stock with desired serial number

        UpdateStockDescriptor updateStockDescriptor = new UpdateStockDescriptor();

        // Update name with new name provided
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            updateStockDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        // Update source with new source provided
        if (argMultimap.getValue(PREFIX_SOURCE).isPresent()) {
            updateStockDescriptor.setSource(ParserUtil.parseSource(argMultimap.getValue(PREFIX_SOURCE).get()));
        }

        // Update quantity with new quantity provided
        if (argMultimap.getValue(PREFIX_NEW_QUANTITY).isPresent()) {
            updateStockDescriptor.setQuantity(
                    ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_NEW_QUANTITY).get()));
        }

        // Increment quantity with increment value provided
        if (argMultimap.getValue(PREFIX_QUANTITY).isPresent()
                && !argMultimap.getValue(PREFIX_NEW_QUANTITY).isPresent()) {
            updateStockDescriptor.setQuantity(
                    ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()));
            updateStockDescriptor.setIsIncrement(true);
        }

        // Update location with new location provided
        if (argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            updateStockDescriptor.setLocation(ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()));
        }

        if (!updateStockDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UpdateCommand.MESSAGE_NOT_UPDATED);
        }

        return new UpdateCommand(updateStockDescriptor);
    }
}
