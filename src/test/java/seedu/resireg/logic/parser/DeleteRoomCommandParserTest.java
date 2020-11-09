package seedu.resireg.logic.parser;

import static seedu.resireg.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.resireg.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.commands.DeleteRoomCommand;

/**
 * See javadoc for {@link DeleteCommandParserTest}
 */
public class DeleteRoomCommandParserTest {
    private DeleteRoomCommandParser parser = new DeleteRoomCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteRoomCommand() {
        assertParseSuccess(parser, "1", new DeleteRoomCommand(INDEX_FIRST_ROOM));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteRoomCommand.HELP.getFullMessage()));
    }
}
