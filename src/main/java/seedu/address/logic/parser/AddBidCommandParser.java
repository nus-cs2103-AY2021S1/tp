package seedu.address.logic.parser;

import seedu.address.logic.commands.AddBidCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.bid.Bid;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddBidCommandParser implements Parser<AddBidCommand> {

    public AddBidCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PROPERTY_ID, PREFIX_CLIENT, PREFIX_MONEY);

        if (!arePrefixesPresent(argMultimap, PREFIX_PROPERTY_ID, PREFIX_CLIENT, PREFIX_MONEY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBidCommand.MESSAGE_USAGE));
        }

        String propertyId = ParserUtil.parsePropertyId(argMultimap.getValue(PREFIX_PROPERTY_ID).get());
        String bidderId = ParserUtil.parseBidderId(argMultimap.getValue(PREFIX_CLIENT).get());
        double bidAmount = ParserUtil.parseBidAmount(argMultimap.getValue(PREFIX_MONEY).get());

        Bid bid = new Bid(propertyId, bidderId, bidAmount);

        return new AddBidCommand(bid);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
