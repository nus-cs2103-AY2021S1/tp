package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.testutil.command.RoomCommandTestUtil.ROOM_NUMBER_DESC_ONE;
import static seedu.address.testutil.command.TaskCommandTestUtil.DESCRIPTION_DESC_REMIND_PATIENT;
import static seedu.address.testutil.command.TaskCommandTestUtil.INVALID_DATETIME_DUE_FORMAT_DESC;
import static seedu.address.testutil.command.TaskCommandTestUtil.INVALID_DATETIME_DUE_VALUE_DESC;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.SearchTaskCommand;
import seedu.address.model.task.DateTimeDue;

public class SearchTaskCommandParserTest {

    private SearchTaskCommandParser parser = new SearchTaskCommandParser();
    @Test
    void parseError_empty_input() {
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchTaskCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchTaskCommand.MESSAGE_USAGE);

        // wrong prefix
        assertParseFailure(parser, ROOM_NUMBER_DESC_ONE, expectedMessage);

        // wrong prefix
        assertParseFailure(parser, DESCRIPTION_DESC_REMIND_PATIENT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid due date value
        assertParseFailure(parser, INVALID_DATETIME_DUE_VALUE_DESC, DateTimeDue.MESSAGE_CONSTRAINTS);

        // invalid due date format
        assertParseFailure(parser, INVALID_DATETIME_DUE_FORMAT_DESC, DateTimeDue.MESSAGE_CONSTRAINTS);
    }
}
