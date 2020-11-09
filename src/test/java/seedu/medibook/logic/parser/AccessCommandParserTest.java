package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.medibook.logic.commands.AccessCommand;

public class AccessCommandParserTest {

    private AccessCommandParser parser = new AccessCommandParser();

    @Test
    public void parse_validArgs_returnsAccessCommand() {
        assertParseSuccess(parser, "1", new AccessCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccessCommand.MESSAGE_USAGE));
    }
}
