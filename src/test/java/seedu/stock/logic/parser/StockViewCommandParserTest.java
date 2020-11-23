package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_DUPLICATE_HEADER_FIELD;
import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_SERIAL_NUMBER_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.LOCATION_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.stock.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.stock.logic.commands.CommandTestUtil.QUANTITY_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.SERIAL_NUMBER_DESC_BANANA;
import static seedu.stock.logic.commands.CommandTestUtil.SOURCE_DESC_BANANA;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_SECOND_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_THIRD_STOCK;

import org.junit.jupiter.api.Test;

import seedu.stock.logic.commands.StockViewCommand;
import seedu.stock.model.stock.SerialNumber;

public class StockViewCommandParserTest {

    private StockViewCommandParser parser = new StockViewCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StockViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_nonEmptyPreambleInvalidValue_failure() {

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + SERIAL_NUMBER_DESC_APPLE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StockViewCommand.MESSAGE_USAGE));

    }

    @Test
    public void parse_invalidFieldsPresent_failure() {

        // quantity field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + QUANTITY_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StockViewCommand.MESSAGE_USAGE));

        // location field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + LOCATION_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StockViewCommand.MESSAGE_USAGE));

        // source field present
        assertParseFailure(parser,
                SERIAL_NUMBER_DESC_APPLE + SOURCE_DESC_BANANA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StockViewCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                StockViewCommand.MESSAGE_USAGE);

        // missing any prefix
        assertParseFailure(parser, "", expectedMessage);

    }

    @Test
    public void parse_duplicateCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_DUPLICATE_HEADER_FIELD,
                StockViewCommand.MESSAGE_USAGE);

        // multiple serialNumber
        assertParseFailure(parser, SERIAL_NUMBER_DESC_BANANA
                + SERIAL_NUMBER_DESC_APPLE, expectedMessage);
    }

    @Test
    public void parse_validArgs_returnsNoteViewCommand() {

        // no leading and trailing whitespaces
        StockViewCommand expectedStockViewCommand =
                new StockViewCommand(SERIAL_NUMBER_SECOND_STOCK);
        assertParseSuccess(parser, SERIAL_NUMBER_DESC_APPLE, expectedStockViewCommand);

    }

    @Test
    public void parse_allFieldsPresent_success() {

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + SERIAL_NUMBER_DESC_APPLE,
                new StockViewCommand(SERIAL_NUMBER_SECOND_STOCK));

        // whitespaces in between different headers
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + SERIAL_NUMBER_DESC_BANANA + "          ",
                new StockViewCommand(SERIAL_NUMBER_THIRD_STOCK));

    }

    @Test
    public void parse_validFieldsPresentInvalidFieldFormat_failure() {

        // invalid serial number format
        String invalidSerialNumberExpectedMessage = String.format(SerialNumber.MESSAGE_CONSTRAINTS,
                StockViewCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INVALID_SERIAL_NUMBER_DESC,
                invalidSerialNumberExpectedMessage);

    }

}
