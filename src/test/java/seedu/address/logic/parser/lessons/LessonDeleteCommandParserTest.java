package seedu.address.logic.parser.lessons;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_LESSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.lessons.LessonDeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the LessonDeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class LessonDeleteCommandParserTest {

    private LessonDeleteCommandParser parser = new LessonDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsLessonDeleteCommand() {
        assertParseSuccess(parser, "1", new LessonDeleteCommand(INDEX_FIRST_LESSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser, "a", MESSAGE_INVALID_INDEX);
    }
}
