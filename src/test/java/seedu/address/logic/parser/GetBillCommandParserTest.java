package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetBillCommand;

public class GetBillCommandParserTest {
    private GetBillCommandParser parser = new GetBillCommandParser();

    @Test
    public void parse_valuesSpecified_success() {
        String userInput = BOOKING_ID_DESC_AMY;

        GetBillCommand expectedCommand = new GetBillCommand(VALID_BOOKING_ID_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_prefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetBillCommand.MESSAGE_USAGE);
        String userInput = "12";

        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingBookingIdField_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetBillCommand.MESSAGE_USAGE);
        String userInput = "";

        assertParseFailure(parser, userInput, expectedMessage);
    }

}
