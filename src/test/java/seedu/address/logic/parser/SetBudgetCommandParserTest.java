package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetBudgetCommand;

public class SetBudgetCommandParserTest {
    private SetBudgetCommandParser parser = new SetBudgetCommandParser();

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        assertParseFailure(parser, "", SetBudgetCommand.MESSAGE_SET_BUDGET_FAIL);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, " -1", SetBudgetCommand.MESSAGE_SET_BUDGET_FAIL);
    }

}
