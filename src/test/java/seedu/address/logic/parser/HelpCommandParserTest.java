package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_INPUT_FOR_ONE_WORD_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INPUT_FOR_ONE_WORD_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.setValidCorrectSemester;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.HelpCommand;

public class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_invalidValue_failure() {
        setValidCorrectSemester();
        assertParseFailure(parser, INVALID_INPUT_FOR_ONE_WORD_COMMAND,
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validValue_success() {
        setValidCorrectSemester();
        HelpCommand helpCommand = new HelpCommand();
        assertParseSuccess(parser, VALID_INPUT_FOR_ONE_WORD_COMMAND, helpCommand);
    }
}
