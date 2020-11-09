package seedu.address.logic.parser.bids;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.BID_AMOUNT_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.BID_AMOUNT_DESC_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_BID_AMOUNT_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.PROPERTY_ID_DESC_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BIDDER_ID_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BID_AMOUNT_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_PROPERTY_ID_BID_A;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.bids.TypicalBid.BID_A;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.bidcommands.AddBidCommand;
import seedu.address.logic.parser.bidparser.AddBidCommandParser;
import seedu.address.model.bid.Bid;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.price.Price;
import seedu.address.testutil.bids.BidBuilder;

public class AddBidCommandParserTest {
    private AddBidCommandParser parser = new AddBidCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Bid expectedBid = new BidBuilder(BID_A).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A
                + BID_AMOUNT_DESC_A, new AddBidCommand(expectedBid));

        // multiple property ids - last propertyId accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + PROPERTY_ID_DESC_B + PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A
                + BID_AMOUNT_DESC_A, new AddBidCommand(expectedBid));

        // multiple bid amounts - last bidAmount accepted
        assertParseSuccess(parser, PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A
                + BID_AMOUNT_DESC_B + BID_AMOUNT_DESC_A, new AddBidCommand(expectedBid));

    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBidCommand.MESSAGE_USAGE);

        // missing propertyId prefix
        assertParseFailure(parser, VALID_PROPERTY_ID_BID_A + BIDDER_ID_DESC_A,
                expectedMessage);

        // missing client prefix
        assertParseFailure(parser, PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_PROPERTY_ID_BID_A + VALID_BIDDER_ID_BID_A + VALID_BID_AMOUNT_BID_A,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid propertyId
        assertParseFailure(parser, INVALID_PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A
                 + BID_AMOUNT_DESC_A, PropertyId.MESSAGE_CONSTRAINTS);

        // invalid bidderId
        assertParseFailure(parser, PROPERTY_ID_DESC_A + INVALID_BIDDER_ID_DESC_A
                + BID_AMOUNT_DESC_A, BidderId.MESSAGE_CONSTRAINTS);

        // invalid bidAmount
        assertParseFailure(parser, PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A
                + INVALID_BID_AMOUNT_DESC_A, Price.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_PROPERTY_ID_DESC_A + INVALID_BIDDER_ID_DESC_A
                        + BID_AMOUNT_DESC_A,
                PropertyId.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A
                        + BID_AMOUNT_DESC_A,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBidCommand.MESSAGE_USAGE));


    }
}

