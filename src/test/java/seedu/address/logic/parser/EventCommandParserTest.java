package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_ATTRIBUTES;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATETIME_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATETIME_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_EXPERIMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.DateTimeUtil;
import seedu.address.logic.commands.EventCommand;
import seedu.address.model.lesson.Time;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Description;
import seedu.address.model.task.Title;
import seedu.address.model.task.event.EndDateTime;
import seedu.address.model.task.event.Event;
import seedu.address.model.task.event.StartDateTime;

public class EventCommandParserTest {

    private final EventCommandParser parser = new EventCommandParser();

    @Test
    public void parse_validArgs_returnsEventCommand() {
        Event expectedEvent = VALID_EVENT_EXPERIMENT;
        EventCommand expectedEventCommand = new EventCommand(expectedEvent);

        //no leading and trailing whitespaces
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_EXPERIMENT,
                        PREFIX_DATE, VALID_DATE_EXPERIMENT,
                        PREFIX_START_TIME, VALID_START_TIME_EXPERIMENT,
                        PREFIX_END_TIME, VALID_END_TIME_EXPERIMENT,
                        PREFIX_DESCRIPTION, VALID_DESC_EXPERIMENT,
                        PREFIX_TAG, VALID_TAG_EXPERIMENT),
                expectedEventCommand);

        //multiple whitespaces between keywords
        assertParseSuccess(parser,
                String.format(" %s %s %s %s  %s %s %s%s %s   %s %s %s",
                        PREFIX_TITLE, VALID_TITLE_EXPERIMENT,
                        PREFIX_DATE, VALID_DATE_EXPERIMENT,
                        PREFIX_START_TIME, VALID_START_TIME_EXPERIMENT,
                        PREFIX_END_TIME, VALID_END_TIME_EXPERIMENT,
                        PREFIX_DESCRIPTION, VALID_DESC_EXPERIMENT,
                        PREFIX_TAG, VALID_TAG_EXPERIMENT),
                expectedEventCommand);

        //omit optional description
        Event expectedEvent2 = Event.createUserEvent(new Title(VALID_TITLE_EXPERIMENT),
                new StartDateTime(VALID_START_DATETIME_EXPERIMENT),
                new EndDateTime(VALID_END_DATETIME_EXPERIMENT),
                Description.defaultDescription(),
                new Tag(VALID_TAG_EXPERIMENT));
        EventCommand expectedEventCommand2 = new EventCommand(expectedEvent2);
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_EXPERIMENT,
                        PREFIX_DATE, VALID_DATE_EXPERIMENT,
                        PREFIX_START_TIME, VALID_START_TIME_EXPERIMENT,
                        PREFIX_END_TIME, VALID_END_TIME_EXPERIMENT,
                        PREFIX_TAG, VALID_TAG_EXPERIMENT),
                expectedEventCommand2);

        //omit optional tag
        Event expectedEvent3 = Event.createUserEvent(new Title(VALID_TITLE_EXPERIMENT),
                new StartDateTime(VALID_START_DATETIME_EXPERIMENT),
                new EndDateTime(VALID_END_DATETIME_EXPERIMENT),
                new Description(VALID_DESC_EXPERIMENT),
                Tag.defaultTag());
        EventCommand expectedEventCommand3 = new EventCommand(expectedEvent3);
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_EXPERIMENT,
                        PREFIX_DATE, VALID_DATE_EXPERIMENT,
                        PREFIX_START_TIME, VALID_START_TIME_EXPERIMENT,
                        PREFIX_END_TIME, VALID_END_TIME_EXPERIMENT,
                        PREFIX_DESCRIPTION, VALID_DESC_EXPERIMENT),
                expectedEventCommand3);

        //omit optional tag and description
        Event expectedEvent4 = Event.createUserEvent(new Title(VALID_TITLE_EXPERIMENT),
                new StartDateTime(VALID_START_DATETIME_EXPERIMENT),
                new EndDateTime(VALID_END_DATETIME_EXPERIMENT),
                Description.defaultDescription(),
                Tag.defaultTag());
        EventCommand expectedEventCommand4 = new EventCommand(expectedEvent4);
        assertParseSuccess(parser,
                String.format(" %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_EXPERIMENT,
                        PREFIX_DATE, VALID_DATE_EXPERIMENT,
                        PREFIX_START_TIME, VALID_START_TIME_EXPERIMENT,
                        PREFIX_END_TIME, VALID_END_TIME_EXPERIMENT),
                expectedEventCommand4);
    }

    @Test
    public void parse_invalidTimeRange_returnsFalse() {
        //start time same as end time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_START_TIME_MEETING
                ),
                Time.RANGE_CONSTRAINTS);

        //start time after end time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_END_TIME_MEETING,
                        PREFIX_END_TIME, VALID_START_TIME_MEETING
                ),
                Time.RANGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTime_returnsFalse() {
        //invalid start time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, INVALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                DateTimeUtil.TIME_CONSTRAINTS);

        //invalid end time
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, INVALID_END_TIME_MEETING
                ),
                DateTimeUtil.TIME_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDate_returnsFalse() {
        //invalid date
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, INVALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                DateTimeUtil.DATE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleAttributes_returnsFalse() {
        String expectedErrorMessage = MESSAGE_MULTIPLE_ATTRIBUTES;

        //multiple titles
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple descriptions
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple tags
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple dates
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple start times
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

        //multiple end times
        assertParseFailure(parser,
                String.format(" %s%s %s%s %s%s %s%s %s%s %s%s %s%s",
                        PREFIX_TITLE, VALID_TITLE_MEETING,
                        PREFIX_DESCRIPTION, VALID_DESC_MEETING,
                        PREFIX_TAG, VALID_TAG_MEETING,
                        PREFIX_DATE, VALID_DATE_MEETING,
                        PREFIX_START_TIME, VALID_START_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING,
                        PREFIX_END_TIME, VALID_END_TIME_MEETING
                ),
                expectedErrorMessage);

    }
}
