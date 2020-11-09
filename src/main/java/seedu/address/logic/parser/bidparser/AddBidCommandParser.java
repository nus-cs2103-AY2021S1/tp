package seedu.address.logic.parser.bidparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROPERTY_ID;

import java.util.stream.Stream;

import seedu.address.logic.commands.bidcommands.AddBidCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.id.IdParserUtil;
import seedu.address.model.bid.Bid;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.price.Price;

public class AddBidCommandParser implements Parser<AddBidCommand> {

    /**
     * Parses input from user to create a addBid command
     * @param args the input from the user
     * @return AddBidCommand object
     * @throws ParseException If the command is in the wrong format.
     */
    public AddBidCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROPERTY_ID, PREFIX_CLIENT, PREFIX_MONEY);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROPERTY_ID, PREFIX_CLIENT, PREFIX_MONEY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBidCommand.MESSAGE_USAGE));
        }

        PropertyId propertyId = IdParserUtil.parsePropertyId(argMultimap.getValue(PREFIX_PROPERTY_ID).get());
        BidderId bidderId = IdParserUtil.parseBidderId(argMultimap.getValue(PREFIX_CLIENT).get());
        Price bidAmount = ParserUtil.parsePrice(argMultimap.getValue(PREFIX_MONEY).get());

        Bid bid = new Bid(propertyId, bidderId, bidAmount);

        return new AddBidCommand(bid);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
