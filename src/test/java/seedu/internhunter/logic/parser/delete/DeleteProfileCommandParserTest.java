package seedu.internhunter.logic.parser.delete;

import static seedu.internhunter.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.internhunter.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.internhunter.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.internhunter.logic.commands.delete.DeleteProfileCommand;

public class DeleteProfileCommandParserTest {
    private DeleteProfileCommandParser parser = new DeleteProfileCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteProfileCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_INDEX));
    }
}
