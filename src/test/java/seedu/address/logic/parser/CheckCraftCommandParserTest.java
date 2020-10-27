package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CheckCraftCommand;

public class CheckCraftCommandParserTest {

    private final CheckCraftCommandParser parser = new CheckCraftCommandParser();

    @Test
    public void parse_validArgs_returnsCheckCraftCommand() {
        String userInput = " -n " + APPLE.getName() + " -q " + APPLE.getQuantity().value;
        CheckCraftCommand expectedCommand = new CheckCraftCommand(APPLE.getName(), APPLE.getQuantity());

        //expected user input constructs successful check craft command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CheckCraftCommand.MESSAGE_USAGE));
    }
}
