package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.UNKNOWN_CLEAR_COMMAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;

public class ClearCommandParserTest {
    private ClearCommandParser parser = new ClearCommandParser();

    @Test
    public void parse_invalidCommand_failure() {
        assertParseFailure(parser, "random ", UNKNOWN_CLEAR_COMMAND);

        assertParseFailure(parser, " ", UNKNOWN_CLEAR_COMMAND);
    }

    @Test
    public void parse_validInput_success() {
        assertParseSuccess(parser, "contact" , new ClearCommand());
    }
}
