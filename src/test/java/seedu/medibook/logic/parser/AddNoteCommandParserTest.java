package seedu.medibook.logic.parser;


import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_NOTE_DATE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_CONTENT_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_FUTURE_DATE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_NON_FUTURE_DATE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONTENT;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_NON_FUTURE_DATE;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.medibook.logic.commands.AddNoteCommand;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.medicalnote.Content;

class AddNoteCommandParserTest {
    private final AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_optionalNonFutureDateSpecified_success() {
        assertParseSuccess(parser,
                NOTE_NON_FUTURE_DATE_DESC + NOTE_CONTENT_DESC,
                new AddNoteCommand(new Date(VALID_NON_FUTURE_DATE, true),
                        new Content(VALID_CONTENT)));
    }

    @Test
    public void parse_optionalFutureDateSpecified_failure() {
        assertParseFailure(parser,
                NOTE_FUTURE_DATE_DESC + NOTE_CONTENT_DESC,
                Date.MESSAGE_NON_FUTURE);
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        // missing content field
        assertParseFailure(parser, NOTE_NON_FUTURE_DATE_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_optionalDateMissing_success() {
        assertParseSuccess(parser, NOTE_CONTENT_DESC,
                new AddNoteCommand(new Date(),
                        new Content(VALID_CONTENT)));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser,
                INVALID_NOTE_DATE_DESC + NOTE_CONTENT_DESC,
                Date.MESSAGE_CONSTRAINTS);
    }
}
