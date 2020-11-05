package seedu.address.logic.parser.contactlistparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.contactlistcommands.DeleteContactCommand;

public class DeleteContactParserTest {
    private DeleteContactParser parser = new DeleteContactParser();

    @Test
    public void parse_validArgs_returnsDeleteContactCommand() {
        assertParseSuccess(parser, "1", new DeleteContactCommand(INDEX_FIRST_CONTACT));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteContactCommand.MESSAGE_USAGE));
    }
}
