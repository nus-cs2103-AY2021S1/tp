package seedu.address.logic.parser.bidder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.TAG_DESC_BIDDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.bidder.TypicalBidder.AMY;
import static seedu.address.testutil.bidder.TypicalBidder.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.biddercommands.AddBidderCommand;
import seedu.address.logic.parser.bidderparser.AddBidderCommandParser;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.bidder.BidderBuilder;

public class AddBidderCommandParserTest {

    private AddBidderCommandParser parser = new AddBidderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Bidder expectedBidder = new BidderBuilder(BOB).withTags(VALID_TAG_FRIEND).build().setBidderTag();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + TAG_DESC_BIDDER
                + TAG_DESC_FRIEND, new AddBidderCommand(expectedBidder));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + TAG_DESC_BIDDER
                + TAG_DESC_FRIEND, new AddBidderCommand(expectedBidder));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + TAG_DESC_BIDDER
                + TAG_DESC_FRIEND, new AddBidderCommand(expectedBidder));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TAG_DESC_BIDDER + TAG_DESC_BIDDER
                + TAG_DESC_FRIEND, new AddBidderCommand(expectedBidder));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TAG_DESC_BIDDER
                + TAG_DESC_FRIEND, new AddBidderCommand(expectedBidder));

        // multiple tags - all accepted
        Bidder expectedBidderMultipleTags = new BidderBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build().setBidderTag();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + TAG_DESC_BIDDER
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddBidderCommand(expectedBidderMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Bidder expectedBidder = new BidderBuilder(AMY).withTags().build().setBidderTag();
        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY,
                new AddBidderCommand(expectedBidder));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBidderCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB
                        + PHONE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBidderCommand.MESSAGE_USAGE));
    }
}
