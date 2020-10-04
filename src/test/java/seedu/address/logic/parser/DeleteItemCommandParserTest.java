package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteItemCommand;

public class DeleteItemCommandParserTest {
    private final DeleteItemCommandParser parser = new DeleteItemCommandParser();
    //@Test
    //public void parse_validArgs_returnsDeleteItemCommand() throws Exception {
    //    String userInput = "deli -n " + APPLE.getName();
    //    assertParseSuccess(parser, userInput, new DeleteItemCommand(APPLE.getName()));
    //}
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteItemCommand.MESSAGE_USAGE));
    }
}
