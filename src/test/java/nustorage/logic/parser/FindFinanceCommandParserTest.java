package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.FindFinanceCommand;

public class FindFinanceCommandParserTest {
    private FindFinanceCommandParser parser = new FindFinanceCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindFinanceCommand.MESSAGE_USAGE));
    }
}
