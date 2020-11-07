package seedu.address.logic.parser.gradetrackerparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.schedulercommands.DeleteEventCommand;
import seedu.address.logic.parser.schedulerparsers.DeleteEventParser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.event.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.event.TypicalIndexes.INDEX_SECOND_EVENT;

public class DeleteAssignmentParserTest {

    private DeleteEventParser parser = new DeleteEventParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteEventCommand(INDEX_FIRST_EVENT));
        assertParseSuccess(parser, "2", new DeleteEventCommand(INDEX_SECOND_EVENT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingIndexField_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteEventCommand.MESSAGE_USAGE));
    }
}
