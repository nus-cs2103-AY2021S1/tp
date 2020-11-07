package seedu.address.logic.parser.contactlistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contactlistcommands.ImportantContactCommand;

public class ImportantContactParserTest {
    private ImportantContactParser parser = new ImportantContactParser();

    @Test
    public void parse_validArgs_returnsImportantContactCommand() {
        assertParseSuccess(parser, "1", new ImportantContactCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ImportantContactCommand.MESSAGE_USAGE));
    }
}
