package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ScheduleCommand;

public class ScheduleCommandParserTest {

    private ScheduleCommandParser parser = new ScheduleCommandParser();

    @Test
    public void parse_emptyUserInputDate_throwsParseException() {
        assertParseFailure(parser, "    ",
                ScheduleCommand.EMPTY_DATE_MESSAGE);
    }

    @Test
    public void parse_validUserInputDate_returnsScheduleCommand() {
        LocalDate date = LocalDate.of(2020, 9, 12);
        ScheduleCommand expectedCommand = new ScheduleCommand(date);
        assertParseSuccess(parser, "12/09/2020", expectedCommand);
        assertParseSuccess(parser, "    12/09/2020    ", expectedCommand); // whitespaces will be trimmed
    }

    @Test
    public void parse_invalidUserInputDate_throwsParseException() {
        // invalid date format
        assertParseFailure(parser, "12/09/20", ScheduleCommand.INCORRECT_DATE_FORMAT);
        assertParseFailure(parser, "12-09-20", ScheduleCommand.INCORRECT_DATE_FORMAT);

        // "!" not allowed in user input date
        assertParseFailure(parser, "12/09/21!", ScheduleCommand.INCORRECT_DATE_FORMAT);
    }
}
