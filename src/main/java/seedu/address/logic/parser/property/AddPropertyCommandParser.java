package seedu.address.logic.parser.property;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_IS_RENTAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_SELLER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_TYPE;

import java.util.stream.Stream;

import seedu.address.logic.commands.property.AddPropertyCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.id.IdParserUtil;
import seedu.address.model.id.SellerId;
import seedu.address.model.price.Price;
import seedu.address.model.property.Address;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;
import seedu.address.model.property.PropertyType;

/**
 * Parses input arguments and creates a new AddPropertyCommand object
 */
public class AddPropertyCommandParser implements Parser<AddPropertyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddPropertyCommand
     * and returns an AddPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddPropertyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PROPERTY_NAME, PREFIX_PROPERTY_ADDRESS,
                PREFIX_PROPERTY_SELLER_ID, PREFIX_PROPERTY_ASKING_PRICE, PREFIX_PROPERTY_TYPE,
                PREFIX_PROPERTY_IS_RENTAL);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROPERTY_NAME, PREFIX_PROPERTY_ADDRESS,
                PREFIX_PROPERTY_SELLER_ID, PREFIX_PROPERTY_ASKING_PRICE, PREFIX_PROPERTY_TYPE,
                PREFIX_PROPERTY_IS_RENTAL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPropertyCommand.MESSAGE_USAGE));
        }

        PropertyName propertyName = PropertyParserUtil.parsePropertyName(argMultimap.getValue(PREFIX_PROPERTY_NAME)
                .get());
        Address address = PropertyParserUtil.parseAddress(argMultimap.getValue(PREFIX_PROPERTY_ADDRESS)
                .get());
        SellerId sellerId = IdParserUtil.parseSellerId(argMultimap.getValue(PREFIX_PROPERTY_SELLER_ID)
                .get());
        Price askingPrice = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PROPERTY_ASKING_PRICE)
                .get());
        PropertyType propertyType = PropertyParserUtil.parsePropertyType(argMultimap.getValue(PREFIX_PROPERTY_TYPE)
                .get());
        IsRental isRental = PropertyParserUtil.parseIsRental(argMultimap.getValue(PREFIX_PROPERTY_IS_RENTAL)
                .get());

        Property property = new Property(propertyName, sellerId, address, askingPrice, propertyType,
                isRental, new IsClosedDeal("Active"));
        return new AddPropertyCommand(property);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
