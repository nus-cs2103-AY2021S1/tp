package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelistcommands.TargetCapCalculatorCommand;

public class TargetCapCalculatorParserTest {
    private TargetCapCalculatorParser parser = new TargetCapCalculatorParser();

    @Test
    public void parse_validArgs_returnsTargetCapCalculatorCommand() {
        assertParseSuccess(parser, "5.0", new TargetCapCalculatorCommand(5.0));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                TargetCapCalculatorCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingIndexField_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                TargetCapCalculatorCommand.MESSAGE_USAGE));
    }
}
