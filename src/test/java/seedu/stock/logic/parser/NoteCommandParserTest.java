package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_NOTE_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_SERIAL_NUMBER_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.LOCATION_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.NOTE_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.NOTE_DESC_ORANGE;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.stock.logic.commands.CommandTestUtil.QUANTITY_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SOURCE_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NOTE_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NOTE_ORANGE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_APPLE;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_SECOND_STOCK;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.NoteCommand;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.SerialNumber;

public class NoteCommandParserTest {

    private NoteCommandParser parser = new NoteCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                NoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonEmptyPreambleInvalidValue_failure() {

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SERIAL_NUMBER_DESC_APPLE + NOTE_DESC_APPLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_invalidFieldsPresent_failure() {

        // quantity field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + NOTE_DESC_APPLE + QUANTITY_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));

        // location field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + NOTE_DESC_APPLE + LOCATION_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));

        // source field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + NOTE_DESC_APPLE + SOURCE_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                NoteCommand.MESSAGE_USAGE);

        // missing any prefix
        assertParseFailure(parser, VALID_NOTE_APPLE, expectedMessage);
        assertParseFailure(parser, VALID_SERIAL_NUMBER_APPLE, expectedMessage);

    }

    @Test
    public void parse_duplicateCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_DUPLICATE_HEADER_FIELD,
                NoteCommand.MESSAGE_USAGE);

        // multiple note
        assertParseFailure(parser, SERIAL_NUMBER_DESC_APPLE
                + NOTE_DESC_APPLE + NOTE_DESC_ORANGE, expectedMessage);

        // multiple serialNumber
        assertParseFailure(parser, SERIAL_NUMBER_DESC_BANANA
                + SERIAL_NUMBER_DESC_APPLE + NOTE_DESC_ORANGE, expectedMessage);
    }

    @Test
    public void parse_validArgs_returnsNoteCommand() {

        Note noteStubOrange = new Note(VALID_NOTE_ORANGE);
        // no leading and trailing whitespaces
        NoteCommand expectedNoteCommand =
                new NoteCommand(SERIAL_NUMBER_FIRST_STOCK, noteStubOrange);
        assertParseSuccess(parser,
                SERIAL_NUMBER_DESC_APPLE + NOTE_DESC_ORANGE,
                expectedNoteCommand);

    }

    @Test
    public void parse_allFieldsPresent_success() {
        Note noteStub = new Note(VALID_NOTE_ORANGE);
        Note noteStubWithUnknownPrefix = new Note(VALID_NOTE_ORANGE + "/g");

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + SERIAL_NUMBER_DESC_APPLE + NOTE_DESC_ORANGE,
                new NoteCommand(SERIAL_NUMBER_FIRST_STOCK, noteStub));

        // field headers in different order
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NOTE_DESC_ORANGE + SERIAL_NUMBER_DESC_BANANA,
                new NoteCommand(SERIAL_NUMBER_SECOND_STOCK, noteStub));

        // whitespaces in between different headers
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NOTE_DESC_ORANGE
                + "                  " + SERIAL_NUMBER_DESC_BANANA,
                new NoteCommand(SERIAL_NUMBER_SECOND_STOCK, noteStub));

        // unknown fields eg. r/, g/ success
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NOTE_DESC_ORANGE
                        + "/g                  " + SERIAL_NUMBER_DESC_BANANA,
                new NoteCommand(SERIAL_NUMBER_SECOND_STOCK, noteStubWithUnknownPrefix));

    }

    @Test
    public void parse_validFieldsPresentInvalidFieldFormat_failure() {

        // invalid serial number format
        String invalidSerialNumberExpectedMessage = String.format(SerialNumber.MESSAGE_CONSTRAINTS,
                NoteCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INVALID_SERIAL_NUMBER_DESC + NOTE_DESC_ORANGE,
                invalidSerialNumberExpectedMessage);

        // invalid note format
        String invalidNoteExpectedMessage = String.format(Note.MESSAGE_CONSTRAINTS,
                NoteCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + SERIAL_NUMBER_DESC_APPLE + INVALID_NOTE_DESC,
                invalidNoteExpectedMessage);

    }

}
