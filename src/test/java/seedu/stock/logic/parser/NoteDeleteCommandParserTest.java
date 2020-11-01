package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.*;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_SECOND_STOCK;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.NoteDeleteCommand;
import seedu.stock.model.stock.NoteIndex;
import seedu.stock.model.stock.SerialNumber;

public class NoteDeleteCommandParserTest {

    private NoteDeleteCommandParser parser = new NoteDeleteCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                NoteDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonEmptyPreambleInvalidValue_failure() {

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SERIAL_NUMBER_DESC_APPLE
                        + NOTE_INDEX_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteDeleteCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_invalidFieldsPresent_failure() {

        // quantity field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + NOTE_INDEX_DESC + QUANTITY_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteDeleteCommand.MESSAGE_USAGE));

        // location field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + NOTE_INDEX_DESC + LOCATION_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteDeleteCommand.MESSAGE_USAGE));

        // source field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + NOTE_INDEX_DESC + SOURCE_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                NoteDeleteCommand.MESSAGE_USAGE);

        // missing serial number prefix
        assertParseFailure(parser, NOTE_INDEX_DESC, expectedMessage);
        // missing note index prefix
        assertParseFailure(parser, VALID_SERIAL_NUMBER_APPLE, expectedMessage);

    }

    @Test
    public void parse_duplicateCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_DUPLICATE_HEADER_FIELD,
                NoteDeleteCommand.MESSAGE_USAGE);

        // multiple note index
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE
                + NOTE_INDEX_DESC + NOTE_INDEX_DESC, expectedMessage);

        // multiple serialNumber
        assertParseFailure(parser, SERIAL_NUMBER_DESC_BANANA
                + SERIAL_NUMBER_DESC_APPLE + NOTE_INDEX_DESC, expectedMessage);
    }

    @Test
    public void parse_validArgs_returnsNoteDeleteCommand() {

        NoteIndex noteIndexStub = new NoteIndex(VALID_NOTE_INDEX);
        // no leading and trailing whitespaces
        NoteDeleteCommand expectedNoteCommand =
                new NoteDeleteCommand(SERIAL_NUMBER_FIRST_STOCK, noteIndexStub);
        assertParseSuccess(parser,
                SERIAL_NUMBER_DESC_APPLE + NOTE_INDEX_DESC,
                expectedNoteCommand);

    }

    @Test
    public void parse_allFieldsPresent_success() {
        NoteIndex noteIndexStub = new NoteIndex(VALID_NOTE_INDEX);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + SERIAL_NUMBER_DESC_APPLE + NOTE_INDEX_DESC,
                new NoteDeleteCommand(SERIAL_NUMBER_FIRST_STOCK, noteIndexStub));

        // field headers in different order
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NOTE_INDEX_DESC + SERIAL_NUMBER_DESC_BANANA,
                new NoteDeleteCommand(SERIAL_NUMBER_SECOND_STOCK, noteIndexStub));

        // whitespaces in between different headers
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NOTE_INDEX_DESC
                        + "                  " + SERIAL_NUMBER_DESC_BANANA,
                new NoteDeleteCommand(SERIAL_NUMBER_SECOND_STOCK, noteIndexStub));
    }

    @Test
    public void parse_validFieldsPresentInvalidFieldFormat_failure() {

        // invalid serial number format
        String invalidSerialNumberExpectedMessage = String.format(SerialNumber.MESSAGE_CONSTRAINTS,
                NoteDeleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INVALID_SERIAL_NUMBER_DESC + NOTE_INDEX_DESC,
                invalidSerialNumberExpectedMessage);

        // invalid note index format
        String invalidNoteIndexExpectedMessage = String.format(NoteIndex.MESSAGE_CONSTRAINTS,
                NoteDeleteCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + SERIAL_NUMBER_DESC_APPLE + INVALID_NOTE_INDEX_DESC,
                invalidNoteIndexExpectedMessage);

    }

}
