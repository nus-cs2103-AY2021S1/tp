package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EndAttemptCommand;

//author zeying99

public class EndAttemptCommandParserTest {

    private EndAttemptCommandParser parser = new EndAttemptCommandParser();

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "abcs", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EndAttemptCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCapitalArgs_failure() {
        assertParseFailure(parser, "AttemPt", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            EndAttemptCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsEndAttemptCommand() {
        EndAttemptCommand expectedClearCommand = new EndAttemptCommand();
        assertParseSuccess(parser, "attempt", expectedClearCommand);
    }

}
