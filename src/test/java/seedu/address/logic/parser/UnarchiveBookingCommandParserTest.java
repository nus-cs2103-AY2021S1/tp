package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.BOOKING_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BOOKING_ID_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UnarchiveBookingCommand;


public class UnarchiveBookingCommandParserTest {
    private UnarchiveBookingCommandParser parser = new UnarchiveBookingCommandParser();

    @Test
    public void parse_valuesSpecified_success() {
        String userInput = BOOKING_ID_DESC_AMY;
        UnarchiveBookingCommand expectedCommand = new UnarchiveBookingCommand(VALID_BOOKING_ID_AMY);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingBookingIdField_failure() {
        String userInput = "";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnarchiveBookingCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
