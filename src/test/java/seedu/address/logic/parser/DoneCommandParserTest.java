package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_INPUT_FOR_ONE_WORD_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INPUT_FOR_ONE_WORD_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.setValidCorrectSemester;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.DoneCommand;

public class DoneCommandParserTest {

    private DoneCommandParser parser = new DoneCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        setValidCorrectSemester();
        assertParseFailure(parser, INVALID_INPUT_FOR_ONE_WORD_COMMAND,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DoneCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validValue_success() {
        setValidCorrectSemester();
        DoneCommand doneCommand = new DoneCommand();
        assertParseSuccess(parser, VALID_INPUT_FOR_ONE_WORD_COMMAND, doneCommand);
    }
}
