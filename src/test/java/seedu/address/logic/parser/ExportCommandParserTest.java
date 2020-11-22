package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExportCommand;

/**
 * Test cases for export command.
 */
public class ExportCommandParserTest {

    private ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_validArgsEmail_returnsExportCommand() {
        assertParseSuccess(parser, "email", new ExportCommand("email"));
    }

    @Test
    public void parse_validArgsPhone_returnsExportCommand() {
        assertParseSuccess(parser, "phone", new ExportCommand("phone"));
    }

    @Test
    public void parse_validArgsCapitalizedEmail_returnsExportCommand() {
        assertParseSuccess(parser, "EMAIL", new ExportCommand("email"));
    }

    @Test
    public void parse_validArgsCapitalizedPhone_returnsExportCommand() {
        assertParseSuccess(parser, "PHONE", new ExportCommand("phone"));
    }

}
