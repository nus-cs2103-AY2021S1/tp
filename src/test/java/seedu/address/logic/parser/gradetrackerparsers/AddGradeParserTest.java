package seedu.address.logic.parser.gradetrackerparsers;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.schedulercommands.AddEventCommand;
import seedu.address.logic.parser.schedulerparsers.AddEventParser;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.testutil.event.EventBuilder;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_DATE_2;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddGradeParserTest {
    private static final String multipleNames = " " + PREFIX_NAME + VALID_EVENT_NAME_1
            + " " + PREFIX_NAME + VALID_EVENT_NAME_2
            + " " + PREFIX_DATE + VALID_EVENT_DATE_1;

    private static final String multipleDates = " " + PREFIX_NAME + VALID_EVENT_NAME_1
            + " " + PREFIX_DATE + VALID_EVENT_DATE_1
            + " " + PREFIX_DATE + VALID_EVENT_DATE_2;

    private static final String validInput = " " + PREFIX_NAME + VALID_EVENT_NAME_1
            + " " + PREFIX_DATE + VALID_EVENT_DATE_1;

    private static final String InvalidNameInput = " " + PREFIX_NAME + INVALID_EVENT_NAME
            + " " + PREFIX_DATE + VALID_EVENT_DATE_1;

    private static final String InvalidDateInput = " " + PREFIX_NAME + VALID_EVENT_NAME_1
            + " " + PREFIX_DATE + INVALID_EVENT_DATE;

    private static final String MissingNameInput = " " + PREFIX_DATE + VALID_EVENT_DATE_1;

    private static final String MissingDateInput = " " + PREFIX_NAME + VALID_EVENT_NAME_1;

    private AddEventParser parser = new AddEventParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedModule = new EventBuilder().withName(VALID_EVENT_NAME_1)
                .withDate(VALID_EVENT_DATE_1).build();
        assertParseSuccess(parser, validInput, new AddEventCommand(expectedModule));
    }

    @Test
    public void parse_preambleWhiteSpace_success() {
        Event expectedModule = new EventBuilder().withName(VALID_EVENT_NAME_1)
                .withDate(VALID_EVENT_DATE_1).build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + validInput,
                new AddEventCommand(expectedModule));
    }

    @Test
    public void parse_multipleNames_success() {
        Event expectedModule = new EventBuilder().withName(VALID_EVENT_NAME_2)
                .withDate(VALID_EVENT_DATE_1).build();
        assertParseSuccess(parser, multipleNames, new AddEventCommand(expectedModule));
    }

    @Test
    public void parse_multipleDate_success() {
        Event expectedModule = new EventBuilder().withName(VALID_EVENT_NAME_1)
                .withDate(VALID_EVENT_DATE_2).build();
        assertParseSuccess(parser, multipleDates, new AddEventCommand(expectedModule));
    }

    @Test
    public void parse_compulsoryNameFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, MissingNameInput, expectedMessage);
    }

    @Test
    public void parse_compulsoryDateFieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);
        assertParseFailure(parser, MissingDateInput, expectedMessage);
    }

    @Test
    public void parse_invalidName_failure() {
        assertParseFailure(parser, InvalidNameInput, EventName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_failure() {
        assertParseFailure(parser, InvalidDateInput, EventTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);
        String nonEmptyPreamble = "add " + validInput;
        assertParseFailure(parser, nonEmptyPreamble, expectedMessage);
    }
}
