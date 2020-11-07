package seedu.address.logic.parser.bidder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.biddercommands.EditBidderCommand;
import seedu.address.logic.commands.biddercommands.EditBidderCommand.EditBidderDescriptor;
import seedu.address.logic.parser.bidderparser.EditBidderCommandParser;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.testutil.bidder.EditBidderDescriptorBuilder;

public class EditBidderCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBidderCommand.MESSAGE_USAGE);

    private EditBidderCommandParser parser = new EditBidderCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditBidderCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + NAME_DESC_AMY;

        EditBidderDescriptor descriptor = new EditBidderDescriptorBuilder().withName(VALID_NAME_AMY)
                .withDefaultIdAndTag().withPhone(VALID_PHONE_BOB).build();
        EditBidderCommand expectedCommand = new EditBidderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB;

        EditBidderDescriptor descriptor = new EditBidderDescriptorBuilder()
                .withDefaultIdAndTag().withPhone(VALID_PHONE_BOB).build();
        EditBidderCommand expectedCommand = new EditBidderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditBidderDescriptor descriptor = new EditBidderDescriptorBuilder()
                .withDefaultIdAndTag().withName(VALID_NAME_AMY).build();
        EditBidderCommand expectedCommand = new EditBidderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditBidderDescriptorBuilder()
                .withDefaultIdAndTag().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditBidderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY
                + PHONE_DESC_AMY + PHONE_DESC_BOB;

        EditBidderDescriptor descriptor = new EditBidderDescriptorBuilder()
                .withDefaultIdAndTag().withPhone(VALID_PHONE_BOB).build();
        EditBidderCommand expectedCommand = new EditBidderCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditBidderDescriptor descriptor = new EditBidderDescriptorBuilder()
                .withDefaultIdAndTag().withPhone(VALID_PHONE_BOB).build();
        EditBidderCommand expectedCommand = new EditBidderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        descriptor = new EditBidderDescriptorBuilder()
                .withDefaultIdAndTag().withPhone(VALID_PHONE_BOB).build();
        expectedCommand = new EditBidderCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
