package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CraftItemCommand;


public class CraftItemCommandParserTest {

    private final CraftItemCommandParser parser = new CraftItemCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = " -n " + APPLE.getName() + " -q " + APPLE.getQuantity() + " -i 1";
        CraftItemCommand expectedCommand = new CraftItemCommand(APPLE.getName(), APPLE.getQuantity(),
                Index.fromOneBased(1));

        //expected user input constructs successful craft item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    /**
     * Tests for user input missing recipe index to return craft item command.
     */
    @Test
    public void parse_missingIndex_success() {
        String userInput = " -n " + APPLE.getName() + " -q " + APPLE.getQuantity();
        CraftItemCommand expectedCommand = new CraftItemCommand(APPLE.getName(), APPLE.getQuantity());

        //expected user input constructs successful craft item command
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                CraftItemCommand.MESSAGE_USAGE));
    }
}
