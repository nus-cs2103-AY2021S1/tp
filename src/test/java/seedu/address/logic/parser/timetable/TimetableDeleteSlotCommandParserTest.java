package seedu.address.logic.parser.timetable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.timetable.DurationTest.DURATION_1600_1800;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.timetable.TimetableDeleteSlotCommand;
import seedu.address.model.timetable.Day;
import seedu.address.model.timetable.Duration;
import seedu.address.model.timetable.Slot;

public class TimetableDeleteSlotCommandParserTest {

    private final TimetableDeleteSlotCommandParser parser = new TimetableDeleteSlotCommandParser();
    private final String expectedMessage =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimetableDeleteSlotCommand.MESSAGE_USAGE);

    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        String userInput = " D/monday T/1600-1800";
        Slot slot = new Slot(Day.MONDAY, DURATION_1600_1800);
        TimetableDeleteSlotCommand expectedCommand = new TimetableDeleteSlotCommand(slot);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingDayField_failure() {
        String userInput = " T/1600-1800";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_missingDurationField_failure() {
        String userInput = " D/monday";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_nonEmptyPreamble_failure() {
        String userInput = PREAMBLE_NON_EMPTY + " D/monday T/1600-1800";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_multipleDayField_failure() {
        String userInput = " D/monday D/tuesday T/1600-1800";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_multipleDurationField_failure() {
        String userInput = " D/monday T/1600-1800 T/1800-2000";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidDayField_failure() {
        String userInput = " D/random T/1600-1800";
        assertParseFailure(parser, userInput, Day.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDurationField_failure() {
        String userInputFormat = " D/monday T/1600 - 1800";
        assertParseFailure(parser, userInputFormat, Duration.MESSAGE_CONSTRAINTS_FORMAT);

        String userInputOrder = " D/monday T/1800-1600";
        assertParseFailure(parser, userInputOrder, Duration.MESSAGE_CONSTRAINTS_ORDER);
    }
}
