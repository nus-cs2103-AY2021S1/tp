package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalItems.APPLE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteItemCommandParserTest {
    private final DeleteItemCommandParser parser = new DeleteItemCommandParser();
    @Test
    public void parse_validArgs_returnsDeleteItemCommand(){
        String userInput = " -n " + APPLE.getName();
        DeleteItemCommand expectedCommand = new DeleteItemCommand(APPLE.getName());
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteItemCommand.MESSAGE_USAGE));
    }
}
