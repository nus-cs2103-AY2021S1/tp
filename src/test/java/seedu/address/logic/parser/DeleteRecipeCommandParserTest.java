package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteRecipeCommand;

public class DeleteRecipeCommandParserTest {

    private final DeleteRecipeCommandParser parser = new DeleteRecipeCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteRecipeCommand() {
        String userInput = " -n " + APPLE.getName() + " -i 1";
        DeleteRecipeCommand expectedCommand = new DeleteRecipeCommand(APPLE.getName(), Index.fromOneBased(1));

        //expected user input constructs successful DeleteRecipeCommand
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests for invalid input, only recipe product name without id, to show the relevant error message.
     */
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-n test", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteRecipeCommand.MESSAGE_USAGE));
    }
}
