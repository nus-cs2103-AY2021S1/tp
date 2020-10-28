package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;

import java.util.stream.Stream;

import seedu.stock.logic.commands.AddCommand;
import seedu.stock.logic.parser.exceptions.ParseException;
import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Stock;

/**
 * Parses input arguments and creates a new AddCommand object.
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SOURCE, PREFIX_QUANTITY, PREFIX_LOCATION,
                        PREFIX_LOW_QUANTITY);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_SOURCE, PREFIX_QUANTITY)
                || !argMultimap.getPreamble().isEmpty()) {
            System.out.println("thrown 1");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        // Checks if all the prefixes only appear once in the given command.
        if (!doesPrefixesAppearOnce(argMultimap, PREFIX_NAME, PREFIX_LOCATION, PREFIX_SOURCE, PREFIX_QUANTITY)) {
            throw new ParseException(String.format(MESSAGE_DUPLICATE_HEADER_FIELD, AddCommand.MESSAGE_USAGE));
        }

        // Checks if low quantity prefix only appear at most once in the given command.
        if (!doesLowQuantityPrefixesAppearOnce(argMultimap, PREFIX_LOW_QUANTITY)) {
            throw new ParseException(String.format(MESSAGE_DUPLICATE_HEADER_FIELD, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get().toLowerCase());
        SerialNumber serialNumber = SerialNumber.generateDefaultSerialNumber();
        Source source = ParserUtil.parseSource(argMultimap.getValue(PREFIX_SOURCE).get().toLowerCase());
        Quantity quantity = ParserUtil.parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get());
        Quantity updatedQuantity = ParserUtil.parseLowQuantity(quantity,
                argMultimap.getValue(PREFIX_LOW_QUANTITY));
        Location location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get().toLowerCase());

        Stock stock = new Stock(name, serialNumber, source, updatedQuantity, location);
        return new AddCommand(stock);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns true if all of the prefixes appears only once in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean doesPrefixesAppearOnce(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() == 1);
    }

    /**
     * Returns true if all of the low quantity prefix appears at most one time in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean doesLowQuantityPrefixesAppearOnce(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getAllValues(prefix).size() <= 1);
    }

}
