package seedu.address.logic.parser.itineraryattraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_START_TIME;
import static seedu.address.logic.commands.CommandTestUtil.DAY_VISITING_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.DAY_VISITING_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.END_TIME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_VISITING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_EIFFEL;
import static seedu.address.logic.commands.CommandTestUtil.START_TIME_DESC_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_VISITING_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX_MBS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MBS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY_VISITING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itineraryattraction.AddItineraryAttractionCommand;
import seedu.address.model.itinerary.Day;
import seedu.address.model.itinerary.ItineraryTime;


public class AddItineraryAttractionCommandParserTest {
    private AddItineraryAttractionCommandParser parser = new AddItineraryAttractionCommandParser();

    // todo really bad and messy method, simplify this afterwards.
    private final AddItineraryAttractionCommand expectedAddIaCommand = new AddItineraryAttractionCommand(
            Index.fromOneBased(Integer.parseInt(VALID_INDEX_MBS)),
            new ItineraryTime(VALID_START_TIME_MBS),
            new ItineraryTime(VALID_END_TIME_MBS),
            Index.fromOneBased(Integer.parseInt(VALID_DAY_VISITING_MBS)));


    @Test
    public void parse_allFieldsPresent_success() {
        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedAddIaCommand);

        // multiple start time - last start time accepted
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_EIFFEL
                        + START_TIME_DESC_EIFFEL // should not be accepted, else will be diff from expected
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS
                        + START_TIME_DESC_MBS,
                expectedAddIaCommand);

        // multiple end time - last end time accepted
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_EIFFEL
                        + DAY_VISITING_DESC_MBS
                        + END_TIME_DESC_MBS,
                expectedAddIaCommand);

        // multiple day visiting - last day accepted
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + DAY_VISITING_DESC_EIFFEL
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedAddIaCommand);

        // invalid start time followed by a valid start time, accept last value
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + INVALID_START_TIME_DESC
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedAddIaCommand);

        // invalid end time followed by a valid end time
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + INVALID_END_TIME_DESC
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedAddIaCommand);

        // invalid day visiting followed by a valid day visiting
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + INVALID_DAY_VISITING_DESC
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedAddIaCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddItineraryAttractionCommand.MESSAGE_USAGE);

        // missing start time prefix
        assertParseFailure(parser,
                VALID_START_TIME_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedMessage);

        // missing end time prefix
        assertParseFailure(parser,
                START_TIME_DESC_MBS
                        + VALID_END_TIME_MBS
                        + DAY_VISITING_DESC_MBS,
                expectedMessage);

        // missing day visiting prefix
        assertParseFailure(parser,
                START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + VALID_DAY_VISITING_MBS,
                expectedMessage);

        // all prefix missing
        assertParseFailure(parser,
                VALID_START_TIME_MBS
                        + VALID_END_TIME_MBS
                        + VALID_DAY_VISITING_MBS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid start time
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + INVALID_START_TIME_DESC
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + INVALID_END_TIME_DESC
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid day visiting
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + INVALID_DAY_VISITING_DESC,
                Day.MESSAGE_CONSTRAINTS);

        // valid start time followed by invalid start time
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + INVALID_START_TIME_DESC
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + INVALID_START_TIME_DESC
                        + INVALID_DAY_VISITING_DESC
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser,
                PREAMBLE_NON_EMPTY + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + DAY_VISITING_DESC_MBS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItineraryAttractionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidDayFormat_failure() {

        // invalid day visiting, test day more than 0, checking if it is a valid day is not in parser.
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + " " + PREFIX_DAY_VISITING + "-99",
                Day.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + " " + PREFIX_DAY_VISITING + "0",
                Day.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + " " + PREFIX_DAY_VISITING + "fadsdf",
                Day.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + " " + PREFIX_DAY_VISITING + "03jklfas",
                Day.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + " " + PREFIX_DAY_VISITING + "1#@312",
                Day.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + START_TIME_DESC_MBS
                        + END_TIME_DESC_MBS
                        + " " + PREFIX_DAY_VISITING + "1O", // !10
                Day.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTimeFormat_failure() {
        final ItineraryTime earlyTime = new ItineraryTime("1000");
        final ItineraryTime laterTime = new ItineraryTime("2330");

        // start time later than end time
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + laterTime
                        + " " + PREFIX_END_TIME + earlyTime
                        + DAY_VISITING_DESC_MBS,
                MESSAGE_INVALID_START_TIME);

        // start time same as end time
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + earlyTime
                        + " " + PREFIX_END_TIME + earlyTime
                        + DAY_VISITING_DESC_MBS,
                MESSAGE_INVALID_START_TIME);

        // start time same as end time
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + laterTime
                        + " " + PREFIX_END_TIME + laterTime
                        + DAY_VISITING_DESC_MBS,
                MESSAGE_INVALID_START_TIME);

        // invalid time format
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + "2400"
                        + " " + PREFIX_END_TIME + laterTime
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid time format
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + "-0000"
                        + " " + PREFIX_END_TIME + laterTime
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid time format
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + "1-2-2"
                        + " " + PREFIX_END_TIME + laterTime
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid time format
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + "hhmm"
                        + " " + PREFIX_END_TIME + laterTime
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid time format
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + "fdasfa1200"
                        + " " + PREFIX_END_TIME + laterTime
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid time format
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + "1234fasfd"
                        + " " + PREFIX_END_TIME + laterTime
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);

        // invalid time format
        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + INDEX_DESC_MBS
                        + " " + PREFIX_START_TIME + "1fdasf"
                        + " " + PREFIX_END_TIME + laterTime
                        + DAY_VISITING_DESC_MBS,
                ItineraryTime.MESSAGE_CONSTRAINTS);
    }
}
