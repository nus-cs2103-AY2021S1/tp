package seedu.bagel.logic.parser;

import static seedu.bagel.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.bagel.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.bagel.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.bagel.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import org.junit.jupiter.api.Test;

import seedu.bagel.logic.commands.ViewCommand;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsViewCommand() {
        assertParseSuccess(parser, "1", new ViewCommand(INDEX_FIRST_FLASHCARD));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
    }
}
