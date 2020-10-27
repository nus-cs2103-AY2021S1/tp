package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ARCHIVE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_noPrefixArchive_returnsListCommandInActiveMode() {
        ListCommand expectedListCommand = new ListCommand();

        // empty string
        assertParseSuccess(parser, "", expectedListCommand);

        // can contain leading and trailing whitespaces
        assertParseSuccess(parser, "   ", expectedListCommand);

        // random input without archive prefix
        assertParseSuccess(parser, "  Alice   Bob  ", expectedListCommand);
    }

    @Test
    public void parse_prefixArchive_returnsListCommandInArchiveMode() {
        ListCommand expectedListCommand = new ListCommand(true);

        // can contain leading and trailing whitespaces
        assertParseSuccess(parser, "   " + PREFIX_ARCHIVE + "    ", expectedListCommand);

        // random input with archive prefix
        assertParseSuccess(parser, "  Alice " + PREFIX_ARCHIVE + " Bob  ", expectedListCommand);

        // random input with multiple archive prefix
        assertParseSuccess(parser, "  Alice " + PREFIX_ARCHIVE
                + " Bob  " + PREFIX_ARCHIVE + "Charlie", expectedListCommand);
    }

}
