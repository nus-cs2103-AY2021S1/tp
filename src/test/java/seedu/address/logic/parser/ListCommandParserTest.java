package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_emptyArg_success() {
        String emptyArg = "";

        ListCommand expectedListCommand = new ListCommand();

        assertParseSuccess(parser, emptyArg, expectedListCommand);
    }

    @Test
    public void parse_nonEmptyArg_throwsParseException() {
        String nonEmptyArg = "abc";

        assertParseFailure(parser, nonEmptyArg, ListCommand.MESSAGE_INVALID_USER_INPUT);
    }

}
