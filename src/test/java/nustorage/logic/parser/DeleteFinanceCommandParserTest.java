package nustorage.logic.parser;

import static nustorage.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static nustorage.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.DeleteFinanceCommand;

public class DeleteFinanceCommandParserTest {
    private DeleteFinanceCommandParser parser = new DeleteFinanceCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteFinanceCommand.MESSAGE_USAGE));
    }
}
