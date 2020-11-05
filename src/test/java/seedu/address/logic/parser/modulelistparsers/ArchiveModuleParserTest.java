package seedu.address.logic.parser.modulelistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.modulelistcommands.ArchiveModuleCommand;

public class ArchiveModuleParserTest {
    private ArchiveModuleParser parser = new ArchiveModuleParser();

    @Test
    public void parse_validArgs_returnsArchiveModuleCommand() {
        assertParseSuccess(parser, "1", new ArchiveModuleCommand(INDEX_FIRST_MODULE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ArchiveModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingIndexField_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ArchiveModuleCommand.MESSAGE_USAGE));
    }
}
