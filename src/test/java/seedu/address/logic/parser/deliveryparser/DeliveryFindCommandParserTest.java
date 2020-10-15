package seedu.address.logic.parser.deliveryparser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.deliverycommand.DeliveryFindCommand;
import seedu.address.logic.commands.itemcommand.ItemFindCommand;
import seedu.address.logic.parser.itemparser.ItemFindCommandParser;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

class DeliveryFindCommandParserTest {

    private DeliveryFindCommandParser parser = new DeliveryFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ItemFindCommand.MESSAGE_USAGE));
    }

    @Test
    void parse() {
    }
}