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

    /*
    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
     */
}
