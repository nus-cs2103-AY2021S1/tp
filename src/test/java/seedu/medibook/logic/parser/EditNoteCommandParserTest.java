package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_NOTE_CONTENT_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_NOTE_DATE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_CONTENT_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_NON_FUTURE_DATE_DESC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.core.index.Index;
import seedu.medibook.logic.commands.EditNoteCommand;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.medicalnote.Content;

public class EditNoteCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE);

    private final EditNoteCommandParser parser = new EditNoteCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, NOTE_CONTENT_DESC, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditNoteCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NOTE_NON_FUTURE_DATE_DESC, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NOTE_NON_FUTURE_DATE_DESC, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 l/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser, "1" + INVALID_NOTE_DATE_DESC, Date.MESSAGE_CONSTRAINTS);

        // invalid content
        assertParseFailure(parser, "1" + INVALID_NOTE_CONTENT_DESC, Content.MESSAGE_CONSTRAINTS);

        // invalid date followed by valid content
        assertParseFailure(parser, "1" + INVALID_NOTE_DATE_DESC + NOTE_CONTENT_DESC, Date.MESSAGE_CONSTRAINTS);

        // valid date followed by invalid content
        assertParseFailure(parser, "1" + NOTE_NON_FUTURE_DATE_DESC + INVALID_NOTE_CONTENT_DESC,
                Content.MESSAGE_CONSTRAINTS);

        // multiple invalid values but only the first is captured
        assertParseFailure(parser, "1" + INVALID_NOTE_DATE_DESC + INVALID_NOTE_CONTENT_DESC, Date.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + NOTE_NON_FUTURE_DATE_DESC + NOTE_CONTENT_DESC;

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();

        descriptor.setDate(new Date("15-10-2020", true));
        descriptor.setContent(new Content("Patient is sick."));

        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + NOTE_NON_FUTURE_DATE_DESC;

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();

        descriptor.setDate(new Date("15-10-2020", true));

        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // date
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + NOTE_NON_FUTURE_DATE_DESC;

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setDate(new Date("15-10-2020", true));
        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // content
        userInput = targetIndex.getOneBased() + NOTE_CONTENT_DESC;

        descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setContent(new Content("Patient is sick."));
        expectedCommand = new EditNoteCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + " "
                + PREFIX_DATE + "20-10-2008" + NOTE_NON_FUTURE_DATE_DESC
                + " " + PREFIX_CONTENT + "Patient is bad." + NOTE_CONTENT_DESC;

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();

        descriptor.setDate(new Date("15-10-2020", true));
        descriptor.setContent(new Content("Patient is sick."));

        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_NOTE_DATE_DESC + NOTE_NON_FUTURE_DATE_DESC;

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setDate(new Date("15-10-2020", true));
        EditNoteCommand expectedCommand = new EditNoteCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_NOTE_DATE_DESC + NOTE_NON_FUTURE_DATE_DESC
                + NOTE_CONTENT_DESC;

        descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setDate(new Date("15-10-2020", true));
        descriptor.setContent(new Content("Patient is sick."));

        expectedCommand = new EditNoteCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
