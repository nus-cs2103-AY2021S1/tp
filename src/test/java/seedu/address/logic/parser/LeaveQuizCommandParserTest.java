package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.LeaveQuizCommand;

//author zeying99

public class LeaveQuizCommandParserTest {

    private LeaveQuizCommandParser parser = new LeaveQuizCommandParser();

    @Test
    public void parse_invalidArgs_failure() {
        assertParseFailure(parser, "123", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            LeaveQuizCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidCapitalArgs_failure() {
        assertParseFailure(parser, "QuIZ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            LeaveQuizCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsLeaveQuizCommand() {
        LeaveQuizCommand expectedClearCommand = new LeaveQuizCommand();
        assertParseSuccess(parser, "quiz", expectedClearCommand);
    }

}
