package seedu.address.logic.parser.modulelistparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.modulelistcommands.ArchiveModuleCommand;
import seedu.address.logic.commands.modulelistcommands.UnarchiveModuleCommand;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MODULE;


public class UnarchiveModuleParserTest {
    private UnarchiveModuleParser parser = new UnarchiveModuleParser();

    @Test
    public void parse_validArgs_returnsArchiveModuleCommand() {
        assertParseSuccess(parser, "1", new UnarchiveModuleCommand(INDEX_FIRST_MODULE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnarchiveModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingIndexField_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnarchiveModuleCommand.MESSAGE_USAGE));
    }
}
