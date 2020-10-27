package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.stock.logic.commands.CommandTestUtil.SORT_FIELD_DESC;
import static seedu.stock.logic.commands.CommandTestUtil.SORT_ORDER_DESC;
import static seedu.stock.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

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
        assertParseFailure(parser, SORT_ORDER_DESC, MESSAGE_INVALID_FORMAT);

        // EP: no sort order
        assertParseFailure(parser, SORT_FIELD_DESC, MESSAGE_INVALID_FORMAT);
    }
}
