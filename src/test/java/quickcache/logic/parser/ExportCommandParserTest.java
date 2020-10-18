package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseFailure;
import static quickcache.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import quickcache.logic.commands.ExportCommand;

class ExportCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE);

    private final ExportCommandParser parser = new ExportCommandParser();

    @Test
    public void parse_validArgs_returnsExportCommand() {
        String pathString = "test.json";
        Path path = Paths.get("export", pathString);
        assertParseSuccess(parser, "test.json", new ExportCommand(path));
    }

    @Test
    public void parse_noInput_failure() {
        // no path specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }
}
