package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HelpCommand;

class HelpCommandParserTest {

    private HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_emptyArg_success() {
        String emptyArg = "";

        HelpCommand expectedHelpCommand = new HelpCommand(emptyArg);

        assertParseSuccess(parser, emptyArg, expectedHelpCommand);
    }

    @Test
    public void parse_nonemptyarg_success() {
        String arg = "hfwhfewfhwefi";

        HelpCommand expectedHelpCommand = new HelpCommand(arg);

        assertParseSuccess(parser, arg, expectedHelpCommand);
    }
}
