package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_BOOKS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_BOOKS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Date;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);
    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_CATEGORY_BOOKS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + AMOUNT_DESC_BOOKS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + AMOUNT_DESC_BOOKS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS); // invalid amount
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date

        // invalid amount followed by valid category
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC + VALID_CATEGORY_BOOKS, Amount.MESSAGE_CONSTRAINTS);

        // valid amount followed by invalid amount. The test case for invalid amount followed by valid amount
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + AMOUNT_DESC_BOOKS + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC + INVALID_DATE_1 + VALID_CATEGORY_BOOKS,
                Amount.MESSAGE_CONSTRAINTS);
    }
}
