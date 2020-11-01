package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_TOO_MANY_ARGUMENTS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.SwitchVendorCommand;
import seedu.address.logic.commands.VendorCommand;

public class VendorCommandParserTest {

    private VendorCommandParser parser = new VendorCommandParser();

    @Test
    public void parse_allFieldsSpecified_success() {
        assertParseSuccess(parser, "1", new SwitchVendorCommand(Index.fromOneBased(1)));
        assertParseSuccess(parser, "2", new SwitchVendorCommand(Index.fromOneBased(2)));
    }

    @Test
    public void parse_invalidValues_failure() {
        //TODO: Refactor to VendorCommandTest
        //        // empty String
        //        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        //                String.format(MESSAGE_INSUFFICENT_ARGUMENTS, VendorCommand.COMMAND_WORD, 1,
        //                        VendorCommand.MESSAGE_USAGE)));

        // More than 2 arguments
        assertParseFailure(parser, "1 2 3", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                String.format(MESSAGE_TOO_MANY_ARGUMENTS,
                VendorCommand.COMMAND_WORD, 1, SwitchVendorCommand.MESSAGE_USAGE)));

        // Index passed is not a non-zero integer
        assertParseFailure(parser, "1.4", String.format(MESSAGE_INVALID_INDEX, "Vendor Index"));
    }
}
