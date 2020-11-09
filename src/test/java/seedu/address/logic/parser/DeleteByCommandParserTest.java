package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOOK2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOOK2;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteByCommand;


class DeleteByCommandParserTest {

    private DeleteByCommandParser parser = new DeleteByCommandParser();

    @Test
    void parse_validArgs_returnsDeleteByCommand() {
        assertParseSuccess(parser, NAME_DESC_BOOK2, new DeleteByCommand(VALID_NAME_BOOK2, 0));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "6666", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteByCommand.MESSAGE_USAGE));
    }
}
