package seedu.address.logic.parser.lessons;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.lessons.LessonFindCommand;
import seedu.address.model.lesson.LessonNameContainsKeywordsPredicate;

public class LessonFindCommandParserTest {

    private LessonFindCommandParser parser = new LessonFindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, LessonFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsLessonFindCommand() {
        // no leading and trailing whitespaces
        LessonFindCommand expectedLessonFindCommand =
                new LessonFindCommand(new LessonNameContainsKeywordsPredicate(Arrays.asList("GES1028", "CS2106")));
        assertParseSuccess(parser, "GES1028 CS2106", expectedLessonFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n GES1028 \n \t CS2106  \t", expectedLessonFindCommand);
    }

}
