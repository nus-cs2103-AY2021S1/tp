package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_SORT_FIELD_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.INVALID_SORT_ORDER_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.SORT_FIELD_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.SORT_ORDER_ASCENDING_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.SORT_ORDER_DESCENDING_DESC;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.util.SortUtil;
import seedu.stock.logic.commands.SortCommand;

public class SortCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_missingPrefixes_failure() {
        // EP: empty input
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        // EP: no sort field
        assertParseFailure(parser, SORT_ORDER_ASCENDING_DESC, MESSAGE_INVALID_FORMAT);

        // EP: no sort order
        assertParseFailure(parser, SORT_FIELD_DESC, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // EP: invalid sort order
        assertParseFailure(parser, INVALID_SORT_ORDER_DESC + SORT_FIELD_DESC, SortCommand.MESSAGE_INVALID_ORDER);

        // EP: invalid sort field
        assertParseFailure(parser, INVALID_SORT_FIELD_DESC + SORT_ORDER_ASCENDING_DESC,
                SortCommand.MESSAGE_INVALID_FIELD);

        // EP: invalid sort order and sort field
        assertParseFailure(parser, INVALID_SORT_ORDER_DESC + INVALID_SORT_FIELD_DESC,
                SortCommand.MESSAGE_INVALID_ORDER);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // EP: valid ascending sort
        String userInput = SORT_ORDER_ASCENDING_DESC + SORT_FIELD_DESC;
        SortCommand expectedCommand = new SortCommand(SortUtil.Field.SERIALNUMBER, false);
        assertParseSuccess(parser, userInput, expectedCommand);

        // EP: valid descending sort
        userInput = SORT_ORDER_DESCENDING_DESC + SORT_FIELD_DESC;
        expectedCommand = new SortCommand(SortUtil.Field.SERIALNUMBER, true);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
