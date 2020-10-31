package seedu.address.logic.parser.bids;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.BIDDER_ID_DESC_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.BID_AMOUNT_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.BID_AMOUNT_DESC_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_BIDDER_ID_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_BIDDER_ID_DESC_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_BID_AMOUNT_DESC_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.INVALID_PROPERTY_ID_DESC_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.PROPERTY_ID_DESC_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.PROPERTY_ID_DESC_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BIDDER_ID_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BIDDER_ID_BID_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BID_AMOUNT_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BID_AMOUNT_BID_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_PROPERTY_ID_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_PROPERTY_ID_BID_B;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BID;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BID;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_BID;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.bidcommands.EditBidCommand;
import seedu.address.logic.parser.bidparser.EditBidCommandParser;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.PropertyId;
import seedu.address.model.price.Price;
import seedu.address.testutil.bids.EditBidDescriptorBuilder;

public class EditBidCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBidCommand.MESSAGE_USAGE);

    private EditBidCommandParser parser = new EditBidCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_PROPERTY_ID_BID_A, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditBidCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_PROPERTY_ID_BID_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_PROPERTY_ID_BID_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_PROPERTY_ID_DESC_B,
                PropertyId.MESSAGE_CONSTRAINTS); // invalid propertyId
        assertParseFailure(parser, "1" + INVALID_BIDDER_ID_DESC_B,
                BidderId.MESSAGE_CONSTRAINTS); // invalid bidderId
        assertParseFailure(parser, "1" + INVALID_BID_AMOUNT_DESC_B,
                Price.MESSAGE_CONSTRAINTS); // invalid bidAmount

        // invalid propertyId followed by valid bidderId
        assertParseFailure(parser, "1" + INVALID_PROPERTY_ID_DESC_B + BIDDER_ID_DESC_B,
                PropertyId.MESSAGE_CONSTRAINTS);

        // valid bidderId followed by invalid bidderId. The test case for invalid bidderId followed by valid bidderId
        assertParseFailure(parser, "1" + BIDDER_ID_DESC_B + INVALID_BIDDER_ID_DESC_B, BidderId.MESSAGE_CONSTRAINTS);

        // valid bidAmount followed by invalid bidAmount. The test case for invalid bidAmount then valid bidAmount
        assertParseFailure(parser, "1" + BID_AMOUNT_DESC_B + INVALID_BID_AMOUNT_DESC_B, Price.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_PROPERTY_ID_DESC_B
                        + INVALID_BIDDER_ID_DESC_B + INVALID_BID_AMOUNT_DESC_B,
                PropertyId.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_BID;
        String userInput = targetIndex.getOneBased() + PROPERTY_ID_DESC_B + BIDDER_ID_DESC_A
                + BID_AMOUNT_DESC_B;

        EditBidCommand.EditBidDescriptor descriptor = new EditBidDescriptorBuilder()
                .withPropertyId(VALID_PROPERTY_ID_BID_B)
                .withBidderId(VALID_BIDDER_ID_BID_A).withBidAmount(VALID_BID_AMOUNT_BID_B).build();
        EditBidCommand expectedCommand = new EditBidCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_BID;
        String userInput = targetIndex.getOneBased() + BIDDER_ID_DESC_B;

        EditBidCommand.EditBidDescriptor descriptor = new EditBidDescriptorBuilder().withBidderId(VALID_BIDDER_ID_BID_B)
                .build();
        EditBidCommand expectedCommand = new EditBidCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // propertyId
        Index targetIndex = INDEX_THIRD_BID;
        String userInput = targetIndex.getOneBased() + PROPERTY_ID_DESC_A;
        EditBidCommand.EditBidDescriptor descriptor = new EditBidDescriptorBuilder()
                .withPropertyId(VALID_PROPERTY_ID_BID_A).build();
        EditBidCommand expectedCommand = new EditBidCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // bidderId
        userInput = targetIndex.getOneBased() + BIDDER_ID_DESC_A;
        descriptor = new EditBidDescriptorBuilder().withBidderId(VALID_BIDDER_ID_BID_A).build();
        expectedCommand = new EditBidCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // bidAmount
        userInput = targetIndex.getOneBased() + BID_AMOUNT_DESC_A;
        descriptor = new EditBidDescriptorBuilder().withBidAmount(VALID_BID_AMOUNT_BID_A).build();
        expectedCommand = new EditBidCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_BID;
        String userInput = targetIndex.getOneBased() + PROPERTY_ID_DESC_A + BIDDER_ID_DESC_A + BID_AMOUNT_DESC_B
                + PROPERTY_ID_DESC_B + BIDDER_ID_DESC_B + BID_AMOUNT_DESC_B;

        EditBidCommand.EditBidDescriptor descriptor = new EditBidDescriptorBuilder()
                .withPropertyId(VALID_PROPERTY_ID_BID_B).withBidderId(VALID_BIDDER_ID_BID_B)
                .withBidAmount(VALID_BID_AMOUNT_BID_B).build();
        EditBidCommand expectedCommand = new EditBidCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_BID;
        String userInput = targetIndex.getOneBased() + INVALID_BIDDER_ID_DESC_A + BIDDER_ID_DESC_B;
        EditBidCommand.EditBidDescriptor descriptor = new EditBidDescriptorBuilder()
                .withBidderId(VALID_BIDDER_ID_BID_B).build();
        EditBidCommand expectedCommand = new EditBidCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PROPERTY_ID_DESC_A + PROPERTY_ID_DESC_A;
        descriptor = new EditBidDescriptorBuilder().withPropertyId(VALID_PROPERTY_ID_BID_A).build();
        expectedCommand = new EditBidCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
