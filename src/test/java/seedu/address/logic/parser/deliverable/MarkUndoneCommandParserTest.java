package seedu.address.logic.parser.deliverable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.deliverable.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.deliverable.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deliverable.MarkUndoneCommand;

public class MarkUndoneCommandParserTest {
    private MarkUndoneCommandParser parser = new MarkUndoneCommandParser();

    @Test
    void parse_validArgs_returnsUndoneCommand() {
        assertParseSuccess(parser, "1", new MarkUndoneCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkUndoneCommand.MESSAGE_USAGE));
    }


}
