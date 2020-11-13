package seedu.address.logic.parser.mainpageparser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.mainpageparser.SortBudgetCommandParser.MESSAGE_INVALID_SORT_TYPE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.main.SortBudgetCommand;
import seedu.address.model.sort.SortType;


class SortBudgetCommandParserTest {
    private SortBudgetCommandParser parser = new SortBudgetCommandParser();

    @Test
    public void parse_sortTime_success() {
        assertParseSuccess(parser, " time",
                new SortBudgetCommand(SortType.TIME));
    }

    @Test
    public void parse_sortName_success() {
        assertParseSuccess(parser, " name",
                new SortBudgetCommand(SortType.NAME));
    }

    @Test
    public void parse_typeNotPresent_throwParseException() {
        assertParseFailure(parser, "",
                String.format(SortBudgetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongType_throwParseException() {
        assertParseFailure(parser, "price",
                String.format(MESSAGE_INVALID_SORT_TYPE));
    }
}
