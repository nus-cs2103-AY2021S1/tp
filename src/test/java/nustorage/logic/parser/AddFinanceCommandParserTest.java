package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_A;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_DESC_A;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_DESC_B;
import static nustorage.logic.commands.CommandTestUtil.DATE_DESC_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_STRING_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_DESC_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_DESC_B;
import static nustorage.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static nustorage.logic.commands.CommandTestUtil.TIME_DESC_A;
import static nustorage.logic.commands.CommandTestUtil.TIME_STRING_A;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_A;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.AddFinanceCommand;
import nustorage.model.record.FinanceRecord;
import nustorage.testutil.FinanceRecordBuilder;

public class AddFinanceCommandParserTest {
    private AddFinanceCommandParser parser = new AddFinanceCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        FinanceRecord expectedFinanceRecord = new FinanceRecordBuilder(RECORD_A).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + AMOUNT_DESC_A + DATE_TIME_DESC_A,
                new AddFinanceCommand(expectedFinanceRecord));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, AMOUNT_DESC_B + AMOUNT_DESC_A + DATE_TIME_DESC_A,
                new AddFinanceCommand(expectedFinanceRecord));

        // multiple date times - last datetime accepted
        assertParseSuccess(parser, AMOUNT_DESC_A + DATE_TIME_DESC_B + DATE_TIME_DESC_A,
                new AddFinanceCommand(expectedFinanceRecord));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // no date time
        FinanceRecord blankFinanceRecord = new FinanceRecord(AMOUNT_A);
        assertParseSuccess(parser, AMOUNT_DESC_A,
                new AddFinanceCommand(blankFinanceRecord));

        // no date
        FinanceRecord timeFinanceRecord = new FinanceRecordBuilder(RECORD_A).withDateTime(TIME_STRING_A).build();
        assertParseSuccess(parser, AMOUNT_DESC_A + TIME_DESC_A,
                new AddFinanceCommand(timeFinanceRecord));

        // no time
        FinanceRecord dateFinanceRecord = new FinanceRecordBuilder(RECORD_A).withDateTime(DATE_STRING_A).build();
        assertParseSuccess(parser, AMOUNT_DESC_A + DATE_DESC_A,
                new AddFinanceCommand(dateFinanceRecord));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFinanceCommand.MESSAGE_USAGE);

        // missing amount prefix
        assertParseFailure(parser, DATE_TIME_DESC_A, expectedMessage);
    }
}
