package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.commands.CommandTestUtil.DATE_A;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.FindFinanceCommand;

public class FindFinanceCommandParserTest {
    private FindFinanceCommandParser parser = new FindFinanceCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindFinanceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindFinanceCommand expectedFindFinanceCommand = new FindFinanceCommand();
        expectedFindFinanceCommand.setAfterDatetime(DATE_A);
        assertParseSuccess(parser, "2020-10-01", expectedFindFinanceCommand);
    }
}
