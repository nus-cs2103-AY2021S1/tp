package seedu.tr4cker.logic.parser;

import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.tr4cker.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.tr4cker.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.tr4cker.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.logic.commands.TodoCommand;

/**
 * Contains integration tests for {@code TodoCommandParser}.
 */
public class TodoCommandParserTest {

    private final TodoCommandParser parser = new TodoCommandParser();

    @Test
    public void parse_validArgs_returnsTodoCommand() {
        assertParseSuccess(parser, "1", new TodoCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "-1", String.format(MESSAGE_INVALID_INDEX,
                TodoCommand.MESSAGE_USAGE));
    }
}
