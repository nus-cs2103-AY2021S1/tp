package seedu.address.logic.parser.budgetpageparser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.mainpageparser.SortBudgetCommandParser.MESSAGE_INVALID_SORT_TYPE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.budget.SortExpenditureCommand;
import seedu.address.model.sort.SortType;

class SortExpenditureCommandParserTest {
    private SortExpenditureCommandParser parser = new SortExpenditureCommandParser();

    @Test
    public void parse_sortTime_success() {
        assertParseSuccess(parser, " time",
                new SortExpenditureCommand(SortType.TIME));
    }

    @Test
    public void parse_sortName_success() {
        assertParseSuccess(parser, " name",
                new SortExpenditureCommand(SortType.NAME));
    }

    @Test
    public void parse_typeNotPresent_throwParseException() {
        assertParseFailure(parser, "",
                String.format(SortExpenditureCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongType_throwParseException() {
        assertParseFailure(parser, "price",
                String.format(MESSAGE_INVALID_SORT_TYPE));
    }
}
