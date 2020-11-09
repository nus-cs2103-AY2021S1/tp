package tutorspet.logic.parser.lesson;

import static tutorspet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutorspet.logic.commands.lesson.DeleteLessonCommand.MESSAGE_USAGE;
import static tutorspet.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static tutorspet.logic.parser.CliSyntax.PREFIX_LESSON_INDEX;
import static tutorspet.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tutorspet.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static tutorspet.testutil.TypicalIndexes.INDEX_FIRST_ITEM;
import static tutorspet.testutil.TypicalIndexes.INDEX_SECOND_ITEM;

import org.junit.jupiter.api.Test;

import tutorspet.logic.commands.lesson.DeleteLessonCommand;

/**
 * As we are only doing white-box testing, out test cases do not cover path variations
 * outside of the DeleteLessonCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteLessonCommand, and therefore we test only one of them.
 * The path variations for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteLessonCommandParserTest {

    private DeleteLessonCommandParser parser = new DeleteLessonCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteLessonCommand() {
        assertParseSuccess(parser, " "
                + PREFIX_CLASS_INDEX + "1" + " "
                + PREFIX_LESSON_INDEX + "1",
                new DeleteLessonCommand(INDEX_FIRST_ITEM, INDEX_FIRST_ITEM));

        // multiple class indexes -> last class index accepted
        assertParseSuccess(parser, " "
                + PREFIX_CLASS_INDEX + "1" + " "
                + PREFIX_LESSON_INDEX + "1" + " "
                + PREFIX_CLASS_INDEX + "2",
                new DeleteLessonCommand(INDEX_SECOND_ITEM, INDEX_FIRST_ITEM));

        // multiple lesson indexes -> last lesson index accepted
        assertParseSuccess(parser, " "
                + PREFIX_CLASS_INDEX + "1" + " "
                + PREFIX_LESSON_INDEX + "1" + " "
                + PREFIX_LESSON_INDEX + "2",
                new DeleteLessonCommand(INDEX_FIRST_ITEM, INDEX_SECOND_ITEM));
    }

    @Test
    public void parse_missingClassIndex_throwsParseException() {
        assertParseFailure(parser, " "
                + PREFIX_LESSON_INDEX + "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_missingLessonIndex_throwsParseException() {
        assertParseFailure(parser, " "
                + PREFIX_CLASS_INDEX + "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidClassIndex_throwsParseException() {
        assertParseFailure(parser, " "
                + PREFIX_CLASS_INDEX + "a" + " "
                + PREFIX_LESSON_INDEX + "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidLessonIndex_throwsParseException() {
        assertParseFailure(parser, " "
                + PREFIX_CLASS_INDEX + "1" + " "
                + PREFIX_LESSON_INDEX + "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyClassIndex_throwsParseException() {
        assertParseFailure(parser, " "
                + PREFIX_CLASS_INDEX + " "
                + PREFIX_LESSON_INDEX + "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyLessonIndex_throwsParseException() {
        assertParseFailure(parser, " "
                + PREFIX_CLASS_INDEX + "1" + " "
                + PREFIX_LESSON_INDEX,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwParseException() {
        assertParseFailure(parser, " a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }
}
