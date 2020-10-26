package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_DESC_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_STRING_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_DESC_A;
import static nustorage.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC_A;
import static nustorage.logic.commands.CommandTestUtil.VALID_AMOUNT;
import static nustorage.logic.parser.CliSyntax.PREFIX_TAG;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.EditFinanceCommand;

public class EditFinanceCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditFinanceCommand.MESSAGE_USAGE);

    private EditFinanceCommandParser parser = new EditFinanceCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, String.valueOf(VALID_AMOUNT), MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditFinanceCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + AMOUNT_DESC_A, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + AMOUNT_DESC_A, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC_A, "Amount if not a decimal value."); // invalid name

        // invalid amount followed by valid date
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC_A + DATE_STRING_A, "Amount if not a decimal value.");

        // invalid amount followed by valid date and time
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC_A + DATE_TIME_DESC_A, "Amount if not a decimal value.");
    }
}
