package seedu.address.logic.parser.timetable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.timetable.DurationTest.DURATION_1600_1800;
import static seedu.address.testutil.TypicalLessons.MA1521;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.timetable.TimetableAddLessonCommand;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.util.Name;

public class TimetableAddLessonCommandParserTest {

    private final TimetableAddLessonCommandParser parser = new TimetableAddLessonCommandParser();
    private final String expectedMessage =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableAddLessonCommand.MESSAGE_USAGE);

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        String userInput = " n/MA1521 D/monday T/1600-1800";
        TimetableAddLessonCommand expectedCommand = new TimetableAddLessonCommand(
                MA1521, Day.MONDAY, DURATION_1600_1800);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingLessonField_failure() {
        String userInput = " D/monday T/1600-1800";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingDayField_failure() {
        String userInput = " n/MA1521 T/1600-1800";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingDurationField_failure() {
        String userInput = " n/MA1521 D/monday";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        String userInput = PREAMBLE_NON_EMPTY + " n/MA1521 D/monday T/1600-1800";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_multipleLessonField_failure() {
        String userInput = "n/MA1521 n/MA1101R D/monday T/1600-1800";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_multipleDayField_failure() {
        String userInput = " n/MA1521 D/monday D/tuesday T/1600-1800";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_multipleDurationField_failure() {
        String userInput = " n/MA1521 D/monday T/1600-1800 T/1800-2000";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidLessonField_failure() {
        String userInputFormat = " n/MA1521& D/monday T/1600-1800";
        assertParseFailure(parser, userInputFormat, Name.MESSAGE_CONSTRAINTS_FORMAT);

        String userInputLimit = " n/This is just a very long name of a lesson that will exceed 50 characters "
                + "D/monday T/1600-1800";
        assertParseFailure(parser, userInputLimit, Name.MESSAGE_CONSTRAINTS_LIMIT);
    }

    @Test
    public void parse_invalidDayField_failure() {
        String userInput = " n/MA1521 D/random T/1600-1800";
        assertParseFailure(parser, userInput, Day.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDurationField_failure() {
        String userInputFormat = " n/MA1521 D/monday T/1600 - 1800";
        assertParseFailure(parser, userInputFormat, Duration.MESSAGE_CONSTRAINTS_FORMAT);

        String userInputOrder = " n/MA1521 D/monday T/1800-1600";
        assertParseFailure(parser, userInputOrder, Duration.MESSAGE_CONSTRAINTS_ORDER);
    }
}
